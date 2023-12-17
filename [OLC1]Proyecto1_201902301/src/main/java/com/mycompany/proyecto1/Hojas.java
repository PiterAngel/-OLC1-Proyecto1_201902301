package com.mycompany.proyecto1;
// FUNCIONES PARA CADA HOJA
import java.util.ArrayList;

public class Hojas {
    public void addLeave(Nodo nodo, ArrayList<Nodo> leaves){
        leaves.add(nodo);
    }
    
    public Nodo getLeave(int numLeave, ArrayList<Nodo> leaves){
        for (Nodo item : leaves) {
            if(item.id == numLeave) return item;
        }
        return null;
    }
    
    public boolean isAccept(int numLeave, ArrayList<Nodo> leaves){
        for (Nodo item : leaves) {
            if(item.id == numLeave) return item.aceptado;
        }
        return false;
    }
    
    public TIPOS getTipo(int numLeave, ArrayList<Nodo> leaves){
        for (Nodo item : leaves) {
            if(item.id == numLeave) return item.tipo;
        }
        return TIPOS.CADENA;
    }
}
