package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.models.Customer;
import com.example.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	@Query(value="select t from Transaction t where t.cid=?1")
	public List<Transaction> filterByCid(Customer id);
}
