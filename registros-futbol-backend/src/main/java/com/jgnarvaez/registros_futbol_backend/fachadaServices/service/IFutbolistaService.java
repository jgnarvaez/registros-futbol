package com.jgnarvaez.registros_futbol_backend.fachadaServices.service;
import java.util.List;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO.FutbolistaDTO;

public interface IFutbolistaService {
    
    List<FutbolistaDTO> obtenerFutbolistas();
    FutbolistaDTO obtenerFutbolistaPorId(Integer id);
    FutbolistaDTO crearFutbolista(FutbolistaDTO futbolista);
    FutbolistaDTO actualizarFutbolista(Integer id, FutbolistaDTO futbolista);
    boolean eliminarFutbolista(Integer id);
}
