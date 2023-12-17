package py1;

import gui.InterfaZzz;
import java.io.StringReader;

public class Py1 {

    public static void main(String[] args) {
       //para mandar a llamar desde la interfaz 
        InterfaZzz pantalla = new InterfaZzz();
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        
        
                // TODO code application logic here
        
        analizadores("src/compi/", "Lexer.jflex","Parser.cup");
        analizadoresjson("src/analizarxd/", "Lexer.jflex","Parser.cup");
      
       
       String entrada = """
                        
                        //ola
                        /* ola 
                            */
                        void main ( ){
                            int c = 2;
                            if (b > a){
                            		Console.Write("b mayor que a");
                            	}
                        }    
                        """;
       //func.Funcion.mostrar(entrada);
       
       
       //analizar(entrada); 
       /*double [] numeros ={1.0,2.0,3.0,4.0,5.0};
       String[] ejeX={ "1","2","3","4","5"};
       Graficas.graf.barras("Grafica1", "EjeX", "EjeY", numeros, ejeX);  
       Graficas.graf.linea("Grafica1", "EjeX", "EjeY", numeros, ejeX); 
       Graficas.graf.Pie("Grafica1", "EjeX", "EjeY", numeros, ejeX); */
       for (String i : data.Info.listaVariables.keySet()){
           System.out.println("Key: " + i + " Value: " + data.Info.listaVariables.get(i));
       }
       
       
       
       //System.out.println(compi.Parser.resultado);
       //System.out.println(data.Info.listaErrores);
       
       /*System.out.println("\n*** Errores ***");
       data.Info.listaErrores.forEach((t)->{
           System.out.println(t.toString());
        });*/
    
       
        
        
        
        
        
    }
    
    
   
    
        public static void analizadores(String ruta, String jflexFile, String cupFile){
        try {
            String opcionesJflex[] =  {ruta+jflexFile,"-d",ruta};
            jflex.Main.generate(opcionesJflex);

            String opcionesCup[] =  {"-destdir", ruta,"-parser","Parser",ruta+cupFile};
            java_cup.Main.main(opcionesCup);
            
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
        }
    }
        
            public static void analizadoresjson(String ruta, String jflexFile, String cupFile){
        try {
            String opcionesJflex[] =  {ruta+jflexFile,"-d",ruta};
            jflex.Main.generate(opcionesJflex);

            String opcionesCup[] =  {"-destdir", ruta,"-parser","Parser",ruta+cupFile};
            java_cup.Main.main(opcionesCup);
            
        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
        }
    }
    

    
    
    
    
}