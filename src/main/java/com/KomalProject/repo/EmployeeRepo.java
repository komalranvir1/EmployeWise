package com.KomalProject.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.KomalProject.entity.Employee;

public interface EmployeeRepo extends PagingAndSortingRepository<Employee, UUID>{

	Optional<Employee> findById(String id);

	void deleteById(String id);

}
