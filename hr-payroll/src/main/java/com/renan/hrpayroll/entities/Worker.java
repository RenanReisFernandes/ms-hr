package com.renan.hrpayroll.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Long id;
	private Long agency;
	private Long account;
	private String cpf;
	private String name;
	private String address;
	private Double dailyIncome;

}
