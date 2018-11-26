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
public class Periodo {
    
   String Periodo;
   int Activo;
   String LeyendaOficio;

    public Periodo(String Periodo, int Activo, String LeyendaOficio) {
        this.Periodo = Periodo;
        this.Activo = Activo;
        this.LeyendaOficio = LeyendaOficio;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Perdiodo) {
        this.Periodo = Perdiodo;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public String getLeyendaOficio() {
        return LeyendaOficio;
    }

    public void setLeyendaOficio(String LeyendaOficio) {
        this.LeyendaOficio = LeyendaOficio;
    }

    @Override
    public String toString() {
        return "Periodo{" + "Periodo=" + Periodo + ", Activo=" + Activo + ", LeyendaOficio=" + LeyendaOficio + '}';
    }
   
   
    
}
