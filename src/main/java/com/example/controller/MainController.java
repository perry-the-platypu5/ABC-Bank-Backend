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
import com.example.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class MainController {
	
	@Autowired
	CustomerRepository cr;
	
	@Autowired
	CustomerService cs;
	
	@GetMapping("/getall")
	public Customers getAll() {
		return cs.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Customer c) {
		cs.add(c);
	}
	
    @CrossOrigin("http://localhost:8888")
	@GetMapping("/getone/{id}")
	public Customer getone(@PathVariable int id) {
		return cs.getone(id);
	}
    
    @CrossOrigin("http://localhost:8888")
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable int id, @RequestBody Customer cnew) {
		return cs.update(id, cnew);
	}
   
    @CrossOrigin("http://localhost:8888")
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		cs.delete(id);
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
