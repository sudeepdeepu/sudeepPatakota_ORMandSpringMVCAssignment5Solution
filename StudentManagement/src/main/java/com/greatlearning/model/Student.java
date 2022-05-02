package com.greatlearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="student")
public class Student {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;


	@Column(name="name")
	private String name;


	@Column(name="department")
	private String department;


	@Column(name="country")
	private String country;


	// define constructors

	public Student()
	{

	}


	public int getid() {
		return id;
	}


	public void setid(int id) {
		this.id = id;
	}


	public String getname() {
		return name;
	}


	public void setname(String name) {
		this.name = name;
	}


	public String getdepartment() {
		return department;
	}


	public void setdepartment(String department) {
		this.department = department;
	}


	public String getcountry() {
		return country;
	}


	public void setcountry(String country) {
		this.country = country;
	}


	public Student( String name, String department, String country) {
		super();
		this.name = name;
		this.department = department;
		this.country = country;
	}
}

