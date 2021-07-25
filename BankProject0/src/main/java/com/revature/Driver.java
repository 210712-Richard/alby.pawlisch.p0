package com.revature;

import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
		
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
