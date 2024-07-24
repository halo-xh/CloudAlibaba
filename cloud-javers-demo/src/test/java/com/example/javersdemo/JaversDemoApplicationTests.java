package com.example.javersdemo;

import com.example.javersdemo.domain.Role;
import com.example.javersdemo.domain.RoleRepo;
import com.example.javersdemo.domain.User;
import com.example.javersdemo.domain.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class JaversDemoApplicationTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Test
    void contextLoads() {




        User user = userRepo.getReferenceById(2485L);
        for (int i = 0; i < 1000; i++) {
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());

            Role role = new Role();
            role.setId((long) new Random().nextInt(1000));
            role.setName(UUID.randomUUID().toString());


            Role role2 = new Role();
            role2.setId((long) new Random().nextInt(1000));
            role2.setName(UUID.randomUUID().toString());

            List<Role> roles = Arrays.asList(role, role2);
            roleRepo.saveAllAndFlush(roles);

            user.setRoles(roles);
            userRepo.save(user);
        }

    }

}
