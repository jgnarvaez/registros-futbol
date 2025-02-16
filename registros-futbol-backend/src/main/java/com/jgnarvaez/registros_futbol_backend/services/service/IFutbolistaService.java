package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;

import com.jgnarvaez.registros_futbol_backend.services.DTO.FutbolistaDTO;

public interface IFutbolistaService {
    
    List<FutbolistaDTO> obtenerFutbolistas();
    FutbolistaDTO obtenerFutbolistaPorId(Integer id);
    FutbolistaDTO crearFutbolista(FutbolistaDTO futbolista);
    FutbolistaDTO actualizarFutbolista(Integer id, FutbolistaDTO futbolista);
    boolean eliminarFutbolista(Integer id);
}
