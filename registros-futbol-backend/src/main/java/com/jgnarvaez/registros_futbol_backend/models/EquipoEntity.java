package com.jgnarvaez.registros_futbol_backend.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(unique = true, length = 3)
    private String codigoEquipo;

    private String nombre;

    private String pais;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private Integer anioFundacion;

    private Double presupuesto;

    @OneToMany(mappedBy = "equipo") // Relación OneToMany con FutbolistaEntity
    @JsonManagedReference // Esta anotación indica "la parte principal" de la relación
    private List<FutbolistaEntity> futbolistas; // Lista de futbolistas del equipo
}
