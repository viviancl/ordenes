package com.altioracorp.ordenes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.altioracorp.ordenes.model.Cliente;
import com.altioracorp.ordenes.repository.ClienteRepository;
import com.altioracorp.ordenes.service.ClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	protected JpaRepository<Cliente, Integer> getRepo() {
		return clienteRepo;
	}

}
