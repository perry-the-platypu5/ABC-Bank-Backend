package com.example.models;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Auto increment
	private long transId;
	@Column(nullable=false)
	private float amount;
	@Column(nullable=false)
	private boolean isCredit;
	@Column(nullable=false)
	private LocalDateTime dt;
	@ManyToOne
	@JsonBackReference
	private Customer cid;
}
