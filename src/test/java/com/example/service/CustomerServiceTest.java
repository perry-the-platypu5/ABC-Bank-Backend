package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.models.Customer;
import com.example.models.Customers;
import com.example.repo.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerService CustomerService;
	
	private Customer customer1;
	private Customer customer2;
	
	@BeforeEach
	void setUp() {
		customer1 = new Customer();
		customer1.setAddress("Kadugodi");
		customer1.setAge(23);
		customer1.setBalance(100.25);
		customer1.setEmail("mymail@dom.com");
		customer1.setId(1);
		customer1.setPassword("1234");
		customer1.setPhoneNo("9876543210");
		customer1.setUserName("myUserName");
		
		customer2 = new Customer();
		customer2.setAddress("Kengeri");
		customer2.setAge(22);
		customer2.setBalance(100000.25);
		customer2.setEmail("yourMail@bmd.com");
		customer2.setId(2);
		customer2.setPassword("4325");
		customer2.setPhoneNo("9877854123");
		customer2.setUserName("yourUserName");
	}
	
	@Test
	void testRead() { 
		List<Customer> Customers = Arrays.asList(customer1, customer2);
		when(customerRepository.findAll()).thenReturn(Customers);
		
		Customers result = CustomerService.getAll();
		assertEquals(2, result.getCustomerList().size());
		assertEquals("myUserName",result.getCustomerList().get(0).getUserName());
		assertEquals("yourUserName",result.getCustomerList().get(1).getUserName());
	}
	
	@Test
	void testAdd() {
		CustomerService.add(customer1);
		verify(customerRepository).save(customer1);
	}
	
	@Test
	void testUpdate() {
		Customer updatedCustomer = new Customer();
		updatedCustomer = new Customer();
		updatedCustomer.setAddress("Kangari");
		updatedCustomer.setAge(23);
		updatedCustomer.setBalance(100000.25);
		updatedCustomer.setEmail("yourMail@bmd.com");
		updatedCustomer.setId(2);
		updatedCustomer.setPassword("43256");
		updatedCustomer.setPhoneNo("9877854123");
		updatedCustomer.setUserName("updatedyourUserName");
		
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
		CustomerService.update(1, updatedCustomer);
		assertEquals("updatedyourUserName",updatedCustomer.getUserName());
		assertEquals("43256",updatedCustomer.getPassword());
		assertEquals(23,updatedCustomer.getAge());
		assertEquals("Kangari",updatedCustomer.getAddress());
		verify(customerRepository).save(customer1);
	}
	
	@Test
	void testDelete() {
		CustomerService.delete(1);
		verify(customerRepository).deleteById(1);
	}
}

