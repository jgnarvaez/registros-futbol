package com.jgnarvaez.registros_futbol_backend.service;
import java.util.List;
import com.jgnarvaez.registros_futbol_backend.model.EquipoEntity;

public interface EquipoService {
    List<EquipoEntity> obtenerEquipos();
    EquipoEntity obtenerEquipoId(Integer id);
    EquipoEntity crearEquipo(EquipoEntity equipo);
    EquipoEntity actualizarEquipo(Integer id, EquipoEntity equipo);
    void eliminarEquipo(Integer id);
}
