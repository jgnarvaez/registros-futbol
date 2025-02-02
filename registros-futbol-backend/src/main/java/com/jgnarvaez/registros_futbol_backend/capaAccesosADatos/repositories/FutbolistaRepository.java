package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.FutbolistaEntity;

@Repository
public interface FutbolistaRepository extends JpaRepository<FutbolistaEntity, Integer>{

}