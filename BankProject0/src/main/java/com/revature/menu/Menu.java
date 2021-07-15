package com.revature.menu;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.util.SingletonScanner;

public class Menu {
	
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	
	
	public void start() {
		
		
		mainLoop: while(true) {
			switch(startMenu()) {
			// LOGIN
			case 1:
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				// get the user
				User u = us.login(username);
				if(u == null) {
					// if username is wrong
					System.out.println("Please try again.");
				} else {
					loggedUser = u;
					System.out.println("Hello, " + u.getUsername());
					
					// go to either customer menu or banker menu
					switch(loggedUser.getType()) {
					case CUSTOMER:
						customer();
						break;
					case BANKER:
						banker();
						break;
					
					}
				}
				
			// CREATE ACCOUNT
			case 2:
				break;
			// QUIT
			case 3:
				break mainLoop;
			default:
				System.out.println("Invalid option. Please try again.");
				
			}
		}
		
		
	}
	
	// selecting an option from the menu
	private int select() {
		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		
		return selection;
	}
	
	
	// menus
	private int startMenu() {
		System.out.println("Thank you for choosing Pearl Bank.");
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Create an Account");
		System.out.println("\t3. Quit");
		
		return select();
	}
	
	private int customer() {
		
	}
	
	private int banker() {
		
	}
	
	

}
