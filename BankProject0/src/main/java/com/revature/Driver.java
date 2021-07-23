package com.revature;

import com.revature.controllers.UserController;
import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
//		Menu m = new Menu();
//		m.start();
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
		
		app.get("/", (ctx)->ctx.html("Hello World"));
		
		// login
		app.post("/users", uc::login);
		// logout
		app.delete("/users", uc::logout);
		// register
		app.put("/users/:username", uc::register);
		// view balance
		app.get("/users/:username/money", uc::getMoney);
		// deposit
		app.put("/users/:username/deposit", uc::deposit);
		// withdraw
		app.put("/users/:username/withdraw", uc::withdraw);
		
	}

}
