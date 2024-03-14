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

import com.altioracorp.ordenes.dto.ArticuloDto;
import com.altioracorp.ordenes.exception.ModeloNotFoundException;
import com.altioracorp.ordenes.exception.OrdenesClientesException;
import com.altioracorp.ordenes.model.Articulo;
import com.altioracorp.ordenes.service.ArticuloService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/articulos")
public class ArticuloController {

	public static final String ID_NO_ENCONTRADO = "ID NO ENCONTRADO ";

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ArticuloService articuloService;

	@GetMapping
	public ResponseEntity<List<ArticuloDto>> listar() throws OrdenesClientesException {
		List<ArticuloDto> lista = articuloService.listar().stream().map(p -> mapper.map(p, ArticuloDto.class))
				.toList();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArticuloDto> listarPorId(@PathVariable("id") Integer id) throws OrdenesClientesException {
		ArticuloDto dtoResponse;
		Articulo obj = articuloService.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		} else {
			dtoResponse = mapper.map(obj, ArticuloDto.class); //
		}

		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ArticuloDto> registrar(@Valid @RequestBody ArticuloDto dtoRequest)
			throws OrdenesClientesException {
		Articulo a = mapper.map(dtoRequest, Articulo.class);
		Articulo obj = articuloService.registrar(a);
		ArticuloDto dtoResponse = mapper.map(obj, ArticuloDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ArticuloDto> modificar(@RequestBody ArticuloDto dtoRequest) throws OrdenesClientesException {
		Articulo articulo = articuloService.listarPorId(dtoRequest.getIdArticulo());

		if (articulo == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + dtoRequest.getIdArticulo());
		}

		Articulo a = mapper.map(dtoRequest, Articulo.class);
		Articulo obj = articuloService.modificar(a);
		ArticuloDto dtoResponse = mapper.map(obj, ArticuloDto.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws OrdenesClientesException {
		Articulo articulo = articuloService.listarPorId(id);

		if (articulo == null) {
			throw new ModeloNotFoundException(ID_NO_ENCONTRADO + id);
		}
		articuloService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}