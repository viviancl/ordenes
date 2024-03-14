package com.altioracorp.ordenes.dto;

import java.time.LocalDate;
import java.util.List;

import com.altioracorp.ordenes.model.Articulo;
import com.altioracorp.ordenes.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDto {

	private Integer idOrden;

	private String codigo;

	private LocalDate fecha;
	
	private Cliente cliente;

	private List<Articulo> articulos;

}
