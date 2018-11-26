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

public class ValSesion extends HttpServlet {
       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ValSesion(req, res);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ValSesion(req, res);
    }
  
    private final Procedimientos proce = new Procedimientos();
    private final Conexion2005 con = new Conexion2005();
    private Connection SCE2005;
    private ArrayList<Login> Log;
    private String valida=null;
    private ArrayList status;
public void ValSesion(HttpServletRequest req, HttpServletResponse res) throws IOException{
            
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            
            res.setHeader("Cache-Control","no-cache"); 
            res.setHeader("Cache-Control","no-store"); 
            res.setDateHeader("Expires", 0); 
            res.setHeader("Pragma","no-cache"); 
            
            String Usuario=req.getParameter("Usu").trim();
            String Contrasena=req.getParameter("Pas").trim(); 
            
    try{  
        SCE2005=con.conexion_SCE2005();
    }catch(Exception a){
        System.out.println(a);
        out.println("Error1");
    }   
    
    try{
        
        HttpSession sesion = req.getSession(true);
            
        if(sesion.getAttribute("usu") != null){
            sesion.removeAttribute("usu");
            sesion.invalidate();
            sesion = req.getSession();  
        }  
        else if(sesion.getAttribute("Administrador") != null){
            sesion.removeAttribute("Administrador");
            sesion.invalidate();
            sesion = req.getSession();  
        } 
        
        Log=proce.Password(SCE2005, Usuario);  

        if (Log.size() == 1 && Log.get(0).getPassword().equalsIgnoreCase(Contrasena)) {     
            status=proce.Status(SCE2005);
            if(status.get(0).equals("1")){
            sesion.setAttribute("usu",Usuario);
            out.println("InicioED"); 
            }
            else{
            out.println("Error4");
            SCE2005.close();
            log("Evaluación Docente fuera de fechas");      
            }
        }
        else{

        valida=proce.RevisaUsuario(SCE2005, Usuario, Contrasena);
        if(valida.equals("EvaDoc")){
           sesion.setAttribute("Administrador",Usuario);
           sesion.setMaxInactiveInterval(1800);
           out.println("ReporteEva");  
        }
        else{
        out.println("Error3");
        SCE2005.close();
        log("Error en credenciales del Administrador");    
        }
      } 
    }catch(Exception b){
        System.out.println(b);
        out.println("Error2");
         log("Error en ValSesion:" +b.getLocalizedMessage());
    }
  }    
}
