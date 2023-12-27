package com.KomalProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KomalProject.entity.Employee;
import com.KomalProject.service.EmployeeService;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAll")
	public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "employeeName") String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return employeeService.getAllEmployees(pageable);
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {

		return employeeService.addEmployee(employee);
	}

	@GetMapping("/get/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable String id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable String id, @RequestBody Employee updatedEmployee) {
		Optional<Employee> updated = employeeService.updateEmployee(id, updatedEmployee);

		if (updated.isPresent()) {
			return ResponseEntity.ok(" Employee updated successfully");
		} else {
			return ResponseEntity.ok("Employee with this id is Not Found");
		}
	}
	
	   @GetMapping("/{employeeId}/manager/{level}")
	    public ResponseEntity<Employee> getNthLevelManager(
	            @PathVariable String employeeId,
	            @PathVariable int level) {

	        Optional<Employee> nthLevelManager = employeeService.findNthLevelManager(employeeId, level);

	        return nthLevelManager.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
	}

}
