package com.revature.data;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Loan;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class LoanDAOFile implements LoanDAO {
	
	public UserDAO userDAO = (UserDAO) BeanFactory.getFactory().get(UserDAO.class, UserDAOFile.class);
	
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
			
			ds.writeObjectsToFile(loans, filename);
		}
	}
	
	@Override
	public Loan getLoan (Integer id) {
		return loans.stream()
				.filter((loan) -> loan.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public List<Loan> getLoans(){
		return loans;
	}
	
	@Override
	public void addLoan(Loan loan) {
		loan.setId(loans.size());
		loans.add(loan);
	}
	
	public void writeToFile() {
		new DataSerializer<Loan>().writeObjectsToFile(loans, filename);
	}

}

