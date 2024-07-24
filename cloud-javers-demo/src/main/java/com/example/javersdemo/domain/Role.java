package com.example.javersdemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @TableName role
 */
@Data
@Entity
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Version
    private Integer version;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private static final long serialVersionUID = 1L;
}