package com.team13.view;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.team13.controller.StudentDAOImp;
import com.team13.model.Student;
import com.team13.model.StudentCredits;
import com.team13.model.StudentPattern;

/*
 * Author: Pham Tuan Hung & Nguyen Thanh Long
 * Creat UI and handle tasks 
 */

public class StudentView extends JFrame {
	private JPanel contentPane;
	private String[] types = { "Credits", "Pattern" };
	private String[] academicYear = { "K1", "K2", "K3", "K4", "K5", "K6", "K7", "K8" };
	private JTable table;
	private JTextField tfID, tfName, tfEmail, tfAddress, tfAchievement, tfSearch;
	private JComboBox cbType, cbAcademicYear;
	private JButton btAdd, btUpdate, btDelete, btClear, btStatistical, btStatisticalOff;
	private JLabel lbNumberCredits, lbNumberPattern;
	private DefaultTableModel model = new DefaultTableModel();

	private int oldStudentIDClicked;
	private StudentDAOImp controller;

	public StudentView() {
		controller = new StudentDAOImp();
		initUI();
		loadAllData();
		addEvent();
		setDataWhenSelect();
		showDataWhenSearch();
		statisticalTypeStudent();
	}

	private void initUI() {
		setTitle("Graduate Management");
		// Set style for UI
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Color colorText = new java.awt.Color(0, 102, 102);
		JLabel lblNewLabel = new JLabel("Graduate Management");
		lblNewLabel.setForeground(colorText);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(0, 0, 1178, 45);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 85, 914, 410);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Type");
		model.addColumn("Email");
		model.addColumn("Address");
		model.addColumn("Academic Year");
		model.addColumn("Achievements");

		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setRowHeight(25);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(170);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(15, 82, 23, 20);
		contentPane.add(lblNewLabel_1);

		tfID = new JTextField();
		tfID.setBounds(83, 82, 166, 26);
		contentPane.add(tfID);
		tfID.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Name:");
		lblNewLabel_1_1.setBounds(15, 118, 47, 20);
		contentPane.add(lblNewLabel_1_1);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(83, 118, 166, 26);
		contentPane.add(tfName);

		JLabel lblNewLabel_1_1_1 = new JLabel("Type:");
		lblNewLabel_1_1_1.setBounds(15, 154, 47, 20);
		contentPane.add(lblNewLabel_1_1_1);

		cbType = new JComboBox(types);
		cbType.setBounds(83, 151, 76, 26);
		contentPane.add(cbType);

		JLabel lblNewLabel_1_1_2 = new JLabel("Email:");
		lblNewLabel_1_1_2.setBounds(15, 190, 47, 20);
		contentPane.add(lblNewLabel_1_1_2);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(83, 190, 166, 26);
		contentPane.add(tfEmail);

		JLabel lblNewLabel_1_1_3 = new JLabel("Address:");
		lblNewLabel_1_1_3.setBounds(15, 226, 69, 20);
		contentPane.add(lblNewLabel_1_1_3);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(83, 226, 166, 26);
		contentPane.add(tfAddress);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Academic Year:");
		lblNewLabel_1_1_3_1.setBounds(15, 262, 117, 20);
		contentPane.add(lblNewLabel_1_1_3_1);

		cbAcademicYear = new JComboBox(academicYear);
		cbAcademicYear.setBounds(110, 259, 76, 26);
		contentPane.add(cbAcademicYear);

		btAdd = new JButton("Add");
		btAdd.setForeground(Color.WHITE);
		btAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btAdd.setBackground(new Color(0, 102, 102));
		btAdd.setBounds(32, 362, 88, 35);
		contentPane.add(btAdd);

		btUpdate = new JButton("Update");
		btUpdate.setForeground(Color.WHITE);
		btUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btUpdate.setBackground(new Color(0, 102, 102));
		btUpdate.setBounds(149, 362, 88, 35);
		contentPane.add(btUpdate);

		btDelete = new JButton("Delete");
		btDelete.setForeground(Color.WHITE);
		btDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btDelete.setBackground(new Color(0, 102, 102));
		btDelete.setBounds(32, 413, 88, 35);
		contentPane.add(btDelete);

		btClear = new JButton("Clear");
		btClear.setForeground(Color.WHITE);
		btClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btClear.setBackground(new Color(0, 102, 102));
		btClear.setBounds(149, 413, 88, 35);
		contentPane.add(btClear);

		JLabel lblNewLabel_1_1_4 = new JLabel("Achievements:");
		lblNewLabel_1_1_4.setBounds(15, 297, 105, 20);
		contentPane.add(lblNewLabel_1_1_4);

		tfAchievement = new JTextField();
		tfAchievement.setColumns(10);
		tfAchievement.setBounds(110, 297, 76, 26);
		contentPane.add(tfAchievement);

		JLabel lblNewLabel_2 = new JLabel("Student Credits:");
		lblNewLabel_2.setBounds(266, 65, 117, 20);
		contentPane.add(lblNewLabel_2);

		lbNumberCredits = new JLabel("0");
		lbNumberCredits.setBounds(358, 65, 69, 20);
		contentPane.add(lbNumberCredits);

		lbNumberPattern = new JLabel("0");
		lbNumberPattern.setBounds(525, 65, 69, 20);
		contentPane.add(lbNumberPattern);

		JLabel lblNewLabel_2_1 = new JLabel("Student Pattern:");
		lblNewLabel_2_1.setBounds(432, 65, 117, 20);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_5 = new JLabel("Enter Value To Search :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(264, 503, 191, 20);
		contentPane.add(lblNewLabel_1_5);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(417, 500, 526, 26);
		contentPane.add(tfSearch);

		btStatistical = new JButton("Statistical");
		btStatistical.setForeground(Color.WHITE);
		btStatistical.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btStatistical.setBackground(new Color(0, 102, 102));
		btStatistical.setBounds(940, 55, 105, 26);
		contentPane.add(btStatistical);

		btStatisticalOff = new JButton("Statistical Off");
		btStatisticalOff.setForeground(Color.WHITE);
		btStatisticalOff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btStatisticalOff.setBackground(new Color(0, 102, 102));
		btStatisticalOff.setBounds(1048, 55, 115, 26);
		btStatisticalOff.setEnabled(false);
		contentPane.add(btStatisticalOff);

	}

	private void addEvent() {
		btAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStudent();
			}
		});
		btUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStudent();
			}
		});
		btDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
		btClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAllInput();
			}
		});
		btStatistical.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statisticalStudentGraduate();

			}
		});
		btStatisticalOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statisticalOff();
			}
		});

	}

	private void statisticalOff() {
		btStatistical.setEnabled(true);
		btStatisticalOff.setEnabled(false);
		loadAllData();
	}

	private void statisticalStudentGraduate() {
		List<Student> listStudent = controller.getListStudentGraduate();
		showDataToTable(listStudent);
		btStatistical.setEnabled(false);
		btStatisticalOff.setEnabled(true);
	}

	private void deleteStudent() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Please Select Row First");
		} else {
			controller.delete(oldStudentIDClicked);
			resetAllInput();
			loadAllData();
			statisticalTypeStudent();
		}
	}

	private void updateStudent() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Please Select Row First");
		} else {
			checkBeforeHandle();
			int studentID = Integer.parseInt(tfID.getText());
			String name = tfName.getText();
			String type = cbType.getItemAt(cbType.getSelectedIndex()).toString();
			String email = tfEmail.getText();
			String address = tfAddress.getText();
			String academicYear = cbAcademicYear.getItemAt(cbAcademicYear.getSelectedIndex()).toString();
			if (type.equals("Credits")) {
				if (controller.isInt(tfAchievement.getText())
						&& Integer.parseInt(tfAchievement.getText()) <= StudentCredits.MAX_CREDITS) {
					Student student = new StudentCredits(studentID, name, type, email, address, academicYear,
							Integer.parseInt(tfAchievement.getText()));

					controller.update(student, oldStudentIDClicked);
				} else {
					JOptionPane.showMessageDialog(null, "Number Credits invalid!");
					return;
				}
			} else {
				if (controller.isFloat(tfAchievement.getText())) {
					float gpa = Float.parseFloat(tfAchievement.getText());
					if (gpa >= 0 && gpa <= 10) {
						Student student = new StudentPattern(studentID, name, type, email, address, academicYear, gpa);
						controller.update(student, oldStudentIDClicked);
					} else {
						JOptionPane.showMessageDialog(null, "GPA invalid!");
						return;
					}
				}
			}
			resetAllInput();
			loadAllData();
			statisticalTypeStudent();
		}

	}

	private void addStudent() {
		checkBeforeHandle();
		int studentID = Integer.parseInt(tfID.getText());
		String name = tfName.getText();
		String type = cbType.getItemAt(cbType.getSelectedIndex()).toString();
		String email = tfEmail.getText();
		String address = tfAddress.getText();
		String academicYear = cbAcademicYear.getItemAt(cbAcademicYear.getSelectedIndex()).toString();
		if (type.equals("Credits")) {
			if (controller.isInt(tfAchievement.getText())
					&& Integer.parseInt(tfAchievement.getText()) <= StudentCredits.MAX_CREDITS) {
				Student student = new StudentCredits(studentID, name, type, email, address, academicYear,
						Integer.parseInt(tfAchievement.getText()));

				controller.add(student);
			} else {
				JOptionPane.showMessageDialog(null, "Number Credits invalid!");
				return;
			}
		} else {
			if (controller.isFloat(tfAchievement.getText())) {
				float gpa = Float.parseFloat(tfAchievement.getText());
				if (gpa >= 0 && gpa <= 10) {
					Student student = new StudentPattern(studentID, name, type, email, address, academicYear, gpa);
					controller.add(student);
				} else {
					JOptionPane.showMessageDialog(null, "GPA invalid!");
					return;
				}
			}
		}
		resetAllInput();
		loadAllData();
		statisticalTypeStudent();
	}

	// Author: Nguyen Thanh Long
	private void statisticalTypeStudent() {
		int[] quantityType = controller.statisticalTypeStudent();
		lbNumberCredits.setText(quantityType[0] + "");
		lbNumberPattern.setText(quantityType[1] + "");
	}

	private void showDataWhenSearch() {
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (tfSearch.getText().equals("")) {
					loadAllData();
				} else {
					showResult(tfSearch.getText());
				}
			}
		});

	}

	private void showResult(String valueSearch) {
		List<Student> listStudent = controller.searchStudent(valueSearch);
		showDataToTable(listStudent);
	}

	private void loadAllData() {
		List<Student> listStudent = controller.getListStudent();
		showDataToTable(listStudent);
	}

	private void showDataToTable(List<Student> listStudent) {
		model.setRowCount(0);
		for (Student student : listStudent) {
			int studentID = student.getStudentID();
			String name = student.getName();
			String type = student.getType();
			String email = student.getEmail();
			String address = student.getAddress();
			String academicYear = student.getAcademicYear();
			Object achievements = null;
			if (student instanceof StudentCredits) {
				achievements = ((StudentCredits) student).getNumberCredits();
			} else {
				achievements = ((StudentPattern) student).getGpa();
			}
			model.addRow(new Object[] { studentID, name, type, email, address, academicYear, achievements });
		}
	}

	// Author: Nguyen Thanh Long
	private void setDataWhenSelect() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				oldStudentIDClicked = (int) model.getValueAt(i, 0);
				tfID.setText(model.getValueAt(i, 0).toString());
				tfName.setText(model.getValueAt(i, 1).toString());
				cbType.setSelectedItem(model.getValueAt(i, 2).toString());
				tfEmail.setText(model.getValueAt(i, 3).toString());
				tfAddress.setText(model.getValueAt(i, 4).toString());
				cbAcademicYear.setSelectedItem(model.getValueAt(i, 5).toString());
				tfAchievement.setText(model.getValueAt(i, 6).toString());
			}
		});

	}

	// Author: Nguyen Thanh Long
	private void checkBeforeHandle() {
		if (tfID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Student ID");
		} else if (tfName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Name");
		} else if (tfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Email");
		} else if (tfAddress.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Address");
		} else if (tfAchievement.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Achievement");
		}
	}

	// Author: Nguyen Thanh Long
	private void resetAllInput() {
		tfID.setText("");
		tfName.setText("");
		tfEmail.setText("");
		tfAddress.setText("");
		tfAchievement.setText("");
		cbType.setSelectedIndex(0);
		cbAcademicYear.setSelectedIndex(0);
	}
}
