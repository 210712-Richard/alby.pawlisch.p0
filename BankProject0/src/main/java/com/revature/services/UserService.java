package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.menu.Menu;

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

}
