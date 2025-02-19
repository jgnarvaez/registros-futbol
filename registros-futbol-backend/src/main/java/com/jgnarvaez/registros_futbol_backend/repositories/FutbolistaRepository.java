package com.jgnarvaez.registros_futbol_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;

public interface FutbolistaRepository extends CrudRepository<FutbolistaEntity, Integer>{
    
}