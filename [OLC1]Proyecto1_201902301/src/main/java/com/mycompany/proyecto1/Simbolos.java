package com.mycompany.proyecto1;
import Simbolos.Conjunto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// IMPORTACIONES PARA LOS SIMBOLOS Y AGREGAR CONJUNTOS
public class Simbolos {
    private Map<String, Conjunto> tablaConjuntos;
    private Map<String, Expresiones> tablaExpresiones;
    public ArrayList<Pruebas> pruebas = new ArrayList<Pruebas>();

    public void setPruebas(ArrayList<Pruebas> pruebas) {
        this.pruebas = pruebas;
    }
     
    
    public Simbolos() {
        tablaConjuntos = new HashMap<String, Conjunto>();
        tablaExpresiones = new HashMap<String, Expresiones>();
    }
    
    public boolean agregarConjunto(Conjunto conj) {
        if (!tablaConjuntos.containsKey(conj.getId())) {
            tablaConjuntos.put(conj.getId(), conj);
            return true;
        }
        return false;
    }

    public Conjunto obtenerConjunto(String nombre) {
        if (tablaConjuntos.containsKey(nombre)) {
            return tablaConjuntos.get(nombre);
        }
        return null;
    }
    
    public boolean agregarExpresiones(Expresiones exp) {
        if (!tablaExpresiones.containsKey(exp.getId())) {
            tablaExpresiones.put(exp.getId(), exp);
            return true;
        }
        return false;
    }

    public Expresiones obtenerExpresiones(String nombre) {
        if (tablaExpresiones.containsKey(nombre)) {
            return tablaExpresiones.get(nombre);
        }
        return null;
    }
    
    
}
