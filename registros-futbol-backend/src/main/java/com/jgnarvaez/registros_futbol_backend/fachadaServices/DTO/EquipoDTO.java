package com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    
    private String codigoEquipo;
    private String nombre;
    private String pais;
    private String categoria;
    private Integer anioFundacion;
    private Double presupuesto;
}
