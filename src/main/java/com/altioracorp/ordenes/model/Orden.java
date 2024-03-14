package com.altioracorp.ordenes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "orden", uniqueConstraints = {
    @UniqueConstraint(name = "uk_codigo", columnNames = { "codigo" }) })
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    private String codigo;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
	@JoinTable(name = "orden_articulo", joinColumns = @JoinColumn(name = "id_orden", referencedColumnName = "idOrden"), inverseJoinColumns = @JoinColumn(name = "id_articulo", referencedColumnName = "idArticulo"))
	private List<Articulo> articulos;
    
}

