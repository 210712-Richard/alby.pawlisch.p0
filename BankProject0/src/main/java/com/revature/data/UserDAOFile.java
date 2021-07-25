package com.revature.data;

import java.util.List;
import java.util.ArrayList;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.factory.Log;

@Log
public class UserDAOFile implements UserDAO {
	
	private static List<User> users;
	private static String filename = "users.dat";
	
	static {
		DataSerializer<User> ds = new DataSerializer<User>();
		users = ds.readObjectsFromFile(filename);
		
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
			
			ds.writeObjectsToFile(users, filename);
		}
	}
	
	
	@Override
	public User getUser (String username) {
		return users.stream()
				.filter((u) -> u.getUsername().equals(username))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public List<User> getUsers(){
		return users;
	}
	
	
	@Override
	public void addUser(User u) {
		u.setId(users.size());
		users.add(u);
	}
	
	public void writeToFile() {
		new DataSerializer<User>().writeObjectsToFile(users, filename);
	}
	

}
