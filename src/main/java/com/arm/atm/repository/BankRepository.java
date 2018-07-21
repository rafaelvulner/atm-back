package com.arm.atm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arm.atm.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

	Optional<Bank> findByName(String name);
	
	Bank save(Bank bank);
	
	List<Bank> findAll();
	
	Bank findOne(Long id);
	
	Optional<Bank> findByNameContainingIgnoreCase(String name);

}
