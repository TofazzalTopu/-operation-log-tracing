package com.asl.pms.repository;

import java.util.List;

import com.asl.pms.utility.UserAuthorities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asl.pms.mymodel.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    //	public User findByUsername(String username);
//	public User findByEmail(String email);
//	public List<User> findByActiveAndAuthorities(boolean active, String authority);
    public List<User> findByActive(boolean active);

    public List<User> findByAuthorityIsNotAndActive(UserAuthorities authorities, boolean active);


    /*String str = "SELECT id, active, address, authority, first_name, last_name, mobile_number, \n" +
            "    modified, created, PASSWORD, photo, post_code, service_description \n" +
            "    FROM users\n" +
            "    WHERE authority IN ('ADMIN', 'USER') AND active = :active \n" +
            "    ORDER BY created DESC";

    @Query(value = str, nativeQuery = true)
    public List<User> findByAuthorityIsNotAndActiveOrderByCreatedDesc(@Param("authority") UserAuthorities authority, @Param("active") boolean active);
   */
//    public List<User> findByAuthorityIsNotAndActiveOrderByCreatedDesc(UserAuthorities authority, boolean active);
    public List<User> findByAuthorityIsNotOrderByCreatedDesc(UserAuthorities authority);


    String qry = "SELECT id, active, address, authority, first_name, last_name, mobile_number, \n" +
            "    modified, created, PASSWORD, photo, post_code, service_description \n" +
            "    FROM users\n" +
            "    WHERE authority = 'CUSTOMER' \n" +
            "    ORDER BY created DESC";

    @Query(value = qry, nativeQuery = true)
    public List<User> findByAuthorityOrderByCreatedDesc();
//    public List<User> findByAuthorityAndActiveOrderByCreatedDesc(@Param("active") boolean active);

    public User findById(String id);

//	public List<ICustomer> findByEmailStartingWith(String email);
//	public List<User> findByRolesAndEmailStartingWith(Role role, String email);
}
