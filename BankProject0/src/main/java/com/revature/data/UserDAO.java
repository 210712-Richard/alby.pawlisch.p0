package com.revature.data;

import java.util.List;
import java.util.ArrayList;

import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAO {
	
	private static List<User> users;
	private static String filename = "users.dat";
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readMoneyFromFile(filename);
		
		// users generated for if users.dat is empty
		// id(user.size()), username, email, phone, money, password
		if(users == null) {
			users = new ArrayList<User>();
			users.add(new User(users.size(), "WillTurner", "turner@potc.com", "920-444-2222", 500l, "pirates" ));
			users.add(new User(users.size(), "ElizabethSwan", "eswan@potc.com", "920-555-3333", 3000l, "pirates" ));
			users.add(new User(users.size(), "JackSparrow", "captain@potc.com", "920-666-1111", 50l, "pirates" ));
			// barbossa admin account set manually as banker
			User u = new User(users.size(), "Barbossa", "barbank@pearl.com", "920-123-4567", 1000l, "pirates");
			u.setType(UserType.BANKER);
			users.add(u);
			
			ds.writeMoneyToFile(users, filename);
		}
	}
	
	
	// login, no password rn
	public User getUser (String username) {
		
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	
	public void addUser(User u) {
		
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeMoneyToFile(users, filename);
	}
	

}
