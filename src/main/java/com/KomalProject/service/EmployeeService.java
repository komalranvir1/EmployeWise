package com.KomalProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.KomalProject.entity.Employee;
import com.KomalProject.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;


	public Employee addEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}

	public Optional<Employee> getEmployeeById(String id) {
		return employeeRepo.findById(id);
	}

	public Page<Employee> getAllEmployees(Pageable pageable) {
		return employeeRepo.findAll(pageable);
	}

	public void deleteEmployeeById(String id) {
		employeeRepo.deleteById(id);
	}

	public Optional<Employee> updateEmployee(String id, Employee updatedEmployee) {
		Optional<Employee> existingEmployee = employeeRepo.findById(id);

		if (existingEmployee.isPresent()) {
			Employee employeeToUpdate = existingEmployee.get();
			employeeToUpdate.setEmployeeName(updatedEmployee.getEmployeeName());
			employeeToUpdate.setPhoneNumber(updatedEmployee.getPhoneNumber());
			employeeToUpdate.setEmail(updatedEmployee.getEmail());
			employeeToUpdate.setReportsTo(updatedEmployee.getReportsTo());
			employeeToUpdate.setProfileImage(updatedEmployee.getProfileImage());

			return Optional.of(employeeRepo.save(employeeToUpdate));
		}

		return Optional.empty();
	}

	public Optional<Employee> findNthLevelManager(String employeeId, int level) {
		 Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);

	        if (employeeOptional.isPresent()) {
	            Employee employee = employeeOptional.get();
	            return findNthLevelManagerRecursive(employee, level);
	        }
	     // Employee with the given ID not found
	        return Optional.empty(); 
	    }

	    private Optional<Employee> findNthLevelManagerRecursive(Employee employee, int level) {
	        if (level == 0) {
	            return Optional.of(employee);
	        }

	        if (employee.getReportsTo() == null) {
	        	
	        	// No manager at this level
	            return Optional.empty(); 
	        }

	        return findNthLevelManagerRecursive(employee, level - 1);
	    }

		


}
