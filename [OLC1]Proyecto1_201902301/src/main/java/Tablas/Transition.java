package Tablas;

import com.mycompany.proyecto1.Hojas;
import com.mycompany.proyecto1.Nodo;
import com.mycompany.proyecto1.TIPOS;
import java.util.ArrayList;
import java.util.Collections;

public class Transition {
    public ArrayList<ArrayList> estados;
    public int contador;
    
    public Transition(Nodo root, ArrayList tabla, ArrayList<Nodo> leaves) {
        this.estados = new ArrayList();
        
        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.prim_pos); 
        datos.add(new ArrayList());
        datos.add(false);
        
        this.estados.add(datos);
        this.contador = 1;
        
        for(int i = 0; i < estados.size(); i++){
            ArrayList state = estados.get(i);
            ArrayList<Integer> elementos = (ArrayList) state.get(1);
            
            
            for(int hoja : elementos){
                Follow sigTabla = new Follow();
                ArrayList lexemeNext = (ArrayList) sigTabla.next(hoja, tabla).clone();
             
                boolean existe = false;
                String found = "";
                ArrayList<Integer> completo = new ArrayList<Integer>();
                
                //comporbacion de todos los demas estados para ser agregados al estado actual
                for(int hoja2 : elementos){
                    Follow sigTabla2 = new Follow();
                    ArrayList lexemeNext2 = (ArrayList) sigTabla2.extra((String) lexemeNext.get(0), hoja2, tabla).clone();
                    
                    if(!lexemeNext2.equals(lexemeNext)){
                        if((String) lexemeNext2.get(0) != ""){
                            completo.addAll((ArrayList)lexemeNext2.get(1));
                        }
                        
                    }
                }
                
                for (int elemento : completo) {
                    ((ArrayList<Integer>)lexemeNext.get(1)).add(elemento);
                }
                
                //ordenamiento de menor a mayor
                Collections.sort((ArrayList<Integer>)lexemeNext.get(1));
                
                for( ArrayList e : estados ){
                    if(e.get(1).equals(lexemeNext.get(1))){
                        existe = true;
                        found = (String)e.get(0);
                        break;
                    }
                }
                
                if(!existe){
                    Hojas ho = new Hojas();
                    if(ho.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    TIPOS tipo = ho.getTipo(hoja, leaves);
                    if(lexemeNext.get(0)==""){
                        continue;
                    }
                    
                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S"+contador);
                    nuevo.add(lexemeNext.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);
                    
                    transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", nuevo.get(0) + "", tipo);
                    ((ArrayList)state.get(2)).add(trans);
                    
                    contador += 1;
                    estados.add(nuevo);
                
                }
                else{
                    Hojas ho = new Hojas();
                    if(ho.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    TIPOS tipo = ho.getTipo(hoja, leaves);
                    
                    boolean trans_exist = false;
                    
                    for(Object trans : (ArrayList)state.get(2)){
                        transicion t = (transicion) trans;
                        if(t.compare(state.get(0) + "", lexemeNext.get(0) + "")){
                            trans_exist = true;
                            break;
                        }
                    }
                    if(!trans_exist){
                        transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", found + "", tipo);
                        ((ArrayList)state.get(2)).add(trans);
                    }
                }
            }
            
        }
    }
    
    
    public String impTable(){
        ArrayList<String> encabezados = new ArrayList<String>();
        String cab = "       <tr>";
        for(ArrayList state : estados){
            String tran = "";
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                if (!encabezados.contains(t.transition)) {
                    encabezados.add(t.transition);
                    cab += "            <td>" + t.transition +"</td> \n";
                }
            }
        }
        cab += "        </tr>\n";
        
        String filas = "";
        for(ArrayList state : estados){
            String[] transicion = new String[encabezados.size()];
            String color ="";
                    if((boolean) state.get(3)){
                        color = "bgcolor=\"#D8D364\"";
                    }
                    
            String fila = "        <tr>\n" +
"            <td " + color + " >" + state.get(0) + " </td>";
            
            String tran = "[";
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                
                int indice = encabezados.indexOf(t.transition);
                if (indice != -1) {
                    transicion[indice] = t.finalState;
                }
                
                tran += t.toString() + ", ";           
            }
            tran += "]";
            tran = tran.replace(", ]", "]");
            System.out.println(state.get(0) + " " + state.get(1) + " " + tran + " " + state.get(3));
            
            for (int i = 0; i < transicion.length; i++) {
                if (transicion[i] != null) {
                    fila += "            <td>"  +  transicion[i] + "</td> \n";
                }else{
                    fila += "            <td> --</td>\n";
                }
            }
            
            filas += fila;
            filas += "            </tr> \n";
            fila = "";
        }
        
        String salida = cab + filas;
        return salida;
    }
    
    public String impGraph(){
        String salida = ""; 
        for(ArrayList state : estados){
            String graph = "";
            if((Boolean)state.get(3) == true){
                graph += (String)state.get(0) + "[shape = doublecircle] \n";
            }
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                graph += t.graph();
            }
            salida += graph;
            System.out.println(graph);
        }
        
        return salida; 
    }
    
    public boolean analizar(String cadena, Object sim){
        ArrayList es = estados.get(0);
        String estadoActual = (String)es.get(0);
        ArrayList s = es;
        for (int i = 0; i < cadena.length(); i++) {
            String simbolo = Character.toString(cadena.charAt(i));
            if(simbolo.equals("\\")){
                i++;
                simbolo += Character.toString(cadena.charAt(i));
            }
            boolean transicionEncontrada = false;
            for (int y = 0; y < estados.size(); y++) {
                ArrayList state = estados.get(y);
                
                if (state.get(0).equals(estadoActual)) {
                    ArrayList<transicion> transiciones = (ArrayList<transicion>) state.get(2);
                    for (transicion trans : transiciones) {
                        if (trans.initialState.equals(estadoActual) && trans.getTransition(simbolo, sim)) {
                            estadoActual = trans.finalState;
                            transicionEncontrada = true;
                            
                            for (int x = 0; x < estados.size(); x++) {
                                ArrayList st = estados.get(x);
                                if (st.get(0).equals(estadoActual)) {
                                    s = estados.get(x);
                                }
                            }
                            break;
                        }
                    }
                    if (transicionEncontrada) {
                        break;
                    }
                }
            }
            if (!transicionEncontrada) {
                return false;
            }
        }
        
        return (boolean) s.get(3);
    }
}
