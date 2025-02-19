package com.jgnarvaez.registros_futbol_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;

public interface EquipoRepository extends CrudRepository<EquipoEntity, String>{
    
}
