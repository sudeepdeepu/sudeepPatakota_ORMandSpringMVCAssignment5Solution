package com.greatLearning.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatLearning.cruddemo.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	
}
