package com.revature.services;

import com.revature.beans.User;

public interface UserService {

	User login(String name);

	void Deposit(User user, Long money);

	void Withdraw(User user, Long money);

	boolean checkAvailability(String newName);

	boolean checkIfExists(String newName);

	void register(String username, String password, String email, String phone);

	User jsonregister(String username, String password, String email, String phone);

}