package com.altioracorp.ordenes.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altioracorp.ordenes.dto.OrdenDto;
import com.altioracorp.ordenes.exception.ModeloNotFoundException;
import com.altioracorp.ordenes.exception.OrdenesClientesException;
import com.altioracorp.ordenes.model.Orden;
import com.altioracorp.ordenes.service.OrdenService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ordenes")
public class OrdenController {

	public static final String ID_NO_ENCONTRADO = "ID NO ENCONTRADO ";

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrdenService ordenService;

	@GetMapping
	public ResponseEntity<List<OrdenDto>> listar() throws OrdenesClientesException {
		List<OrdenDto> lista = ordenService.listar().stream().map(p -> mapper.map(p, OrdenDto.class))
				.toList();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrdenDto> listarPorId(@PathVariable("id") Integer id) throws OrdenesClientesException {
		OrdenDto dtoResponse;
		Orden obj = ordenService.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		} else {
			dtoResponse = mapper.map(obj, OrdenDto.class); //
		}

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrdenDto> registrar(@Valid @RequestBody OrdenDto dtoRequest)
			throws OrdenesClientesException {
		Orden a = mapper.map(dtoRequest, Orden.class);
		Orden obj = ordenService.registrar(a);
		OrdenDto dtoResponse = mapper.map(obj, OrdenDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<OrdenDto> modificar(@RequestBody OrdenDto dtoRequest) throws OrdenesClientesException {
		Orden orden = ordenService.listarPorId(dtoRequest.getIdOrden());

		if (orden == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + dtoRequest.getIdOrden());
		}

		Orden a = mapper.map(dtoRequest, Orden.class);
		Orden obj = ordenService.modificar(a);
		OrdenDto dtoResponse = mapper.map(obj, OrdenDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws OrdenesClientesException {
		Orden orden = ordenService.listarPorId(id);

		if (orden == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		}
		ordenService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// @GetMapping("/ordenes/{idOrden}")
	// public ResponseEntity<List<OrdenDto>> listarPorOrdenId(@PathVariable("idOrden") Integer id)
	// 		throws OrdenesClientesException {
	// 	List<OrdenDto> lista = ordenService.findByIdOrden(id);
	// 	return new ResponseEntity<>(lista, HttpStatus.OK);
	// }

}