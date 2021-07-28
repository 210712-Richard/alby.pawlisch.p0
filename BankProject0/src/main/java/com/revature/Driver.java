package com.revature;

import com.revature.controllers.LoanController;
import com.revature.controllers.LoanControllerImpl;
import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
		LoanController lc = (LoanController) BeanFactory.getFactory().get(LoanController.class, LoanControllerImpl.class);
		
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
		// check other's balance
		app.get("/banking/customerbalance/:requestuser", uc::getOtherMoney);
		// submit a loan
		app.put("/loanapplication", lc::applyLoan);
		// see all loans
		app.get("/banking/loans", lc::viewAllLoans);
		// request to see a specific loan
		app.get("/banking/loans/:loanid", lc::viewLoan);
		// set new loan status
		app.put("/banking/loans/:loanid/:status", lc::changeLoanStatus);
		
	}

}
