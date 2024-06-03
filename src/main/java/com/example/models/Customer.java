package com.example.models;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Auto increment
	private int id;
	@Column(unique=true,nullable=false)
	private String userName;
	@Column(nullable=false)
	private String password;
	private String address;
	private String email;
	private String phoneNo;
	private int age;
	private double balance;
	@OneToMany(mappedBy="cid",fetch=FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
}	
