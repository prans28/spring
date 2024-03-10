package com.te.jspiders.controller;

import com.te.jspiders.dto.EmployeeDTO;
import com.te.jspiders.dto.LoginDTO;
import com.te.jspiders.dto.StudentDTO;
import com.te.jspiders.jwt.utils.JwtUtils;
import com.te.jspiders.response.ApiResponse;
import com.te.jspiders.service.EmployeeService;
import com.te.jspiders.service.StudentService;
import com.te.jspiders.service.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.te.jspiders.dto.TrainerDTO;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/public")
@RestController
public class ApplicationController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final TrainerService trainerService;
    private final EmployeeService employeeService;

    @PostMapping(path = "/trainer/register")
    public ApiResponse<String> registerTrainer(@RequestBody TrainerDTO trainerDTO) {
        log.info("TrainerController:registerTrainer execution start, {}", trainerDTO);
        Optional<String> trainerId = trainerService.registerTrainer(trainerDTO);
        if (trainerId.isPresent()) {
            return new ApiResponse<String>("Trainer registration successfull!", null, trainerId.get());
        }
        throw new RuntimeException("Trainer registration failed");
    }

    @PostMapping(path = "/student/register")
    public ApiResponse<String> registerStudent(@RequestBody StudentDTO studentDTO) {
        log.info("StudentController:registerStudent execution start, {}", studentDTO);
        Optional<String> stuId = studentService.registerStudent(studentDTO);
        if (stuId.isPresent()) {
            return new ApiResponse<String>("Student registration successfull!", null, stuId.get());
        }
        throw new RuntimeException("Student registration failed");
    }

    @PostMapping(path = "/employee/register")
    public ApiResponse<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("EmployeeController:registerEmployee execution start, {}", employeeDTO);
        Optional<String> empId = employeeService.registerEmployee(employeeDTO);
        if (empId.isPresent()) {
            return new ApiResponse<String>("Employee registration successfull!", null, empId.get());
        }
        throw new RuntimeException("Employee registration failed");
    }

    @PostMapping(path = "/login")
    public ApiResponse<Object> login(@RequestBody LoginDTO loginDTO) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        String token = jwtUtils.generateToken(loginDTO.getUsername());
        return new ApiResponse<Object>("Login successfull!", token, null);
    }
}
