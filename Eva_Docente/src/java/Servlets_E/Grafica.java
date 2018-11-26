/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Sql_E.Conexion;
import Sql_E.Procedimientos;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author mac3
 */
public class Grafica extends HttpServlet {

    File f= new File("Gráficos.png");
    //DecimalFormat decimales = new DecimalFormat("0.00");
    String Docente=null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Conexion c= new Conexion();
        Connection con;
        con=c.conexion_SCE2005();
         Procedimientos pr= new Procedimientos();
        //response.setContentType("text/html;charset=iso-8859-1");
        try {
            OutputStream out = response.getOutputStream();
            double PDA =  Double.parseDouble(request.getParameter("PDA"));
            double PPC = Double.parseDouble(request.getParameter("PPC"));
            double PAA = Double.parseDouble(request.getParameter("PAA"));
            double PEM = Double.parseDouble(request.getParameter("PEM"));
            double PM = Double.parseDouble(request.getParameter("PM"));
            double PE = Double.parseDouble(request.getParameter("PE"));
            double PC = Double.parseDouble(request.getParameter("PC"));
            double PG = Double.parseDouble(request.getParameter("PG"));
            double PTLA = Double.parseDouble(request.getParameter("PTLA"));
            double PS = Double.parseDouble(request.getParameter("PS"));
            double Total= Double.parseDouble(request.getParameter("DesemTO"));
            String leyenda = request.getParameter("leyenda");
            String leyenda2 = request.getParameter("leyenda2");
            Docente=pr.Docente(con,Integer.parseInt(leyenda));
            HttpSession session= request.getSession();
            String s=(String) session.getAttribute("Administrador");
            if(null!=s){
            try{
                        System.out.println("leyenda: "+Docente+" "+leyenda2);      
                       DefaultCategoryDataset datos = new DefaultCategoryDataset();
                       datos.setValue(PDA,"A: "+PDA+"","A");
                       datos.setValue(PPC,"B: "+PPC+"","B");
                       datos.setValue(PAA,"C: "+PAA+"","C");
                       datos.setValue(PEM,"D: "+PEM+"","D");
                       datos.setValue(PM,"E: "+PM+"","E");
                       datos.setValue(PE,"F: "+PE+"","F");
                       datos.setValue(PC,"G: "+PC+"","G");
                       datos.setValue(PG,"H: "+PG+"","H");
                       datos.setValue(PTLA,"I: "+PTLA+"","I");
                       datos.setValue(PS,"J: "+PS+"","J");
                      
//                       float Subtotal=(float) (PDA+PPC+PAA+PEM+PM+PE+PC+PG+PTLA+PS);
//                       float Total1=Subtotal/10;
//                       String Total=String.valueOf(decimales.format(Total1));
                       datos.setValue(Total,"T: "+Total+"","T");
                        //Se crea el gráfico y se asignan algunas caracteristicas
                        JFreeChart grafico_barras = ChartFactory.createBarChart3D(
                               "PROFESOR(A): "+Docente+"\n"+leyenda2,
                               "Módulos", 
                               "Promedios",
                                datos, 
                                PlotOrientation.VERTICAL, 
                                true, 
                                true, 
                                false);
                   
                       grafico_barras.setBackgroundPaint(Color.white);
                       response.setContentType("image/png");
                       ChartUtilities.writeChartAsPNG(out, grafico_barras, 1200, 600);
                       // System.out.println("ruta2: "+f.getAbsolutePath());
                       ChartUtilities.saveChartAsPNG(f, grafico_barras, 1200, 600);

                    }
                    catch (Exception e)
                    {
                       System.out.println(e.toString());
                       log("error al crear la gráfica: "+e.getLocalizedMessage());
                    }
                    finally
                    {
                       out.close();
                    }
            } else{
                 PrintWriter out2 = response.getWriter();
                 response.setContentType("text/html");  
                 out2.println("<script type=\"text/javascript\">");  
                 out2.println("alert('Sesión expirada');");  
                 out2.println("location.href='index.html'");  
                 out2.println("</script>");
                 log("sesión expirada");
        }
        }catch(Exception e){
            log("error al crear la gráfica: "+e.getLocalizedMessage());
        }
    }

    public String Imagen(){
        String ruta=null;
        ruta=f.getAbsolutePath();
        System.out.println("ruta: "+ruta);
        return ruta;
    }
    
    public boolean Borra(){
        boolean ruta;
        ruta=f.delete();
        
        return ruta;
    }

}
