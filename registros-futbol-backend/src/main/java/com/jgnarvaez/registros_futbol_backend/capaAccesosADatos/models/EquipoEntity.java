package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, length = 3)
    private String codigoEquipo;

    private String nombre;

    private String pais;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private Integer anioFundacion;

    private Double presupuesto;
}
