package com.revature.services;

import com.revature.beans.Loan;
import com.revature.beans.User;
import com.revature.data.LoanDAO;
import com.revature.data.LoanDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class LoanServiceImpl implements LoanService{
	
	public LoanDAO loanDAO = (LoanDAO) BeanFactory.getFactory().get(LoanDAO.class, LoanDAOFile.class);
	
	@Override
	public Loan applyLoan(String applyingUser, Long loanAmount, Double interest) {
		Loan loan = new Loan();
		loan.setApplyingUser(applyingUser);
		loan.setLoanAmount(loanAmount);
		loan.setInterest(interest);
		loan.setLoanStatus(false);
		
		loanDAO.addLoan(loan);
		(new LoanDAOFile()).writeToFile();
		
		return loan;
	}
	
	@Override
	public Boolean checkIfExists(Integer id) {
		return loanDAO.getLoans()
				.stream()
				.anyMatch((loan)-> loan.getId().equals(id));
	}
	
	

}
