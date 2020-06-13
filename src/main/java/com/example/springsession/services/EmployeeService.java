package com.example.springsession.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import com.example.springsession.dao.EmployeeDao;
import com.example.springsession.dto.EmployeeDto;
import com.example.springsession.entity.EmployeeEntity;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeDao employeeDao;

	@SneakyThrows
	public EmployeeDto employeeById(Long id) {
		return employeeDao.findById(id)
			.map((data) -> {
				return EmployeeDto.builder()
				.id(data.getId())
				.name(data.getFirstName() + " " + data.getLastName())
				.address(data.getAddress())
				.build();
			})
			.orElseThrow(Exception::new);
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
		Iterable<EmployeeEntity> allEmployees = employeeDao.findAll();
		List<EmployeeDto> dataToReturn = new ArrayList<>();
		allEmployees.forEach((data) -> {
			dataToReturn.add(new EmployeeDto(
				data.getId(), 
				data.getFirstName() + " " + data.getLastName(), 
				data.getAddress()
			));
		});
		return dataToReturn;
	}

	@SneakyThrows
	public EmployeeDto updateEmployee(Long id, EmployeeDto body) {
		String[] name = body.getName().split(" ");
		EmployeeEntity employeeEntity = EmployeeEntity
			.builder()
			.firstName(name[0])
			.lastName(name[1])
			.address(body.getAddress())
			.id(id)
			.build();
		if (employeeDao.existsById(id) == true) {
			EmployeeEntity saved = employeeDao.save(employeeEntity);
			return EmployeeDto.builder()
				.id(saved.getId())
				.name(saved.getFirstName() + " " + saved.getLastName())
				.address(saved.getAddress())
				.build();
		}
		throw new Exception("No employee found!");
	}

	public void deleteEmployee(Long id) {
		employeeDao.deleteById(id);
	}

}
