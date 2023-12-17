package com.mycompany.proyecto1;
// PARA EXPRESIONES REGULARES
import java.util.ArrayList;

public class Expresiones {
    public String id;
    public Arbol raiz; 

    
    public Expresiones(String id, Arbol raiz){
        this.raiz = raiz;
        this.id = id;
        
        
    }
    
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
     public Arbol getArbol(){
        return raiz;
    }
}
