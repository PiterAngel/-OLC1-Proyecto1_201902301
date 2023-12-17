package com.mycompany.proyecto1;
// PARA LA REALIZACIÓN DE CADA  ARBOL
import Tablas.Follow;
import Tablas.Transition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Arbol {
    public Nodo raiz;
    public String id = "";
    public String archivo = "";
    ArrayList<Nodo> leaves;
    ArrayList<ArrayList> table;
    
    public Arbol(Nodo raiz, ArrayList<Nodo> leaves, ArrayList<ArrayList> table ) {
        this.raiz = raiz;
        this.leaves = leaves;
        this.table = table;
    }
    
    public void GraficarSintactico() throws InterruptedException{
  
        String grafica = "graph Arbol_Sintactico{\n" +
                "\n" +
                "graph [pad=\"0.5\", nodesep=\"1.2\", ranksep=\"0.2\"];\n" +
                "edge [penwidth=2, color=\"#48BADC\"];\n" +
                "node [shape = circle, color=\"#06346A\"];" + 
                GraficaNodos(this.raiz, "0")    + 
                "\n edge [penwidth=0];"                +
                GraficaPos(this.raiz, "0")      +
                "\n\n}";        
        GenerarDot("ARBOLES_201902301/Arbol_Sintactico", grafica);

    }
    
    public void GraficarEstados(String estados) throws InterruptedException{
        String grafica = "digraph AFD{\n\n rankdir=LR;\n dpi=150; \n" +
"    node [shape = circle]" + estados + "\n\n}";        
        GenerarDot("AFD_201902301/AFD", grafica); 
    }
    
    public void GraficarSiguientes(String filas) throws InterruptedException{
        String grafica = "digraph H {\n dpi=200; \n" +
"    graph [label=< <font color= \"#290524\" face=\"Impact\" size=\"30\">Tabla de siguientes</font> >];\n" +
"  parent [\n" +
"   shape=plaintext\n" +
"   label=<\n" +
"     <table border='0' cellborder='1' cellspacing='0'>\n" +
"       <tr>\n" +
"            <td bgcolor=\"#1EA59B\" colspan=\"2\">Hoja</td>\n" +
"            <td bgcolor=\"#1EA59B\" colspan=\"1\">Siguientes</td>\n" +
"        </tr>\n" +
"       \n" +
"       "+ filas + "\n" +
"     </table>\n" +
"  >];\n" +
"}";     
        
        GenerarDot("SIGUIENTES_201902301/Tabla_siguientes", grafica); 
    }
    
    
    public void GraficarTransiciones(String filas) throws InterruptedException{
        String grafica = "digraph H {\n dpi=200; \n" +
"    graph [label=< <font color= \"#290524\" face=\"Impact\" size=\"30\">Tabla de Transiciones</font> >];\n" +
"  parent [\n" +
"   shape=plaintext\n" +
"   label=<\n" +
"     <table border='0' cellborder='1' cellspacing='0'>\n" +
"       <tr>\n" +
"            <td bgcolor=\"#19CD9F\"  rowspan=\"2\" colspan=\"1\">Estado</td>\n" +
"            <td bgcolor=\"#19CD9F\" colspan=\"999\">Terminales</td>\n" +
"        </tr>\n" +
"       \n" +
"        " + filas +
"     </table>\n" +
"  >];\n" +
"}";     
        
        GenerarDot("TRANSICIONES_201902301/Tabla_transiciones", grafica); 
    }
    
    public String Imprime(String i){
  
        String texto = " Expresion " + ImprimeNodos(this.raiz, "0") + "\n\n";        
        return texto; 

    }
    
    
    public void MetodoArbol() throws InterruptedException{
        raiz.getNodo();
        GraficarSintactico();
        
        raiz.follow();
        //--------------------------TABLA SIGUIENTES
        Follow ft = new Follow();
        GraficarSiguientes(ft.printTable(table));
        
        Transition tran = new Transition(raiz, table, leaves);
        //------------------TABLA TRANSICIONES 
        GraficarTransiciones(tran.impTable());
        //----------------------- GRAPHVIZ
        GraficarEstados(tran.impGraph());

    }
    
    
    public void GraficarThompson() throws InterruptedException{
  
        String grafica = "digraph AFD{\n" +
"\n" +
" rankdir=LR;\n" +
"node [shape = circle]\n" + 
                GraficaNodosThompson(this.raiz.hijoIzq, "0")    + 
                this.raiz.hijoIzq.last_thomp + "[label = \"\", shape=doublecircle ] \n}";    
        GenerarDot("AFND_201902301/AFND", grafica);

    }
    
    public boolean AnalizarCadena(String text, Object sim) {
        Transition tran = new Transition(raiz, table, leaves);
        return tran.analizar(text, sim);
    }   
    
    
    private String GraficaNodos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
        
        String extra = "";
        
        if(nodo.tipo != TIPOS.SIMBOLO){
            extra = ", shape = oval";
        }
        if(nodo.aceptado){
            extra = ",shape=doublecircle, color=\"#2BFF1D\"";
        }
        if(nodo.anulable){
            extra = "shape=doublecircle, color=red";
        }
        
        nodoTerm = nodoTerm.replace("\"", "");
        r= "node" + i + "[label = \"" + nodoTerm + "\"" + extra  + " ];\n";
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -- node" + i + k + "\n";
       
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        return r;
    }
    
    private String GraficaNodosThompson(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
        String nodoName = "node" + i;
        nodoTerm = nodoTerm.replace("\"", "");
        //Primero nodo = _0, ultimo nodo = _1 
        if(nodo.tipo != TIPOS.SIMBOLO){
            if("".equals(nodo.first_thomp)){
                nodo.first_thomp = "nodeh" + nodo.id + "_0";
            }
            nodo.last_thomp = "nodeh" + nodo.id + "_1";
            r +=  nodo.first_thomp + "[label = \"\"] \n";
            r +=  nodo.last_thomp + "[label = \"\"] \n";
            r +=  nodo.first_thomp + "->" + nodo.last_thomp + "[label= \"" + nodoTerm + "\"]; \n";
        }
   
        if(nodo.tipo == TIPOS.SIMBOLO) switch(nodo.token){
            case ".":          
                if(!"".equals(nodo.first_thomp)){
                    nodo.hijoIzq.first_thomp = nodo.first_thomp;
                }
                r += GraficaNodosThompson(nodo.hijoIzq, i+"0");
                nodo.hijoDer.first_thomp = nodo.hijoIzq.last_thomp;
                r += GraficaNodosThompson(nodo.hijoDer, i+"1");    
                
                nodo.first_thomp = nodo.hijoIzq.first_thomp;
                nodo.last_thomp = nodo.hijoDer.last_thomp;
                break;
                
            case "|":      
                if("".equals(nodo.first_thomp)){
                    nodo.first_thomp = "nodo" + i + "_0";
                }
                r += GraficaNodosThompson(nodo.hijoIzq, i+"0");
                r += GraficaNodosThompson(nodo.hijoDer, i+"1");   
                
                r +=  nodo.first_thomp + "[label = \"\"] \n";
                r +=  "nodo" + i + "_1[label = \"\"] \n";
                r +=  nodo.first_thomp + "->" + nodo.hijoIzq.first_thomp + "[label= \"ε\"]; \n";
                r +=  nodo.first_thomp + "->" + nodo.hijoDer.first_thomp + "[label= \"ε\"]; \n";
                
                r +=  nodo.hijoIzq.last_thomp + "->" +"nodo" + i + "_1"   + "[label= \"ε\"]; \n";
                r +=  nodo.hijoDer.last_thomp + "->" + "nodo" + i + "_1"  + "[label= \"ε\"]; \n";                
                
                nodo.last_thomp = "nodo" + i + "_1";
                break;
                
            case "+":   
                if("".equals(nodo.first_thomp)){
                    nodo.first_thomp = "nodo" + i + "_0";
                }
                r += GraficaNodosThompson(nodo.hijoIzq, i+"0");  
                
                r +=  nodo.first_thomp  + "[label = \"\"] \n";
                r +=  "nodo" + i + "_1[label = \"\"] \n";
                r +=  nodo.first_thomp + "->" + nodo.hijoIzq.first_thomp + "[label= \"ε\"]; \n";                
                r +=  nodo.hijoIzq.last_thomp + "->" +"nodo" + i + "_1"   + "[label= \"ε\"]; \n";
                
                r +=  nodo.hijoIzq.last_thomp + "->" +nodo.hijoIzq.first_thomp+ "[label= \"ε\", constraint=false]; \n";
                
                nodo.last_thomp = "nodo" + i + "_1";
                break;
                
            case "*":   
                if("".equals(nodo.first_thomp)){
                    nodo.first_thomp = "nodo" + i + "_0";
                }
                r += GraficaNodosThompson(nodo.hijoIzq, i+"0");  
                
                r +=  nodo.first_thomp + "[label = \"\"] \n";
                r +=  "nodo" + i + "_1[label = \"\"] \n";
                r +=  nodo.first_thomp + "->" + nodo.hijoIzq.first_thomp + "[label= \"ε\"]; \n";                
                r +=  nodo.hijoIzq.last_thomp + "->" +"nodo" + i + "_1"   + "[label= \"ε\"]; \n";
                
                r +=  nodo.hijoIzq.last_thomp + "->" +nodo.hijoIzq.first_thomp+ "[label= \"ε\" , constraint=false]; \n";
                r +=  nodo.first_thomp + "->" +"nodo" + i + "_1" + "[label= \"ε\" , constraint=false]; \n";
                
                nodo.last_thomp = "nodo" + i + "_1";
                break;
                
             case "?":   
                if("".equals(nodo.first_thomp)){
                    nodo.first_thomp = "nodo" + i + "_0";
                }
                r += GraficaNodosThompson(nodo.hijoIzq, i+"0");  
                
                r +=  nodo.first_thomp + "[label = \"\"] \n";
                r +=  "nodo" + i + "_1[label = \"\"] \n";
                r +=  nodo.first_thomp + "->" + nodo.hijoIzq.first_thomp + "[label= \"ε\"]; \n";                
                r +=  nodo.hijoIzq.last_thomp + "->" +"nodo" + i + "_1"   + "[label= \"ε\"]; \n";
                
                r +=  nodo.first_thomp + "->" +"nodo" + i + "_1" + "[label= \"ε\", constraint=false]; \n";
                
                nodo.last_thomp = "nodo" + i + "_1";
                break;

            default: 
                break;       
        }
        k++;
        
        return r;
    }
    
    
     private String GraficaPos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
        
        String name_w = "node" + i + ":w";          //primeros    
        String name_e = "node" + i + ":e";          //ultimos
        String name_s = "node" + i + ":s";          //identificador de hoja
        
        String lista_primeros = "";
        for (Integer numero : nodo.prim_pos) {
            lista_primeros += numero.toString() + ",";
        }
        lista_primeros = lista_primeros.substring(0, lista_primeros.length() - 1);
        
        String lista_ultimos = "";
        for (Integer numero : nodo.ulti_pos) {
            lista_ultimos += numero.toString() + ",";
        }
        lista_ultimos = lista_ultimos.substring(0, lista_ultimos.length() - 1);
        
        r += name_w + "--" + name_w + "[taillabel=\""  +  lista_primeros + "\"]\n";
        r += name_e + "--" + name_e + "[taillabel=\""  +  lista_ultimos + "\"]\n";
        
        if (nodo.id != -1){
            r += name_s + "--" + name_s + "[taillabel=\""  +  nodo.id + "\"]\n";
        }
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){       
            r= r + GraficaPos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        return r;
    }
    
    private void GenerarDot(String nombre, String cadena) throws InterruptedException{
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter(nombre+archivo+ "_" + id + ".dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar(nombre);
        } catch (IOException e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
    }
    
    public void reportar(String nombre) throws IOException, InterruptedException {
        
        String file_input_path = nombre+archivo+ "_" + id +".dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String file_get_path =  nombre+archivo+ "_" + id +".jpg" ;
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
       //Desktop.getDesktop().open(new File(file_get_path));
    }
    
    
    private String ImprimeNodos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
     
        r=  nodoTerm + " ";
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r= r + ImprimeNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema;
          
            r += nodoToken + " ";
        }
        
        return r;
    }
}
