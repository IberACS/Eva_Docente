/**
 *
 * @author mac2
 */

package Clases;


public class Foto {
    
    private byte[] Imagen;
    
    public Foto(){
}
    
    public Foto(byte[] Imagen){
         this.Imagen=Imagen;
}
    
     public byte[] getImagen() {
        return Imagen;
    }

}
