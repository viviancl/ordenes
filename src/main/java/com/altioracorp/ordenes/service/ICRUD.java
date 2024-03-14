package com.altioracorp.ordenes.service;

import java.util.List;

import com.altioracorp.ordenes.exception.OrdenesClientesException;

public interface ICRUD<T, ID> {

	T registrar(T t) throws OrdenesClientesException;

	T modificar(T t) throws OrdenesClientesException;

	List<T> listar() throws OrdenesClientesException;

	T listarPorId(ID id) throws OrdenesClientesException;

	void eliminar(ID id) throws OrdenesClientesException;
}
