package com.jgnarvaez.registros_futbol_backend.capaControladores;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO.EquipoDTO;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.service.IEquipoService;

@RestController
@RequestMapping("/api")
public class EquipoRestController {
    
    @Autowired
    private IEquipoService equipoService;
    
    @GetMapping("/equipos")
    public List<EquipoDTO> index() {
        return equipoService.obtenerEquipos();
    }

    @GetMapping("/equipos/{codigo}")
    public EquipoDTO show(@PathVariable String codigo) {
        EquipoDTO objEquipo = null;
        objEquipo = equipoService.obtenerEquipoPorCodigo(codigo);
        return objEquipo;
    }

    @PostMapping("/equipos")
    public EquipoDTO create(@RequestBody EquipoDTO equipo) {
        EquipoDTO objEquipo = null;
        objEquipo = equipoService.crearEquipo(equipo);
        return objEquipo;
    }

    @PutMapping("/equipos/{codigo}")
    public EquipoDTO update(@RequestBody EquipoDTO equipo, @PathVariable String codigo) {
        EquipoDTO objEquipo = null;
        EquipoDTO equipoActual = equipoService.obtenerEquipoPorCodigo(codigo);
        if (equipoActual != null) {
            objEquipo = equipoService.actualizarEquipo(codigo, equipo);
        }
        return objEquipo;
    }

    @DeleteMapping("/equipos/{codigo}")
    public Boolean delete(@PathVariable String codigo) {
        Boolean bandera = false;
        EquipoDTO equipoActual = equipoService.obtenerEquipoPorCodigo(codigo);
        if (equipoActual != null) {
            bandera = equipoService.eliminarEquipo(codigo);
        }
        return bandera;
    }

    @GetMapping("equipos2/{nombre}/{categoria}")
	public String getMessage(@PathVariable String nombre,
			@PathVariable("category") String categoria) {
		String mensaje = String.format("%s pertenece a la categoria %s del futbol profesional", nombre, categoria);
		System.out.println(mensaje);
		return mensaje;
	}

	@GetMapping("consultarEquipos")
	public String getMessageParametros(@RequestParam String nombre,
			@RequestParam String anio) {
		String mensaje = String.format("Buscando un equipo por nombre: %s, anio: %s", nombre, anio);
		System.out.println(mensaje);
		return mensaje;
	}

    @GetMapping("/equipos/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}
}
