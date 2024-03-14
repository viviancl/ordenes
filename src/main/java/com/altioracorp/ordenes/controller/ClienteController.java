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

import com.altioracorp.ordenes.dto.ClienteDto;
import com.altioracorp.ordenes.exception.ModeloNotFoundException;
import com.altioracorp.ordenes.exception.OrdenesClientesException;
import com.altioracorp.ordenes.model.Cliente;
import com.altioracorp.ordenes.service.ClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	public static final String ID_NO_ENCONTRADO = "ID NO ENCONTRADO ";

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDto>> listar() throws OrdenesClientesException {
		List<ClienteDto> lista = clienteService.listar().stream().map(p -> mapper.map(p, ClienteDto.class))
				.toList();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> listarPorId(@PathVariable("id") Integer id) throws OrdenesClientesException {
		ClienteDto dtoResponse;
		Cliente obj = clienteService.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		} else {
			dtoResponse = mapper.map(obj, ClienteDto.class); //
		}

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDto> registrar(@Valid @RequestBody ClienteDto dtoRequest) throws OrdenesClientesException {
		Cliente c = mapper.map(dtoRequest, Cliente.class);
		Cliente obj = clienteService.registrar(c);
		ClienteDto dtoResponse = mapper.map(obj, ClienteDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ClienteDto> modificar(@RequestBody ClienteDto dtoRequest) throws OrdenesClientesException {
		Cliente cliente = clienteService.listarPorId(dtoRequest.getIdCliente());

		if (cliente == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + dtoRequest.getIdCliente());
		}

		Cliente c = mapper.map(dtoRequest, Cliente.class);
		Cliente obj = clienteService.modificar(c);
		ClienteDto dtoResponse = mapper.map(obj, ClienteDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws OrdenesClientesException {
		Cliente cliente = clienteService.listarPorId(id);

		if (cliente == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		}
		clienteService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}