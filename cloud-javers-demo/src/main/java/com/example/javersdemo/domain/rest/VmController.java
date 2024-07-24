package com.example.javersdemo.domain.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VmController {


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

}
