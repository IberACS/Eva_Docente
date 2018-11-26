/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author mac2
 */
public class Inscripciones {
    
    int IdInscripcion;
    String NombreAsig,Grupo,NombreP,App,Amp;
    
    
    public Inscripciones(){
    }
    
    public Inscripciones(int IdInscripcion, String NombreAsig,String Grupo,String NombreP,String App,String Amp){
        
        this.IdInscripcion = IdInscripcion;
        this.NombreAsig = NombreAsig;
        this.Grupo = Grupo;
        this.NombreP = NombreP;
        this.App = App;
        this.Amp = Amp;
    }

    public int getIdInscripcion() {
        return IdInscripcion;
    }

    public String getNombreAsig() {
        return NombreAsig;
    }

    public String getGrupo() {
        return Grupo;
    }

    public String getNombreP() {
        return NombreP;
    }

    public String getApp() {
        return App;
    }

    public String getAmp() {
        return Amp;
    }
    
    
    
}
