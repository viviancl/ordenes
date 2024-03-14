package com.altioracorp.ordenes.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altioracorp.ordenes.exception.OrdenesClientesException;
import com.altioracorp.ordenes.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID>{

	protected abstract JpaRepository<T, ID> getRepo();
	
	@Override
	public T registrar(T t) throws OrdenesClientesException {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) throws OrdenesClientesException {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() throws OrdenesClientesException {
		return getRepo().findAll();
	}

	@Override
	public T listarPorId(ID id) throws OrdenesClientesException {
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public void eliminar(ID id) throws OrdenesClientesException {
		getRepo().deleteById(id);
	}

}

