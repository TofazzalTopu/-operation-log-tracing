package com.asl.pms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import com.asl.pms.config.SecurityUtility;
import com.asl.pms.utility.UserAuthorities;
import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asl.pms.mymodel.Role;
import com.asl.pms.mymodel.User;
import com.asl.pms.service.RoleService;
import com.asl.pms.service.UserService;
import com.asl.pms.utility.GlobalMethod;


@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    @Autowired
    private Environment env;

    public String getPicturePath() {
        String picturePath = env.getProperty("pms.picture.upload.path");
        return picturePath;
    }
    public String getPictureStoredPath() {
        String picturePath = env.getProperty("picture.stored.path");
        return picturePath;
    }

    /*@Autowired
    BranchService branchService;*/
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    List<Role> roleList = new ArrayList<>();
    private Connection connection;
    @Autowired
    private Environment environment;
    @Autowired
    Enforcer enforcer;
    @Autowired
    GlobalMethod globalMethod;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = {"/adminpanel/user/userlists"}, method = RequestMethod.GET)
    public String userList(Model model) {

        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";
    }


    @RequestMapping(value = {"/adminpanel/user/adduser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        roleList = roleService.findByNameIsNotCustomer();
        model.addAttribute("rolelist", roleList);
        model.addAttribute("user", new User());
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/add_user";
    }

    @RequestMapping(value = {"/adminpanel/user/usersave"}, method = RequestMethod.POST)
    public String saveUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/user/add_user";
        }

        if(user.getSelectedRole().size() == 0){
            model.addAttribute("error", "Please select at least one role!");
            model.addAttribute("user", user);
            model.addAttribute("rolelist", roleList);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/user/add_user";
        }

        User user1 = userService.findById(user.getId());
        if (user1 != null) {
            if (!user1.isActive()) {
                model.addAttribute("error", "An inactive entry already exist with this email: "+ user1.getId());
            } else {
                model.addAttribute("error", "Email "+ user.getId() +" already exist");
            }
            logger.info("Username already exist");
//            user.setId("");
            model.addAttribute("user", user);
            model.addAttribute("rolelist", roleList);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/user/add_user";
        }

        String base64String = user.getPhoto();
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:
                extension = "jpg";
                break;
        }
        String fileName = "";
        if (strings.length > 1) {
            byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);

            fileName = String.format("%s.%s", System.currentTimeMillis(), extension);

            Path picPath = Paths.get(getPicturePath());
            Files.write(picPath.resolve(fileName), data);
            logger.info("Image name : "+ fileName);
            logger.info("Image saved to the path : "+ picPath); }

        user.setPhoto(fileName);
        Role role = new Role();
        for (String id : user.getSelectedRole()) {
             role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(id))).findFirst().orElse(null);
             user.getRoles().add(role);
        }

        user.setId(user.getId());
        if(role.getName().equals("ADMIN")) {
            user.setAuthority(UserAuthorities.ADMIN);
        }else {
            user.setAuthority(UserAuthorities.USER);
        }

        user.setActive(true);
        user.setCreated(new Date());
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        insertToCasbin(user);
        enforcer.loadPolicy();
        model.addAttribute("success", "HCA "+user.getId()+" created successfully!");

        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";

//        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/useredit/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        for (Role role : user.getRoles()) {
            user.getSelectedRole().add(role.getId() + "");
        }

        roleList = roleService.findByNameIsNotCustomer();
        model.addAttribute("rolelist", roleList);
        model.addAttribute("user", user);
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("getPictureStoredPath", getPictureStoredPath());
        model.addAttribute("imageWithPath", getPicturePath() + user.getPhoto());
        logger.info("photo :"+ user.getPhoto());
        return "/dashboard/user/edit_user";
    }


    @RequestMapping(value = {"/adminpanel/user/userupdate/{id}"}, method = RequestMethod.POST)
    public String updateUser(@PathVariable String id, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/user/edit_user";
        }

        if(user.getSelectedRole().size() == 0){
            model.addAttribute("error", "Please select a role!");
            model.addAttribute("user", user);
            model.addAttribute("rolelist", roleList);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/user/edit_user";
        }

        User newUser = new User();
        String photo = user.getPhoto();
        if (!photo.isEmpty()) {
            String base64String = photo;
            String[] strings = base64String.split(",");
            String extension;
            switch (strings[0]) {
                case "data:image/jpeg;base64":
                    extension = "jpeg";
                    break;
                case "data:image/png;base64":
                    extension = "png";
                    break;
                default:
                    extension = "jpg";
                    break;
            }

            String fileName = "";
            if (strings.length > 1) {
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
                fileName = String.format("%s.%s", System.currentTimeMillis(), extension);
                Path picPath = Paths.get(getPicturePath());
                Files.write(picPath.resolve(fileName), data);
                logger.info("Image name : "+ fileName);
                logger.info("Image saved to the path : "+ picPath);
            }
            newUser.setPhoto(fileName);

        } else {
            newUser.setPhoto(userService.findById(id).getPhoto());
        }

        Role role = new Role();
        for (String roleId : user.getSelectedRole()) {
             role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(roleId))).findFirst().orElse(null);
            newUser.getRoles().add(role);
        }

        newUser.setId(id);
        newUser.setUserName(user.getId());
        newUser.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));

        if(role.getName().equals("ADMIN")) {
            newUser.setAuthority(UserAuthorities.ADMIN);
        }else {
            newUser.setAuthority(UserAuthorities.USER);
        }

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMobileNumber(user.getMobileNumber());
        newUser.setAddress(user.getAddress());
        newUser.setPostCode(user.getPostCode());
//        newUser.setActive(true);
        newUser.setModified(new Date());
        userService.saveUser(newUser);
//        insertToCasbin(newUser);
//        deleteFromCasbin(newUser);
        insertToCasbin(newUser);
        enforcer.loadPolicy();

        model.addAttribute("success", "HCA "+id+" updated successfully!");

        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";

//        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/activeUser/{id}"}, method = RequestMethod.GET)
    public String activeUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        user.setActive(true);
        user.setModified(new Date());
        userService.saveUser(user);
        enforcer.loadPolicy();

        model.addAttribute("success", "HCA "+ user.getId() +" activated successfully!");
        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";

//        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/userdelete/{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        enforcer.loadPolicy();

        model.addAttribute("success", "HCA "+ user.getId() +" deleted successfully!");
        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";

//        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/deactivateUser/{id}"}, method = RequestMethod.GET)
    public String deactivateUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        user.setActive(false);
        user.setModified(new Date());
        userService.saveUser(user);
        enforcer.loadPolicy();

        model.addAttribute("success", "HCA "+ user.getId() +" deactivated successfully!");
        model.addAttribute("userlist", userService.findByAuthorityIsNotAndActive(UserAuthorities.CUSTOMER, true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/user/user_list";

//        return "redirect:/adminpanel/user/userlists";
    }


    @RequestMapping(value = {"/adminpanel/user/customerList"}, method = RequestMethod.GET)
    public String customerList(Model model) {

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/customer/customer_list";
    }


    @RequestMapping(value = {"/adminpanel/user/addCustomer"}, method = RequestMethod.GET)
    public String addCustomer(Model model) {
        roleList = roleService.findByNameIsCustomer();

//        mymodel.addAttribute("branchlist", branchService.getAll());
        model.addAttribute("rolelist", roleList);
        model.addAttribute("user", new User());
        List<String> stringList =  globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        return "/dashboard/customer/add_customer";
    }

    @RequestMapping(value = {"/adminpanel/user/saveCustomer"}, method = RequestMethod.POST)
    public String saveCustomer(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/customer/add_customer";
        }

        User user1 = userService.findById(user.getId());
        if (user1 != null) {
            if (!user1.isActive()) {
                model.addAttribute("error", "An inactive entry already exist with this email: "+ user1.getId());
            } else {
                model.addAttribute("error", "Email "+ user.getId() +" already exist!");
            }
            logger.info("HCA Name already exist");
//            user.setId("");
            model.addAttribute("user", user);
            model.addAttribute("rolelist", roleList);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/customer/add_customer";
        }

        String base64String = user.getPhoto();
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:
                extension = "jpg";
                break;
        }

        String fileName = "";
        if (strings.length > 1) {
            byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
            fileName = String.format("%s.%s", System.currentTimeMillis(), extension);
            Path picPath = Paths.get(getPicturePath());
            Files.write(picPath.resolve(fileName), data);
            logger.info("Image name : "+ fileName);
            logger.info("Image saved to the path : "+ picPath);
        }

        user.setPhoto(fileName);
//        user.setSelectedRole();
//        List<Role> roleList1 = roleService.findByNameIsCustomer();
//        user.setSelectedRole(roleList1.getName());
        for (String id : user.getSelectedRole()) {
            Role role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(id))).findFirst().orElse(null);
            user.getRoles().add(role);
        }

        user.setId(user.getId());
        user.setAuthority(UserAuthorities.CUSTOMER);
        user.setCreated(new Date());
        user.setActive(true);
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        insertToCasbin(user);
        enforcer.loadPolicy();

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("success", "SU "+ user.getId() +" created successfully!");
        return "/dashboard/customer/customer_list";

//        return "redirect:/adminpanel/user/customerList";
    }

    @RequestMapping(value = {"/adminpanel/user/editCustomer/{id}"}, method = RequestMethod.GET)
    public String editCustomer(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        for (Role role : user.getRoles()) {
            user.getSelectedRole().add(role.getId() + "");
        }

        roleList = roleService.findByNameIsCustomer();
        model.addAttribute("rolelist", roleList);
//        mymodel.addAttribute("branchlist", branchService.getAll());
        model.addAttribute("user", user);
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("getPictureStoredPath", getPictureStoredPath());
        model.addAttribute("imageWithPath", getPictureStoredPath() + user.getPhoto());
        logger.info("photo :"+ user.getPhoto());
        return "/dashboard/customer/edit_customer";
    }

    @RequestMapping(value = {"/adminpanel/user/customerUpdate/{id}"}, method = RequestMethod.POST)
    public String customerUpdate(@PathVariable String id, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = globalMethod.getAllFromCasbin();
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            model.addAttribute("image", globalMethod.getUserImage());
            return "/dashboard/customer/edit_customer";
        }
        User newUser = new User();

        String photo = user.getPhoto();
        if (!photo.isEmpty()) {
            String base64String = photo;
            String[] strings = base64String.split(",");
            String extension;
            switch (strings[0]) {
                case "data:image/jpeg;base64":
                    extension = "jpeg";
                    break;
                case "data:image/png;base64":
                    extension = "png";
                    break;
                default:
                    extension = "jpg";
                    break;
            }
            String fileName = "";
            if (strings.length > 1) {
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
                fileName = String.format("%s.%s", System.currentTimeMillis(), extension);
                Path picPath = Paths.get(getPicturePath());
                Files.write(picPath.resolve(fileName), data);
                logger.info("Image name : "+ fileName);
                logger.info("Image saved to the path : "+ picPath);
            }

            newUser.setPhoto(fileName);

        } else {
            newUser.setPhoto(userService.findById(id).getPhoto());
        }

        for (String roleId : user.getSelectedRole()) {
            Role role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(roleId))).findFirst().orElse(null);
            newUser.getRoles().add(role);
        }

        newUser.setId(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMobileNumber(user.getMobileNumber());
        newUser.setPostCode(user.getPostCode());
        newUser.setAddress(user.getAddress());
        newUser.setServiceDescription(user.getServiceDescription());
        newUser.setUserName(user.getId());
        newUser.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        newUser.setModified(new Date());

        newUser.setAuthority(UserAuthorities.CUSTOMER);
//        newUser.setActive(true);
        userService.saveUser(newUser);
//        insertToCasbin(newUser);
//        deleteFromCasbin(newUser);
        insertToCasbin(newUser);
        enforcer.loadPolicy();

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("success", "SU "+ user.getId() +" updated successfully!");
        return "/dashboard/customer/customer_list";

//        return "redirect:/adminpanel/user/customerList";
    }

    @RequestMapping(value = {"/adminpanel/user/activeCustomer/{id}"}, method = RequestMethod.GET)
    public String activeCustomer(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        user.setModified(new Date());
        user.setActive(true);
        userService.saveUser(user);
        enforcer.loadPolicy();

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("success", "SU "+ user.getId() +" activated successfully!");
        return "/dashboard/customer/customer_list";

//        return "redirect:/adminpanel/user/customerList";
    }

    @RequestMapping(value = {"/adminpanel/user/customerDelete/{id}"}, method = RequestMethod.GET)
    public String customerDelete(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        enforcer.loadPolicy();

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("success", "SU "+ user.getId() +" deleted successfully!");
        return "/dashboard/customer/customer_list";

//        return "redirect:/adminpanel/user/customerList";
    }

    @RequestMapping(value = {"/adminpanel/user/deactiveCustomer/{id}"}, method = RequestMethod.GET)
    public String customerInactive(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        user.setModified(new Date());
        user.setActive(false);
        userService.saveUser(user);
        enforcer.loadPolicy();

        model.addAttribute("userlist", userService.findByAuthorityAndActive(true));
        List<String> stringList = globalMethod.getAllFromCasbin();
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        model.addAttribute("image", globalMethod.getUserImage());
        model.addAttribute("success", "SU "+ user.getId() +" deactivated successfully!");
        return "/dashboard/customer/customer_list";

//        return "redirect:/adminpanel/user/customerList";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userName;
    }

    /*public List<String> getAllFromCasbin() {
        List<String> stringList = new ArrayList<>();
        try {
            Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

            String url = environment.getRequiredProperty("spring.datasource.url");
            String username = environment.getRequiredProperty("spring.datasource.username");
            String password = environment.getRequiredProperty("spring.datasource.password");

            Connection connection = DriverManager.getConnection(url, username, password);
//            String sql = "SELECT v1 from casbin_rule WHERE v0=? AND v2=?";
            String sql = "SELECT v1 from casbin_rule WHERE v2=?";
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, role);
            statement.setString(1, "GET");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stringList.add(resultSet.getString("v1"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringList;
    }
*/
/*

    public List<String> getAllFromCasbin(String role) {
        List<String> stringList = new ArrayList<>();
        try {
            Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

            String url = environment.getRequiredProperty("spring.datasource.url");
            String username = environment.getRequiredProperty("spring.datasource.username");
            String password = environment.getRequiredProperty("spring.datasource.password");

            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT v1 from casbin_rule WHERE v0=? AND v2=?";
//            String sql = "SELECT v1 from casbin_rule WHERE v0=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            statement.setString(2, "GET");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stringList.add(resultSet.getString("v1"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringList;
    }
*/

    public void insertToCasbin(User user) {
        for (Role role : user.getRoles()) {
            enforcer.addGroupingPolicy(user.getUsername(), role.getName());
        }
    }

    public void deleteFromCasbin(User user) {
        try {
            Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

            String url = environment.getRequiredProperty("spring.datasource.url");
            String username = environment.getRequiredProperty("spring.datasource.username");
            String password = environment.getRequiredProperty("spring.datasource.password");

            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = null;
            String deleteSql = "DELETE FROM casbin_rule WHERE v0 = ?";
            statement = connection.prepareStatement(deleteSql);
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
