/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Clases_E.GeneraExcel;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac3
 */
public class GExcel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
                response.setContentType("contentType=\"application/vnd.ms-excel\" pageEncoding=\"iso-8859-1\"");
            HttpSession session= request.getSession();
            String s=(String) session.getAttribute("Administrador");
            if(null!=s){
        try {
                 response.setHeader("Content-Disposition", "inline; filename=\""
                + "Listado"
                + ".xls\"");
        String Periodo=request.getParameter("PeriodoL");
        OutputStream out = response.getOutputStream();
        response.setContentType("CONTENT_TYPE_EXCEL");
        response.setHeader("expires", "-1");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("max-age", "0");
        //GeneraExcel ex = new GeneraExcel(Periodo);
        GeneraExcel ex = new GeneraExcel();
        ex.Genera(Periodo).write(out);
        out.close();

                }
        catch(Exception e){
            log("No se pudo crear el archivo excel: "+e.getMessage());
        }
    }
              else{
                 PrintWriter out2 = response.getWriter();
                 response.setContentType("text/html");  
                 out2.println("<script type=\"text/javascript\">");  
                 out2.println("alert('Sesión expirada');");  
                 out2.println("location.href='index.html'");  
                 out2.println("</script>");
                 log("sesión expirada");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


}
