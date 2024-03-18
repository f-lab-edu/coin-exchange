package com.coin.facade;

public class Credit {
	public boolean hasGoodCredit(Customer c) {
		System.out.println("check credit for " + c.getName());
		return true;
	}
}
