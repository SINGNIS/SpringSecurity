package com.example.springsecurity.controller;

import com.example.springsecurity.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/home")
class HomeController {

    @Autowired
    EmployeeService emp;

    @GetMapping("/hello")
    public String getHome() {
        return "hello";
    }

    @GetMapping("/employees")
    public List<String> getAllEmployees() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return emp.getAllEmployees();
    }
}
