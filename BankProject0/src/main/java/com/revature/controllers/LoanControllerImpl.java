package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Loan;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.LoanDAO;
import com.revature.data.LoanDAOFile;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.LoanService;
import com.revature.services.LoanServiceImpl;

import io.javalin.http.Context;

@Log
public class LoanControllerImpl implements LoanController {
		private static Logger log = LogManager.getLogger(LoanController.class);
		private LoanService loanService = (LoanService) BeanFactory.getFactory().get(LoanService.class, LoanServiceImpl.class);
		public LoanDAO loanDAO = (LoanDAO) BeanFactory.getFactory().get(LoanDAO.class, LoanDAOFile.class);
		

		@Override
		public void applyLoan(Context ctx) {
			log.debug(ctx.body());
			User loggedUser = ctx.sessionAttribute("loggedUser");
			String applyingUser = loggedUser.getUsername();

			Loan loan = ctx.bodyAsClass(Loan.class);
			Long loanAmount = loan.getLoanAmount();
			Double interest = loan.getInterest();
			
			ctx.json(loan);
			Loan newLoan = loanService.applyLoan(applyingUser, loanAmount, interest);
			ctx.status(201);
			ctx.json(newLoan);
			
			
		}
		
		@Override
		public void viewAllLoans(Context ctx) {
			User loggedUser = ctx.sessionAttribute("loggedUser");
			if(loggedUser.getType() != UserType.BANKER) {
				ctx.status(403);
				return;
			} else {
				ctx.json(loanDAO.getLoans());
			}
		}
		
		@Override
		public void viewLoan(Context ctx) {
			User loggedUser = ctx.sessionAttribute("loggedUser");
			Integer loanId = Integer.parseInt(ctx.pathParam("loanid"));
			
			if(loggedUser.getType() != UserType.BANKER) {
				ctx.status(403);
				return;
			} else {
				boolean notExist = loanService.checkIfExists(loanId);
				if(notExist == true) {
					ctx.json(loanDAO.getLoan(loanId));
				} else {
					ctx.status(404);
					ctx.html("Loan ID "+loanId+" does not exist");
				}
				
				
			}
			
			
		}
		
		@Override
		public void changeLoanStatus(Context ctx) {
			User loggedUser = ctx.sessionAttribute("loggedUser");
			Integer loanId = Integer.parseInt(ctx.pathParam("loanid"));
			
			if(loggedUser.getType() != UserType.BANKER) {
				ctx.status(403);
				return;
			} else {
				
				boolean notExist = loanService.checkIfExists(loanId);
				
				if(notExist == false) {
					ctx.status(404);
					ctx.html("Loan ID "+loanId+" does not exist");
				} else {
					Loan changedLoan = loanDAO.getLoan(loanId);
					String approve = "approve";
					String status = ctx.pathParam("status");
					if(status.equals(approve)) {
						changedLoan.setLoanStatus(true);
						ctx.json("Loan has been approved.");
						
					} else {
						changedLoan.setLoanStatus(false);
						ctx.json("Loan has not been approved.");
					}
				}
				
				
			}
			
		}

		

}
