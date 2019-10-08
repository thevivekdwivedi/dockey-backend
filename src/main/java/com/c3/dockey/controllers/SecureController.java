package com.c3.dockey.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secured")
    public String secured() {
        System.out.println("Inside secured.");
        return "Hello, Secured.";
    }
}
