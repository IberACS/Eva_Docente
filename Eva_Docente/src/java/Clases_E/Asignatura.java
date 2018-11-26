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
public class Asignatura {

    public Asignatura() {
    }
    
    int IdAsigTese;
    String Grupo;
    int Carrea;
    String Nombre;
    int IdInscripcion;
    public Asignatura(int IdAsigTese, String Grupo, int Carrea) {
        this.IdAsigTese = IdAsigTese;
        this.Grupo = Grupo;
        this.Carrea = Carrea;
    }

    public Asignatura(int IdAsigTese, String Nombre, String Grupo) {
        this.IdAsigTese = IdAsigTese;
        this.Nombre = Nombre;
         this.Grupo = Grupo;
    }

    public Asignatura(int IdAsigTese, String Nombre, String Grupo, int IdInscripcion) {/////////////////////////constructor para obtener algunos dato para generar la estdistica por docente
        this.IdAsigTese = IdAsigTese;
        this.Nombre = Nombre;
        this.Grupo = Grupo;
        this.IdInscripcion = IdInscripcion;
    }

    public int getIdInscripcion() {
        return IdInscripcion;
    }

    public void setIdInscripcion(int IdInscripcion) {
        this.IdInscripcion = IdInscripcion;
    }
    
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    
    public int getIdAsigTese() {
        return IdAsigTese;
    }

    public void setIdAsigTese(int IdAsigTese) {
        this.IdAsigTese = IdAsigTese;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public int getCarrea() {
        return Carrea;
    }

    public void setCarrea(int Carrea) {
        this.Carrea = Carrea;
    }

  
    
           
    
}
