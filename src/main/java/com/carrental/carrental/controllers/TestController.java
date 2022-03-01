package com.carrental.carrental.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('MECHANIC') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public String userAccess() {
        return "User content";
    }

    @GetMapping("/mechanic")
    @PreAuthorize("hasRole('MECHANIC') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public String mechanicAccess() {
        return "mechanic content";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public String employeeAccess() {
        return "Employee Content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin content";
    }
}
