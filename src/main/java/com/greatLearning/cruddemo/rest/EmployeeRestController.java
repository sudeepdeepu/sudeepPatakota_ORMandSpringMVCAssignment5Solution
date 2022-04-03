package com.greatLearning.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.cruddemo.entity.Employee;
import com.greatLearning.cruddemo.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		List<Employee> theEmployee = employeeService.findAll();
		theModel.addAttribute("Employees",theEmployee);
		return "list-Employees";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee theEmployee = new Employee();
		theModel.addAttribute("Employee",theEmployee);
		return "Employee-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		Employee theEmployee = employeeService.findById(theId);
		theModel.addAttribute("Student",theEmployee);
		return "Employee-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,@RequestParam("email") String email) {
		Employee theEmployee;
		if(id!=0) {
			theEmployee=employeeService.findById(id);
			theEmployee.setFirstName(firstName);
			theEmployee.setLastName(lastName);
			theEmployee.setEmail(email);
		}else
			theEmployee= new Employee(firstName,lastName,email);
		employeeService.save(theEmployee);
		return "redirect:/employee/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employee/list";
	}
	
	
}










