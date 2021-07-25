package com.revature.data;

import java.util.List;

import com.revature.beans.User;

public interface UserDAO {

	User getUser(String username);

	List<User> getUsers();

	void addUser(User u);

}