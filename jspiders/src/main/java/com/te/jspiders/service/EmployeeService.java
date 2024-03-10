package com.te.jspiders.service;

import com.te.jspiders.dto.EmployeeDTO;

import java.util.Optional;

public interface EmployeeService {

    Optional<String> registerEmployee(EmployeeDTO employeeDTO);
}
