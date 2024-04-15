package com.example.springsecurity.service.impl;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    public List<String> getAllEmployees() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;
}
