/**
 *
 * @author mac2
 */

package Clases;


public class Alumnos {

String Carrera, Nombre;
    
  
public Alumnos(){
}
    
public Alumnos(String Carrera, String Nombre){
        
    this.Carrera=Carrera;
    this.Nombre=Nombre;
    
}
    public String getCarrera() {
        return Carrera;
    }
    
    public String getNombre() {
        return Nombre;
    }
}