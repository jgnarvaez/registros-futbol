package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.repositories.EquipoRepository;
import com.jgnarvaez.registros_futbol_backend.services.DTO.EquipoDTO;

import jakarta.transaction.Transactional;

@Service
public class EquipoServiceImpl implements IEquipoService {

    @Autowired
    private EquipoRepository servicioAccesoBaseDatos;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EquipoDTO> obtenerEquipos() {
        List<EquipoEntity> equiposEntity = (List<EquipoEntity>) this.servicioAccesoBaseDatos.findAll();
        List<EquipoDTO> equiposDTO = this.modelMapper.map(equiposEntity, new TypeToken<List<EquipoDTO>>() {
        }.getType());
        return equiposDTO;
        // List<EquipoEntity> equipos = servicioAccesoBaseDatos.obtenerEquipos();
        // return equipos.stream()
        // .map(equipo -> modelMapper.map(equipo, EquipoDTO.class))
        // .toList();
    }

    @Override
    public EquipoDTO obtenerEquipoPorCodigo(String codigo) {
        Optional<EquipoEntity> objEquipoEntity = this.servicioAccesoBaseDatos.findById(codigo);
        EquipoDTO objEquipoDTO = this.modelMapper.map(objEquipoEntity, EquipoDTO.class);
        return objEquipoDTO;
        // EquipoEntity equipo = servicioAccesoBaseDatos.obtenerEquipoPorId(codigo);
        // return modelMapper.map(equipo, EquipoDTO.class);
    }

    @Override
    @Transactional
    public EquipoDTO crearEquipo(EquipoDTO equipo) {
        EquipoEntity equipoEntity = this.modelMapper.map(equipo, EquipoEntity.class);
        EquipoEntity objEquipoEntity = this.servicioAccesoBaseDatos.save(equipoEntity);
        EquipoDTO objEquipoDTO = this.modelMapper.map(objEquipoEntity, EquipoDTO.class);
        return objEquipoDTO;
        // EquipoEntity equipo = modelMapper.map(equipoDTO, EquipoEntity.class);
        // EquipoEntity nuevoEquipo = servicioAccesoBaseDatos.crearEquipo(equipo);
        // return modelMapper.map(nuevoEquipo, EquipoDTO.class);
    }

    @Override
    @Transactional
    public EquipoDTO actualizarEquipo(String codigo, EquipoDTO equipo) {
        EquipoEntity equipoEntity = this.modelMapper.map(equipo, EquipoEntity.class);
        EquipoEntity equipoEntityActualizado = this.servicioAccesoBaseDatos.update(codigo, equipoEntity);
        EquipoDTO objEquipoDTO = this.modelMapper.map(equipoEntityActualizado, EquipoDTO.class);
        return objEquipoDTO;
        // EquipoEntity equipo = modelMapper.map(equipoDTO, EquipoEntity.class);
        // EquipoEntity equipoActualizado = servicioAccesoBaseDatos.actualizarEquipo(codigo, equipo);
        // return modelMapper.map(equipoActualizado, EquipoDTO.class);
    }

    @Override
    @Transactional
    public void eliminarEquipo(String codigo) {
        this.servicioAccesoBaseDatos.deleteById(codigo);
        // return servicioAccesoBaseDatos.eliminarEquipo(codigo);
    }
}
