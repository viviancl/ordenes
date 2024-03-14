package com.altioracorp.ordenes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orden_articulo")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenArticulo;
    
    @Column(name = "id_orden")
    private Long idOrden;
    
    @Column(name = "id_articulo")
    private Long idArticulo;

}
