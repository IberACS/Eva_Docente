/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Clases_E.Carrera;
import Clases_E.Periodo;
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
public class MMateria extends HttpServlet {
        Procedimientos pr=new Procedimientos();
        Conexion c= new Conexion();
        Connection con;
       
    private ArrayList<Periodo> Periodo=null;
    private ArrayList<Carrera> Carrera=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        String s=(String) session.getAttribute("Administrador");
        if(null!=s){
        try  {
          
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<div id=\"pestana3\"> \n");
            
            out.println("           <center>\n" +
                        "                            <table id=\"cu\" width=\"75%\">\n" +
                        "                                <tr id=\"cab\">\n" +
                        "                                    <td align=\"center\"><center><strong><p class=\"buscar\"><font size=\"3\">Estadísticas <br>Evaluación Docente Por Materia </p></font></strong></center></td>\n" +
                        "                                </tr>\n" +
                        "                            </table>"
                      + " <hr><hr>\n" );

        
         
    try{////////////////////////////////////////////////////////////////////////////Sección 1///////////////////////////////////////////////////////////////////////////////////////////////////
                    
                        out.println("<br><br>");
                        out.println("<div id=\"sec1\">");
                        con=c.conexion_SCE2005();
                        Periodo=pr.Periodo(con);
                        out.println("<form onsubmit=\"return false\">");
                        out.println("<h3>Grupo: <input type=\"text\" id=\"grupoM\" name=\"grupoM\" required>"); 
                        out.println("<h3>Periodo:<select name=\"periodoM\" id=\"periodoM\">"
                                + "<option value=\"0\">Selecciona</option>");
                        for(int i=0;i<Periodo.size();i++){
                        out.println("<option value=\""+Periodo.get(i).getPeriodo()+"\">"+Periodo.get(i).getPeriodo()+"");
                        out.println("</option>");
                        }
                        out.println("</select>");
                        Carrera=pr.Carreras(con);
                        out.println("<H3>Carrera: <select name=\"carreraM\" id=\"carreraM\" onchange=\"MuestraProfesor2M('MMateriaD','sec5')\" >"
                                );
                        for(int x=0;x<Carrera.size();x++){
                        out.println("<option value=\""+Carrera.get(x).getIdCarrera()+"\">"+Carrera.get(x).getNombre()+"");
                        out.println("</option>");
                        }
                        out.println("</select>");
                          
                        out.println("<input type=\"text\" value=\"1\" id=\"cambio11\" name=\"cambio11\" hidden>\n");    
                       // out.println("<br><br><input type=\"submit\" class=\"myButton\" value=\"Consultar\"  " );
                        out.println("</form");
                        out.println(""
                                + "</div><div id=\"sec5\"></div>" );
    }catch(Exception e){
                    log("Se generó un error :"+e.getMessage());
}///////////////////////////////////////////////////////////////////////////////Fin Sección 1/////////////////////////////////////////////////////////////////////////////////////////////////
                        

out.println("</div>");//////////////////////////////////////////////////////////////////div final        
}
        catch(Exception e){
            log("error fatal: "+e.getLocalizedMessage());
        }
    }
         else{
                 out.println("<br><center><font size=\"4\" color=\"red\"> Sesión Expirada</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("sesión expirada");
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
