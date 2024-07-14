
package com.renan.hrworker.DTO.response;

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
public class WorkerResponse {
	
	private Long id;
	private String name;
	private String cpf;
	private String address;
	private Double dailyIncome;
}

