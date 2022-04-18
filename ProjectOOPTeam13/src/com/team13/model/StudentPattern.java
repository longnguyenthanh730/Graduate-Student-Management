package com.team13.model;

/*
 * Author: Ngo Quoc Dung
 * 
 */
public class StudentPattern extends Student {
	private float gpa;

	public StudentPattern(int studentID, String name, String type, String email, String address, String academicYear,
			float gpa) {
		super(studentID, name, type, email, address, academicYear);
		this.gpa = gpa;
	}

	public float getGpa() {
		return gpa;
	}
}
