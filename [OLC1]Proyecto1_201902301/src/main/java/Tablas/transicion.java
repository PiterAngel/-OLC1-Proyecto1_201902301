package Tablas;

import Simbolos.Conjunto;
import com.mycompany.proyecto1.Simbolos;
import com.mycompany.proyecto1.TIPOS;

public class transicion {
    public String initialState;
    public String transition;
    public String finalState;
    public TIPOS tipo;
    
    public transicion( String initialState, String transition, String finalState, TIPOS tipo ) {
        this.initialState = initialState;
        this.transition = transition;
        this.finalState = finalState;
        this.tipo = tipo;
    }
    
    public boolean compare(String initialState, String transition){
        
        return this.initialState.equals(initialState) && this.transition.equals(transition);
    }
    
    @Override
    public String toString(){
        return this.initialState + " -> " + this.transition + " -> " + this.finalState;
    }
    
    public String graph(){
        String salida = "";
        if(tipo == TIPOS.CADENA){
            salida = this.transition;
        }else if (tipo == TIPOS.ID){
            salida = "\"{- " + this.transition + " -}\"";
        }else if (tipo == TIPOS.ESCAPE){
            salida = "\"  " + this.transition + "  \"";
        }
        return this.initialState +  "->"  + this.finalState + "[label= " + salida + " ]";
    }
    
    public boolean getTransition(String dato, Object sim ){
        if(tipo == TIPOS.CADENA){
            String subcadena = this.transition.substring(1, this.transition.length() - 1);
            return dato.equals(subcadena) ?  true : false;
        }else if (tipo == TIPOS.ESCAPE){
            boolean tf = dato.equals(this.transition) ?  true : false;
            return tf;
        }else if (tipo == TIPOS.ID){
            Simbolos tab = (Simbolos) sim;
            Conjunto conj = (Conjunto) tab.obtenerConjunto(this.transition);
            
            for (char c : conj.getCaracteres()) {
                if(c == dato.charAt(0)){
                    return true;
                }
            }
        
        }
        return false; 
    }
}
