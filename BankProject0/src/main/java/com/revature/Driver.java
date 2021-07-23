package com.revature;

import com.revature.menu.Menu;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
//		Menu m = new Menu();
//		m.start();
		
		Javalin app = Javalin.create().start(8080);
		app.get("/", (ctx)->ctx.html("Hello World"));
		
	}

}
