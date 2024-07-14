
package com.renan.hrworker.DTO.request;

import org.hibernate.validator.constraints.br.CPF;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
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
public class WorkerRequest {
	
	@NotBlank
	private String name;
	@NotBlank
	private String address;
	@NonNull
	@CPF
	private String cpf;
	private Double dailyIncome;

}

