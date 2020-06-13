package com.example.springsession.controllers;

import java.util.List;

import com.example.springsession.dto.EmployeeDto;
import com.example.springsession.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class Employees {

    private final EmployeeService employeeService;
    
    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable Integer id) {
        return employeeService.employeeById(id);
    }

    @PostMapping
    public EmployeeDto post(@RequestBody EmployeeDto body) {
        return employeeService.createEmployee(body);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Integer id) {
        return employeeService.updateEmployee(id);
    }

    @DeleteMapping("/{id}")
    public EmployeeDto deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }


    
}