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
public class APersona extends HttpServlet {
        Connection c;
        Conexion con= new Conexion();
        Procedimientos pr= new Procedimientos();
        private String Actualiza;
        private String valida=null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        String s=(String) session.getAttribute("Administrador");    
        PrintWriter out = response.getWriter();
        
        try{ 
            
//        ArrayList ar= new ArrayList();
//        Enumeration<String> enn = request.getParameterNames();
//        
//        while (enn.hasMoreElements()) {
//
//        String Nombre = enn.nextElement();
//        String Respuesta = request.getParameter(Nombre).trim();
//        ar.add(Nombre);
//        ar.add(Respuesta);
//        System.out.println("datos: "+Respuesta);
//        }       
       
        String Nombre=request.getParameter("name").trim();
        String Paterno=request.getParameter("pater").trim();
        String Materno=request.getParameter("mater").trim();
        String pass=request.getParameter("pa").trim();
        String ti=request.getParameter("ti").trim();
        
        c=con.conexion_SCE2005();
        out.println("<div id=\"respuestag\"> \n");   
        valida=pr.RevisaUsuario(c, s, pass);
        if(valida==null || valida.equals("SinLoggeo")){
            out.println("<img src=\"images/error.jpg\"><br>"
        + "<font size=\"4\" color=\"red\"> Tu contraseña es incorrecta ");
        }
        else if(valida.equals("EvaDoc")){
        Actualiza=pr.Actualiza(c, Nombre, Paterno, Materno, Integer.parseInt(s), pass,ti);
        if(Actualiza.equals("si")){
        out.println("<img src=\"images/done.jpg\"><br> "
        + "<font size=\"4\" color=\"green\"> ¡Los datos se actualizarón correctamente! ");
        }
        else{
        out.println("<img src=\"images/error.jpg\"><br>"
        + "<font size=\"4\" color=\"red\"> ¡Verifica la información, si el problema persiste contacta a soporte! ");
        } 
        }
        out.println("</div> \n");////div final
        }catch(Exception e){
           out.println("<img src=\"images/error.jpg\"><br>"
        + "<font size=\"4\" color=\"red\"> ¡Ocurrio el siguiente error: "+e.getMessage()+", si el problema persiste contacta a soporte! "); 
           log("Error al actualizar datos: "+e.getMessage()); 
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       this.doPost(request, response);
    }

}
