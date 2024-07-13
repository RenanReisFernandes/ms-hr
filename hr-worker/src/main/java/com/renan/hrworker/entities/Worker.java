package com.renan.hrworker.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Worker {
	
	private Long id;
	private Long agency;
	private Long account;
	private String cpf;
	private String name;
	private String address;
	private Double dailyIncome;

}
