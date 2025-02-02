package com.jgnarvaez.registros_futbol_backend.capaControladores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.fachadaServices.service.EquipoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/equipos")
public class EquipoController {
    
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<EquipoEntity>> obtenerEquipos() {
        List<EquipoEntity> equipos = equipoService.obtenerEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EquipoEntity> obtenerEquipoId(@PathVariable Integer id) {
        EquipoEntity equipo = equipoService.obtenerEquipoId(id);
        return new ResponseEntity<EquipoEntity>(equipo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipoEntity> crearEquipo(@RequestBody EquipoEntity equipo) {
        EquipoEntity nuevoEquipo = equipoService.crearEquipo(equipo);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoEntity> actualizarEquipo(@PathVariable Integer id, @RequestBody EquipoEntity equipo) {
        EquipoEntity equipoActualizado = equipoService.actualizarEquipo(id, equipo);
        return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Integer id) {
        equipoService.eliminarEquipo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
