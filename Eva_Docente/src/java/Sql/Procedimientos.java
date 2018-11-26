/**
 *
 * @author mac2
 */

package Sql;

import Clases.*;
import static java.rmi.server.LogStream.log;
import java.sql.*;
import java.util.*;


public class Procedimientos {
    
    private ArrayList arr;
    
    public Procedimientos(){
    }
  
public String RevisaUsuario(Connection con, String usuario,String pass){
    String Mensaje=null;
     try {
            
            CallableStatement cstmt = con.prepareCall("{call dbo.VerificaUsuario(?,?,?)}");
            cstmt.setString(1, usuario);
            cstmt.setString(2, pass);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR, 3);
            cstmt.execute();
            Mensaje = cstmt.getString(3);
        }
    catch(Exception e){
        log("Error en Procedimiento RevisaUsuario: "+e.getLocalizedMessage());
    }
        return Mensaje;
}
    
public ArrayList<Login>Password(Connection con, String Matricu) {
       arr= new <Login>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.sp_SelPassWordAlumno(?)}");
      cstmt.setString(1,Matricu);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos  
          String Pass=r.getString(1);
          Login alu=new Login(Pass);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<Alumnos> Alumno(Connection con,String User) {
          arr= new <Alumnos>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.SacaAlumnoXMatricula(?)}");
      cstmt.setString(1,User);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos     
         
          String Carrera=r.getString("CarreraNombre");
          String Nombre=r.getString("Nombre");
          String AP=r.getString("Paterno");
          String AM=r.getString("Materno");
          
        Alumnos alu=new Alumnos(Carrera, Nombre+" "+AP+" "+AM);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<Foto> Imagen(Connection con,String User) {
          arr= new <Foto>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.sp_SelAlumFoto(?)}");
      cstmt.setString(1,User);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos     
            
          byte[] Imag=r.getBytes("Imagen"); 
          
        Foto alu=new Foto(Imag);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<Periodo>PeriodoActivo(Connection con) {
       arr= new <Periodo>ArrayList();
   try {
      CallableStatement cstmt = con.prepareCall("{call dbo.sp_SelPeriodoInscripcion()}");
      cstmt.execute();
      ResultSet r = cstmt.getResultSet();
        while(r.next()){  
          String PeriodoA=r.getString("Periodo");
          Periodo alu=new Periodo(PeriodoA);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<Inscripciones>Inscripcion(Connection con, String User, String Periodo) {
       arr= new <Inscripciones>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.SacaInscripciones(?,?)}");
      cstmt.setString(1,Periodo);
      cstmt.setString(2,User);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos 
            
          int IdInscripcion=r.getInt("IdInscripcion");
          String NombreAsig=r.getString("NombreAsig"); 
          String Grupo=r.getString("Grupo");
          String NombreP=r.getString("NombreProf"); 
          String App=r.getString("Paterno");
          String Amp=r.getString("Materno");
          
          Inscripciones alu=new Inscripciones(IdInscripcion, NombreAsig, Grupo, NombreP, App, Amp);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<ProfEva>ProfesorEvaluado(Connection con, String User, String Periodo) {
       arr= new <ProfEva>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.Sel_ProfesorEvaluado(?,?)}");
      cstmt.setString(1,User);
      cstmt.setString(2,Periodo);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos 
            
          int IdInscripcion=r.getInt("IdInscripcion");
          
          ProfEva alu=new ProfEva(IdInscripcion);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}
public ArrayList<ProfEva>ProfesorEvaluado_I (Connection con, String User, String Periodo) {
       arr= new <ProfEva>ArrayList();//se crea un arrayList de alumnos
   try {
       //se crea un objetos de tipo AllableStatement y se prepara para llamar el procedimiento almacendado
      CallableStatement cstmt = con.prepareCall("{call dbo.Sel_ProfesorEvaluado_I(?,?)}");
      cstmt.setString(1,User);
      cstmt.setString(2,Periodo);
      //se pone el parametro solicitado
      cstmt.execute();//se ejecuta el procedimiento
        //se extrae un resulset de el objeto de tipo CallableStatement
        ResultSet r = cstmt.getResultSet();
        while(r.next()){     //ciclo para extraer los datos 
            
          int IdInscripcion=r.getInt("IdInscripcion");
          
          ProfEva alu=new ProfEva(IdInscripcion);
            arr.add(alu);//se crean los alumnos y se adieren al ArrayList
           }
   }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }      
    return arr;
}

public ArrayList<Preguntas>PreguntasEvaD(Connection con) {
       arr= new <Preguntas>ArrayList();
   try {
      CallableStatement cstmt = con.prepareCall("{call dbo.Sel_PreguntasEvaD()}");
      cstmt.execute();
      ResultSet r = cstmt.getResultSet();
      while(r.next()){   
          String Pregunta=r.getString("Pregunta");
          Preguntas alu=new Preguntas(Pregunta);
            arr.add(alu);
         }
       }
   catch (Exception e) {
      e.printStackTrace();
   }      
    return arr;
 }


public String AddEncuesta(Connection con, int Idinsc, float Pregunta1,float Pregunta2,float Pregunta3,float Pregunta4,float Pregunta5,
                                                      float Pregunta6,float Pregunta7,float Pregunta8,float Pregunta9,float Pregunta10,
                                                      float Pregunta11,float Pregunta12,float Pregunta13,float Pregunta14,float Pregunta15){
        String Mensaje = null;   
    try {
        CallableStatement cstmt = con.prepareCall("{call dbo.AddEva_Docente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cstmt.setInt(1, Idinsc);
        cstmt.setFloat(2, Pregunta1);
        cstmt.setFloat(3, Pregunta2);
        cstmt.setFloat(4, Pregunta3);
        cstmt.setFloat(5, Pregunta4);
        cstmt.setFloat(6, Pregunta5);
        cstmt.setFloat(7, Pregunta6);
        cstmt.setFloat(8, Pregunta7);
        cstmt.setFloat(9, Pregunta8);
        cstmt.setFloat(10, Pregunta9);
        cstmt.setFloat(11, Pregunta10);
        cstmt.setFloat(12, Pregunta11);
        cstmt.setFloat(13, Pregunta12);
        cstmt.setFloat(14, Pregunta13);
        cstmt.setFloat(15, Pregunta14);
        cstmt.setFloat(16, Pregunta15);
        cstmt.execute();
        Mensaje="ok";
      }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }
   return Mensaje;//se retorna un ArrayList
}


public String AddEncuesta_I(Connection con, int Idinsc, float Pregunta1,float Pregunta2,float Pregunta3,float Pregunta4,float Pregunta5,
                                                      float Pregunta6,float Pregunta7,float Pregunta8,float Pregunta9,float Pregunta10,
                                                      float Pregunta11,float Pregunta12,float Pregunta13,float Pregunta14,float Pregunta15){
        String Mensaje = null;   
    try {
        CallableStatement cstmt = con.prepareCall("{call dbo.AddEva_Docente_I(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cstmt.setInt(1, Idinsc);
        cstmt.setFloat(2, Pregunta1);
        cstmt.setFloat(3, Pregunta2);
        cstmt.setFloat(4, Pregunta3);
        cstmt.setFloat(5, Pregunta4);
        cstmt.setFloat(6, Pregunta5);
        cstmt.setFloat(7, Pregunta6);
        cstmt.setFloat(8, Pregunta7);
        cstmt.setFloat(9, Pregunta8);
        cstmt.setFloat(10, Pregunta9);
        cstmt.setFloat(11, Pregunta10);
        cstmt.setFloat(12, Pregunta11);
        cstmt.setFloat(13, Pregunta12);
        cstmt.setFloat(14, Pregunta13);
        cstmt.setFloat(15, Pregunta14);
        cstmt.setFloat(16, Pregunta15);
        cstmt.execute();
        Mensaje="ok";
      }
   catch (Exception e) {//se captura cualquier error que puede pasar
      e.printStackTrace();
   }
   return Mensaje;//se retorna un ArrayList
}

public ArrayList Status(Connection con){
   ArrayList datos = new ArrayList();
   
   try{
     Statement st= con.createStatement();
     String Consulta="select Activo,Regular from Activa_EDocente"; 
     ResultSet rs=st.executeQuery(Consulta);
     while(rs.next()){
     datos.add(rs.getString(1));
     datos.add(rs.getString(2));
     }
   }
   catch(Exception e){
   e.printStackTrace();
   }
   return datos;
}

}
