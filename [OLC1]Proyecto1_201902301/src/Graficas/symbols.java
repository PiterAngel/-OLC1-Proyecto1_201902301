/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficas;

/**
 *
 * @author Piter
 */
public class symbols {
    private String simbolo = "";
    private String valor = "";
    private String tipo ="";


    public symbols(){
        
    }
    
    public void agregarsymbols(String simbolo, String valor, String tipo){
       this.setSimbolo(simbolo);
       this.setValor(valor);
       this.setTipo(tipo);
    }
    
    
    public String getSimbolo() {
        return simbolo;
    }


    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

 

    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
      
    
}
