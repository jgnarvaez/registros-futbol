package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "equipo") // Relación OneToMany con FutbolistaEntity
    private List<FutbolistaEntity> futbolistas; // Lista de futbolistas del equipo
}
