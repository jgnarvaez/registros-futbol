package com.jgnarvaez.registros_futbol_backend.service;
import java.util.List;
import com.jgnarvaez.registros_futbol_backend.model.FutbolistaEntity;

public interface FutbolistaService {
    List<FutbolistaEntity> obtenerFutbolistas();
    FutbolistaEntity obtenerFutbolistaId(Integer id);
    FutbolistaEntity crearFutbolista(FutbolistaEntity futbolista);
    FutbolistaEntity actualizarFutbolista(Integer id, FutbolistaEntity futbolista);
    void eliminarFutbolista(Integer id);
}
