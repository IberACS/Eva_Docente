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
public class Evaluadores {

    public Evaluadores() {
    }
        int Matricula;
    String Nombre,Paterno,Materno;
    String Carrera;

    public Evaluadores(int Matricula, String Nombre, String Paterno, String Materno, String Carrera) {
        this.Matricula = Matricula;
        this.Nombre = Nombre;
        this.Paterno = Paterno;
        this.Materno = Materno;
        this.Carrera = Carrera;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int Matricula) {
        this.Matricula = Matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String Paterno) {
        this.Paterno = Paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String Materno) {
        this.Materno = Materno;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }
    
    
}
