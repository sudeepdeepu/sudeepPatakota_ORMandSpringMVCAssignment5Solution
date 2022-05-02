package com.greatlearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.model.Student;
import com.greatlearning.service.StudentService;



@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		// get Students from db
		List<Student> theStudents = studentService.findAll();

		// add to the spring model
		theModel.addAttribute("Students", theStudents);

		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentid") int theId,
			Model theModel) {

		// get the Student from the service
		Student theStudent = studentService.findById(theId);


		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "Student-form";			
	}


	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if(id!=0)
		{
			theStudent=studentService.findById(id);
			theStudent.setname(name);
			theStudent.setdepartment(department);
			theStudent.setcountry(country);
		}
		else
			theStudent=new Student(name, department, country);
		// save the Student
		studentService.save(theStudent);


		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("studentid") int theId) {

		// delete the Student
		studentService.deleteById(theId);

		// redirect to /Students/list
		return "redirect:/student/list";

	}
}


