package com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FutbolistaDTO {
    
    private Integer id;
    private String nombre;
    private String equipoId;
    private Integer edad;
    private Integer golesAnotadosPorTemporada;
    private String nacionalidad;
    private String posicion;
    private Boolean lesiones;
}
