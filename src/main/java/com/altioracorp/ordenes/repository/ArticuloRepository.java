package com.altioracorp.ordenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altioracorp.ordenes.dto.ArticuloDto;
import com.altioracorp.ordenes.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
}
