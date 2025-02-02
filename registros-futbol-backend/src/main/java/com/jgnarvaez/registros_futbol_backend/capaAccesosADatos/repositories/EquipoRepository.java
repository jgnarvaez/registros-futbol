package com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.repositories;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.CategoriaEnum;
import com.jgnarvaez.registros_futbol_backend.capaAccesosADatos.models.EquipoEntity;

@Repository
public class EquipoRepository {
    
    private ArrayList<EquipoEntity> listaDeEquipos;

    public EquipoRepository() {
        this.listaDeEquipos = new ArrayList<EquipoEntity>();
        cargarEquipos();
    }

    public List<EquipoEntity> buscarTodos() {
        System.out.println("Invocando a listar equipos");
        return this.listaDeEquipos;
    }

    public EquipoEntity buscarPorId(Integer id) {
        System.out.println("Invocando a consultar un equipo");
        EquipoEntity objEquipo = null;
        for (EquipoEntity equipo : listaDeEquipos) {
            if (equipo.getId() == id) {
                objEquipo = equipo;
                break;
            }
        }
        return objEquipo;
    }

    public EquipoEntity guardar(EquipoEntity equipo) {
        System.out.println("Invocando a almacenar equipo");
        EquipoEntity objEquipo = null;
        if (this.listaDeEquipos.add(equipo)) {
            objEquipo = equipo;
        }
        return objEquipo;
    }

    public EquipoEntity actualizar(Integer id, EquipoEntity equipo) {
        System.out.println("Invocando a actualizar un equipo");
        EquipoEntity objEquipo = null;
        for (int i = 0; i < this.listaDeEquipos.size(); i++) {
            if (this.listaDeEquipos.get(i).getId() == id) {
                this.listaDeEquipos.set(i, equipo);
                objEquipo = equipo;
                break;
            }
        }
        return objEquipo;
    }

    public boolean eliminar(Integer id) {
        System.out.println("Invocando a eliminar un equipo");
        boolean bandera = false;
        for (int i = 0; i < this.listaDeEquipos.size(); i++) {
            if (this.listaDeEquipos.get(i).getId() == id) {
                this.listaDeEquipos.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    private void cargarEquipos() {
        EquipoEntity objEquipo1 = new EquipoEntity(01, "NAL", "Colombia", CategoriaEnum.A, 1900, 100000000.00);
        this.listaDeEquipos.add(objEquipo1);
        EquipoEntity objEquipo2 = new EquipoEntity(02, "AME", "Colombia", CategoriaEnum.A, 1910, 200000000.00);
        this.listaDeEquipos.add(objEquipo2);
        EquipoEntity objEquipo3 = new EquipoEntity(03, "MIL", "Colombia", CategoriaEnum.A, 1920, 100000000.00);
        this.listaDeEquipos.add(objEquipo3);
    }
}
