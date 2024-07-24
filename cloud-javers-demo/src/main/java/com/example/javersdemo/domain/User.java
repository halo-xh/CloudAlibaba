package com.example.javersdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * @TableName user
 */
@Data
@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.javers.core.metamodel.annotation.Id
    private Long id;


    private String password;

    private String username;

    @Version
    private Integer version;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER ,mappedBy = "user")
    private List<Role> roles;


    private static final long serialVersionUID = 1L;
}