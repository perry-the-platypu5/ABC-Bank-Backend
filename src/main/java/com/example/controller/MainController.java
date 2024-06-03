package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Customer;
import com.example.models.Customers;
import com.example.repo.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class MainController {
	
	@Autowired
	CustomerRepository cr;
	
	@GetMapping("/getall")
	public Customers getAll() {
		Customers cs = new Customers(cr.findAll());
		return cs;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Customer c) {
		cr.save(c);
	}
	
    @CrossOrigin("http://localhost:8888")
	@GetMapping("/getone/{id}")
	public Customer getone(@PathVariable int id) {
		Customer c = cr.findById(id).get();
		return c;
	}
    
    @CrossOrigin("http://localhost:8888")
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable int id, @RequestBody Customer cnew) {
		Customer c = cr.findById(id).get();
		c.setAddress(cnew.getAddress());
		c.setAge(cnew.getAge());
		c.setUserName(cnew.getUserName());
		c.setEmail(cnew.getEmail());
		c.setPhoneNo(cnew.getPhoneNo());
		cr.save(c);
		return c;
	}
    
    @CrossOrigin("http://localhost:8888")
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		cr.deleteById(id);
	}
    
//    @CrossOrigin("http://localhost:8888")
	@PostMapping(value="/customerlogin")
	public Customer customerLogin(@RequestBody Customer c) {
		Integer id = cr.getIdByUserName(c.getUserName());
		if(id == null) {
			return null;
		}
		Optional <Customer> cn = cr.findById(id);
		
		if(c.getPassword().equals(cn.get().getPassword())) {
			return cn.get();
		}
		return null;
	}

}
