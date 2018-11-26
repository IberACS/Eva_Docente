/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Clases_E.Asignatura;
import Clases_E.Operaciones;
import Clases_E.Personal;
import Clases_E.RespuestasE;
import Sql_E.Conexion;
import Sql_E.Procedimientos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
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
public class MProfesores extends HttpServlet {
    
   
    private ArrayList<Personal> Profesor=null;
    private ArrayList<Asignatura> Asignaturas=null;
    private  ArrayList<RespuestasE> Respuestas=null;
    Operaciones op =new Operaciones();
    String Docente=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        String s=(String) session.getAttribute("Administrador");
        if(null!=s){
        try  {
            Procedimientos pr= new Procedimientos();
            Conexion c= new Conexion();
            Connection con;
            
            String periodo=request.getParameter("periodo");
            String carrera=request.getParameter("carrera");
            String IdProfesor=request.getParameter("idprofesor");
            int cambio=Integer.parseInt(request.getParameter("cambio"));
            con=c.conexion_SCE2005();
          
switch (cambio)
{
    case 1: cambio=1;
        try{////////////////////////////////////////////////////////////////////////////Sección 1///////////////////////////////////////////////////////////////////////////////////////////////////
                      
                out.println("<div id=\"sec2\">");
                Profesor=pr.ObtieneProfesorxCa(con, periodo,Integer.parseInt(carrera) );
              
                if(Profesor.isEmpty()){
                out.println("<br><div id=\"error\"><center><table><tr><td><font size=\"4\" color=\"red\">¡No se encontrarón resultados! </font></td></tr></table></center></div>");
                }else{
                        out.println("<form onsubmit=\"return false\" >"
                                + "<h4> Profesor:<select name=\"idp\" id=\"idp\" onchange=\"MuestraMaterias('MProfesores','sec3');\">"
                                + "<option value=\"0\">Selecciona</option>");
                        for(int i=0;i<Profesor.size();i++){ 
                        out.println("<option value=\""+Profesor.get(i).getIdPersona()+"\">"+Profesor.get(i).getNombre()+" "+Profesor.get(i).getPaterno()+" "+Profesor.get(i).getMaterno());
                        out.println("</option>");
                        }
                        out.println("</select>");
                        out.println("<input type=\"text\" value=\""+carrera+"\" id=\"car\" name=\"car\" hidden>"
                                 + "<input type=\"text\" value=\""+periodo+"\" id=\"per\" name=\"per\" hidden>"
                                 + "");
                        
                        out.println("<input type=\"text\" value=\"2\" id=\"cambio2\" name=\"cambio2\" hidden>" );
                        out.println("</form><div id=\"sec3\"></div>"
                                + "</div>" );
                }
                }catch(Exception e){
                    log("Se generó un error en Secciòn 1: "+e.getLocalizedMessage()); 
                }
    break;    
///////////////////////////////////////////////////////////////////////////////Fin Sección 1/////////////////////////////////////////////////////////////////////////////////////////////////
    case 2: cambio=2;
    try {////////////////////////////////////////////////////////////////////////////Sección 2///////////////////////////////////////////////////////////////////////////////////////////////////
       
        Asignaturas=pr.Asignaturas(con, periodo,Integer.parseInt(carrera), Integer.parseInt(IdProfesor));
        
        if(Asignaturas.isEmpty()){
                out.println("<br><div id=\"error\"><center><table><tr><td><font size=\"4\" color=\"red\">¡No se encontrarón resultados! </font></td></tr></table></center></div>");
            }
        else{
                        out.println("<br><div id=\"sec3\">");
                            out.println("<style type=\"text/css\">\n" +
                                ".tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc; }\n" +
                                ".tg td{text-align:center; font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;border-top-width:1px;border-bottom-width:1px;}\n" +
                                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;border-top-width:1px;border-bottom-width:1px;}\n" +
                                ".tg .tg-2ji4{background-color:#f9f9f9;font-size:11px;font-family:Arial, Helvetica, sans-serif !important;;vertical-align:top}\n" +
                                ".tg .tg-yw4l{vertical-align:top}\n" +
                                ".tg .tg-b7b8{background-color:#f9f9f9;vertical-align:top}\n" +
                                "</style>\n" +
                                "<form onsubmit=\"return false\" ><table class=\"tg\" width=\"100%\">\n");
       out.println("            <tr>\n" +
                                "     <th class=\"tg-031e\">MATERIA</th>\n" +
                                 "    <th class=\"tg-031e\">GRUPO</th>\n" +
                                "  </tr>\n" );
       for(int i=0;i<Asignaturas.size();i++){
       out.println(" <tr>\n" );
       out.println("<input type=\"text\" value=\""+carrera+"\" id=\"carrera\" name=\"carrera\" hidden>");
       out.println("<td class=\"tg-b7b8\">"+Asignaturas.get(i).getNombre()+"</td>\n"+
                   "<td class=\"tg-b7b8\"><a href=\"#\" title=\"Graficar Materia\" onclick=\"GraficaDocentes2MM('MMateriaD','sec3',"+Asignaturas.get(i).getGrupo()+");\">"+Asignaturas.get(i).getGrupo()+"</a></td>"
                   + "<input type=\"hidden\" value=\""+Asignaturas.get(i).getGrupo()+"\" id=\"grupo\" name=\"grupo\">");       
       out.println("<input type=\"text\" value=\"3\" id=\"cambio3\" name=\"cambio3\" hidden>"
                + "<input type=\"text\" value=\""+IdProfesor+"\" id=\"idp\" name=\"idp\" hidden>"
                + "<input type=\"text\" value=\""+periodo+"\" id=\"per\" name=\"per\" hidden>"
                + "</tr>");
        } 
         out.println(""
                + "</table><br><input type=\"submit\" class=\"myButton\" value=\"Graficar\" id=\"g1\" name=\"g1\" onclick=\"GraficaDocentes('MProfesores','sec3');this.form.reset();\" ></form>");
                        out.println("</div><div id=\"sec3\"></div><br><br>"
                                + "" );
            }           
    }catch(Exception e){
        log("Ocurrio un error en Seccion 2: "+e.getLocalizedMessage());
    }
    break;
    ////////////////////////////////////////////////////////////////////////////Fin Sección 2///////////////////////////////////////////////////////////////////////////////////////////////////
    case 3: cambio=3;
    try{
    //System.out.println("periodo "+periodo+" carrera: "+carrera+" IdProfesor: "+IdProfesor );   

    Respuestas=pr.Respuestas(con, periodo, Integer.parseInt(IdProfesor),Integer.parseInt(carrera));
    //Docente=pr.Docente(con,Integer.parseInt(IdProfesor));
    double Dominio,Planificacion,Ambientes,Estrategias,Motivacion,Evaluacion,Comunicacion,Gestion,Tecnologia,Satisfaccion;
    double TDominio,TPlanificacion,TAmbientes,TEstrategias,TMotivacion,TEvaluacion,TComunicacion,TGestion,TTecnologia,TSatisfaccion;
    Dominio=(Respuestas.get(0).getR1()+Respuestas.get(0).getR2())/Respuestas.get(0).getTotal();
    Planificacion=(Respuestas.get(0).getR3())/Respuestas.get(0).getTotal();
    Ambientes=(Respuestas.get(0).getRr4())/Respuestas.get(0).getTotal();
    Estrategias=(Respuestas.get(0).getR5()+Respuestas.get(0).getR6())/Respuestas.get(0).getTotal();
    Motivacion=(Respuestas.get(0).getR7()+Respuestas.get(0).getR8())/Respuestas.get(0).getTotal();
    Evaluacion=(Respuestas.get(0).getR9()+Respuestas.get(0).getR10()+Respuestas.get(0).getR11())/Respuestas.get(0).getTotal();
    Comunicacion=(Respuestas.get(0).getR12())/Respuestas.get(0).getTotal();
    Gestion=(Respuestas.get(0).getR13())/Respuestas.get(0).getTotal();
    Tecnologia=(Respuestas.get(0).getR14())/Respuestas.get(0).getTotal();
    Satisfaccion=(Respuestas.get(0).getR15())/Respuestas.get(0).getTotal();
    
    TDominio=Dominio/2;
    TPlanificacion=Planificacion;
    TAmbientes=Ambientes;
    TEstrategias=Estrategias/2;
    TMotivacion=Motivacion/2;
    TEvaluacion=Evaluacion/3;
    TComunicacion=Comunicacion;
    TGestion=Gestion;
    TTecnologia=Tecnologia;
    TSatisfaccion=Satisfaccion;
    
    //System.out.println("Dominio :"+TDominio+" Planificacion: "+TPlanificacion+" Ambiente: "+TAmbientes+" Estrategias: "+TEstrategias+"\n"
    //+ " TMotivacion: "+TMotivacion+" TEvaluacion: "+TEvaluacion+ " TComunicacion: "+TComunicacion+" TGestion: "+TGestion+" TTecnologia: "+TTecnologia+" TSatisfaccion: "+TSatisfaccion);
    //String leyenda="PROFESOR(A): "+Docente;
    String leyenda2="CUESTIONARIOS APLICADOS: "+Respuestas.get(0).getTotal();
    out.println(" <div id=\"sec3\">\n" );
//    out.println(" <table align=\"left\" ><tr><td><h3>"+leyenda+"</td></tr>"
//            + "   <tr><td><h3>"+leyenda2+"</td></tr>"
//            + "   </table>" );
    DecimalFormat decimales = new DecimalFormat("0.00");
    double DesemTO= (TDominio+TPlanificacion+TAmbientes+TEstrategias+TMotivacion+TEvaluacion+TComunicacion+TGestion+TTecnologia+TSatisfaccion)/10;
    out.println("<table width=\"50%\" ><tr><td><img width=\"800\"  height=\"600\" id=\"imgs\" src=\"Grafica?PDA="+decimales.format(TDominio)+"&PPC="+decimales.format(TPlanificacion)+"&"
                                                     + "PAA="+decimales.format(TAmbientes)+"&PEM="+decimales.format(TEstrategias)+"&"
                                                     + "PM="+decimales.format(TMotivacion)+"&PE="+decimales.format(TEvaluacion)+"&"
                                                     + "PC="+decimales.format(TComunicacion)+"&PG="+decimales.format(TGestion)+"&"
                                                     + "PTLA="+decimales.format(TTecnologia)+"&PS="+decimales.format(TSatisfaccion)+"&DesemTO="+decimales.format(DesemTO)+"&leyenda="+IdProfesor+"&leyenda2="+leyenda2+"\"/>"
                + "</td></tr></table>" );
                        out.println("<div id=\"tabla\" >");
                            out.println("<table border=\"1\" width=\"70%\">");
                                out.println("<tr >"); //para imprimir

                                    out.println("<td  width=\"10%\" bgcolor=\"#ef7070\">"+
                                    "<font color=\"white\">Dominio de la disciplina</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#7070ef\">"+
                                    "<font color=\"white\">Planificación del Curso</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#78f778\">"+
                                    "<font color=\"white\">Ambientes de Aprendizaje</font></td>");  
                                    out.println("<td  width=\"10%\" bgcolor=\"#efef70\">"+
                                    "<font color=\"white\">Estrategias, Métodos y Técnicas</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#ef70ef\">"+
                                    "<font color=\"white\">Motivación</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#70efef\">"+
                                    "<font color=\"white\">Evaluación del Aprendizaje</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#f7bbbb\">"+
                                    "<font color=\"white\">Comunicación</font></td>");  
                                    out.println("<td  width=\"10%\" bgcolor=\"#909090\">"+
                                    "<font color=\"white\">Gestión del Curso</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#c03030\">"+
                                    "<font color=\"white\">Tecnologia de la Información y la Comunicación</font></td>"); 
                                    out.println("<td  width=\"10%\" bgcolor=\"#3030c0\">"+
                                    "<font color=\"white\">Satisfacción</font></td>");
                                    out.println("<td  width=\"10%\" bgcolor=\"#2bb446\">"+
                                    "<font color=\"white\">Resultado Global</font></td>");
                                out.println("</tr>");
                                out.println("<tr >"); //para imprimir
                                   

                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TDominio)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TPlanificacion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TAmbientes)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TEstrategias)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TMotivacion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TEvaluacion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TComunicacion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TGestion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TTecnologia)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(TSatisfaccion)+"</font></td>");
                                        out.println("<td  width=\"9%\" >"+
                                        "<font >"+op.nivel(DesemTO)+"</font></td>");
                                        
                                out.println("</tr>");
                                out.println("<div id=\"porcentajesNombrados\">");
                                    out.print("<input type=\"hidden\" name=\"PDA2\" id=\"PDA2\" value=\""+op.nivel(TDominio)+" "+decimales.format(TDominio)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PPC2\" id=\"PPC2\" value=\""+op.nivel(TPlanificacion)+" "+decimales.format(TPlanificacion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PAA2\" id=\"PAA2\" value=\""+op.nivel(TAmbientes)+" "+decimales.format(TAmbientes)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PEM2\" id=\"PEM2\" value=\""+op.nivel(TEstrategias)+" "+decimales.format(TEstrategias)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PM2\" id=\"PM2\" value=\""+op.nivel(TMotivacion)+" "+decimales.format(TMotivacion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PE2\" id=\"PE2\" value=\""+op.nivel(TEvaluacion)+" "+decimales.format(TEvaluacion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PC2\" id=\"PC2\" value=\""+op.nivel(TComunicacion)+" "+decimales.format(TComunicacion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PG2\" id=\"PG2\" value=\""+op.nivel(TGestion)+" "+decimales.format(TGestion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PTLA2\" id=\"PTLA2\" value=\""+op.nivel(TTecnologia)+" "+decimales.format(TTecnologia)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"PS2\" id=\"PS2\" value=\""+op.nivel(TSatisfaccion)+" "+decimales.format(TSatisfaccion)+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"RG2\" id=\"RG2\" value=\""+op.nivel(DesemTO)+" "+decimales.format(DesemTO)+"\"  >");
                                    //out.print("<input type=\"hidden\" name=\"NombrePro\" id=\"NombrePro\" value=\""+leyenda+"\"  >");
                                    out.print("<input type=\"hidden\" name=\"period\" id=\"period\" value=\""+periodo+"\"  >");
                                out.println("</div>");

                    out.println("</table>");
                            
                    out.println("</div>");
                    
                    out.println("<div id=\"imprime\"  >");
                    out.print("<input  class=\"myButton\"  type=\"submit\" value=\"Imprimir\" onclick=\"generarPDF() \" >");
                    out.println("</div>");
                    out.println(" </div>\n" );
    }catch(Exception e){
        log("Error en Sec3: "+e.getLocalizedMessage());
    }
    break;

    default: cambio=0;
    out.println("<br><div id=\"error\"><center><table><tr><td><font size=\"4\" color=\"red\">¡No se encontrarón resultados! </center></div><br><br>");
    break;    
    }
            out.println("</body>");
            out.println("</html>");
            
        }
        catch(Exception e){
            log("Error no controlado: "+e.getLocalizedMessage());
            throw e;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


}
