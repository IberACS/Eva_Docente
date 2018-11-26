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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac3
 */
public class APeriodoED extends HttpServlet {

        Procedimientos pr= new Procedimientos();
        Conexion c= new Conexion();
        Connection con;
        String Actualiza=null;
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires", 0);
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String s=(String) session.getAttribute("Administrador");
        PrintWriter out = response.getWriter();
        if(null!=s){
        try {
            
          
            String cambio=request.getParameter("cambio");
            String cambio2=request.getParameter("cambio2");
            con=c.conexion_SCE2005();
            Actualiza=pr.Activa(con, Integer.parseInt(cambio),Integer.parseInt(cambio2));
            if(Actualiza.equals("si")){
                    out.println("<img src=\"images/done.jpg\"><br> "
            + "<font size=\"4\" color=\"green\"> ¡Los datos se actualizarón correctamente! ");
            }
            else{
            out.println("<img src=\"images/error.jpg\"><br>"
            + "<font size=\"4\" color=\"red\"> ¡Verifica la información, si el problema persiste contacta a soporte! ");
            } 
            
        }catch (Exception e){
                 out.println("<center><br><font size=\"4\" color=\"red\"> Lo sentimos se generó un error: "+e.getMessage()+"</font></center><br>");
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Ocurrio un error: ');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("Error: "+e.getMessage());
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
        }

}
}
