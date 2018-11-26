/**
 *
 * @author mac2
 */

package Servlets;

import Clases.*;
import Sql.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class InicioED extends HttpServlet{
    
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Inicio(req, res);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Inicio(req, res);
    }
   
    private final Procedimientos proce = new Procedimientos();
    private final Conexion2005 con = new Conexion2005();
    private Connection SCE2005;
    private ArrayList<Alumnos> Alum;
    private ArrayList<Periodo> Per;
    private ArrayList<Inscripciones> Ins;
    private ArrayList<ProfEva> PEva;
    private ArrayList<ProfEva> PEva_I;
    String Usuario;
    private ArrayList status;
    public void Inicio(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
         
        HttpSession sesion = req.getSession();
             
if(sesion.getAttribute("usu") != null){
         
        Usuario = String.valueOf(sesion.getAttribute("usu"));  
        SCE2005=con.conexion_SCE2005();
        Alum=proce.Alumno(SCE2005, Usuario);
        Per=proce.PeriodoActivo(SCE2005);
        Ins=proce.Inscripcion(SCE2005, Usuario, Per.get(0).getPeriodo());
        
try{

res.setHeader("Cache-Control","no-cache"); 
res.setHeader("Cache-Control","no-store"); 
res.setDateHeader("Expires", 0); 
res.setHeader("Pragma","no-cache");

out.println("<html>\n");
        
        out.println("<head>\n" +
                        "<meta name=\"tipo_contenido\" http-equiv=\"content-type\" content=\"text/html;\" charset=\"utf-8\">" +
                        "<title>Evaluación Docente</title>\n" +

                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\"/>\n" +
    
                        "<script type=\"text/javascript\" src=\"js/ActivarCampos.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js/cargaenlamisma.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js/jquery.flexslider-min.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"js/funciones.js\"></script>\n"+
                    "</head><!-- end #header -->\n");

out.println("<body onload=\"init();\">\n");

out.println("<div id=\"wrapper\">\n");

            out.println("<div id=\"menu\">\n" +
                            "<nav id=\"colorNav\">\n" +
                                "<ul>\n" +
                                    "<li id=\"Sel1\" class=\"current_page_item\" onclick=\"viewSection(1);\"><a href=\"#\">Inicio</a></li>\n" +
                                    "<li><a onclick=\"f=document.createElement('form'); document.body.appendChild(f);\n" +
                                    "f.method='POST'; f.action=this.href; f.submit();return false;\" href=\"CerSesion\">Cerrar sesión</a></li>\n" +
                                "</ul><!-- end #ul -->\n" +
                            "</nav><!-- end #nav -->\n" +
                        "</div><!-- end #menu -->\n");

out.println("<div id=\"page\">\n");
        
    out.println("<div id=\"pestana1\">\n");
                   
        out.println("<div id=\"form1\">\n");
            
            out.println("<p class=\"buscar\"> Bienvenido </p>"+
            
                            "<table align=\"right\">"+
                            "<tr><td><img src=\""+req.getContextPath()+"/MuestraImagen\" align=\"center\" border=\"5.0\" style=\"max-width: 106px; max-height: 159px\"></td></tr>"+
                            "</table>"+
                            "<h1>Nombre: "+Alum.get(0).getNombre()+"</h1><br><br>"+
                            "<h1>Carrera: "+Alum.get(0).getCarrera()+"</h1><br><br><br><br><br><br><br>"+     
            
                        "<p class=\"buscar\"> Profesores a evaluar del periodo "+Per.get(0).getPeriodo()+"</p>");
            out.println("<div id=\"Inscrip\">");
             
            if(Ins.size() > 0){
                

                status=proce.Status(SCE2005);
                if(status.get(1).equals("1")){//-----------------------si la evaluacion es regular
                    
                    System.out.println("Entrada regular"); 
                    
                PEva=proce.ProfesorEvaluado(SCE2005, Usuario, Per.get(0).getPeriodo());///------Revisa si han evaluado
                out.println("<table width=\"100%\">\n" +
                                        "<tr class=\"FondoTE\"> <td align=\"center\">No.</td> <td align=\"center\">Nombre del Profesor</td> <td align=\"center\">Materia</td> <td align=\"center\">Grupo</td> <td></td> </tr>");
                            for(int i=0; i < Ins.size(); i++){
                            int prog=i+1;
                            String NombProf=Ins.get(i).getNombreP()+" "+Ins.get(i).getApp()+" "+Ins.get(i).getAmp() ;
                            out.println("<tr class=\"FondoTC\">"+
                                            "<td>"+prog+"</td> <td>"+NombProf+"</td> <td>"+Ins.get(i).getNombreAsig()+"</td> <td align=\"center\">"+Ins.get(i).getGrupo()+"</td>"+
                                            "<td align=\"center\">");
                            int Eva=0;
                            for(int e=0; e < PEva.size(); e++){//------------------------Revisa aquí-----------------
                                
                                if(Ins.get(i).getIdInscripcion() == PEva.get(e).getIdInscripcion()){
                                    Eva=1;
                                }
                                
                            }//-------------------------------------- fin de si esta en base 
                               if(Eva == 1){
                                   out.println("Evaluado");
                               }else{
                                   out.println("<input type=\"submit\" value=\"Evaluar\" onclick=\"MuestraEncues('Encuesta','"+Ins.get(i).getIdInscripcion()+"','"+NombProf+"','"+Ins.get(i).getNombreAsig()+"')\" class=\"myButton2\">");   
                               }
                                out.println("</td>"+
                                        "</tr>");
                            } 
                out.println("</table><br>");
                }else{//// si la evaluacion es fuera de tiempo
                System.out.println("Entrada irregular");   
                PEva_I=proce.ProfesorEvaluado_I(SCE2005, Usuario, Per.get(0).getPeriodo());///------Revisa si han evaluado
                out.println("<table width=\"100%\">\n" +
                                        "<tr class=\"FondoTE\"> <td align=\"center\">No.</td> <td align=\"center\">Nombre del Profesor</td> <td align=\"center\">Materia</td> <td align=\"center\">Grupo</td> <td></td> </tr>");
                            for(int i=0; i < Ins.size(); i++){
                            int prog=i+1;
                            String NombProf=Ins.get(i).getNombreP()+" "+Ins.get(i).getApp()+" "+Ins.get(i).getAmp() ;
                            out.println("<tr class=\"FondoTC\">"+
                                            "<td>"+prog+"</td> <td>"+NombProf+"</td> <td>"+Ins.get(i).getNombreAsig()+"</td> <td align=\"center\">"+Ins.get(i).getGrupo()+"</td>"+
                                            "<td align=\"center\">");
                            int Eva=0;
                            for(int e=0; e < PEva_I.size(); e++){//------------------------Revisa aquí-----------------
                                
                                if(Ins.get(i).getIdInscripcion() == PEva_I.get(e).getIdInscripcion()){
                                    Eva=1;
                                }
                                
                            }//-------------------------------------- fin de si esta en base 
                               if(Eva == 1){
                                   out.println("Evaluado");
                               }else{
                                   out.println("<input type=\"submit\" value=\"Evaluar\" onclick=\"MuestraEncues('Encuesta','"+Ins.get(i).getIdInscripcion()+"','"+NombProf+"','"+Ins.get(i).getNombreAsig()+"')\" class=\"myButton2\">");   
                               }
                                out.println("</td>"+
                                        "</tr>");
                            } 
                out.println("</table><br>");   
                    
                    
                }
                
                
            }else{
                out.println("<center><img class=\"imagen2\" src=\"images/Sinprof.jpg\"/></center>");
            }  
            out.println("</div>");
        out.println("</div>"+
              
                    "<div id=\"form2\">\n"+
                        "<div id=\"Encu\"></div>"+         
                    "</div>");
            
    out.println("</div>\n");
    
out.println("</div><!-- end #page -->\n");
        
out.println("</div><!-- end #wrapper -->\n");
        
out.println("<div id=\"footer\">\n" +
                "<p>Copyright (c) 2017 Sistema de Evaluación Docente del TEST. All rights reserved.</p>\n" +
            "</div><!-- end #footer -->\n");
        
    out.println("<div style=\"text-align: center; font-size: 0.95em;\">Webmaster: SoporteTEST.</div>\n");

    out.println("</body><!-- end #body -->\n");
    out.println("</html><!-- end #html -->");
    
}catch(Exception e){
out.println("Error al cargar la pagina.");
}    

}else{
res.sendRedirect("index.html");
}        
                   
  }
}

