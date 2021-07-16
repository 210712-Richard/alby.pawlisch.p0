package com.revature.menu;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.util.SingletonScanner;

public class Menu {
	
	private UserService us = new UserService();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	private Long newMoney = null;
	
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
					// if user name is wrong
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
	
	
	private void customer() {
		customerLoop: while(true) {
			// view account balance (money), deposit money, withdraw money, apply for loan, log out
			switch(customerMenu()) {
			case 1:
				// View account balance
				System.out.println("You have " + loggedUser.getMoney() + "in your account.");
				break;
			case 2:
				// Deposit money
				depositMenu();
				us.Deposit(loggedUser, newMoney);
				
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 3:
				// Withdraw money
				withdrawMenu();
				us.Withdraw(loggedUser, newMoney);
				
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 4:
				// Apply for loan
				break;
			case 5:
				// Log Out
				loggedUser = null;
				break customerLoop;
			default:
				System.out.println("Invalid option. Please try again.");
				
			}
		}
		
	}
	
	private void banker() {
		bankerLoop: while(true) {
			// "view my account balance", view other's account balances, Deposit money to "my" account,
			// Withdraw money from "my" account, approve loan requests, log out
			switch(bankerMenu()) {
			case 1:
				// view own account
				System.out.println("You have " + loggedUser.getMoney() + "in your account.");
				break;
			case 2:
				// deposit
				depositMenu();
				us.Deposit(loggedUser, newMoney);
				
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 3:
				// withdraw
				withdrawMenu();
				us.Withdraw(loggedUser, newMoney);
				
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 4:
				// view others' balances
				break;
			case 5:
				// view/approve loan requests
				break;
			case 6:
				// log out
				loggedUser = null;
				break bankerLoop;
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
		
	// money reading for deposit/withdrawal
		private long moneyChange() {
			long newMoney;
			try {
				newMoney = Long.parseLong(scan.nextLine());
			} catch(Exception e) {
				newMoney = 0;
			}
			
			return newMoney;
		}
	
	
	// MENUS
		private int startMenu() {
			System.out.println("Thank you for choosing Pearl Bank.");
			System.out.println("What would you like to do?");
			System.out.println("\t1. Login");
			System.out.println("\t2. Create an Account");
			System.out.println("\t3. Quit");
			
			return select();
		}
		
		private int customerMenu() {
			System.out.println("What would you like to do?");
			System.out.println("\t1. View Account Balance");
			System.out.println("\t2. Deposit Money");
			System.out.println("\t3. Withdraw Money");
			System.out.println("\t4. Apply for Loan");
			System.out.println("\t5. Log Out");
			
			return select();
		}
		
		private int bankerMenu() {
			System.out.println("What would you like to do?");
			System.out.println("\t1. View My Account Balance");
			System.out.println("\t2. Deposit Money");
			System.out.println("\t3. Withdraw Money");
			System.out.println("");
			System.out.println("\t4. View Other's Account Balance");
			System.out.println("\t5. View Loan Requests");
			System.out.println("\t6. Log Out");
			
			return select();
		}
		
		
		private long depositMenu() {
			System.out.println("How much would you like to deposit into your account?");
			
			newMoney = moneyChange();
			return newMoney;
		}
		
		private long withdrawMenu() {
			System.out.println("How much would you like to withdraw from your account?");
			
			newMoney = moneyChange();
			return newMoney;
		}
	
	

}
