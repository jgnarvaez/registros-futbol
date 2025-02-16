package com.jgnarvaez.registros_futbol_backend.repositories;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jgnarvaez.registros_futbol_backend.models.CategoriaEnum;
import com.jgnarvaez.registros_futbol_backend.models.EquipoEntity;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
import com.jgnarvaez.registros_futbol_backend.models.PosicionEnum;

@Repository
public class FutbolistaRepository {
    private ArrayList<FutbolistaEntity> listaDeFutbolistas;

    public FutbolistaRepository() {
        this.listaDeFutbolistas = new ArrayList<FutbolistaEntity>();
        cargarFutbolistas();
    }

    public List<FutbolistaEntity> obtenerFutbolistas() {
        System.out.println("Invocando a listar futbolistas");
        return this.listaDeFutbolistas;
    }

    public FutbolistaEntity obtenerFutbolistaPorId(Integer id) {
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

    public FutbolistaEntity crearFutbolista(FutbolistaEntity futbolista) {
        System.out.println("Invocando a almacenar futbolista");
        FutbolistaEntity objFutbolista = null;
        if (this.listaDeFutbolistas.add(futbolista)) {
            objFutbolista = futbolista;
        }
        return objFutbolista;
    }

    public FutbolistaEntity actualizarFutbolista(Integer id, FutbolistaEntity futbolista) {
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

    public boolean eliminarFutbolista(Integer id) {
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
        EquipoEntity equipo1 = new EquipoEntity("AME", "América de Cali", "Colombia", CategoriaEnum.A, 1900, 100000000.00,listaDeFutbolistas);
        
        FutbolistaEntity objFutbolista1 = new FutbolistaEntity(5, "Fabian Vargas", equipo1, equipo1.getCodigoEquipo(), 35, 10, "Ecuador", PosicionEnum.DEFENSA, false);
        this.listaDeFutbolistas.add(objFutbolista1);
        FutbolistaEntity objFutbolista2 = new FutbolistaEntity(10, "Duvan Vergara", equipo1, equipo1.getCodigoEquipo(), 30, 30, "Colombiano", PosicionEnum.MEDIO, true);
        this.listaDeFutbolistas.add(objFutbolista2);
        FutbolistaEntity objFutbolista3 = new FutbolistaEntity(9, "Michael Rangel", equipo1, equipo1.getCodigoEquipo(), 25, 60, "Colombiano", PosicionEnum.DELANTERO, false);
        this.listaDeFutbolistas.add(objFutbolista3);
    }
}