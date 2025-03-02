package com.jgnarvaez.registros_futbol_backend.models;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FutbolistaEntity {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    @JsonBackReference // Esta anotación indica "la parte inversa" de la relación
    private EquipoEntity equipo;

    private String codigoEquipo;
    
    private Integer edad;

    private Integer golesAnotadosPorTemporada;
    
    private String nacionalidad;
    
    @Enumerated(EnumType.STRING)
    private PosicionEnum posicion;
    
    private Boolean lesiones;
}
