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
			Loan loan = ctx.bodyAsClass(Loan.class);
			User loggedUser = ctx.sessionAttribute("loggedUser");
			String applyingUser = loggedUser.getUsername();
			
			Loan newLoan = loanService.applyLoan(applyingUser, loan.getLoanAmount(), loan.getInterest());
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
				ctx.json(loanDAO.getLoan(loanId));
			}
			
			
		}
		
		@Override
		public void changeLoanStatus(Context ctx) {
			User loggedUser = ctx.sessionAttribute("loggedUser");
			
			if(loggedUser.getType() != UserType.BANKER) {
				ctx.status(403);
				return;
			} else {
				
			}
			
		}

		

}
