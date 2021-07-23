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
		
		User user = ctx.bodyAsClass(User.class);
		log.debug(user);
		
		// create session if login was successful
		if(user != null) {
			ctx.sessionAttribute("loggedUser", user);
			ctx.json(user);
			return;
		}
		
		// send 401 if login was not successful
		ctx.status(401);
	}
	
	public void getMoney(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		
		ctx.json(loggedUser.getMoney());
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void register(Context ctx) {
		User user = ctx.bodyAsClass(User.class);
		
		if(us.checkAvailability(user.getUsername())) {
			User newUser = us.register(user.getUsername(), user.getEmail(), user.getPhone());
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
	}

}
