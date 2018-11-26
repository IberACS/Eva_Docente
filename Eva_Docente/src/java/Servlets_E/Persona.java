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
public class Persona extends HttpServlet {
        private ArrayList Persona;
        Connection c;
        Conexion con= new Conexion();
        Procedimientos pr= new Procedimientos();
        private String Actualiza;
        private String valida=null;
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();    
        HttpSession session= request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String s=(String) session.getAttribute("Administrador");
        if(null!=s){
        try  {
        //int bandera=0;

        out.println("<div id=\"persona\"> \n");
        out.println("           <center>\n" +
                        "                            <table id=\"cu\" width=\"75%\">\n" +
                        "                                <tr id=\"cab\">\n" +
                        "                                    <td align=\"center\"><center><strong><p class=\"buscar\"><font size=\"3\">Actualizar Datos <br>Personales </p></font></strong></center></td>\n" +
                        "                                </tr>\n" +
                        "                            </table>"
                    + " <hr><hr>\n" );
      
        out.println("<br>");
        c=con.conexion_SCE2005();
        Persona=pr.Persona(c,Integer.parseInt(s)); 
        out.println("<form  onsubmit=\"return false\" accept-charset=\"UTF-8\" id=\"formu\">");
        out.println("<table width=\"100%\">");
        out.println("<tr><td><h3>Nombre: <input type=\"text\" id=\"nam\" name=\"nam\" value=\""+Persona.get(0)+"\" size=\"35\" required></h3></td></tr>");
        out.println("<tr><td><h3>Paterno: <input type=\"text\" id=\"pat\" name=\"pat\" value=\""+Persona.get(1)+"\" size=\"35\" required></h3></td></tr>");
        out.println("<tr><td><h3>Materno: <input type=\"text\" id=\"mat\" name=\"mat\" value=\""+Persona.get(2)+"\" size=\"35\" required></h3></td></tr>");
        out.println("<tr><td><h3>Profesión : <input type=\"text\" id=\"ti\" name=\"ti\" value=\""+Persona.get(3)+"\" size=\"10\" maxlength=\"8\" required></h3></td></tr>");
        out.println("<tr><td><h3>Contraseña: <input type=\"password\" id=\"pa\"size=\"31\" required></h3></td></tr>");
        out.println("<tr><td><h3><input type=\"button\" id=\"b\" value=\"Guardar\" class=\"myButton\" onclick=\"Actualiza('APersona','persona');this.form.reset();\" ></h3></td></tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</div>\n");////div final
        //out.println("<div id=\"respuestag\"></div>");

        }
        catch(Exception e){
            log("Error no controlado: "+e.getMessage());
        }  
        }else{
                 out.println("<center><br><font size=\"4\" color=\"red\"> Sesión Expirada</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("sesión expirada");
        }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
