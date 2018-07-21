package com.arm.atm.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.entity.Account;
import com.arm.atm.response.Response;
import com.arm.atm.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	public AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> findAllAccount() {

		return ResponseEntity.ok(accountService.findAll());
	}

	@PostMapping
	public ResponseEntity<Response<Account>> createAccount(@RequestBody Account account, BindingResult result) {

		Response<Account> response = new Response<Account>();

		verifyExistingAccount(account, result);

		if (result.hasErrors()) {

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(accountService.save(account));

		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<Account>> updateAccount(@RequestBody Account account, BindingResult result) {

		Response<Account> response = new Response<Account>();

		verifyAccount(account, result);

		if (result.hasErrors()) {

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(accountService.update(account));

		return ResponseEntity.ok(response);

	}

	@DeleteMapping
	public ResponseEntity<String> deleteAccount(@RequestBody Account account, BindingResult result) {

		this.accountService.delete(account);

		return ResponseEntity.ok("Successfully deleted");
	}

	/**
	 * checks whether the existing account
	 * 
	 * @param account
	 * @param result
	 */
	private void verifyExistingAccount(Account account, BindingResult result) {

		Optional<Account> accountValid = accountService.findByNumberAndBank(account.getNumber(),
				account.getBank().getName());

		if (accountValid.isPresent()) {

			result.addError(new ObjectError("Account", "Account already exists."));

		}

	}
	
	private void verifyAccount(Account account, BindingResult result) {

		Optional<Account> accountValid = accountService.findByNumberAndBank(account.getNumber(),
				account.getBank().getName());

		if (!accountValid.isPresent()) {

			result.addError(new ObjectError("Account", "Account already exists."));

		}

	}
}
