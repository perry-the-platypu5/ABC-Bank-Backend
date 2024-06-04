package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Customer;
import com.example.models.Customers;
import com.example.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository cr;
	
	public Customers getAll() {
		Customers cs = new Customers(cr.findAll());
		return cs;
	}
	
	public void add(Customer c) {
		cr.save(c);
	}
	
	public Customer getone(int id) {
		Customer c = cr.findById(id).get();
		return c;
	}
	
	public Customer update(int id, Customer cnew) {
		Customer c = cr.findById(id).get();
		c.setAddress(cnew.getAddress());
		c.setAge(cnew.getAge());
		c.setUserName(cnew.getUserName());
		c.setEmail(cnew.getEmail());
		c.setPhoneNo(cnew.getPhoneNo());
		cr.save(c);
		return c;
	}
	
	public void delete(int id) {
		cr.deleteById(id);
	}
}
