package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private EquipoEntity equipo;

    private String codigoEquipo;
    
    private Integer edad;

    private Integer golesAnotadosPorTemporada;
    
    private String nacionalidad;
    
    @Enumerated(EnumType.STRING)
    private PosicionEnum posicion;
    
    private Boolean lesiones;
}
