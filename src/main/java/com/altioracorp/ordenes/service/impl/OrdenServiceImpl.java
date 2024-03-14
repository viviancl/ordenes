package com.altioracorp.ordenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.altioracorp.ordenes.model.Orden;
import com.altioracorp.ordenes.repository.OrdenRepository;
import com.altioracorp.ordenes.service.OrdenService;

@Service
public class OrdenServiceImpl extends CRUDImpl<Orden, Integer> implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepo;

	@Override
	protected JpaRepository<Orden, Integer> getRepo() {
		return ordenRepo;
	}

}
