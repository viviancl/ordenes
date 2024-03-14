package com.altioracorp.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

	private Integer idCliente;

	private String nombre;

	private String apellido;	

}
