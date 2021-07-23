package com.revature.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.services.UserService;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	
	public void login(Context ctx) {
		
		log.trace("Login method called");
		log.debug(ctx.body());
		
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		
		u = us.login(u.getUsername());
		log.debug(u);
		
		if(u != null) {
			ctx.sessionAttribute("loggedUser", u);
			ctx.json(u);
			return;
		}
		
		//if not successful sends this
		ctx.status(401);
		
	}
	
	public void getMoney(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		try {
			ctx.json(loggedUser.getMoney());
		} catch (Exception e) {
			String error = e.toString();
			ctx.json(error);
		}
		
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void register(Context ctx) {
		User user = ctx.bodyAsClass(User.class);
		
		if(us.checkAvailability(user.getUsername())) {
			User newUser = us.jsonregister(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone());
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
	}
	
	public void deposit(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		Long depositMoney = ctx.bodyAsClass(Long.class);
		
		log.trace("Depositing "+depositMoney.toString()+" to "+loggedUser);
		
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		us.Deposit(loggedUser, depositMoney);
		ctx.html("Withdrew funds. New balance: "+loggedUser.getMoney());
		
	}
	
	public void withdraw(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		Long withdrawMoney = ctx.bodyAsClass(Long.class);
		
		log.trace("Depositing "+withdrawMoney.toString()+" to "+loggedUser);
		
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		us.Withdraw(loggedUser, withdrawMoney);
		ctx.html("Withdrew funds. New balance: "+loggedUser.getMoney());
	}

}
