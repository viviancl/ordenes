package com.altioracorp.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.altioracorp.ordenes.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
}
