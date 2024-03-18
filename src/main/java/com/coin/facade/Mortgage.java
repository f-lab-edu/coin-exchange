package com.coin.facade;

public class Mortgage {
	private Bank bank;
	private Loan loan;
	private Credit credit;
	
	public Mortgage() {
		this.bank = new Bank();
		this.credit = new Credit();
		this.loan = new Loan();
	}
	
	public boolean isEligible(Customer c, int amount) {
		System.out.printf("%s applies for %,d loan\n", c.getName(), amount);
		boolean eligible = true;
		if(!bank.hasSufficientSavings(c, amount)) {
			eligible = false;
		} 
		if(!credit.hasGoodCredit(c)) {
			eligible = false;
		}
		if(!loan.hasNoBadLoans(c)) {
			eligible = false;
		}
		return eligible;
	}
	
}
