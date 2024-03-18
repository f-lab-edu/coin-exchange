package com.coin.facade;

public class Loan {
	public boolean hasNoBadLoans(Customer c) {
        System.out.println("Check loans for " + c.getName());
        return true;
    }
}
