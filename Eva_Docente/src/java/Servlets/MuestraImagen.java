/**
 *
 * @author mac2
 */

package Servlets;

import Clases.*;
import Sql.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MuestraImagen extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        MuestraImagen(req, res);
    }

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        MuestraImagen(req, res);
    }

    private byte[] im;
    private final Conexion2005 con = new Conexion2005();
    private Connection SCE2005;
    private final Procedimientos proce = new Procedimientos();
    private ArrayList<Foto> Fo;
    String usuario;

    protected void MuestraImagen(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("image/jpeg");
        OutputStream sos = res.getOutputStream();
         
        HttpSession session = req.getSession();
        usuario = String.valueOf(session.getAttribute("usu"));
        
        SCE2005 = con.conexion_SCE2005();
        Fo = proce.Imagen(SCE2005, usuario);
       
        im = Fo.get(0).getImagen();

        if (im == null) {
            byte[] resultado;
            ServletContext sc = getServletContext();
            String filename = sc.getRealPath("/images/faltaInf.jpg");
            File f = new File(filename);
            int size = (int) f.length();
            resultado = new byte[size];
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(f));
            stream.read(resultado);
            sos.write(resultado);
            sos.flush();
            sos.close();
        } else {
            sos.write(im);
            sos.flush();
            sos.close();
        }
    }
}