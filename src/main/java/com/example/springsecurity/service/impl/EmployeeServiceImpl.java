package com.example.springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Value("${spring.datasource.driver-class-name}")
    String driver;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;


    @Override
    public List<String> getAllEmployees() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName(driver).newInstance();
        Connection con = DriverManager.getConnection(url,
                username,password);
        PreparedStatement ps = con.prepareStatement("select first_name from employee");
        ResultSet result = ps.executeQuery();
        List<String> firstName= new ArrayList<>();
        String val = null;
        while(result.next()){
            val = result.getString("first_name");
            firstName.add(val);
            System.out.println(val);
        }

        return firstName;
    }
}
