/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Clases_E.Periodo;
import Sql_E.Conexion;
import Sql_E.Procedimientos;
import java.io.File;
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
public class LEvaluados extends HttpServlet {

         Procedimientos pr=new Procedimientos();
        private Conexion c= new Conexion();
        private Connection con;
        private ArrayList<Periodo> Periodo=null;
        private ArrayList Total ;
        File f= new File("Gráficos.png");
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String dato=request.getParameter("dato");
        PrintWriter out = response.getWriter();
        HttpSession session= request.getSession();
        String s=(String) session.getAttribute("Administrador");
        if(null!=s){
        try  {
            con=c.conexion_SCE2005();
                        out.println("<div id=\"pestana4\"> \n");
                        if(dato==null){
                        out.println("           <center>\n" +
                        "                            <table id=\"cu\" width=\"75%\">\n" +
                        "                                <tr id=\"cab\">\n" +
                        "                                    <td align=\"center\"><center><strong><p class=\"buscar\"><font size=\"3\">Estadisticas <br>Reporte de Alumnos </p></font></strong></center></td>\n" +
                        "                                </tr>\n" +
                        "                            </table>"
                      + " <hr><hr>\n" );
                        
                        
                        Periodo=pr.Periodo(con);
                        
                        out.println("<form onsubmit=\"return false\">"
                                + "<h3>Periodo:</font> <select name=\"pl\" id=\"pl\" onchange=\"CreaReportePeriodo('LEvaluados','respuesta')\" this.form.reset();>"
                                + "<option value=\"0\">Selecciona</option>");
                        for(int i=0;i<Periodo.size();i++){
                        out.println("<option value=\""+Periodo.get(i).getPeriodo()+"\">"+Periodo.get(i).getPeriodo()+"");
                        out.println("</option>");
                        }
                        out.println("</select></form>");
                        out.println("<br><div id=\"respuesta\" align=\"center\"></div>");
                        
                        }
                        else{
                        Total=pr.Total(con, dato);
                        if(Total.isEmpty()){
                        out.println("<font size=\"4\" color=\"red\">¡No se encontrarón resultados!");   
                        }else{
                        int a=Integer.parseInt((String) Total.get(1));
                        int b=Integer.parseInt((String) Total.get(0));
                        int resta=a-b;
                        out.println("<center><div id=\"respuesta\">");
                        out.println("<br><form onsubmit=\"return false\"><table width=\"100%\"  border=\"0\" >");
                        out.println("<tr><th>Alumnos Inscritos</th>");
                        out.println("<th>Alumnos Cumplidos</th>");
                        out.println("<th>Alumnos Sin Cumplir</th></tr>");
                        out.println("<tr><th>"+Total.get(1)+"</th>");
                        out.println("<th>"+Total.get(0)+"</th>"); 
                        out.println("<th>"+resta+"</th></tr>"); 
                        out.println("<tr><th><th><br><a href=\"GExcel?PeriodoL="+dato+"\" value=\"Exportar\" class=\"myButton\"> Crear Reporte</a></th></th></tr></table></form>"); 
                        out.println("<br><img width=\"800\"  height=\"600\" src=\"GraficaC?b="+b+"&resta="+resta+"\">");
                        out.println("</div></center>");
                        }    
                        } 
                        out.println("<div id=\"pestana4\"> \n");////div final
                        
                        
        }catch(Exception e){
            log("Error en LEvaluados: "+e.getMessage());
        }
    }
                 else{
                  out.println("<center><br><font size=\"4\" color=\"red\"> Sesión Expirada</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("sesión expirada");
        }}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
