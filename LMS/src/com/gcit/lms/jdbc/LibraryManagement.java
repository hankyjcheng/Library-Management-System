package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LibraryManagement {
	
	
	
	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yuanju_cheng");
			Statement stmt = conn.createStatement();
			String selectQuery = "SELECT * FROM tbl_author";
			ResultSet rs = stmt.executeQuery(selectQuery);
			mainMenu();
			/*
			while (rs.next()) {
				System.out.println("Author ID: " + rs.getInt("authorId"));
				System.out.println("Author Name: " + rs.getString("authorName"));
				System.out.println("");
			}
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a new Author: ");
			String authorName = scan.nextLine();
			String createQuery = "INSERT INTO tbl_author (authorName) VALUES ('" +authorName+"' )";
			
			stmt.executeUpdate(createQuery);
			*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mainMenu() {
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextInt()) {
			int answer = scan.nextInt();
			if (answer == 1) {
				librarianMenu();
			}
			else if (answer == 2) {
				administratorMenu();
			}
			else if (answer == 3) {
				borrowerMenu();
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
		scan.close();
	}
	
	public static void librarianMenu() {
		System.out.println("You are now in Librarian Menu.");
		System.out.println("1) Enter the branch you manage");
		System.out.println("2) Return to previous page.");
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextInt()) {
			int answer = scan.nextInt();
			if (answer == 1) {
				branchMenu();
			}
			else if (answer == 2) {
				mainMenu();
			}
			else {
				System.out.println("Invalid Input. Please try again.");
				librarianMenu();
			}
		}
		else {
			System.out.println("Invalid Input. Please try again.");
			librarianMenu();
		}
		scan.close();
	}
	
	public static void administratorMenu() {
		System.out.println("You are now in Administrator Menu.");
		
	}
	
	public static void borrowerMenu() {
		System.out.println("You are now in Borrower Menu.");
	}
	
	public static void branchMenu() {
		System.out.println("You are now in the branch Menu.");
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "yuanju_cheng");
			Statement stmt = conn.createStatement();
			String selectQuery = "SELECT * FROM tbl_library_branch";
			ResultSet rs = stmt.executeQuery(selectQuery);
			int i = 1;
			while (rs.next()) {
				System.out.println(i + ") " + rs.getString("branchName"));
				i++;
			}
			System.out.println(i + ") Return to previous page.");
			Scanner scan = new Scanner(System.in);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
