package com.revature.controllers;

import io.javalin.http.Context;

public interface LoanController {
	

	void applyLoan(Context ctx);
	
	public void viewAllLoans(Context ctx);
	
	public void viewLoan(Context ctx);
	
	public void changeLoanStatus(Context ctx);

}
