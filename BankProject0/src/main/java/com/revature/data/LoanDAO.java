package com.revature.data;

import java.util.List;

import com.revature.beans.Loan;

public interface LoanDAO {

	Loan getLoan(Integer id);

	List<Loan> getLoans();

	void addLoan(Loan loan);

}