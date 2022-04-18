package com.team13.model;

/*
 * Author: Nguyen Thanh Long
 * 
 */
public class StudentCredits extends Student {
	public static final int MAX_CREDITS = 180;
	private int numberCredits;

	public StudentCredits(int studentID, String name, String type, String email, String address, String academicYear,
			int numberCredits) {
		super(studentID, name, type, email, address, academicYear);
		this.numberCredits = numberCredits;
	}

	public int getNumberCredits() {
		return numberCredits;
	}
}
