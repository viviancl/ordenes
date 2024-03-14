package com.altioracorp.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altioracorp.ordenes.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
