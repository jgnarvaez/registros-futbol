package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;

import com.jgnarvaez.registros_futbol_backend.services.DTO.EquipoDTO;

public interface IEquipoService {
    
    List<EquipoDTO> obtenerEquipos();
    EquipoDTO obtenerEquipoPorCodigo(String codigo);
    EquipoDTO crearEquipo(EquipoDTO equipo);
    EquipoDTO actualizarEquipo(String codigo, EquipoDTO equipo);
    boolean eliminarEquipo(String codigo);
}
