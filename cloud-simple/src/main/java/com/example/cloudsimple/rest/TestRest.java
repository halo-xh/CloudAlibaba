package com.example.cloudsimple.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/te")
public class TestRest {

    @GetMapping("/t1")
    public String importKnowledgeType() {
        log.info("simple - recv");
        return "cloud-simple-t1";
    }

    @PostMapping("/t2")
    public String importKnowledgeType(@RequestBody String s) {
        log.info("simple - t2 {}", s);
        return "cloud-simple-s";
    }

}
