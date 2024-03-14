package com.altioracorp.ordenes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "articulo", uniqueConstraints = {
        @UniqueConstraint(name = "uk_codigo", columnNames = { "codigo" }) })
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticulo;

    private String codigo;

    private String nombre;

    private double precioUnitario;

}
