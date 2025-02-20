package com.jgnarvaez.registros_futbol_backend.services.service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadNoExisteException;
import com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions.EntidadYaExisteException;
import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.models.PosicionEnum;
import com.jgnarvaez.registros_futbol_backend.repositories.EquipoRepository;
import com.jgnarvaez.registros_futbol_backend.repositories.FutbolistaRepository;
import com.jgnarvaez.registros_futbol_backend.services.DTO.FutbolistaDTO;

@Service
public class FutbolistaServiceImpl implements IFutbolistaService {

    @Autowired
    private FutbolistaRepository servicioAccesoBaseDatos;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<FutbolistaDTO> obtenerFutbolistas() {
        Iterable<FutbolistaEntity> futbolistasEntityIterable = this.servicioAccesoBaseDatos.findAll();
        System.out.println("antes de la consulta");
        List<FutbolistaDTO> futbolistasDTO = this.modelMapper.map(futbolistasEntityIterable, new TypeToken<List<FutbolistaDTO>> () {
        }.getType());
        return futbolistasDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public FutbolistaDTO obtenerFutbolistaPorId(Integer id) {
        Optional<FutbolistaEntity> optional = this.servicioAccesoBaseDatos.findById(id);
        FutbolistaEntity user = optional.get();
        System.out.println("antes de la consulta");
        FutbolistaDTO futbolistaDTO = this.modelMapper.map(user, FutbolistaDTO.class);
        return futbolistaDTO;
    }

    @Override
    @Transactional
    public FutbolistaDTO crearFutbolista(FutbolistaDTO futbolista) {
        System.out.println("Almacenando futbolista: " + futbolista.getNombre());
        if (this.servicioAccesoBaseDatos.existsById(futbolista.getId())) {
            throw new EntidadYaExisteException("Futbolista con ID " + futbolista.getId() + " ya existe en la BD");
        }

        FutbolistaEntity futbolistaEntity = this.modelMapper.map(futbolista, FutbolistaEntity.class);

        // Obtener el equipo al que pertenece el futbolista
        EquipoEntity equipoEntity = this.equipoRepository.findById(futbolista.getCodigoEquipo())
              .orElseThrow(() -> new EntidadNoExisteException("Equipo con código " + futbolista.getCodigoEquipo() + " no encontrado en la BD"));
        
        // Establecer la relación con el equipo
        futbolistaEntity.setEquipo(equipoEntity);

        // Agregar el futbolista a la lista de futbolistas del equipo
        equipoEntity.getFutbolistas().add(futbolistaEntity);

        FutbolistaEntity nuevoFutbolistaEntity = this.servicioAccesoBaseDatos.save(futbolistaEntity);
        return this.modelMapper.map(nuevoFutbolistaEntity, FutbolistaDTO.class);
    }

    @Override
    @Transactional(readOnly = false)
    public FutbolistaDTO actualizarFutbolista(Integer id, FutbolistaDTO futbolista) {
        Optional<FutbolistaEntity> optional = this.servicioAccesoBaseDatos.findById(id);
        FutbolistaDTO futbolistaDTOActualizado = null;
        FutbolistaEntity objFutbolistaAlmacenado = null;

        if (optional.isPresent()) {
            objFutbolistaAlmacenado = optional.get();

            // Actualizar los datos del futbolista uno por uno
            objFutbolistaAlmacenado.setId(futbolista.getId());
            objFutbolistaAlmacenado.setNombre(futbolista.getNombre());
            objFutbolistaAlmacenado.setCodigoEquipo(futbolista.getCodigoEquipo());
            objFutbolistaAlmacenado.setEdad(futbolista.getEdad());
            objFutbolistaAlmacenado.setGolesAnotadosPorTemporada(futbolista.getGolesAnotadosPorTemporada());
            objFutbolistaAlmacenado.setNacionalidad(futbolista.getNacionalidad());
            objFutbolistaAlmacenado.setPosicion(PosicionEnum.valueOf(futbolista.getPosicion())); // Convertir String a PosicionEnum
            objFutbolistaAlmacenado.setLesiones(futbolista.getLesiones());

            // Actualizar la referencia al equipo en el futbolista
            EquipoEntity equipoEntity = this.equipoRepository.findById(futbolista.getCodigoEquipo())
                  .orElseThrow(() -> new EntidadNoExisteException("Equipo con código " + futbolista.getCodigoEquipo() + " no encontrado en la BD"));
            objFutbolistaAlmacenado.setEquipo(equipoEntity);

            FutbolistaEntity futbolistaEntityActualizado = this.servicioAccesoBaseDatos.save(objFutbolistaAlmacenado);
            futbolistaDTOActualizado = this.modelMapper.map(futbolistaEntityActualizado, FutbolistaDTO.class);
        } else {
            throw new EntidadNoExisteException("Futbolista con ID " + id + " no encontrado en la BD");
        }

        return futbolistaDTOActualizado;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean eliminarFutbolista(Integer id) {
        if (!this.servicioAccesoBaseDatos.existsById(id)) {
            throw new EntidadNoExisteException("Futbolista con ID " + id + " no encontrado en la BD");
        }
        try {
            this.servicioAccesoBaseDatos.deleteById(id);
            return true; // Se eliminó correctamente
        } catch (Exception e) {
            return false; // Ocurrió un error al eliminar
        }
    }
}
