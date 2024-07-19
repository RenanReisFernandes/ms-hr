package com.renan.hrpayroll.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Double rendimentoDiario;
	private Integer dias;
	
	public double getTotal() {
		return rendimentoDiario * dias;
	}

}
