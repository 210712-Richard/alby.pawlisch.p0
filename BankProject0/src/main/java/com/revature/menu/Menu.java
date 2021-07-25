package com.revature.menu;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.util.SingletonScanner;

public class Menu {
	
	private static final Logger log = LogManager.getLogger(Menu.class);
	
	private UserService us = new UserServiceImpl();
	private User loggedUser = null;
	private Scanner scan = SingletonScanner.getScanner().getScan();
	private Long newMoney = null;
	
	public void start() {
		log.trace("Begin the bank application. start()");
		mainLoop: while(true) {
			switch(startMenu()) {
			// LOGIN
			case 1:
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				log.debug(username);
				// get the user
				User u = us.login(username);
				if(u == null) {
					// if user name is wrong
					log.warn("Unsuccessful login attempt using "+username);
					System.out.println("Incorrect username. Please try again.");
				} else {
					
					System.out.println("Enter password: ");
					String password = scan.nextLine();
					log.debug(password);
					
					if(!u.getPassword().equals(password)) {
						log.warn("Unsuccessful login attempt to "+username+" using password: "+password);
						System.out.println("Incorrect password.");
						continue mainLoop;
					}else {
				
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
				}
				break;
			// CREATE ACCOUNT
			case 2:
				System.out.println("Choose your username: ");
				String newName = scan.nextLine();
				if(!us.checkAvailability(newName)) {
					System.out.println("Username not available, please try again.");
					continue mainLoop;
				}
				System.out.println("Enter your password: ");
				String password = scan.nextLine();
				
				System.out.println("Enter your email address: ");
				String email = scan.nextLine();
				
				System.out.println("Enter your phone number (XXX-XXX-XXXX): ");
				String phone = scan.nextLine();
				
				System.out.println("Registering...");
				us.register(newName, password, email, phone);
				
				break;
			// QUIT
			case 3:
				System.out.println("Goodbye!");
				break mainLoop;
			default:
				System.out.println("Invalid option. Please try again.");
				
			}
		}
		log.trace("Ending start()");
	} 
	
	
	private void customer() {
		customerLoop: while(true) {
			// view account balance (money), deposit money, withdraw money, apply for loan, log out
			switch(customerMenu()) {
			case 1:
				// View account balance
				System.out.println("You have " + loggedUser.getMoney() + " in your account.");
				break;
			case 2:
				// Deposit money
				System.out.println("How much would you like to deposit into your account?");
				
				newMoney = moneyChange();
				us.Deposit(loggedUser, newMoney);
				log.trace("Deposited "+newMoney+" to balance");
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 3:
				// Withdraw money
				System.out.println("How much would you like to withdraw from your account?");
				
				newMoney = moneyChange();
				
				us.Withdraw(loggedUser, newMoney);
				log.trace("Withdrew "+newMoney+" from balance");
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
				System.out.println("You have " + loggedUser.getMoney() + " in your account.");
				break;
			case 2:
				// deposit
				System.out.println("How much would you like to deposit into your account?");
				
				newMoney = moneyChange();
				
				us.Deposit(loggedUser, newMoney);
				log.trace("Deposited "+newMoney+" to balance");
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 3:
				// withdraw
				System.out.println("How much would you like to withdraw from your account?");
				
				newMoney = moneyChange();
				
				us.Withdraw(loggedUser, newMoney);
				log.trace("Withdrew "+newMoney+" from balance");
				System.out.println("Your new balance is " + loggedUser.getMoney() + ".");
				break;
			case 4:
				// view others' balances
				System.out.println("Enter the username of the account balance you'd like to view: ");
				
				String inputUser = scan.nextLine();
				
				boolean exists = us.checkIfExists(inputUser); 
				if(!exists) {
					System.out.println("User does not exist.");
				}else {
					User u = us.login(inputUser);
					System.out.println("User "+inputUser+" has "+u.getMoney()+" in their account.");
					log.trace("Viewed balance of user: "+inputUser);
				}
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
	
	
	
		private int startMenu() {
			log.trace("called startMenu()");
			System.out.println("Thank you for choosing Pearl Bank.");
			System.out.println("What would you like to do?");
			System.out.println("\t1. Login");
			System.out.println("\t2. Create an Account");
			System.out.println("\t3. Quit");
			int selection = select();
			log.trace("Start menu returning selection: "+selection);
			return selection;
		}
		
		private int customerMenu() {
			log.trace("called customer()");
			System.out.println("What would you like to do?");
			System.out.println("\t1. View Account Balance");
			System.out.println("\t2. Deposit Money");
			System.out.println("\t3. Withdraw Money");
			System.out.println("\t4. Apply for Loan");
			System.out.println("\t5. Log Out");
			int selection = select();
			log.trace("Customer menu returning selection: "+selection);
			return selection;
		}
		
		private int bankerMenu() {
			log.trace("called banker()");
			System.out.println("What would you like to do?");
			System.out.println("\t1. View My Account Balance");
			System.out.println("\t2. Deposit Money");
			System.out.println("\t3. Withdraw Money");
			System.out.println("");
			System.out.println("\t4. View Other's Account Balance");
			System.out.println("\t5. View Loan Requests");
			System.out.println("\t6. Log Out");
			int selection = select();
			log.trace("Banker menu returning selection: "+selection);
			return selection;
		}
		
		
	
	

}
