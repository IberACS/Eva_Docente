/**
 *
 * @author mac2
 */

package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CerSesion extends HttpServlet{
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        CerSesion(req, response);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        CerSesion(req, response);
    }
       public void CerSesion(HttpServletRequest req, HttpServletResponse response)throws IOException{
  try{  
      response.setContentType("text/html");
         response.setContentType("onload='nobackbutton();'");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, mustrevalidate");
        response.setDateHeader("Expires", 0);
        response.setHeader("Last-Modified","content=0");
       HttpSession sesion = req.getSession(true);
       //sesion.removeAttribute("usu");
        Enumeration<String> r = sesion.getAttributeNames();
        while (r.hasMoreElements()) {
            String n = r.nextElement();
            sesion.removeAttribute(n);
        }
       sesion.invalidate();
       response.sendRedirect("index.html");
   
  }catch(Exception e){
   e.printStackTrace();
   }     
  }
    
}