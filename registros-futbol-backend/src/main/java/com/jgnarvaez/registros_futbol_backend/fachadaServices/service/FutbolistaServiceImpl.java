package com.jgnarvaez.registros_futbol_backend.fachadaServices.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.PosicionEnum;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.repositories.FutbolistaRepository;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO.EquipoDTO;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO.FutbolistaDTO;

@Service
public class FutbolistaServiceImpl implements IFutbolistaService {

    @Autowired
    private FutbolistaRepository servicioAccesoBaseDatos;

    @Autowired
    private IEquipoService equipoService; // Inyectar el servicio de equipos

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FutbolistaDTO> obtenerFutbolistas() {
        List<FutbolistaEntity> futbolistasEntity = this.servicioAccesoBaseDatos.obtenerFutbolistas();
        List<FutbolistaDTO> futbolistasDTO = this.modelMapper.map(futbolistasEntity, new TypeToken<List<FutbolistaDTO>>() {
        }.getType());
        return futbolistasDTO;
        // List<FutbolistaEntity> futbolistas = servicioAccesoBaseDatos.obtenerFutbolistas();
        // return futbolistas.stream()
        //         .map(futbolista -> modelMapper.map(futbolista, FutbolistaDTO.class))
        //         .toList();
    }

    @Override
    public FutbolistaDTO obtenerFutbolistaPorId(Integer id) {
        FutbolistaEntity objFutbolistaEntity = this.servicioAccesoBaseDatos.obtenerFutbolistaPorId(id);
        FutbolistaDTO objFutbolistaDTO = this.modelMapper.map(objFutbolistaEntity, FutbolistaDTO.class);
        return objFutbolistaDTO;
        // FutbolistaEntity futbolista = servicioAccesoBaseDatos.obtenerFutbolistaPorId(id);
        // return modelMapper.map(futbolista, FutbolistaDTO.class);
    }

    @Override
    public FutbolistaDTO crearFutbolista(FutbolistaDTO futbolista) {
        // Obtener el equipo por su código
        EquipoDTO equipoDTO = equipoService.obtenerEquipoPorCodigo(futbolista.getCodigoEquipo());

        // Convertir el EquipoDTO a EquipoEntity
        EquipoEntity equipoEntity = modelMapper.map(equipoDTO, EquipoEntity.class); 

        FutbolistaEntity futbolistaEntity = new FutbolistaEntity();
        futbolistaEntity.setId(futbolista.getId());
        futbolistaEntity.setNombre(futbolista.getNombre());
        futbolistaEntity.setEquipo(equipoEntity); // Asigna el EquipoEntity directamente
        futbolistaEntity.setEdad(futbolista.getEdad());
        futbolistaEntity.setGolesAnotadosPorTemporada(futbolista.getGolesAnotadosPorTemporada());
        futbolistaEntity.setNacionalidad(futbolista.getNacionalidad());
        futbolistaEntity.setPosicion(PosicionEnum.valueOf(futbolista.getPosicion()));
        futbolistaEntity.setLesiones(futbolista.getLesiones());
        FutbolistaEntity objfutbolistaEntity = this.servicioAccesoBaseDatos.crearFutbolista(futbolistaEntity);
        FutbolistaDTO objfutbolistaDTO = this.modelMapper.map(objfutbolistaEntity, FutbolistaDTO.class);
        return objfutbolistaDTO;

        // FutbolistaEntity futbolistaEntity = this.modelMapper.map(futbolista, FutbolistaEntity.class);
        // FutbolistaEntity objfutbolistaEntity = this.servicioAccesoBaseDatos.crearFutbolista(futbolistaEntity);
        // FutbolistaDTO objfutbolistaDTO = this.modelMapper.map(objfutbolistaEntity, FutbolistaDTO.class);
        // return objfutbolistaDTO;

        // FutbolistaEntity futbolista = modelMapper.map(futbolistaDTO, FutbolistaEntity.class);
        // FutbolistaEntity nuevoFutbolista = servicioAccesoBaseDatos.crearFutbolista(futbolista);
        // return modelMapper.map(nuevoFutbolista, FutbolistaDTO.class);
    }

    @Override
    public FutbolistaDTO actualizarFutbolista(Integer id, FutbolistaDTO futbolista) {
        FutbolistaEntity futbolistaEntity = this.modelMapper.map(futbolista, FutbolistaEntity.class);
        FutbolistaEntity futbolistaEntityActualizado = this.servicioAccesoBaseDatos.actualizarFutbolista(id, futbolistaEntity);
        FutbolistaDTO objfutbolistaDTO = this.modelMapper.map(futbolistaEntityActualizado, FutbolistaDTO.class);
        return objfutbolistaDTO;
        // FutbolistaEntity futbolista = modelMapper.map(futbolistaDTO, FutbolistaEntity.class);
        // FutbolistaEntity futbolistaActualizado = servicioAccesoBaseDatos.actualizarFutbolista(id, futbolista);
        // return modelMapper.map(futbolistaActualizado, FutbolistaDTO.class);
    }

    @Override
    public boolean eliminarFutbolista(Integer id) {
        return this.servicioAccesoBaseDatos.eliminarFutbolista(id);
        // return servicioAccesoBaseDatos.eliminarFutbolista(id);
    }
}
