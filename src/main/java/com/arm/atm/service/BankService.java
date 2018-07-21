package com.arm.atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arm.atm.entity.Bank;
import com.arm.atm.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	public Bank save(Bank bank) {
		return bankRepository.save(bank);
	}
	
	public Bank update(Bank bank) {
		return bankRepository.save(bank);
	}
	
	public void delete (Bank bank) {
		bankRepository.delete(bank);
	}
	
	public Optional<Bank> findByName(String name) {
		return bankRepository.findByName(name);
	}
	
	public Bank findOne(Long id) {
		return bankRepository.findOne(id);
	}
	
	public List<Bank> findAll(){
		return bankRepository.findAll();
	}
	
	public Optional<Bank> findByNameContainingIgnoreCase(String name) {
		return bankRepository.findByNameContainingIgnoreCase(name);
	}

}
