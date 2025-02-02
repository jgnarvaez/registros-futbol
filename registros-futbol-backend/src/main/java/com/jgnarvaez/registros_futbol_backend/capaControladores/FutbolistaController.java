package com.jgnarvaez.registros_futbol_backend.capaControladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.service.FutbolistaService;

@RestController
@RequestMapping("/futbolistas")
public class FutbolistaController {
    
    @Autowired
    private FutbolistaService futbolistaService;

    @GetMapping
    public ResponseEntity<List<FutbolistaEntity>> obtenerFutbolistas() {
        List<FutbolistaEntity> futbolistas = futbolistaService.obtenerFutbolistas();
        return new ResponseEntity<>(futbolistas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FutbolistaEntity> obtenerFutbolistaId(@PathVariable Integer id) {
        FutbolistaEntity futbolista = futbolistaService.obtenerFutbolistaId(id);
        return new ResponseEntity<>(futbolista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FutbolistaEntity> crearFutbolista(@RequestBody FutbolistaEntity futbolista) {
        FutbolistaEntity nuevoFutbolista = futbolistaService.crearFutbolista(futbolista);
        return new ResponseEntity<>(nuevoFutbolista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FutbolistaEntity> actualizarFutbolista(@PathVariable Integer id, @RequestBody FutbolistaEntity futbolista) {
        FutbolistaEntity futbolistaActualizado = futbolistaService.actualizarFutbolista(id, futbolista);
        return new ResponseEntity<>(futbolistaActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFutbolista(@PathVariable Integer id) {
        futbolistaService.eliminarFutbolista(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
