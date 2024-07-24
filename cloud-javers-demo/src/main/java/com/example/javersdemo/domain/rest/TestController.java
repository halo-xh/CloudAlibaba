package com.example.javersdemo.domain.rest;


import com.example.javersdemo.domain.Role;
import com.example.javersdemo.domain.RoleRepo;
import com.example.javersdemo.domain.User;
import com.example.javersdemo.domain.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private Javers javers;

    private ObjectMapper objectMapper = new ObjectMapper();

    String contextLoads() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setUsername("demo-javers-update1");
        user.setPassword("demo-javers2-update");

        Role role = new Role();
        role.setId(1L);
        role.setName("11");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("22");

        List<Role> roles = Arrays.asList(role, role2);
        roleRepo.saveAllAndFlush(roles);

        user.setRoles(roles);

        userRepo.save(user);
        return objectMapper.writeValueAsString(user);
    }

    @RequestMapping("/test")
    public String test() throws JsonProcessingException {
        return contextLoads();
    }

    @GetMapping("/user/{useId}/changes")
    public String getProductChanges(@PathVariable Long useId) {
        User user = userRepo.getReferenceById(useId);
        QueryBuilder jqlQuery = QueryBuilder.byInstance(user);
        Changes changes = javers.findChanges(jqlQuery.build());
        return javers.getJsonConverter().toJson(changes);
    }


    @GetMapping("/stores/snapshots")
    public String getStoresSnapshots() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(User.class);
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
        return javers.getJsonConverter().toJson(snapshots);
    }


}
