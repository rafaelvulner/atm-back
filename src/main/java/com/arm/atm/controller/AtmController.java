package com.arm.atm.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.Atm;
import com.arm.atm.dtos.AccountDto;
import com.arm.atm.dtos.DepositDto;
import com.arm.atm.entity.Account;
import com.arm.atm.response.Response;
import com.arm.atm.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/atm-api")
public class AtmController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private Atm atm;

	@PutMapping("/deposit")
	public ResponseEntity<Response<Account>> deposit(@RequestBody DepositDto depositdto, BindingResult result) {

		Response<Account> response = new Response<Account>();

		Optional<Account> account = atm.authenticate(depositdto.getBankName(), depositdto.getAccountNumber(),
				depositdto.getPassword(), result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		account = this.sumConvertDtoToAccount(depositdto, account);
		response.setData(accountService.update(account.get()));

		return ResponseEntity.ok(response);
	}

	@PutMapping("/withdraw")
	public ResponseEntity<Response<Account>> createBank(@RequestBody DepositDto depositdto, BindingResult result) {

		Response<Account> response = new Response<Account>();

		Optional<Account> account = atm.authenticate(depositdto.getBankName(), depositdto.getAccountNumber(),
				depositdto.getPassword(), result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		account = this.subtractConvertDtoToAccount(depositdto, account, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(accountService.update(account.get()));

		return ResponseEntity.ok(response);
	}

	@PostMapping("/balance")
	public ResponseEntity<Response<AccountDto>> balance(@RequestBody DepositDto depositdto, BindingResult result) {

		Response<AccountDto> response = new Response<AccountDto>();
		
		Optional<Account> account = atm.authenticate(depositdto.getBankName(), depositdto.getAccountNumber(),
				depositdto.getPassword(), result);


		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		AccountDto accountDto = this.convertAccountToDto(account.get());
		response.setData(accountDto);

		return ResponseEntity.ok(response);
	}

	private Optional<Account> sumConvertDtoToAccount(DepositDto depositdto, Optional<Account> account) {

		BigDecimal deposit = depositdto.getBalance();

		deposit = deposit.add(account.get().getBalance());

		account.get().setBalance(deposit);

		return account;
	}

	private Optional<Account> subtractConvertDtoToAccount(DepositDto depositdto, Optional<Account> account,
			BindingResult result) {
		
		if(verifyCedulaAllowed(depositdto.getBalance().doubleValue()) > 0) {
			result.addError(new ObjectError("Account", "Available only 10, 20, 50 and 100"));
			return account;
		}

		BigDecimal deposit = account.get().getBalance();

		deposit = deposit.subtract(depositdto.getBalance());

		if (deposit.signum() == -1) {
			result.addError(new ObjectError("Account", "insufficient balance"));
			return account;
		}		

		account.get().setBalance(deposit);
		account.get().setQtdCedula(verifyQtdCedula(depositdto.getBalance().doubleValue()));

		return account;
	}

	private int verifyQtdCedula(Double value) {

		int hundred = (int) (value / 100);
		int fifty = (int) ((value % 100) / 50);
		int twenty = (int) (((value % 100) % 50) / 20);
		int ten = (int) (((value % 100) % 50) % 20 / 10);

		return (hundred + fifty + twenty + ten);
	}

	private Double verifyCedulaAllowed(Double value) {

		return ((((value % 100) % 50)% 20)% 10) /5;
	}
	
	private AccountDto convertAccountToDto(Account account) {
		
		AccountDto dto = new AccountDto();
		
		dto.setBalance(account.getBalance());
		dto.setBank(account.getBank());
		dto.setNumber(account.getNumber());
		dto.setOwner(account.getOwner());
		
		return dto;
	}
}
