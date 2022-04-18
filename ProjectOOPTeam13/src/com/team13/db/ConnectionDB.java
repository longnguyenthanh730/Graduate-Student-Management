package com.team13.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Author: Ngo Quoc Dung 
 * 
 */
public class ConnectionDB {
	private static Connection con;

	private static String URL_DB = "jdbc:sqlserver://localhost;database=GraduateManagement;";
	private static String USER = "sa";
	private static String PASS_WORD = "123456";

	public static Connection getConnection() {
		try {
			con = DriverManager.getConnection(URL_DB, USER, PASS_WORD);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection Error");
		}
		return con;
	}
}
