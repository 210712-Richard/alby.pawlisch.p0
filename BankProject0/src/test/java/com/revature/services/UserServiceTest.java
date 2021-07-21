package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDAO;

public class UserServiceTest {
	private static UserService service;
	private static User u;
	
	@BeforeAll 
	public static void setUpClass() {
		u = new User();
		u.setUsername("Test");
		u.setUsername("test");
	}
	
	@BeforeEach 
	public void setUpTests() {
		service = new UserService(); 
		u.setMoney(500l);
		service.ud = Mockito.mock(UserDAO.class);
	}
	
	@Test
	public void testDeposit() {
		Long startingBalance = u.getMoney();
		
		service.Deposit(u, 500l);
		assertEquals(startingBalance + 500l, u.getMoney(), "Asserting that currency is correct");
		
		Mockito.verify(service.ud).writeToFile();
	}
	
	@Test
	public void testWithdraw() {
		Long startingBalance = u.getMoney();
		
		service.Withdraw(u, 500l);
		assertEquals(startingBalance - 500l, u.getMoney(), "Asserting that currency is correct");
		
		Mockito.verify(service.ud).writeToFile();
	}
	
	@Test
	public void testRegister() {
		String username = "test";
		String password = "test";
		String email = "test@test.test";
		String phone = "000-000-0000";
		service.register(username, password, email,phone);
		
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		Mockito.verify(service.ud).addUser(captor.capture());
		
		Mockito.verify(service.ud).writeToFile();
		
		User u = captor.getValue();
		assertEquals(0l, u.getMoney(), "Asserting starting balance is 1000");
		assertEquals(UserType.CUSTOMER, u.getType(), "Asserting user is a Customer");
		assertEquals(username, u.getUsername(), "Asserting username is correct");
		assertEquals(password, u.getPassword(), "Asserting password is correct");
		assertEquals(email, u.getEmail(), "Asserting email is correct");
		assertEquals(phone, u.getPhone(), "Asserting phone is correct");
		
	}

}
