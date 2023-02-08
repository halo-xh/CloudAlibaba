package com.example.cloudsimple.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/te")
public class TestRest {

    @GetMapping("/t1")
    public String importKnowledgeType() {
        log.info("simple - recv");
        return "cloud-simple-t1";
    }

}
