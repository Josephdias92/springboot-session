package com.example.springsession.services;

import java.util.Arrays;
import java.util.List;

import com.example.springsession.dao.EmployeeDao;
import com.example.springsession.dto.EmployeeDto;
import com.example.springsession.entity.EmployeeEntity;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeDao employeeDao;

	public EmployeeDto employeeById(Long id) {
		EmployeeEntity employeeEntity = employeeDao.findById(id).get();
        return EmployeeDto.builder()
			.id(employeeEntity.getId())
			.name(employeeEntity.getFirstName() + " " + employeeEntity.getLastName())
			.address(employeeEntity.getAddress())
			.build();
	}

	public EmployeeDto createEmployee(EmployeeDto body) {
		String[] name = body.getName().split(" ");
		EmployeeEntity employeeEntity = EmployeeEntity
			.builder()
			.firstName(name[0])
			.lastName(name[1])
			.address(body.getAddress())
			.build();
		EmployeeEntity saved = employeeDao.save(employeeEntity);
		return EmployeeDto.builder()
		.id(saved.getId())
		.name(saved.getFirstName() + " " + saved.getLastName())
		.address(saved.getAddress())
		.build();
	}

	public List<EmployeeDto> getAllEmployees() {
		return Arrays.asList();
	}

	public EmployeeDto updateEmployee(Integer id) {
		return new EmployeeDto();
	}

	public EmployeeDto deleteEmployee(Integer id) {
		return new EmployeeDto();
	}

}
