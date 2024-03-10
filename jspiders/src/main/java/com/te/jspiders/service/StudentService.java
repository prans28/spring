package com.te.jspiders.service;

import com.te.jspiders.dto.StudentDTO;

import java.util.Optional;

public interface StudentService {
    Optional<String> registerStudent(StudentDTO studentDTO);
}
