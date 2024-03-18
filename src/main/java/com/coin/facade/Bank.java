package com.coin.facade;

public class Bank {
	public boolean hasSufficientSavings(Customer c, int amount) {
		System.out.println("check bank for " + c.getName());
		return true;
	}
}
