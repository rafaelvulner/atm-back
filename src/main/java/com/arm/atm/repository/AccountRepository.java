package com.arm.atm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;


public interface AccountRepository extends JpaRepository<Account, Long>{
	

	@Query(value = "select * from account a inner join bank b on a.bank_id = b.id where b.name = :bank and a.number = :number and a.password = :password", nativeQuery = true)
	Optional<Account> findByBankAndNumberAndPassword(@Param("bank") String bank,@Param("number") Long number,@Param("password") String password);	
	
	Optional<Account> findById(Long id);	
	
	@Query(value ="select * from account a inner join bank b on a.bank_id = b.id where a.number = :number and b.name = :bank", nativeQuery = true)
	Optional<Account> findByNumberAndBank(@Param("number") Long number, @Param("bank") String bank);

	Account save(Account newAccount);
	
	List<Account> findAll();
}
