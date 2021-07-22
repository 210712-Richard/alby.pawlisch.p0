package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Loan;

public class LoanDAO {
	
	private static String filename = "loan.dat";
	private static List<Loan> loans;
	
	static {
		DataSerializer<Loan> ds = new DataSerializer<Loan>();
		loans = ds.readObjectsFromFile(filename);
		// id, applyingUser, loanAmount, loanInterest, loanStatus
		if(loans == null) {
			loans = new ArrayList<Loan>();
			loans.add(new Loan(loans.size(), "WillTurner", 2000l, .02, false));
			loans.add(new Loan(loans.size(), "JackSparrow", 3000l, .02, false));
		}
	}

}

