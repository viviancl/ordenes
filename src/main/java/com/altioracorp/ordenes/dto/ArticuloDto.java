package com.altioracorp.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {

	private Integer idArticulo;

	private String codigo;

	private String nombre;
	
	private double precioUnitario;

}
