package com.example.cloudsimple.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/te")
public class TestRest {

    @GetMapping("/t1")
    public String importKnowledgeType() {
        return "cloud-simple-t1";
    }

}
