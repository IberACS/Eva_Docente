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

public class GEncuesta extends HttpServlet {
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GEncuesta(req, res);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GEncuesta(req, res);
    }
    
    private final Procedimientos proce = new Procedimientos();
    private final Conexion2005 con = new Conexion2005();
    private Connection SCE2005;
    private ArrayList<Periodo> Per;
    private ArrayList<Inscripciones> Ins;
    private ArrayList<ProfEva> PEva;
    String Usuario;
    private ArrayList status;
    private ArrayList<ProfEva> PEva_I;
    
    public void GEncuesta(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
        HttpSession sesion = req.getSession();
        Usuario = String.valueOf(sesion.getAttribute("usu"));
         
        try{
            SCE2005 = con.conexion_SCE2005();   
        }catch(Exception a){
            System.out.println(a);
            out.println("Error1");
        }
         
            
        try{
            
            int IdInscr = Integer.parseInt(req.getParameter("IdInscr"));
            
            float pregunta1 = Float.parseFloat(req.getParameter("Pregunta1"));
            float pregunta2 = Float.parseFloat(req.getParameter("Pregunta2"));
            float pregunta3 = Float.parseFloat(req.getParameter("Pregunta3"));
            float pregunta4 = Float.parseFloat(req.getParameter("Pregunta4"));
            float pregunta5 = Float.parseFloat(req.getParameter("Pregunta5"));
            float pregunta6 = Float.parseFloat(req.getParameter("Pregunta6"));
            float pregunta7 = Float.parseFloat(req.getParameter("Pregunta7"));
            float pregunta8 = Float.parseFloat(req.getParameter("Pregunta8"));
            float pregunta9 = Float.parseFloat(req.getParameter("Pregunta9"));
            float pregunta10 = Float.parseFloat(req.getParameter("Pregunta10"));
            float pregunta11 = Float.parseFloat(req.getParameter("Pregunta11"));
            float pregunta12 = Float.parseFloat(req.getParameter("Pregunta12"));
            float pregunta13 = Float.parseFloat(req.getParameter("Pregunta13"));
            float pregunta14 = Float.parseFloat(req.getParameter("Pregunta14"));
            float pregunta15 = Float.parseFloat(req.getParameter("Pregunta15"));
            
            status=proce.Status(SCE2005);
            
            if(status.get(1).equals("1")){//------------------------------------------Almacena Regular
            
             System.out.println("almacena regular");
             String AddEncuesta=proce.AddEncuesta(SCE2005, IdInscr, pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, 
                                                                   pregunta6, pregunta7, pregunta8, pregunta9, pregunta10, 
                                                                   pregunta11, pregunta12, pregunta13, pregunta14, pregunta15); 
            //String AddEncuesta="ok";
            if(AddEncuesta.equalsIgnoreCase("ok")){
                
                Per=proce.PeriodoActivo(SCE2005); 
                Ins=proce.Inscripcion(SCE2005, Usuario, Per.get(0).getPeriodo()); 
                PEva=proce.ProfesorEvaluado(SCE2005, Usuario, Per.get(0).getPeriodo());
                
                out.println("<table width=\"100%\">\n" +
                                        "<tr class=\"FondoTE\"> <td align=\"center\">No.</td> <td align=\"center\">Nombre del Profesor</td> <td align=\"center\">Materia</td> <td align=\"center\">Grupo</td> <td></td> </tr>");
                            for(int i=0; i < Ins.size(); i++){
                            int prog=i+1;
                            String NombProf=Ins.get(i).getNombreP()+" "+Ins.get(i).getApp()+" "+Ins.get(i).getAmp() ;
                            out.println("<tr class=\"FondoTC\">"+
                                            "<td>"+prog+"</td> <td>"+NombProf+"</td> <td>"+Ins.get(i).getNombreAsig()+"</td> <td align=\"center\">"+Ins.get(i).getGrupo()+"</td>"+
                                            "<td align=\"center\">");
                            int Eva=0;
                            for(int e=0; e < PEva.size(); e++){
                                if(Ins.get(i).getIdInscripcion() == PEva.get(e).getIdInscripcion()){
                                    Eva=1;
                                }     
                            }  
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
            else{
               out.println("Error2");
            }

            }
            else{//------------------------------------------Almacena Irregular
                
            String AddEncuesta_I=proce.AddEncuesta_I(SCE2005, IdInscr, pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, 
                                                                   pregunta6, pregunta7, pregunta8, pregunta9, pregunta10, 
                                                                   pregunta11, pregunta12, pregunta13, pregunta14, pregunta15); 
            //String AddEncuesta_I="ok";
            System.out.println("almacena irregular"); 
            if(AddEncuesta_I.equalsIgnoreCase("ok")){
                
                Per=proce.PeriodoActivo(SCE2005); 
                Ins=proce.Inscripcion(SCE2005, Usuario, Per.get(0).getPeriodo()); 
                PEva_I=proce.ProfesorEvaluado_I(SCE2005, Usuario, Per.get(0).getPeriodo());
                
                out.println("<table width=\"100%\">\n" +
                                        "<tr class=\"FondoTE\"> <td align=\"center\">No.</td> <td align=\"center\">Nombre del Profesor</td> <td align=\"center\">Materia</td> <td align=\"center\">Grupo</td> <td></td> </tr>");
                            for(int i=0; i < Ins.size(); i++){
                            int prog=i+1;
                            String NombProf=Ins.get(i).getNombreP()+" "+Ins.get(i).getApp()+" "+Ins.get(i).getAmp() ;
                            out.println("<tr class=\"FondoTC\">"+
                                            "<td>"+prog+"</td> <td>"+NombProf+"</td> <td>"+Ins.get(i).getNombreAsig()+"</td> <td align=\"center\">"+Ins.get(i).getGrupo()+"</td>"+
                                            "<td align=\"center\">");
                            int Eva=0;
                            for(int e=0; e < PEva_I.size(); e++){
                                if(Ins.get(i).getIdInscripcion() == PEva_I.get(e).getIdInscripcion()){
                                    Eva=1;
                                }     
                            }  
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
            else{
               out.println("Error2");
            }
            
            }
            
        }catch(Exception b){
            System.out.println(b);
            out.println("Error2");
        }
        
    }
    
}
