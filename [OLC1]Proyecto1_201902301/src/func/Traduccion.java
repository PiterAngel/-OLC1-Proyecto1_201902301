
package func;

import java.util.LinkedList;


public class Traduccion {
    
    public static LinkedList<String>Trad= new LinkedList<>();  
    public static int waozzz =0;
    
    
    
    // Agregar Tabulaciones
    public static LinkedList<String>tab(LinkedList<String> lista){
        String tabs = "";
        for (int i = 0; i < waozzz; i++) {
            tabs = "\t"+tabs;
        }
        
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, tabs+lista.get(i));
        }
        
        return lista;
    }
    
    public static void database(){
       Trad.forEach((valor)->{
            System.out.println(valor);
        });
        
    }
    
    
    
}
