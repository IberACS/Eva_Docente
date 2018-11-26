/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sql_E;

import Clases_E.Asignatura;
import Clases_E.Carrera;
import Clases_E.Evaluadores;
import Clases_E.Periodo;
import Clases_E.Personal;
import Clases_E.RespuestasE;
import static java.rmi.server.LogStream.log;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mac3
 */
public class Procedimientos {

    public Procedimientos() {
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
    
   public ArrayList<RespuestasE> Respuestas(Connection con,String periodo,int IdProfe,int Carrera){
       System.out.println("periodo: "+periodo+" profe: "+IdProfe+" carrera: "+Carrera);
    ArrayList preguntas= new ArrayList();
    try{
    CallableStatement st= con.prepareCall("{call dbo.EvaDocResulXP(?,?,?)}");
    st.setInt(1, Carrera);
    st.setInt(2,IdProfe);
    st.setString(3, periodo);
    st.execute();
    ResultSet r = st.getResultSet();  // se ejecuta la consulta
    double r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
    int total;
    while(r.next()){
    total=r.getInt(1);
    r1=r.getDouble(2);
    r2=r.getDouble(3);
    r3=r.getDouble(4);
    r4=r.getDouble(5);
    r5=r.getDouble(6);
    r6=r.getDouble(7);
    r7=r.getDouble(8);
    r8=r.getDouble(9);
    r9=r.getDouble(10);
    r10=r.getDouble(11);
    r11=r.getDouble(12);
    r12=r.getDouble(13);
    r13=r.getDouble(14);
    r14=r.getDouble(15);
    r15=r.getDouble(16);
    RespuestasE datos= new RespuestasE(total,r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
    preguntas.add(datos);
    }
    }
    catch(Exception e){
            System.out.println("Error en procedimientos Respuestas: "+e.getLocalizedMessage());
            }
    return preguntas;   
   }
   
    public ArrayList<RespuestasE> RespuestasM(Connection con,String periodo,int IdProfe,int Carrera,int Grupo){
      // System.out.println("periodo: "+periodo+" profe: "+IdProfe+" carrera: "+Carrera);
    ArrayList preguntas= new ArrayList();
    try{
    CallableStatement st= con.prepareCall("{call dbo.EvaDocResulXM(?,?,?,?)}");
    st.setInt(1, Carrera);
    st.setInt(2,IdProfe);
    st.setString(3, periodo);
    st.setInt(4,Grupo);
    st.execute();
    ResultSet r = st.getResultSet();  // se ejecuta la consulta
    float r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
    int total;
    while(r.next()){
    total=r.getInt(1);
    r1=r.getFloat(2);
    r2=r.getFloat(3);
    r3=r.getFloat(4);
    r4=r.getFloat(5);
    r5=r.getFloat(6);
    r6=r.getFloat(7);
    r7=r.getFloat(8);
    r8=r.getFloat(9);
    r9=r.getFloat(10);
    r10=r.getFloat(11);
    r11=r.getFloat(12);
    r12=r.getFloat(13);
    r13=r.getFloat(14);
    r14=r.getFloat(15);
    r15=r.getFloat(16);
    RespuestasE datos= new RespuestasE(total,r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
    preguntas.add(datos);
    }
    }
    catch(Exception e){
            System.out.println("Error en procedimientos Respuestas: "+e.getLocalizedMessage());
            }
    return preguntas;   
   }
   
   
   public ArrayList<Personal> ObtieneProfesorxCa(Connection con,String Periodo,int Carrera){
      ArrayList Persona = new ArrayList();
      try {
          Statement st=con.createStatement();
          String consulta="select  distinct (i.IdProfesor), d.Nombre, d.Paterno, d.Materno \n" +
                            "from Profesor d\n" +
                            "left join Inscripciones i on i.IdProfesor=d.IdProfesor\n" +
                            "left join Asignaturas a on i.IdAsigTESE=a.IdAsigTESE\n" +
                            "where i.Periodo='"+Periodo+"' and a.Carrera="+Carrera+"\n" +
                            "";
          ResultSet r=st.executeQuery(consulta);
          int IdProfesor;
          String Nombre,Paterno,Materno;
          while(r.next()){
              
          IdProfesor=r.getInt(1);
          Nombre=r.getString(2);
          Paterno=r.getString(3);
          Materno=r.getString(4);
          Personal per= new Personal(IdProfesor, Nombre, Paterno, Materno, Paterno);
          Persona.add(per);
          }
      } catch(Exception e){
         log("Error en  ObtieneProfesorxCarrera: "+e.getLocalizedMessage());
      }
       return Persona;
   }
   
      public ArrayList<Personal> ObtieneProfesorxM(Connection con,String Periodo,int Carrera,int Grupo){
           System.out.println("periodo: "+Periodo+" carrera: "+Carrera+" grupo: "+Grupo);
      ArrayList Persona = new ArrayList();
      try {
          Statement st=con.createStatement();
          String consulta="select  distinct (i.IdProfesor), d.Nombre, d.Paterno, d.Materno \n" +
                            "from Profesor d\n" +
                            "left join Inscripciones i on i.IdProfesor=d.IdProfesor\n" +
                            "left join Asignaturas a on i.IdAsigTESE=a.IdAsigTESE\n" +
                            "where a.Carrera="+Carrera+" and i.Periodo='"+Periodo+"' and i.Grupo="+Grupo+"";
          ResultSet r=st.executeQuery(consulta);
          int IdProfesor;
          String Nombre,Paterno,Materno;
          while(r.next()){
              
          IdProfesor=r.getInt(1);
          Nombre=r.getString(2);
          Paterno=r.getString(3);
          Materno=r.getString(4);
          Personal per= new Personal(IdProfesor, Nombre, Paterno, Materno, Paterno);
          Persona.add(per);
          }
      } catch(Exception e){
         log("Error en  ObtieneProfesorxCarrera: "+e.getLocalizedMessage());
      }
       return Persona;
   }
   
   public ArrayList<Carrera> Carreras(Connection con){
       ArrayList Carrera = new ArrayList();
       try{
       Statement st =con.createStatement();
       String Consulta="select IDCarrera,CarreraNombre from Carreras";
       ResultSet rs=st.executeQuery(Consulta);
       while(rs.next()){
       int IdCarrera=rs.getInt(1);
       String Nombre=rs.getString(2);
       Carrera car= new Carrera(IdCarrera, Nombre);
       Carrera.add(car);
       }
       }catch(Exception e){
           log("Error al obtener carreras: "+e.getLocalizedMessage());
           System.out.println("Error al obtener carreras: "+e.getLocalizedMessage());
       }
               return Carrera;
   }
   
   public ArrayList<Periodo> Periodo (Connection con){
       ArrayList Periodos= new ArrayList();
       try{
          Statement st=con.createStatement();
          String Consulta="select Periodo,Activo,LeyendaOficio from Cat_Periodos where Año>=2017 and Consecutivo>=55";
          ResultSet r=st.executeQuery(Consulta);
          while(r.next()){
          String periodo=r.getString(1);
          int Activo=r.getInt(2);
          String Leyenda=r.getString(3);
          
          Periodo per=new Periodo(periodo, Activo, Leyenda);
          Periodos.add(per);
       }
       }catch(Exception e){
           System.out.println("Se generó un error en Periodo "+e.getLocalizedMessage());
           log("Se generó un error en Periodo "+e.getLocalizedMessage());
       }
       return Periodos;
   }
   
   public ArrayList<Asignatura> Asignaturas (Connection con, String Periodo ,int Carrera, int IdProfesor){
       
       ArrayList Asigna= new ArrayList();
       try{
            Statement st= con.createStatement();
            String Consulta="select distinct (i.IdAsigTESE),a.Nombre,i.Grupo\n" +
            "from Inscripciones i,Asignaturas a,RespuestasEvaD e\n" +
            "where i.IdAsigTESE=a.IdAsigTESE and i.Periodo='"+Periodo+"'\n" +
            "and a.carrera="+Carrera+" and i.IdProfesor="+IdProfesor+"\n" +
            "and e.IdInscripcion=i.IdInscripcion";
            ResultSet r=st.executeQuery(Consulta);
            while(r.next()){
            int IdAsigTESE=r.getInt(1);
            String nombre=r.getString(2);
            String Grupo=r.getString(3);
            
            Asignatura as = new Asignatura(IdAsigTESE, nombre, Grupo);
            Asigna.add(as);
            }
       }catch(Exception e){
           log("Error para Asignaturas");
       }
       return Asigna;
   }
   
      public ArrayList<Asignatura> Asignaturas2 (Connection con, String Periodo ,int Carrera, int IdProfesor,int grupo){
        //  System.out.println("asignaturas2: periodo: "+Periodo+" Carrera: "+Carrera+" IdProfesor: "+IdProfesor+ "grupo"+grupo);
       ArrayList Asigna= new ArrayList();
       try{
            Statement st= con.createStatement();
            String Consulta="select distinct (i.IdAsigTESE),a.Nombre,i.Grupo\n" +
            "from Inscripciones i,Asignaturas a,RespuestasEvaD e\n" +
            "where i.IdAsigTESE=a.IdAsigTESE and i.Periodo='"+Periodo+"'\n" +
            "and a.carrera="+Carrera+" and i.IdProfesor="+IdProfesor+"\n" +
            "and e.IdInscripcion=i.IdInscripcion\n"+
            "and i.Grupo="+grupo+"";
            ResultSet r=st.executeQuery(Consulta);
            while(r.next()){
            int IdAsigTESE=r.getInt(1);
            String nombre=r.getString(2);
            String Grupo=r.getString(3);
            Asignatura as = new Asignatura(IdAsigTESE, nombre, Grupo);
            Asigna.add(as);
            }
       }catch(Exception e){
           log("Error para Asignaturas");
       }
       return Asigna;
   }
   
      
    public  ArrayList Total (Connection con,String Periodo){
        System.out.println("periodo: "+Periodo);
    ArrayList Mensaje=new ArrayList();
    try{
    CallableStatement st= con.prepareCall("{call dbo.EvaDocTotal(?)}");
    st.setString(1, Periodo);
    st.execute();
    ResultSet r = st.getResultSet();
    while(r.next()){
    //String res=r.getString(1);
    Mensaje.add(r.getString(1));
    //Mensaje.add(r.getString(2));
    //Mensaje=res;
       
    } }
    catch(Exception e){
        log("Error en TOTAL: "+e.getLocalizedMessage());
    }
    return Mensaje;
    }
    
        public String Docente(Connection con,int IdProfe){
        String respuesta = null;
        try{
        Statement st= con.createStatement();
        String Consulta="select Nombre, Paterno, Materno from Profesor where IdProfesor="+IdProfe+"";
        ResultSet rs=st.executeQuery(Consulta);
        while(rs.next()){

        String Nombre=rs.getString(1);
        String Paterno=rs.getString(2);
        String Materno=rs.getString(3);
        respuesta=Nombre+" "+Paterno+" "+Materno;
            System.out.println(" doc: "+respuesta);
        }
        }catch(SQLException e){
          log("Error sql en Docente: "+e.getSQLState());
        }
        return respuesta;
    }
    
    public ArrayList<Evaluadores>Evaluaron(Connection c,String periodo){
        ArrayList res= new ArrayList();
        try{
            Statement st=c.createStatement();
            String Consulta="select distinct (a.Matricula), a.Nombre, a.Paterno, a.Materno,c.CarreraNombre\n" +
                            "from Alumnos a, Inscripciones i, RespuestasEvaD e, Carreras c\n" +
                            "where i.Periodo='"+periodo+"' and\n" +
                            "i.IdInscripcion= e.IdInscripcion\n" +
                            "and a.Matricula=i.Matricula\n" +
                            "and a.Carrera=c.IDCarrera";
            ResultSet r=st.executeQuery(Consulta);
            while(r.next()){
            int Matricula=r.getInt(1);
            String Nombre=r.getString(2);
            String Paterno=r.getString(3);
            String Materno=r.getString(4);
            String Carrera=r.getString(5);
            Evaluadores ev= new Evaluadores(Matricula, Nombre, Paterno, Materno, Carrera);
            res.add(ev);
            }
        }catch(Exception e){
            log("Error en Inscritos: "+e.getLocalizedMessage());
        }
            
        return res;
    }   

        public ArrayList Persona(Connection con,int user){
           
        ArrayList respuesta = new ArrayList();
        try{
        Statement st= con.createStatement();
        String Consulta="select Nombre, Paterno, Materno, Titulo from Personal where IdPersona="+user+"";
        ResultSet rs=st.executeQuery(Consulta);
        while(rs.next()){
        String Nombre=rs.getString(1);
        String Paterno=rs.getString(2);
        String Materno=rs.getString(3);
        String Titulo=rs.getString(4);
        respuesta.add(Nombre);
        respuesta.add(Paterno);
        respuesta.add(Materno);
        respuesta.add(Titulo);
        }
        }catch(SQLException e){
          log("Error sql en Persona: "+e.getSQLState());
        }
        return respuesta;
    }
        
    public String Actualiza(Connection con,String Nombre,String Paterno,String Materno,int usu,String pass,String Titulo){
        System.out.println("Nombre: "+Nombre +Paterno+Materno+usu+pass);
    String Mensaje=null;
    try{
        String Consulta="update Personal set Nombre=?,Paterno=?, Materno=?, Titulo=?  where IdPersona=? and Password=?";
        PreparedStatement ps=con.prepareStatement(Consulta);
        ps.setString(1, Nombre);
        ps.setString(2, Paterno);
        ps.setString(3, Materno);
        ps.setString(4, Titulo);
        ps.setInt(5, usu);
        ps.setString(6, pass);
        ps.executeUpdate();
        ps.close();
        return Mensaje="si";
    } catch(Exception e){
         e.printStackTrace();
        return Mensaje="no";
    }    
    }

public String Activa(Connection con,int activa,int fase){
String Mensaje=null;   
try{
    PreparedStatement ps= con.prepareStatement("update Activa_EDocente set Activo=?, Regular=?");
    ps.setInt(1, activa);
    ps.setInt(2, fase);
    ps.executeUpdate();
    ps.close();
    return Mensaje="si";
}    
catch(Exception e){
    e.printStackTrace();
    return Mensaje="no";
}    
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


    public static void main(String[] args) {
        Procedimientos pr= new Procedimientos();
        Conexion con = new Conexion();
        Connection c;
        c=con.conexion_SCE2005();
        
        ArrayList<RespuestasE> respuesta;
        ArrayList<RespuestasE> Respuestas;
        //respuesta=pr.Respuestas(c);
        ArrayList<Personal> persona;
        ArrayList<Carrera> car;
        ArrayList<Asignatura> Asignaturas;
        persona=pr.ObtieneProfesorxCa(c,"2018-1",3);
         ArrayList<Periodo> periodo=null;
         periodo=pr.Periodo(c);
         car=pr.Carreras(c);
         ArrayList Total;
         ArrayList Persona;
         //Asignaturas=pr.Asignaturas(c, Periodo, 0, 0);
         //respuesta=pr.Respuestas(c, "2017-2", 508, 5);
         
//        Persona=pr.Persona(c, 8000);
//         
Respuestas=pr.Respuestas(c, "2018-1", 324, 6);
for (int i=0; i<Respuestas.size();i++){
            System.out.println("tamaño :"+Respuestas.get(i).getR1());
           
        }

        String Status;
        //Status=pr.Status(c);
        //System.out.println("Status: "+Status);
    }

}
