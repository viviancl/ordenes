package com.altioracorp.ordenes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.altioracorp.ordenes.dto.ArticuloDto;
import com.altioracorp.ordenes.model.Articulo;
import com.altioracorp.ordenes.repository.ArticuloRepository;
import com.altioracorp.ordenes.service.ArticuloService;

@Service
public class ArticuloServiceImpl extends CRUDImpl<Articulo, Integer> implements ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepo;

	@Override
	protected JpaRepository<Articulo, Integer> getRepo() {
		return articuloRepo;
	}

}
