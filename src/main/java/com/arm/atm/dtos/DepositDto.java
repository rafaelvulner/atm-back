package com.arm.atm.dtos;

import java.math.BigDecimal;

public class DepositDto {
	
	
		private String bankName;
		
		private Long accountNumber;
		
		private String owner;
		
		private String password;
		
		private BigDecimal balance;
		
		
		public String getBankName() {
			return bankName;
		}
		
		public Long getAccountNumber() {
			return accountNumber;
		}
		
		public String getPassword() {
			return password;
		}
		
		public String getValue() {
			return "value";
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}		

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		@Override
		public String toString() {
			return "DepositDto [bankName=" + bankName + ", accountNumber=" + accountNumber + ", owner=" + owner
					+ ", password=" + password + ", balance=" + balance + "]";
		}

		
		
		
		
	

}
