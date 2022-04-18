package com.team13.model;

/*
 * Author: Pham Tuan Hung
 * 
 */
public abstract class Student {
	protected int studentID;
	protected String name;
	protected String type;
	protected String email;
	protected String address;
	protected String academicYear;

	public Student(int studentID, String name, String type, String email, String address, String academicYear) {
		this.studentID = studentID;
		this.name = name;
		this.type = type;
		this.email = email;
		this.address = address;
		this.academicYear = academicYear;
	}

	public int getStudentID() {
		return studentID;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getAcademicYear() {
		return academicYear;
	}
}