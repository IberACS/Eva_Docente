/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_E;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


/**
 *
 * @author mac3
 */
public class Conversor extends PdfPageEventHelper {
       public String Date(String fecha){
         
        String cadena="--";
        switch (fecha){
        case "01":
        return cadena="Enero";
        
        case "02":
        return cadena="Febrero";
        
        case "03":  
        return cadena="Marzo";
        
        case "04":
        return cadena="Abril";
        
        case "05":
        return cadena="Mayo";
        
        case "06":
        return cadena="Junio";
        
        case "07":
        return cadena="Julio";
        
        case "08":
        return cadena="Agosto";
        
        case "09":
        return cadena="Septiembre";
        
        case "10":
        return cadena="Octubre";
        
        case "11":
        return cadena="Noviembre";
        
        case "12":
        return cadena="Diciembre";
        }
        
        return cadena;
    }
       
       
       public String Fecha(String fecha){
       //String cadena="Mayo";
        String Cadena="--";
        if(fecha.equals("3")|| fecha.equals("4") || fecha.equals("5") || fecha.equals("6") || fecha.equals("7") || fecha.equals("8")){
        String mes="Mayo";
        Cadena=mes;
        return Cadena;
        }
        else if(fecha.equals("9") || fecha.equals("10") || fecha.equals("11") || fecha.equals("12") || fecha.equals("1") || fecha.equals("2") ){
            String mes="Noviembre";
            return Cadena=mes;
        }
        return Cadena;
    }
       
       
       
       
    public void onEndPage(PdfWriter writer, Document document) {
       
     try {
      //BaseFont baseFont = BaseFont.createFont("/Users/mac3/Documents/NetBeansProjects/SCE2/src/java/ControlEscolarWeb/Utilidades/Fuentes/Gotham Bold Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
     // BaseFont baseFont = BaseFont.createFont("C:\\Fuentes\\Gotham-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);


     Font Fuente = new Font();
     Fuente.setStyle(Font.BOLD);
     Fuente.setSize(10);
     
     Font Fuente2 = new Font();
     Fuente2.setStyle(Font.NORMAL);
     Fuente2.setSize(8);
     

     ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("Rev. 0",Fuente2), 565,76,0);
     ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT, new Phrase("TEST/D-AC-PO-006-01",Fuente2), 130,76,0);
     
//     ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("CARRETERA TENANGO – LA MARQUESA KM. 22, C.P. 52650. SANTIAGO TIANGUISTENCO, ESTADO DE MÉXICO, TELS. (01713) 1310934, 1356212.",Fuente3), 300,28,0);
//     ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase("tecnologico59@prodigy.net.mx              http://test.edomex.gob.mx",Fuente3), 300,18,0);
        
     
    } catch (Exception e) {
         System.out.println(""+e.getLocalizedMessage());
         
     }

}
       

}
