package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.models.PosicionEnum;
import com.jgnarvaez.registros_futbol_backend.repositories.FutbolistaRepository;
import com.jgnarvaez.registros_futbol_backend.services.DTO.EquipoDTO;
import com.jgnarvaez.registros_futbol_backend.services.DTO.FutbolistaDTO;

@Service
public class FutbolistaServiceImpl implements IFutbolistaService {

    @Override
    public List<FutbolistaDTO> obtenerFutbolistas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerFutbolistas'");
    }

    @Override
    public FutbolistaDTO obtenerFutbolistaPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerFutbolistaPorId'");
    }

    @Override
    public FutbolistaDTO crearFutbolista(FutbolistaDTO futbolista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearFutbolista'");
    }

    @Override
    public FutbolistaDTO actualizarFutbolista(Integer id, FutbolistaDTO futbolista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarFutbolista'");
    }

    @Override
    public boolean eliminarFutbolista(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarFutbolista'");
    }

    
}
