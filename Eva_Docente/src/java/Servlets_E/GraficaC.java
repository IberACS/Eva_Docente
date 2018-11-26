/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author mac3
 */
public class GraficaC extends HttpServlet {
  File f= new File("Gráficos.png");
   
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int b=Integer.parseInt(request.getParameter("b"));
          System.out.println("b: "+b);
        int resta=Integer.parseInt(request.getParameter("resta"));
         System.out.println("resta: "+resta);
        
        try{ 
        OutputStream out = response.getOutputStream();

        DefaultPieDataset pieDataset = new DefaultPieDataset();
                        pieDataset.setValue("CONTESTADOS", new Integer(b));
                        pieDataset.setValue("SIN CONTESTAR", new Integer(resta));
                           //datos.setValue(PDA,"A: "+PDA+"","A");
                        JFreeChart chart = ChartFactory.createPieChart(
                                "Estadísticas",
                                pieDataset,
                                true,
                                true,
                                false
                        );
                       chart.setBackgroundPaint(Color.WHITE);
                       response.setContentType("image/png");
                       ChartUtilities.writeChartAsPNG(out,chart, 1200, 600);
                       ChartUtilities.saveChartAsPNG(f, chart, 1200, 600);
        
        }catch (Exception e)
                    {
                       System.out.println(e.toString());
                       log("error al crear la gráfica: "+e.getLocalizedMessage());
                    }

      }
    public String Imagen(){
        String ruta=null;
        ruta=f.getAbsolutePath();
        return ruta;
    }
    
    public boolean Borra(){
        boolean ruta;
        ruta=f.delete();
        
        return ruta;
    }

}
