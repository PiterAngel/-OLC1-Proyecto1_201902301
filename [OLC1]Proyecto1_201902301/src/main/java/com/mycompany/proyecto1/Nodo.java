package com.mycompany.proyecto1;

import Tablas.Follow;
import java.util.ArrayList;
// OBJETOS PARA EL NODO
public class Nodo {
    
    public ArrayList<Integer> prim_pos;
    public ArrayList<Integer> ulti_pos;
    
    public String token;
    public TIPOS tipo;
    public String lexema;
    public int id; 
    
    boolean aceptado;
    boolean anulable;
    
    public Nodo hijoIzq;
    public Nodo hijoDer;
    
    public String first_thomp = "";
    public String last_thomp = ""; 
    
    public ArrayList<Nodo> hijos = new ArrayList<Nodo>();
    
    public ArrayList<ArrayList> tabla;
    public ArrayList<Nodo> hojas;
    
    public Nodo(String token, TIPOS tipo, String lexema, int id, Nodo hijoIzq, Nodo hijoDer,  ArrayList<Nodo> hojas, ArrayList<ArrayList> tabla){
        prim_pos = new ArrayList();
        ulti_pos = new ArrayList();
        
        this.token = token;
        this.lexema = lexema;
        this.tipo = tipo; 
        this.hijoIzq = hijoIzq;
        this.hijoDer = hijoDer;
        this.id = id;
        
        aceptado = "#".equals(this.token);
        
        if(hijoIzq != null){
        this.hijos.add(hijoIzq);
        }
        if(hijoDer != null){
            this.hijos.add(hijoDer);
        }
        
        this.hojas = hojas;
        this.tabla = tabla;
        
    }
    
    public Nodo getNodo(){
        
        Object leftNode =  this.hijoIzq instanceof Nodo ? ((Nodo) this.hijoIzq).getNodo()  : null;
        Object rightNode = this.hijoDer instanceof Nodo ? ((Nodo) this.hijoDer).getNodo()  : null;
        
        if(null != this.tipo)switch (this.tipo) {
            case ESCAPE:
            case CADENA:
            case ID:
                this.anulable = false;
                this.prim_pos.add(this.id);
                this.ulti_pos.add(this.id);
                break;
            case SIMBOLO:
                switch(this.token){
                    case ".":
                        if(leftNode instanceof Nodo && rightNode instanceof Nodo){
                            this.anulable = ((Nodo)leftNode).anulable && ((Nodo) rightNode).anulable;

                            this.prim_pos.addAll(((Nodo)leftNode).prim_pos);
                            if(((Nodo)leftNode).anulable){
                                this.prim_pos.addAll(((Nodo)rightNode).prim_pos);
                            }

                            if(((Nodo)rightNode).anulable){
                                this.ulti_pos.addAll(((Nodo)leftNode).ulti_pos);
                            }
                            this.ulti_pos.addAll(((Nodo)rightNode).ulti_pos);
                        }  
                        break;
                    case "|":
                        if(leftNode instanceof Nodo && rightNode instanceof Nodo){
                            this.anulable = ((Nodo)leftNode).anulable || ((Nodo) rightNode).anulable;

                            this.prim_pos.addAll(((Nodo)leftNode).prim_pos);
                            this.prim_pos.addAll(((Nodo)rightNode).prim_pos);


                            this.ulti_pos.addAll(((Nodo)leftNode).ulti_pos);
                            this.ulti_pos.addAll(((Nodo)rightNode).ulti_pos);
                        }  
                        
                        break;
                    case "*":
                        if(leftNode instanceof Nodo){
                            this.anulable = true;
                            this.prim_pos.addAll(((Nodo)leftNode).prim_pos);
                            this.ulti_pos.addAll(((Nodo)leftNode).ulti_pos);
                        }
                        break;
                    case "?":
                        if(leftNode instanceof Nodo){
                            this.anulable = true;
                            this.prim_pos.addAll(((Nodo)leftNode).prim_pos);
                            this.ulti_pos.addAll(((Nodo)leftNode).ulti_pos);
                        }
                        break;
                    case "+":
                        if(leftNode instanceof Nodo){
                            this.anulable = ((Nodo)leftNode).anulable;
                            this.prim_pos.addAll(((Nodo)leftNode).prim_pos);
                            this.ulti_pos.addAll(((Nodo)leftNode).ulti_pos);
                        }
                        break;
                        
                
                }
                 
                break;
            default:
                break;
        }
        
        
        return this;
    }
    
    
    public Object follow(){
        Object leftFollow=  this.hijoIzq instanceof Nodo ? ((Nodo) this.hijoIzq).follow() : null;
        Object rightFollow =  this.hijoDer instanceof Nodo ? ((Nodo) this.hijoDer).follow() : null;
        
        if(null != this.tipo)switch (this.tipo) {
            case SIMBOLO:
                switch(this.token){
                    case ".":
                        for (int item : ((Nodo)leftFollow).ulti_pos) {
                            Hojas h = new Hojas();
                            Nodo nodo = h.getLeave(item, hojas);
                            Follow ta = new Follow();
                            ta.append(nodo.id, nodo.token, ((Nodo) rightFollow).prim_pos, tabla);
                            
                        }
                
                        break;
                    
                    case "*":
                        for (int item : ((Nodo)leftFollow).ulti_pos) {
                            Hojas h = new Hojas();
                            Nodo nodo = h.getLeave(item, hojas);
                            Follow ta = new Follow();
                            ta.append(nodo.id, nodo.token, ((Nodo) leftFollow).prim_pos, tabla);
                        }
                        break;
                        
                    case "+":
                        for (int item : ((Nodo)leftFollow).ulti_pos) {
                            Hojas h = new Hojas();
                            Nodo nodo = h.getLeave(item, hojas);
                            Follow ta = new Follow();
                            ta.append(nodo.id, nodo.token, ((Nodo) leftFollow).prim_pos, tabla);
                        }
                        break;
                
                }
                break;
            default:
                break;
        }
        
        return this;
    }
    
}
