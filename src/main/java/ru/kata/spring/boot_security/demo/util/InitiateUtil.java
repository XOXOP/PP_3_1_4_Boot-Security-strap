package ru.kata.spring.boot_security.demo.util;

import ru.kata.spring.boot_security.demo.servic.UserService;
import ru.kata.spring.boot_security.demo.servic.RoleService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitiateUtil  {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitiateUtil(UserService userService, RoleService rolesService) {
        this.userService = userService;
        this.roleService = rolesService;
    }

    @PostConstruct
    public void init()  {
        if (roleService.findByRoleName("ROLE_ADMIN") == null) {
            Role role = new Role("ROLE_ADMIN");
            Role role1 = new Role("ROLE_USER");
            roleService.save(role);
            roleService.save(role1);
            List<Role> roleAdminUser = new ArrayList<>();
            roleAdminUser.add(role);
            roleAdminUser.add(role1);
            List<Role> roleUser = new ArrayList<>();
            roleUser.add(role1);
            User admin = new User("2du6ifs@hotmail.net","dummy-user","User-is-dummy" ,
                    "0001", 22,roleAdminUser);
            User user1 = new User("10nr12s@hotmail.com","headless-user",  "userWithoutH",
                    "0002",100, roleUser);
            User user2 = new User("15krwwv@hotmail.net","asleep-user", "User-fell-asleep",
                    "0003",15, roleUser);
            userService.save(admin);
            userService.save(user1);
            userService.save(user2);
        }
    }
}





