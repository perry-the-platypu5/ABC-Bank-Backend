package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Customer;
import com.example.models.Transaction;
import com.example.models.Transactions;
import com.example.repo.CustomerRepository;
import com.example.repo.TransactionRepository;

@RestController
@RequestMapping("/transaction")
public class TransController {
	
	@Autowired
	TransactionRepository tr;
	
	@Autowired
	CustomerRepository cr;
	
	@CrossOrigin("http://localhost:8888")
	@RequestMapping(value="/get/{id}")
	public Transactions get(@PathVariable int id) {
		Transactions ts = new Transactions();
		ts.setCid(id);
		ts.setTransactionList(tr.filterByCid(cr.findById(id).get()));
		return ts;
	}
	
	@CrossOrigin("http://localhost:8888")
	@PostMapping(value="/withdraw/{id}")
	public void deposit(@PathVariable int id,@RequestBody Transaction t) {
		Customer c = cr.findById(id).get();
		System.out.println(c.getUserName());
		System.out.println(t.getAmount());
		if(c.getBalance()<t.getAmount()||t.getAmount()<0) {
			return;
			}
		c.setBalance(c.getBalance()-t.getAmount());
		t.setCid(c);
		t.setCredit(false);
		t.setDt(LocalDateTime.now());
		tr.save(t);
		cr.save(c);
	}
	@CrossOrigin("http://localhost:8888")
	@PostMapping(value="/deposit/{id}")
	public void withdraw(@PathVariable int id, @RequestBody Transaction t) {
		Customer c = cr.findById(id).get();
		if(t.getAmount()<0) {
			return;
			}
		c.setBalance(c.getBalance()+t.getAmount());
		t.setCredit(true);
		t.setCid(c);
		t.setDt(LocalDateTime.now());
		tr.save(t);
		cr.save(c);

	}
}
