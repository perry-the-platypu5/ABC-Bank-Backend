package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query(value="select id from Customer c where c.userName=?1")
	public Integer getIdByUserName(String userName);

}
