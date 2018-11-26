/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Sql_E.Conexion;
import Sql_E.Procedimientos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac3
 */
public class PeriodoED extends HttpServlet {
   private ArrayList status=null;
   private Procedimientos pr= new Procedimientos();
   private Connection con;
   private Conexion c= new Conexion();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session= request.getSession();
        String s=(String) session.getAttribute("Administrador");
        PrintWriter out = response.getWriter();
        if(null!=s){
        try  {
            /* TODO output your page here. You may use following sample code. */
           
            out.println("<!DOCTYPE html>");
            out.println("<div id=\"PeriodoE\"> ");
            
            out.println("           <center>\n" +
                        "                            <table id=\"cu\" width=\"75%\">\n" +
                        "                                <tr id=\"cab\">\n" +
                        "                                    <td align=\"center\"><center><strong><p class=\"buscar\"><font size=\"3\">Activar Periodo<br> Evaluación Docente </p></font></strong></center></td>\n" +
                        "                                </tr>\n" +
                        "                            </table>"
                    + " <hr><hr>\n" );
      
            out.println("<br>");
            con=c.conexion_SCE2005();
            status=pr.Status(con);
            String estado=null;
            String fase=null;
            if(status.get(0).equals("1")){
            estado="Activo";    
            }else{
            estado="Inactivo";    
            }
            if(status.get(1).equals("1")){
            fase="Regular";    
            }else{
            fase="Irregular";    
            }
            out.println("<table ><tr><td>");
            out.println("<font color=\"green\" size=\"4\"> Status: </font></td><td><font size=\"3\">"+estado+"</font></td></tr>");
            out.println("<tr><td><font color=\"green\" size=\"4\"> Fase: </td><td></font><font size=\"3\">"+fase+"</font></td></tr>");
            out.println("</table>");
            out.println(" <form id=\"activa\" onsubmit=\"return false\"> ");
            out.println(" <table > ");
            out.println("<tr><td><br></td></tr><tr> ");
            out.println(" <td>Activar: </td><td><select id=\"act\" title=\"Activa el periodo de evaluación Docente\" >  "
                                            + "<option value=\"x\">Selecciona</option>"
                                            + "<option value=\"1\">Activar</option>"
                                            + "<option value=\"0\">Desactivar</option>"
                                            + "</select>"    
            );
            out.println(" </td></tr> ");
            out.println(" <tr><td>Fase: </td><td><select title=\"selecciona si es periodo regular ó periodo extraordinario \" id=\"fas\" onchange=\"Cambio('APeriodoED','PeriodoE')\">  "
                                            + "<option value=\"x\">Selecciona</option>"
                                            + "<option value=\"1\">Regular</option>"
                                            + "<option value=\"0\">Irregular</option>"
                                            + "</select>"    
            );
            out.println(" </td></tr> ");
            out.println(" </table> ");
            out.println("<br>"
                    + "<p><font color=\"gray\" size=\"2\"> Nota: La Evaluación Docente que se haga fuera del periodo normal (Regular), no se verá reflejada en las estadísticas.</font></p>"
                    + "</div>");
            
            
        }
        catch(Exception e){
                 out.println("<center><br><font size=\"4\" color=\"red\"> Lo sentimos , se generó un errror: "+e.getMessage()+"</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Error no controlado');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("error no controlado: "+e.getMessage());    
        }
    } else{
                 out.println("<center><br><font size=\"4\" color=\"red\"> Sesión Expirada</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("sesión expirada");
        } 

}
}