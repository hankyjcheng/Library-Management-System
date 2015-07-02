package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lms.jdbc.objects.Administrator;
import com.gcit.lms.jdbc.objects.Borrower;
import com.gcit.lms.jdbc.objects.Librarian;

public class LibraryManagement {
	
	private static Connection connection;
	private static Scanner scan;
	
	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yuanju_cheng");
			scan = new Scanner(System.in);
			mainMenu();
			scan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		System.out.println("*******************************************************");
		System.out.println("Welcome to the GCIT Library Management System. ");
		System.out.println("Which category of a user are you? ");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		System.out.println("4) Exit this program");
		if (scan.hasNextInt()) {
			int answer = scan.nextInt();
			if (answer == 1) {
				new Librarian(connection);
			}
			else if (answer == 2) {
				new Administrator(connection);
			}
			else if (answer == 3) {
				new Borrower(connection);
			}
			else if (answer == 4) {
				scan.close();
				return;
			}
			else {
				System.out.println("Invalid Input. Please try again.");
				mainMenu();
			}
		}
		else {
			System.out.println("Invalid Input. Please try again.");
			mainMenu();
		}
	}
	
}
