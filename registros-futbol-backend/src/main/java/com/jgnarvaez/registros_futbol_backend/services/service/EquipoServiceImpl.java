package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.ArrayList;
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
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadNoExisteException;
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadYaExisteException;
import com.jgnarvaez.registros_futbol_backend.models.CategoriaEnum;
import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
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
    
    // Primero verifica si el Optional contiene un valor antes de llamar a get()
    if (optional.isPresent()) {
        EquipoEntity objEquipoAlmacenado = optional.get();
        
        objEquipoAlmacenado.setCodigoEquipo(objEquipoConDatosNuevos.getCodigoEquipo());
        objEquipoAlmacenado.setNombre(objEquipoConDatosNuevos.getNombre());
        objEquipoAlmacenado.setPais(objEquipoConDatosNuevos.getPais());
        objEquipoAlmacenado.setCategoria(CategoriaEnum.valueOf(objEquipoConDatosNuevos.getCategoria()));
        objEquipoAlmacenado.setAnioFundacion(objEquipoConDatosNuevos.getAnioFundacion());
        objEquipoAlmacenado.setPresupuesto(objEquipoConDatosNuevos.getPresupuesto());
        
        // Verifica si la lista de futbolistas es null
        if (objEquipoConDatosNuevos.getFutbolistas() != null) {
            // Actualizar la relación con los futbolistas uno por uno
            List<FutbolistaEntity> futbolistasActualizados = objEquipoConDatosNuevos.getFutbolistas().stream()
                .map(futbolistaDTO -> {
                    FutbolistaEntity futbolistaEntity = null;
                    
                    // Verifica si la lista de futbolistas del equipo almacenado es null
                    if (objEquipoAlmacenado.getFutbolistas() != null) {
                        futbolistaEntity = objEquipoAlmacenado.getFutbolistas().stream()
                            .filter(f -> f.getId() != null && f.getId().equals(futbolistaDTO.getId()))
                            .findFirst()
                            .orElse(new FutbolistaEntity());
                    } else {
                        futbolistaEntity = new FutbolistaEntity();
                    }
                    
                    futbolistaEntity.setId(futbolistaDTO.getId());
                    futbolistaEntity.setNombre(futbolistaDTO.getNombre());
                    futbolistaEntity.setEquipo(objEquipoAlmacenado); // Establece relación con el equipo actual
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
        } else {
            // Si no hay futbolistas en la solicitud, mantener los existentes o establecer una lista vacía
            if (objEquipoAlmacenado.getFutbolistas() == null) {
                objEquipoAlmacenado.setFutbolistas(new ArrayList<>());
            }
        }

        EquipoEntity equipoEntityActualizado = this.servicioAccesoBaseDatos.save(objEquipoAlmacenado);
        equipoDTOActualizado = this.modelMapper.map(equipoEntityActualizado, EquipoDTO.class);
    } else {
        // Manejo de error cuando no se encuentra el equipo
        throw new RuntimeException("No se encontró el equipo con código: " + codigo);
    }

    return equipoDTOActualizado;
}

    @Override
    @Transactional(readOnly = false)
    public boolean eliminarEquipo(String codigo) {
        boolean bandera = false;
        Optional<EquipoEntity> optional = this.servicioAccesoBaseDatos.findById(codigo);
        if (!optional.isPresent()) {
            EntidadNoExisteException objException = new EntidadNoExisteException(
                "Cliente con id " + codigo + "no existe en la BD");
            throw objException;
        } else {
            EquipoEntity user = optional.get();
            this.servicioAccesoBaseDatos.delete(user);
            bandera = true;
        }

        return bandera;
    }
}
