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
import org.springframework.web.bind.annotation.RequestParam;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.DTO.FutbolistaDTO;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.service.IFutbolistaService;

public class FutbolistaRestController {
    
    @Autowired
    private IFutbolistaService futbolistaService;

    @GetMapping("/futbolistas")
    public List<FutbolistaDTO> index() {
        return futbolistaService.obtenerFutbolistas();
    }

    @GetMapping("/futbolistas/{id}")
    public FutbolistaDTO show(@PathVariable Integer id) {
        FutbolistaDTO objFutbolista = null;
        objFutbolista = futbolistaService.obtenerFutbolistaPorId(id);
        return objFutbolista;
    }

    @PostMapping("/futbolistas")
    public FutbolistaDTO create(@RequestBody FutbolistaDTO futbolista) {
        FutbolistaDTO objFutbolista = null;
        objFutbolista = futbolistaService.crearFutbolista(futbolista);
        return objFutbolista;
    }

    @PutMapping("/futbolistas/{id}")
    public FutbolistaDTO update(@RequestBody FutbolistaDTO futbolista, @PathVariable Integer id) {
        FutbolistaDTO objFutbolista = null;
        FutbolistaDTO futbolistaActual = futbolistaService.obtenerFutbolistaPorId(id);
        if (futbolistaActual != null) {
            objFutbolista = futbolistaService.actualizarFutbolista(id, futbolista);
        }
        return objFutbolista;
    }

    @DeleteMapping("/futbolistas/{id}")
    public Boolean delete(@PathVariable Integer id) {
        Boolean bandera = false;
        FutbolistaDTO futbolistaActual = futbolistaService.obtenerFutbolistaPorId(id);
        if (futbolistaActual != null) {
            bandera = futbolistaService.eliminarFutbolista(id);
        }
        return bandera;
    }

    @GetMapping("futbolistas2/{name}/{age}")
	public String getMessage(@PathVariable String name,
			@PathVariable("age") String edad) {
		String mensaje = String.format("%s pertenece a la categoria %s del futbol profesional", name, edad);
		System.out.println(mensaje);
		return mensaje;
	}

	@GetMapping("consultarFutbolistas")
	public String getMessageParametros(@RequestParam String nombre,
			@RequestParam String equipoId) {
		String mensaje = String.format("Buscando un equipo por nombre: %s, anio: %s", nombre, equipoId);
		System.out.println(mensaje);
		return mensaje;
	}

    @GetMapping("/futbolistas/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}
}
