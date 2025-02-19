package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.CodigoError;
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadNoExisteException;
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadYaExisteException;
import com.jgnarvaez.registros_futbol_backend.models.CategoriaEnum;
import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.models.PosicionEnum;
import com.jgnarvaez.registros_futbol_backend.repositories.EquipoRepository;
import com.jgnarvaez.registros_futbol_backend.services.DTO.EquipoDTO;

@Service
public class EquipoServiceImpl implements IEquipoService {

    @Autowired
    private EquipoRepository servicioAccesoBaseDatos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> obtenerEquipos() {
        Iterable<EquipoEntity> equiposEntityIterable = this.servicioAccesoBaseDatos.findAll();
        System.out.println("antes de la consulta");
        List<EquipoDTO> equiposDTO = this.modelMapper.map(equiposEntityIterable, new TypeToken<List<EquipoDTO>> () {
        }.getType());
        return equiposDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public EquipoDTO obtenerEquipoPorCodigo(String codigo) {
        Optional<EquipoEntity> optional = this.servicioAccesoBaseDatos.findById(codigo);
        EquipoEntity user = optional.get();
        System.out.println("antes de la consulta");
        EquipoDTO equipoDTO = this.modelMapper.map(user, EquipoDTO.class);
        return equipoDTO;
    }

    @Override
    @Transactional
    public EquipoDTO crearEquipo(EquipoDTO equipo) {
        System.out.println("Almacenando equipo: " + equipo.getNombre());
        if (equipo.getCodigoEquipo() != null) {
            final Boolean bandera = this.servicioAccesoBaseDatos.existsById(equipo.getCodigoEquipo());
            if (bandera) {
                EntidadYaExisteException objException = new EntidadYaExisteException("Equipo con código " + equipo.getCodigoEquipo() + " existe en la BD");
                throw objException;
            }
        }

        EquipoEntity equipoEntity = this.modelMapper.map(equipo, EquipoEntity.class);
        
        if (equipoEntity.getFutbolistas() != null) {
            equipoEntity.getFutbolistas().forEach(objFutbolista -> objFutbolista.setEquipo(equipoEntity));
        }

        EquipoEntity objEquipoEntity = this.servicioAccesoBaseDatos.save(equipoEntity);
        EquipoDTO equipoDTO = this.modelMapper.map(objEquipoEntity, EquipoDTO.class);
        return equipoDTO;
    }

    @Override
    @Transactional(readOnly = false)
    public EquipoDTO actualizarEquipo(String codigo, EquipoDTO objEquipoConDatosNuevos) {
        Optional<EquipoEntity> optional = this.servicioAccesoBaseDatos.findById(codigo);
        EquipoDTO equipoDTOActualizado = null;
        EquipoEntity objEquipoAlmacenado = optional.get();

        if (objEquipoAlmacenado != null) {
            objEquipoAlmacenado.setCodigoEquipo(objEquipoConDatosNuevos.getCodigoEquipo());
            objEquipoAlmacenado.setNombre(objEquipoConDatosNuevos.getNombre());
            objEquipoAlmacenado.setPais(objEquipoConDatosNuevos.getPais());
            objEquipoAlmacenado.setCategoria(CategoriaEnum.valueOf(objEquipoConDatosNuevos.getCategoria()));
            objEquipoAlmacenado.setAnioFundacion(objEquipoConDatosNuevos.getAnioFundacion());
            objEquipoAlmacenado.setPresupuesto(objEquipoConDatosNuevos.getPresupuesto());
            // Actualizar la relación con los futbolistas uno por uno
            List<FutbolistaEntity> futbolistasActualizados = objEquipoConDatosNuevos.getFutbolistas().stream()
                .map(futbolistaDTO -> {
                    FutbolistaEntity futbolistaEntity = objEquipoAlmacenado.getFutbolistas().stream()
                        .filter(f -> f.getId().equals(futbolistaDTO.getId()))
                        .findFirst()
                        .orElse(new FutbolistaEntity());
                    futbolistaEntity.setId(futbolistaDTO.getId());
                    futbolistaEntity.setNombre(futbolistaDTO.getNombre());
                    futbolistaEntity.setEquipo(futbolistaDTO.getEquipo());
                    futbolistaEntity.setCodigoEquipo(objEquipoConDatosNuevos.getCodigoEquipo());
                    futbolistaEntity.setEdad(futbolistaDTO.getEdad());
                    futbolistaEntity.setGolesAnotadosPorTemporada(futbolistaDTO.getGolesAnotadosPorTemporada());
                    futbolistaEntity.setNacionalidad(futbolistaDTO.getNacionalidad());
                    futbolistaEntity.setPosicion(futbolistaDTO.getPosicion());
                    futbolistaEntity.setLesiones(futbolistaDTO.getLesiones());
                    return futbolistaEntity;
                })
                .collect(Collectors.toList());
                objEquipoAlmacenado.setFutbolistas(futbolistasActualizados);

                EquipoEntity equipoEntityActualizado = this.servicioAccesoBaseDatos.save(objEquipoAlmacenado);
                equipoDTOActualizado = this.modelMapper.map(equipoEntityActualizado, EquipoDTO.class);
        }

        return equipoDTOActualizado;
    }

    @Override
    public void eliminarEquipo(String codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEquipo'");
    }
}
