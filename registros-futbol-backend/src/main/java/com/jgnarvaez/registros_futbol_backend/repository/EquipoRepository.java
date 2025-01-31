package com.jgnarvaez.registros_futbol_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jgnarvaez.registros_futbol_backend.model.EquipoEntity;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoEntity, Integer>{
    
}
