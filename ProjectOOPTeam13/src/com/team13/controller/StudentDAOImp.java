package com.team13.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.team13.db.ConnectionDB;
import com.team13.model.Student;
import com.team13.model.StudentCredits;
import com.team13.model.StudentPattern;

/*
 * Author: Pham Tuan Hung
 * 
 */
public class StudentDAOImp implements StudentDAO {
	private Connection con;
	private PreparedStatement ps;
	private String sql;

	@Override
	public void add(Student student) {
		try {
			con = ConnectionDB.getConnection();
			sql = "INSERT INTO Student(StudentID,Name,Type,Email,Address,AcademicYear,Credits,GPA) VALUES (?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, student.getStudentID());
			ps.setString(2, student.getName());
			ps.setString(3, student.getType());
			ps.setString(4, student.getEmail());
			ps.setString(5, student.getAddress());
			ps.setString(6, student.getAcademicYear());
			if (student instanceof StudentCredits) {
				ps.setInt(7, ((StudentCredits) student).getNumberCredits());
				ps.setFloat(8, Types.NULL);
			} else {
				ps.setInt(7, Types.NULL);
				ps.setFloat(8, ((StudentPattern) student).getGpa());
			}

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Add Data Successfully!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
			return;
		}

	}

	@Override
	public void update(Student student, int studentID) {
		try {
			con = ConnectionDB.getConnection();
			sql = "UPDATE Student SET StudentID=?,Name=?,Type=?,Email=?,Address=?,AcademicYear=?,Credits=?,GPA=? WHERE StudentID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, student.getStudentID());
			ps.setString(2, student.getName());
			ps.setString(3, student.getType());
			ps.setString(4, student.getEmail());
			ps.setString(5, student.getAddress());
			ps.setString(6, student.getAcademicYear());
			if (student instanceof StudentCredits) {
				ps.setInt(7, ((StudentCredits) student).getNumberCredits());
				ps.setFloat(8, Types.NULL);
			} else {
				ps.setInt(7, Types.NULL);
				ps.setFloat(8, ((StudentPattern) student).getGpa());
			}
			ps.setInt(9, studentID);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update Data Successfully!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
			return;
		}

	}

	@Override
	public void delete(int studentID) {
		try {
			con = ConnectionDB.getConnection();
			sql = "delete from Student  WHERE StudentID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, studentID);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Deleted!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
			return;
		}
	}

	@Override
	public List<Student> searchStudent(String valueSearch) {
		List<Student> listStudent = new ArrayList<Student>();
		try {
			con = ConnectionDB.getConnection();
			sql = "SELECT * FROM Student WHERE StudentID like ? or Name like ? or AcademicYear like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + valueSearch + "%");
			ps.setString(2, "%" + valueSearch + "%");
			ps.setString(3, "%" + valueSearch + "%");
			ResultSet rs = ps.executeQuery();
			listStudent = saveDataFromDB(rs);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
		}
		return listStudent;
	}

	@Override
	public List<Student> getListStudent() {
		List<Student> listStudent = new ArrayList<Student>();
		try {
			con = ConnectionDB.getConnection();
			sql = "SELECT * FROM Student";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			listStudent = saveDataFromDB(rs);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
		}
		return listStudent;
	}

	@Override
	public int[] statisticalTypeStudent() {
		int[] quantity = new int[2];
		try {
			con = ConnectionDB.getConnection();
			String sqlPattern = "select count(StudentID) from Student where Type = 'Pattern'";
			String sqlCredits = "select count(StudentID) from Student where Type = 'Credits'";
			ps = con.prepareStatement(sqlCredits);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity[0] = rs.getInt(1);
				ps = con.prepareStatement(sqlPattern);
				rs = ps.executeQuery();
				if (rs.next()) {
					quantity[1] = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quantity;
	}

	public List<Student> getListStudentGraduate() {
		List<Student> listStudent = new ArrayList<Student>();
		try {
			con = ConnectionDB.getConnection();
			sql = "select * from Student where Credits = 180 or GPA >=5.0";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			listStudent = saveDataFromDB(rs);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
		}
		return listStudent;
	}

	private List<Student> saveDataFromDB(ResultSet rs) {
		List<Student> listStudent = new ArrayList<Student>();
		Student student = null;
		try {
			while (rs.next()) {
				int studentID = rs.getInt(1);
				String name = rs.getString(2);
				String type = rs.getString(3);
				String email = rs.getString(4);
				String address = rs.getString(5);
				String academicYear = rs.getString(6);
				int credits = rs.getInt(7);
				if (credits != Types.NULL) {
					student = new StudentCredits(studentID, name, type, email, address, academicYear, credits);
				} else {
					float gpa = rs.getInt(8);
					student = new StudentPattern(studentID, name, type, email, address, academicYear, gpa);
				}

				listStudent.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStudent;
	}

	public boolean isInt(String s) {
		try {
			int i = Integer.parseInt(s);
			return true;
		} catch (NumberFormatException er) {
			return false;
		}
	}

	public boolean isFloat(String s) {
		try {
			float i = Float.parseFloat(s);
			return true;
		} catch (NumberFormatException er) {
			return false;
		}
	}

}
