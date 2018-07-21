package com.arm.atm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.entity.Bank;
import com.arm.atm.response.Response;
import com.arm.atm.service.BankService;

@CrossOrigin("*")
@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private BankService bankService;
	
	@PostMapping
	public ResponseEntity<Response<Bank>> createBank(@RequestBody Bank bankForm, BindingResult result) {
		
		Response<Bank> response = new Response<Bank>();
		
		validateBank(bankForm,result);
		
		if (result.hasErrors()) {
			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(bankService.save(bankForm));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<Bank>> listBanks() {		
		
		return ResponseEntity.ok(bankService.findAll());
	}

	
	private void validateBank(Bank bank, BindingResult result) {
		
		Optional<Bank> bankValid = bankService.findByNameContainingIgnoreCase(bank.getName());
		
		if (bankValid.isPresent()) {		
			
				result.addError(new ObjectError("Bank", "Bank already registered"));	
				
		}
		
	}
	

	
	
}
