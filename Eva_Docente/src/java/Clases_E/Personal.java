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
public class Personal {

    public Personal() {
    }
    
    int IdPersona;
    String Nombre,Paterno,Materno,Password;

    public Personal(int IdPersona, String Nombre, String Paterno, String Materno, String Password) {
        this.IdPersona = IdPersona;
        this.Nombre = Nombre;
        this.Paterno = Paterno;
        this.Materno = Materno;
        this.Password = Password;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Personal{" + "IdPersona=" + IdPersona + ", Nombre=" + Nombre + ", Paterno=" + Paterno + ", Materno=" + Materno + ", Password=" + Password + '}';
    }
    
    
    
}
