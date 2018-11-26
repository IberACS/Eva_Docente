/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_E;

import java.text.DecimalFormat;

/**
 *
 * @author mac3
 */
public class Operaciones {

    public Operaciones() {
    }
    public String nivel(double valor){
        String mensaje=null;
        DecimalFormat decimales = new DecimalFormat("0.00");
        valor=Float.parseFloat(decimales.format(valor));
        System.out.println("valor: "+valor);
        if (valor>=1.0 && valor<3.25){
            mensaje="Insuficiente";
        }
        else if(valor>=3.25 && valor<3.75){
            mensaje="Suficiente";
        }
        else if(valor>=3.75 && valor<4.25){
            mensaje="Bueno";
        }
        else if(valor>=4.25 && valor<4.75){
            mensaje="Notable";
        }
        else if(valor>=4.75 && valor<5.1){
            mensaje="Excelente";
            
        }
        System.out.println("mensaje: "+mensaje+" valor "+valor);
        return mensaje;
    }
}
