
package Generador;

public class G_Sintactico {
    public static void main(String[] args)
    {
        String opciones[] = new String[7];       
        
        opciones[0] = "-destdir"; //dirección de destino
        
        //Direccion de carpeta donde se va a generar el parser.java & el simbolosxxx.java
        opciones[1] = "src/main/java/Analizadores";
        
        opciones[2] = "-symbols"; 
        opciones[3] = "sym"; // nombre que tendra la clase de simbolos
        
        opciones[4] = "-parser";         
        opciones[5] = "Sintactico"; // nombre a la clase del paso anterior
        
        //Ubicacion archivo .cup 
        opciones[6] = "src/main/java/Analizadores/Sintactico.cup"; 
        try 
        {
            java_cup.Main.main(opciones);
        } 
        catch (Exception ex)
        {
            System.out.print(ex);
        }
    }
}
