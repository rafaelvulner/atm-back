package com.arm.atm.component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.arm.atm.entity.Account;
import com.arm.atm.service.AccountService;

@Component
@Scope(SCOPE_PROTOTYPE)
public class Atm {
	
	public Atm(Optional<Account> account) {
		
	}
	
	public Atm() {
		
	}
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
    private ApplicationContext сontext;
	
	public Optional<Account> authenticate(String bank, Long number, String password,BindingResult result) {
		
		Optional<Account> account = findAccount(bank, number, password, result);
		
		сontext.getBean(Atm.class, account);
		
		return account;
	}

	private Optional<Account> findAccount(String bank, Long number, String password, BindingResult result) {
		Optional<Account> account = this.accountService.findByBankAndNumberAndPassword(bank, number, password);
		
		if(!account.isPresent()) {
			result.addError(new ObjectError("Account", "Invalid user or password"));
		}
		
		return account;		
	} 
	
}
