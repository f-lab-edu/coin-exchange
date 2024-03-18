package com.coin.facade;

public class Program {

	public static void main(String[] args) {
		Mortgage m = new Mortgage();
		Customer c = new Customer("Ann");
		boolean eligible = m.isEligible(c, 125000);
		System.out.println("\n" + c.getName() + " has been " + (eligible ? "Approved" : "Rejected"));
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
