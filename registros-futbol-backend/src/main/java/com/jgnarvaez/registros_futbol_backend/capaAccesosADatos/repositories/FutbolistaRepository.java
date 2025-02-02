package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.repositories;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.CategoriaEnum;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.PosicionEnum;

@Repository
public class FutbolistaRepository {
    private ArrayList<FutbolistaEntity> listaDeFutbolistas;

    public FutbolistaRepository() {
        this.listaDeFutbolistas = new ArrayList<FutbolistaEntity>();
        cargarFutbolistas();
    }

    public List<FutbolistaEntity> buscarTodos() {
        System.out.println("Invocando a listar futbolistas");
        return this.listaDeFutbolistas;
    }

    public FutbolistaEntity buscarPorId(Integer id) {
        System.out.println("Invocando a consultar un futbolista");
        FutbolistaEntity objFutbolista = null;
        for (FutbolistaEntity futbolista : listaDeFutbolistas) {
            if (futbolista.getId() == id) {
                objFutbolista = futbolista;
                break;
            }
        }
        return objFutbolista;
    }

    public FutbolistaEntity guardar(FutbolistaEntity futbolista) {
        System.out.println("Invocando a almacenar futbolista");
        FutbolistaEntity objFutbolista = null;
        if (this.listaDeFutbolistas.add(futbolista)) {
            objFutbolista = futbolista;
        }
        return objFutbolista;
    }

    public FutbolistaEntity actualizar(Integer id, FutbolistaEntity futbolista) {
        System.out.println("Invocando a actualizar un futbolista");
        FutbolistaEntity objFutbolista = null;
        for (int i = 0; i < this.listaDeFutbolistas.size(); i++) {
            if (this.listaDeFutbolistas.get(i).getId() == id) {
                this.listaDeFutbolistas.set(i, futbolista);
                objFutbolista = futbolista;
                break;
            }
        }
        return objFutbolista;
    }

    public boolean eliminar(Integer id) {
        System.out.println("Invocando a eliminar un futbolista");
        boolean bandera = false;
        for (int i = 0; i < this.listaDeFutbolistas.size(); i++) {
            if (this.listaDeFutbolistas.get(i).getId() == id) {
                this.listaDeFutbolistas.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    private void cargarFutbolistas() {
        EquipoEntity equipo1 = new EquipoEntity(01, "NAL", "Colombia", CategoriaEnum.A, 1900, 100000000.00);
        
        FutbolistaEntity objFutbolista1 = new FutbolistaEntity(10, "Juan", 20, "Colombiano", PosicionEnum.ARQUERO, false, equipo1);
        this.listaDeFutbolistas.add(objFutbolista1);
        FutbolistaEntity objFutbolista2 = new FutbolistaEntity(11, "Pedro", 22, "Colombiano", PosicionEnum.DEFENSA, true, equipo1);
        this.listaDeFutbolistas.add(objFutbolista2);
        FutbolistaEntity objFutbolista3 = new FutbolistaEntity(12, "David", 24, "Colombiano", PosicionEnum.MEDIO, false, equipo1);
        this.listaDeFutbolistas.add(objFutbolista3);
    }
}