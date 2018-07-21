package com.arm.atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.Account;
import com.arm.atm.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Optional<Account> findByBankAndNumberAndPassword(String bank, Long number, String password){
		return this.accountRepository.findByBankAndNumberAndPassword(bank, number,  password);
	}	
	
	public Optional<Account> findById(Long id){
		return this.accountRepository.findById(id);
	};

	public Account save(Account newAccount) {
		return this.accountRepository.save(newAccount);
	}
	
	public void delete(Account newAccount) {
		this.accountRepository.delete(newAccount);
	}
	
	public Account update(Account newAccount) {
		return this.accountRepository.save(newAccount);
	}
	
	public List<Account> findAll(){
		return this.accountRepository.findAll();
	}
	
	public Optional<Account> findByNumberAndBank(Long number,String bank){
		return this.accountRepository.findByNumberAndBank(number, bank);
	}
	
	
	
}
