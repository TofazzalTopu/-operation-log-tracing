package com.asl.pms.service;

import com.asl.pms.mymodel.User;
import com.asl.pms.repository.UserRepo;
import com.asl.pms.utility.UserAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepo.findById(id);
        UserDetails userd = (UserDetails) user;
        return userd;
    }

    public List<User> findByActive() {
        List<User> userList = new ArrayList<>();
        userList = userRepo.findByActive(true);
        return userList;
    }


    public List<User> getAllUser() {
        return (List<User>) userRepo.findAll();
    }

//	public User usernameExist(String email) {
//		return userRepo.findByEmail(email);
//	}

    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepo.delete(user);
    }


    public User findOne(Long id) {
        return userRepo.findById(id).get();
    }

    public List<User> findActiveUser(boolean active) {
        return userRepo.findByActive(active);
    }

    public List<User> findByAuthorityAndActive(boolean active) {
        return userRepo.findByAuthorityOrderByCreatedDesc();
    }

    public List<User> findByAuthorityIsNotAndActive(UserAuthorities authorities, boolean active) {
        return userRepo.findByAuthorityIsNotOrderByCreatedDesc(authorities);
    }


    public User findByUserName(String username) {
//		return userRepo.findByUsername(username);
        return userRepo.findById(username);
    }

    public User findById(String id) {
        return userRepo.findById(id);
    }

//	public List<ICustomer> findByEmailStartingWith(String query) {
//		return userRepo.findByEmailStartingWith(query);
//	}
	/*public List<User> findByRolesAndEmailStartingWith(Role role, String email) {
		return userRepo.findByRolesAndEmailStartingWith(role, email);
	}*/

}
