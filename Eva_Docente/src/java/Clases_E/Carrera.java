/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_E;

/**
 *
 * @author mac3
 */
public class Carrera {
    int IdCarrera;
    String Nombre;

    public Carrera() {
    }

    public Carrera(int IdCarrera, String Nombre) {
        this.IdCarrera = IdCarrera;
        this.Nombre = Nombre;
    }

    public int getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(int IdCarrera) {
        this.IdCarrera = IdCarrera;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" + "IdCarrera=" + IdCarrera + ", Nombre=" + Nombre + '}';
    }
    
    
}
