package com.asl.pms.mymodel;

import com.asl.pms.utility.UserAuthorities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("deprecation")
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Email
    @Column(name = "id")
    private String id;

    /* @Column(name = "username")
     private String username;
    */

    @NotNull
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    private String photo;

    @NotNull
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    private boolean active;

    private Date modified;

    private Date created;

    @Column(columnDefinition = "enum('ADMIN','USER', 'CUSTOMER')")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserAuthorities authority;
//    private Authorities authority;

    private String postCode;

    private String serviceDescription;

    @Transient
    private List<String> selectedRole = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    @Transient
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String password, String firstName, String lastName, String mobileNumber, String address, String postCode, String serviceDescription) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.postCode = postCode;
        this.serviceDescription = serviceDescription;
    }

    public List<String> getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(List<String> selectedRole) {
        this.selectedRole = selectedRole;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setUsername(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void setUserName(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFullName(User user) {
        return user.firstName + " " + user.lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
//                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", selectedRole=" + selectedRole +
                ", roles=" + roles +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public User getUser() {
        return this;
    }
}

