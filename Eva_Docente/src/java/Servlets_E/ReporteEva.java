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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac3
 */
public class ReporteEva extends HttpServlet {
    private Procedimientos pr=new Procedimientos();
    private Conexion c= new Conexion();
    private Connection con;
    private ArrayList<Periodo> Periodo=null;
    private ArrayList<Carrera> Carrera=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control","no-cache"); 
        response.setHeader("Cache-Control","no-store"); 
        response.setDateHeader("Expires", 0); 
        response.setHeader("Pragma","no-cache");
        
        try{
        
        String s=(String) session.getAttribute("Administrador");
        if (null!=s){
            
        try {
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>\n");        
            out.println("<head>\n" +
                        "<meta name=\"keywords\" content=\"\"/>\n" +
                        "<meta name=\"description\" content=\"\"/>\n" +
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>\n" +
                        "<title></title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\"/>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/Tabla.css\" />\n"+
                        "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"images/favicon.ico\">\n"+
                        "<script type=\"text/javascript\" src=\"js_E/cargaenlamisma.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js_E/Botones.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js_E/jquery.min.js\"></script> \n" );
                        
            out.println("</head><!-- end #header -->\n");
            out.println("<body onload=\"init(); nobackbutton();\">\n");
            out.println("<div id=\"wrapper\">\n");
            out.println("<div id=\"menu\">\n" +
                            "<nav id=\"colorNav\">\n" +
                                   "                <ul class=\"mp\">\n" +
                
                        "                    <li id=\"General\"><a href=\"#\">Estadísticas</a>\n" + 
                        "                    <ul>\n" +    
                        "                    <li id=\"General\" onclick=\"viewSections(1);\" ><a href=\"#\">Por Profesor</a></li>\n" +
                        "                    <li id=\"Reportes\" onclick=\"viewSections2(3);\" ><a href=\"#\">Por Materia</a></li>\n" +   
                        "                    </ul>\n" +                  
                                             "</li>\n" +                    
                                             "</li>\n" +
                        "                    <li id=\"General\" onclick=\"viewSections2(4);\" ><a href=\"#\">Reporte Alumnos</a></li>\n" + 
                        "                    <li id=\"Personal\" onclick=\"viewSections2(5);\" ><a href=\"#\">Personal</a></li>\n" + 
                        "                    <li id=\"Periodo\" onclick=\"viewSections2(6);\"><a href=\"#\">Periodo Evaluación</a>\n" +                
                                             "</li>\n" +                    
                                             "</li>\n" +
                    
                        "                    <li id=\"links\"><a onclick=\"f=document.createElement('form'); document.body.appendChild(f);\n" +
                                    "f.method='POST'; f.action=this.href; f.submit();return false;\" href=\"CerSesion\">Cerrar Sesión</a></li>\n" +
                        "                </ul>\n" +
                         "</nav><!-- end #nav -->\n" +
                        "</div><!-- end #menu -->\n");            
            out.println("<div id=\"page\">\n");      
                        out.println(" <br><br><br><br><div id=\"pestana1\">\n" );
                        out.println("                        <center>\n" +
                        "                            <table id=\"cu\" width=\"75%\">\n" +
                        "                                <tr id=\"cab\">\n" +
                        "                                    <td align=\"center\"><center><strong><p class=\"buscar\"><font size=\"3\">Estadísticas <br>Evaluación Docente Por Profesor </p></font></strong></center></td>\n" +
                        "                                </tr>\n" +
                        "                            </table>"
                                + "                  <hr><hr>\n" );
                      
    try{////////////////////////////////////////////////////////////////////////////Sección 1///////////////////////////////////////////////////////////////////////////////////////////////////
                    
                        out.println("<br><br>");
                        out.println("<div id=\"sec1\">");
                        con=c.conexion_SCE2005();
                        Periodo=pr.Periodo(con);
                        out.println("<form onsubmit=\"return false\">"
                                + "<h3>Periodo:</font> <select name=\"p1\" id=\"p1\">"
                                + "<option value=\"0\">Selecciona</option>");
                        for(int i=0;i<Periodo.size();i++){
                        out.println("<option value=\""+Periodo.get(i).getPeriodo()+"\">"+Periodo.get(i).getPeriodo()+"");
                        out.println("</option>");
                        }
                        out.println("</select>");
                        Carrera=pr.Carreras(con);
                        out.println("<H3>Carrera: <select name=\"car\" id=\"car\" onchange=\"MuestraProfesor('MProfesores','sec2')\" >"
                                );
                        for(int x=0;x<Carrera.size();x++){
                        out.println("<option value=\""+Carrera.get(x).getIdCarrera()+"\">"+Carrera.get(x).getNombre()+"");
                        out.println("</option>");
                        }
                        out.println("</select>");
                        out.println("<input type=\"text\" value=\"1\" id=\"cambio\" name=\"cambio\" hidden>");        
                        out.println("</form");
                       
                        //        + "</form>" );
                        out.println("</div>"
                                + "<div id=\"sec2\"></div>" );
    }catch(Exception e){
                    log("Se generó un error :"+e.getMessage());
                    
}///////////////////////////////////////////////////////////////////////////////Fin Sección 1/////////////////////////////////////////////////////////////////////////////////////////////////

out.println("</div></div><!-- end #pestana1 -->\n");

//}/////////////////////////////////////////////////////////////////////////////// Sección 2////////////////////////////////////////////////////////////////////////////////////////////////////
out.println("<div id=\"pestana3\">\n");
out.println("</div><!-- end #pestana3 -->\n");
//}///////////////////////////////////////////////////////////////////////////////Fin Sección 2////////////////////////////////////////////////////////////////////////////////////////////////////
//}/////////////////////////////////////////////////////////////////////////////// Sección 3////////////////////////////////////////////////////////////////////////////////////////////////////
out.println("<div id=\"pestana4\">\n");
out.println("</div><!-- end #pestana4 -->\n");
//}///////////////////////////////////////////////////////////////////////////////Fin Sección 3////////////////////////////////////////////////////////////////////////////////////////////////////
//}/////////////////////////////////////////////////////////////////////////////// Sección 4////////////////////////////////////////////////////////////////////////////////////////////////////
out.println("<div id=\"pestana5\">\n");
out.println("</div><!-- end #pestana5-->\n");
//}///////////////////////////////////////////////////////////////////////////////Fin Sección 4////////////////////////////////////////////////////////////////////////////////////////////////////
//}/////////////////////////////////////////////////////////////////////////////// Sección 5////////////////////////////////////////////////////////////////////////////////////////////////////
out.println("<div id=\"pestana6\">\n");
out.println("</div><!-- end #pestana6-->\n");
//}///////////////////////////////////////////////////////////////////////////////Fin Sección 5////////////////////////////////////////////////////////////////////////////////////////////////////    
out.println("</div><!-- end #wrapper -->\n");
        
out.println("<div id=\"footer\">\n" +
                "<p>Copyright (c) 2017 Sistema de Evaluación Docente del TEST. All rights reserved.</p>\n" +
            "</div><!-- end #footer -->\n");
        out.println("</div><!-- end #page -->\n");    
   // out.println("<div style=\"text-align: center; font-size: 0.95em;\">Webmaster: SoporteTEST.</div>\n");

    out.println("</body><!-- end #body -->\n");
    out.println("</html><!-- end #html -->");
    
        
        } catch(Exception e){
            response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Error no controlado "+e.getMessage()+"');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
            log("sesión expiro en Reportes: "+e.getLocalizedMessage());
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReporteEva.class.getName()).log(Level.SEVERE, null, ex);
                response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Error SQL no controlado "+e.getMessage()+"');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                log("Error de Conexión "+e.getMessage());
            }
        }
          
        }

        else{
               
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
            log("sesión expirada");
     }
    }catch(Exception e){
        
        out.println("<center><font size=\"4\" color=\"red\"> Sesión Expirada</font></center>");
        response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Error no controlado "+e.getMessage()+"');");  
                 out.println("location.href='index.html'");  
                 out.println("</script>");
                 log("error en Reportes"+e.getMessage());
    }
}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}