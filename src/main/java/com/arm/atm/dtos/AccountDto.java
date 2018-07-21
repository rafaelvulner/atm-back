package com.arm.atm.dtos;

import java.math.BigDecimal;

import com.arm.atm.entity.Bank;

public class AccountDto {
	
	private Long number;
	
	private String owner;
	
	private Bank bank;
	
	private BigDecimal balance;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountDto [number=" + number + ", owner=" + owner + ", bank=" + bank + ", balance=" + balance + "]";
	}
	
	

}
