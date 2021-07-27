package com.revature.services;

import com.revature.beans.Loan;
import com.revature.beans.User;

public interface LoanService {
	Loan applyLoan(String applyingUser, Long loanAmount, Double interest);

}
