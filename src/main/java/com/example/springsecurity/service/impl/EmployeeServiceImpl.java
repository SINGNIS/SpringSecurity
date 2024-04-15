package com.example.springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Value(${spring.datasource.url})
    String driver;
    @Override
    public List<String> getAllEmployees() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_directory",
                "root","root");
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
