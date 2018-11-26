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
public class PreguntasE {

    public PreguntasE() {
    }
    private int IdPregunta;
    private String Pregunta;
    private int Dimension;

    public PreguntasE(int IdPregunta, String Pregunta, int Dimension) {
        this.IdPregunta = IdPregunta;
        this.Pregunta = Pregunta;
        this.Dimension = Dimension;
    }

    public int getIdPregunta() {
        return IdPregunta;
    }

    public void setIdPregunta(int IdPregunta) {
        this.IdPregunta = IdPregunta;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String Pregunta) {
        this.Pregunta = Pregunta;
    }

    public int getDimension() {
        return Dimension;
    }

    public void setDimension(int Dimension) {
        this.Dimension = Dimension;
    }

    @Override
    public String toString() {
        return "RespuestasE{" + "IdPregunta=" + IdPregunta + ", Pregunta=" + Pregunta + ", Dimension=" + Dimension + '}';
    }
    
    
    
}
