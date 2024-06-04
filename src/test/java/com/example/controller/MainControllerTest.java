package com.example.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.models.Customer;
import com.example.models.Customers;
import com.example.repo.CustomerRepository;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private CustomerService customerService;
	
	@InjectMocks
	private MainController mainController;
	
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
	void testRead() throws Exception{
		Customers customers =new Customers(Arrays.asList(customer1,customer2));
		when(customerService.getAll()).thenReturn(customers);
		
		mockMvc.perform(get("/customer/getall"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customerList[0].userName").value("myUserName"))
			.andExpect(jsonPath("$.customerList[1].userName").value("yourUserName"));
	}
	
	@Test
	void testAdd() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		String teamJson = objectMapper.writeValueAsString(customer1);
		
		mockMvc.perform(post("/customer/add")
			.contentType(MediaType.APPLICATION_JSON)
			.content(teamJson))
			.andExpect(status().isOk());
    		verify(customerService).add(customer1);
	}
	
	@Test
	void testReadOne() throws Exception{
//		Customers customers = new Customers(Arrays.asList(customer1));
		when(customerService.getone(1)).thenReturn(customer1);
		
		mockMvc.perform(get("/customer/getone/1"))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.address").value("Kadugodi"));
	}
	
	@Test
	void testUpdate() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		String customerJson = objectMapper.writeValueAsString(customer1);
		
		mockMvc.perform(put("/customer/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(customerJson))
			.andExpect(status().isOk());
		verify(customerService).update(1,customer1);
	}
	
	@Test
	void testDelete() throws Exception{
		mockMvc.perform(delete("/customer/delete/1"))
			.andExpect(status().isOk());
		verify(customerService).delete(1);
	}
		
}
