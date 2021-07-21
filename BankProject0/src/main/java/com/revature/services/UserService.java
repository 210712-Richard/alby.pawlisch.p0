package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;


public class UserService {
	
	private UserDAO ud = new UserDAO();
	
	public User login(String name) {
		User u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}
	
	public void Deposit(User user, Long money) {
		user.setMoney(user.getMoney() + money);
		ud.writeToFile();
	}
	
	public void Withdraw(User user, Long money) {
		user.setMoney(user.getMoney() - money);
		ud.writeToFile();
	}
	
	public boolean checkAvailability(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	
	public void register(String username, String password, String email, String phone) {
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u.setMoney(0l);
		ud.addUser(u);
		ud.writeToFile();
	}


}
