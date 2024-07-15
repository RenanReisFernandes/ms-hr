package com.renan.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	private String nome;
	private Double rendimentoDiario;
	private Integer dias;
	
	public double getTotal() {
		return rendimentoDiario * dias;
	}

}
