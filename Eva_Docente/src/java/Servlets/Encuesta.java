/**
 *
 * @author mac2
 */

package Servlets;

import Sql.*;
import Clases.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Encuesta extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Encuesta(req, res);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Encuesta(req, res);
    }
    
    private final Procedimientos proce = new Procedimientos();
    private final Conexion2005 con = new Conexion2005();
    private Connection SCE2005;
    private ArrayList<Preguntas> Preg;
    
public void Encuesta(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        SCE2005=con.conexion_SCE2005();
        Preg=proce.PreguntasEvaD(SCE2005);
        
    try{
        int IdInscr=Integer.parseInt(req.getParameter("IdInscripcion").trim());
        String Nombre = req.getParameter("NombProf");
        String Asignatura = req.getParameter("NombAsig");
        
        out.println("<br><p class=\"buscar\">ENCUESTA AL DESEMPEÑO DOCENTE POR PARTE DEL ALUMNO</p>"+
                                "<h2>Instrucciones: Selecciona la opción que corresponda a tu opinión con respecto al desempeño de tu profesor en relación a la pregunta correspondiente.</h2><br>"+
                                "<h2>Nombre del docente: "+Nombre+"</h2><br>"+
                                "<h2>Asignatura: "+Asignatura+"</h2><br>"+
                    
                   "<form onsubmit=\"return false\">");
        
        out.println("<table width=\"100%\">\n" +
                                        "<tr class=\"FondoTE\"> <td align=\"center\">NO.</td>"
                                                             + "<td align=\"center\">PREGUNTA</td>"
                                                             + "<td align=\"center\">INSUFICIENTE</td>"
                                                             + "<td align=\"center\">SUFICIENTE</td>"
                                                             + "<td align=\"center\">BUENO</td>"
                                                             + "<td align=\"center\">NOTABLE</td>"
                                                             + "<td align=\"center\">EXCELENTE</td>"
                                      + "</tr>");
                            for(int i=0; i < Preg.size();i++){
                            int conta=i+1;
                            int Res=conta%2;
    
                            if(Res == 1){
                                out.println("<tr class=\"FondoTC\">");
                            }else{
                                out.println("<tr class=\"FondoTC2\">");
                            }
                                out.println("<td>"+conta+"</td> <td>"+Preg.get(i).getPreg()+"</td>"+
                                            "<td align=\"center\"> <input type=\"radio\" id=\"pregunta"+conta+"\" name=\"pregunta"+conta+"\" value=\"3.24\" class=\"option-input radio\" required> </td>"+
                                            "<td align=\"center\"> <input type=\"radio\" id=\"pregunta"+conta+"\" name=\"pregunta"+conta+"\" value=\"3.74\" class=\"option-input radio\" required> </td>"+
                                            "<td align=\"center\"> <input type=\"radio\" id=\"pregunta"+conta+"\" name=\"pregunta"+conta+"\" value=\"4.24\" class=\"option-input radio\" required> </td>"+
                                            "<td align=\"center\"> <input type=\"radio\" id=\"pregunta"+conta+"\" name=\"pregunta"+conta+"\" value=\"4.74\" class=\"option-input radio\" required> </td>"+
                                            "<td align=\"center\"> <input type=\"radio\" id=\"pregunta"+conta+"\" name=\"pregunta"+conta+"\" value=\"5.00\" class=\"option-input radio\" required> </td>"+
                                        "</tr>");
                            }
                             
                out.println("</table><br>"+
                        
                            "<div align=\"center\"><input type=\"submit\" id=\"Guardar\" value=\"Guardar\" onclick=\"GPEncuesta('GEncuesta','"+IdInscr+"')\" class=\"myButton\"></div>"+
                
                    "</form>");
     
        out.println("<input type=\"submit\" id=\"Regresar\" value=\"<<= Regresar\" onclick=\"Regresar('form1','form2')\" class=\"myButton\"><br><br>");
     
    }catch(Exception e){
        System.out.println(e);
    }  
    
  }    
}  