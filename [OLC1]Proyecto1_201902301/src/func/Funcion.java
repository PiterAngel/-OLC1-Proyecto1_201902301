/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func;

import Graficas.symbols;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import java.util.LinkedList;

/**
 *
 * @author Piter
 */
public class Funcion {
    
    
    
    
    
    public static String archivoname ="";
    public static LinkedList<Object> Lista_T = new LinkedList<Object>();
    public static LinkedList<Object> Lista_T_errores = new LinkedList<Object>();
    public static LinkedList<Object> Lista_T_stat = new LinkedList<Object>();
    public static LinkedList<Object> Lista_T_errores_stat = new LinkedList<Object>();
    
    
    static Map<String, LinkedList<Object>> hashjson = new HashMap<>();
    
    // ============ DATOS PARA DEFINIRGLOBALES ==========
    static Map<String, String> globals_String = new HashMap<>();
    static Map<String, String> globals_Dobule = new HashMap<>();
    
    // ============          DATOS PARA PIE    ==========
    static Map<String, String> pie_String = new HashMap<>();
    static Map<String, String> pie_Double = new HashMap<>();
    static LinkedList<String> listpie_valores = new LinkedList<String>();
    static LinkedList<String> listpie_ejex = new LinkedList<String>();
    
    // ============       DATOS PARA BARRAS     ==========
    static Map<String, String> barras_String = new HashMap<>();
    static Map<String, String> barras_Double = new HashMap<>();
    static LinkedList<String> listbarras_valores = new LinkedList<String>();
    static LinkedList<String> listbarras_ejex = new LinkedList<String>();
    
    
    
    
    
      public static  void tablaT(String lexema, String token, int linea, int columna){
          func.Tokens tokenzzz = new func.Tokens();
          tokenzzz.recibir_patron(lexema, token, linea, columna);
          Lista_T.add(tokenzzz);
          
        }
      
      public static void tablaTerrores(String lexema, String token, int linea, int columna){
          func.Tokens tokenzzzerrores = new func.Tokens();
          tokenzzzerrores.recibir_patron(lexema, token, linea, columna);
          Lista_T_errores.add(tokenzzzerrores);
      }
 
      public static  void tabla_T_stat(String lexema, String token, int linea, int columna){
          func.Tokens tokenzzz_stat = new func.Tokens();
          tokenzzz_stat.recibir_patron(lexema, token, linea, columna);
          Lista_T_stat.add(tokenzzz_stat);
          
        }
      
      public static void tabla_T_errores_stat(String lexema, String token, int linea, int columna){
          func.Tokens tokenzzzerrores_stat = new func.Tokens();
          tokenzzzerrores_stat.recibir_patron(lexema, token, linea, columna);
          Lista_T_errores_stat.add(tokenzzzerrores_stat);
      }
      
    
    
        public static String mostrar(String expresion){
        
        return "System.out.println(" + expresion +");";
         //JOptionPane.showMessageDialog(null, mensaje);
    }
        
        
        
public static void htmltokens() {
        // Generar el código HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Json</title>");
        
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {");
        htmlBuilder.append("  background: #192730;");
        htmlBuilder.append("  color: #D2D1D5;");
        htmlBuilder.append("}");
        htmlBuilder.append("table {");
        htmlBuilder.append("  border-collapse: collapse;");
        htmlBuilder.append("  width: 80%;");
        htmlBuilder.append("  margin: 0 auto;");
        htmlBuilder.append("  background-color: #263849;");
        htmlBuilder.append("}");
        htmlBuilder.append("th {");
        htmlBuilder.append("  background-color: #3b465b;");
        htmlBuilder.append("  color: white;");
        htmlBuilder.append("}");
        htmlBuilder.append("th, td {");
        htmlBuilder.append("  border: 1px solid #ddd;");
        htmlBuilder.append("  padding: 8px;");
        htmlBuilder.append("  text-align: center;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(even) {");
        htmlBuilder.append("  background-color: #2f3144;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(odd) {");
        htmlBuilder.append("  background-color: #423b5b;");
        htmlBuilder.append("}");
        htmlBuilder.append(".noBorder {");
        htmlBuilder.append("  border: none !important;");
        htmlBuilder.append("}");
        htmlBuilder.append("</style>");
                
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Tabla de Tokens de JSON</h1>");
        htmlBuilder.append("<table border=\"1\">");
        htmlBuilder.append("<tr><th>Lexema</th>");
        htmlBuilder.append("<th>Token</th>");
        htmlBuilder.append("<th>Linea</th>");
        htmlBuilder.append("<th>Columna</th></tr>");
        
        for (Object token : Lista_T) {
            func.Tokens dato = (func.Tokens)token;
            htmlBuilder.append("<tr><td>").append(dato.getLexema()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getToken()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getLinea()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getColumna()).append("</td></tr>");
            //System.out.println("OLAAAAAAAAA DENTRO ");
        }

        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

    // Escribir el código HTML en un archivo
    try {
        FileWriter token = new FileWriter("tablatokens.html");
        token.write(htmlBuilder.toString());
        token.close();
        System.out.println("Página HTML generada exitosamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    Lista_T.clear();
}
        
    public static void htmltokenserrores() {
        // Generar el código HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Json Error </title>");
        
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {");
        htmlBuilder.append("  background: #193024;");
        htmlBuilder.append("  color: #D2D1D5;");
        htmlBuilder.append("}");
        htmlBuilder.append("table {");
        htmlBuilder.append("  border-collapse: collapse;");
        htmlBuilder.append("  width: 80%;");
        htmlBuilder.append("  margin: 0 auto;");
        htmlBuilder.append("  background-color: #00E0A3;");
        htmlBuilder.append("}");
        htmlBuilder.append("th {");
        htmlBuilder.append("  background-color: #264942;");
        htmlBuilder.append("  color: white;");
        htmlBuilder.append("}");
        htmlBuilder.append("th, td {");
        htmlBuilder.append("  border: 1px solid #ddd;");
        htmlBuilder.append("  padding: 8px;");
        htmlBuilder.append("  text-align: center;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(even) {");
        htmlBuilder.append("  background-color: #3b5b4e;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(odd) {");
        htmlBuilder.append("  background-color: #2f443f;");
        htmlBuilder.append("}");
        htmlBuilder.append(".noBorder {");
        htmlBuilder.append("  border: none !important;");
        htmlBuilder.append("}");
        htmlBuilder.append("</style>");
                
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Tabla de Errores de JSON</h1>");
        htmlBuilder.append("<table border=\"1\">");
        htmlBuilder.append("<tr><th>Lexema</th>");
        htmlBuilder.append("<th>Token</th>");
        htmlBuilder.append("<th>Linea</th>");
        htmlBuilder.append("<th>Columna</th></tr>");
        
        for (Object token : Lista_T_errores) {
            func.Tokens dato = (func.Tokens)token;
            htmlBuilder.append("<tr><td>").append(dato.getLexema()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getToken()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getLinea()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getColumna()).append("</td></tr>");
            //System.out.println("OLAAAAAAAAA DENTRO ");
        }

        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

    // Escribir el código HTML en un archivo
    try {
        FileWriter token = new FileWriter("tablatokenserrores.html");
        token.write(htmlBuilder.toString());
        token.close();
        System.out.println("Página HTML generada exitosamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    Lista_T_errores.clear();
}
        //================================= para statpy ==============
    public static void htmltokens_stat() {
        // Generar el código HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Statpy</title>");
        
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {");
        htmlBuilder.append("  background: #1E1930;");
        htmlBuilder.append("  color: #D2D1D5;");
        htmlBuilder.append("}");
        htmlBuilder.append("table {");
        htmlBuilder.append("  border-collapse: collapse;");
        htmlBuilder.append("  width: 80%;");
        htmlBuilder.append("  margin: 0 auto;");
        htmlBuilder.append("  background-color: #00E0A3;");
        htmlBuilder.append("}");
        htmlBuilder.append("th {");
        htmlBuilder.append("  background-color: #342F44;");
        htmlBuilder.append("  color: white;");
        htmlBuilder.append("}");
        htmlBuilder.append("th, td {");
        htmlBuilder.append("  border: 1px solid #ddd;");
        htmlBuilder.append("  padding: 8px;");
        htmlBuilder.append("  text-align: center;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(even) {");
        htmlBuilder.append("  background-color: #2e2649;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(odd) {");
        htmlBuilder.append("  background-color: #423b5b;");
        htmlBuilder.append("}");
        htmlBuilder.append(".noBorder {");
        htmlBuilder.append("  border: none !important;");
        htmlBuilder.append("}");
        htmlBuilder.append("</style>");
                
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Tabla de Tokens de Statpy</h1>");
        htmlBuilder.append("<table border=\"1\">");
        htmlBuilder.append("<tr><th>Lexema</th>");
        htmlBuilder.append("<th>Token</th>");
        htmlBuilder.append("<th>Linea</th>");
        htmlBuilder.append("<th>Columna</th></tr>");
        
        for (Object token : Lista_T_stat) {
            func.Tokens dato = (func.Tokens)token;
            htmlBuilder.append("<tr><td>").append(dato.getLexema()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getToken()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getLinea()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getColumna()).append("</td></tr>");
            //System.out.println("OLAAAAAAAAA DENTRO ");
        }

        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

    // Escribir el código HTML en un archivo
    try {
        FileWriter token = new FileWriter("tablatokens_stat.html");
        token.write(htmlBuilder.toString());
        token.close();
        System.out.println("Página HTML generada exitosamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    Lista_T_stat.clear();
}
        
        public static void htmltokenserrores_stat() {
        // Generar el código HTML
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Statpy Error</title>");
        
        htmlBuilder.append("<style>");
        htmlBuilder.append("body {");
        htmlBuilder.append("  background: #301919;");
        htmlBuilder.append("  color: #D2D1D5;");
        htmlBuilder.append("}");
        htmlBuilder.append("table {");
        htmlBuilder.append("  border-collapse: collapse;");
        htmlBuilder.append("  width: 80%;");
        htmlBuilder.append("  margin: 0 auto;");
        htmlBuilder.append("  background-color: #00E0A3;");
        htmlBuilder.append("}");
        htmlBuilder.append("th {");
        htmlBuilder.append("  background-color: #492626;");
        htmlBuilder.append("  color: white;");
        htmlBuilder.append("}");
        htmlBuilder.append("th, td {");
        htmlBuilder.append("  border: 1px solid #ddd;");
        htmlBuilder.append("  padding: 8px;");
        htmlBuilder.append("  text-align: center;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(even) {");
        htmlBuilder.append("  background-color: #5b3b3b;");
        htmlBuilder.append("}");
        htmlBuilder.append("tr:nth-child(odd) {");
        htmlBuilder.append("  background-color: #442f2f;");
        htmlBuilder.append("}");
        htmlBuilder.append(".noBorder {");
        htmlBuilder.append("  border: none !important;");
        htmlBuilder.append("}");
        htmlBuilder.append("</style>");
                
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Tabla de Errores de Statpy</h1>");
        htmlBuilder.append("<table border=\"1\">");
        htmlBuilder.append("<tr><th>Lexema</th>");
        htmlBuilder.append("<th>Token</th>");
        htmlBuilder.append("<th>Linea</th>");
        htmlBuilder.append("<th>Columna</th></tr>");
        
        for (Object token : Lista_T_errores_stat) {
            func.Tokens dato = (func.Tokens)token;
            htmlBuilder.append("<tr><td>").append(dato.getLexema()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getToken()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getLinea()).append("</td>");
            htmlBuilder.append("<td>").append(dato.getColumna()).append("</td></tr>");
            //System.out.println("OLAAAAAAAAA DENTRO ");
        }

        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        // Escribir el código HTML en un archivo
        //System.out.println("WAOOOOOOOOOOOOSSSSSSSSSSSS");
        try {
            FileWriter token = new FileWriter("tablatokenserrores_stat.html");
            token.write(htmlBuilder.toString());
            token.close();
            System.out.println("Página HTML generada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Lista_T_errores_stat.clear();

    }

        public static void getnamezz(String namezz){
            archivoname = "\"" + namezz + "\"";
            System.out.println("Name " + archivoname);
        }
        
        public static void agregarjson(String simbolo, String valor, String tipo){
            symbols szzz = new symbols();
            szzz.agregarsymbols(simbolo.toLowerCase(), valor, tipo);
            hashjson.computeIfAbsent(archivoname, key-> new LinkedList<>()).add(szzz); 
        }
        
     
}
