package com.altioracorp.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altioracorp.ordenes.model.Orden;


public interface OrdenRepository extends JpaRepository<Orden, Integer> {

}
