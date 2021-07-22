package com.revature.beans;

import java.io.Serializable;

public class Loan implements Serializable {
	
	private Integer id;
	private String applyingUser;
	private Long loanAmount;
	private Double interest;
	private boolean loanStatus;
	
	public Loan() {
		super();
	}

	public Loan(Integer id, String applyingUser, Long loanAmount, Double interest, boolean loanStatus) {
		this();
		this.id = id;
		this.applyingUser = applyingUser;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.loanStatus = loanStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplyingUser() {
		return applyingUser;
	}

	public void setApplyingUser(String applyingUser) {
		this.applyingUser = applyingUser;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public boolean isLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyingUser == null) ? 0 : applyingUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interest == null) ? 0 : interest.hashCode());
		result = prime * result + ((loanAmount == null) ? 0 : loanAmount.hashCode());
		result = prime * result + (loanStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (applyingUser == null) {
			if (other.applyingUser != null)
				return false;
		} else if (!applyingUser.equals(other.applyingUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interest == null) {
			if (other.interest != null)
				return false;
		} else if (!interest.equals(other.interest))
			return false;
		if (loanAmount == null) {
			if (other.loanAmount != null)
				return false;
		} else if (!loanAmount.equals(other.loanAmount))
			return false;
		if (loanStatus != other.loanStatus)
			return false;
		return true;
	}
	
}