package com.revature.services;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class UserServiceImpl implements UserService {
	
	public UserDAO ud = (UserDAO) BeanFactory.getFactory().get(UserDAO.class, UserDAOFile.class);
	
	@Override
	public User login(String name) {
		User u = ud.getUser(name);
		(new UserDAOFile()).writeToFile();
		return u;
	}
	
	@Override
	public void Deposit(User user, Long money) {
		user.setMoney(user.getMoney() + money);
		(new UserDAOFile()).writeToFile();
	}
	
	@Override
	public void Withdraw(User user, Long money) {
		user.setMoney(user.getMoney() - money);
		(new UserDAOFile()).writeToFile();
	}
	
	@Override
	public boolean checkAvailability(String newName) {
		return ud.getUsers()
				.stream()
				.noneMatch((u)->u.getUsername().equals(newName));
	}
	
	@Override
	public boolean checkIfExists(String newName) {
		return ud.getUsers()
				.stream()
				.anyMatch((u)->u.getUsername().equals(newName));
	}
	
	
	
	@Override
	public void register(String username, String password, String email, String phone) {
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u.setMoney(0l);
		ud.addUser(u);
		(new UserDAOFile()).writeToFile();
	}
	
	@Override
	public User jsonregister(String username, String password, String email, String phone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setMoney(0l);

		ud.addUser(user);
		(new UserDAOFile()).writeToFile();
		
		return user;
	}
	

}
