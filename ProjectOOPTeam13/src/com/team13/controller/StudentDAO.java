package com.team13.controller;

import java.util.List;

import com.team13.model.Student;

/*
 * Author: Pham Tuan Hung
 * 
 */
public interface StudentDAO {
	public void add(Student student);

	public void update(Student student, int studentID);

	public void delete(int studentID);

	public List<Student> searchStudent(String valueSearch);

	public List<Student> getListStudent();

	public List<Student> getListStudentGraduate();

	public int[] statisticalTypeStudent();
}
