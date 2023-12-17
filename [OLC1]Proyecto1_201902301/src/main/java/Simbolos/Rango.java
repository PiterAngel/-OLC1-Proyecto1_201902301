package Simbolos;

import java.util.LinkedList;

public class Rango {
    private String inicio; 
    private String fin; 
    
    public Rango(String inicio, String fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
    
    public LinkedList<Character> Arreglo() {
        //Funcion para crear arreglo con el rango de caracteres
        boolean arr_simbol = false; 
        LinkedList<Character> caracteres = new LinkedList<>();
        char primerChar = this.inicio.charAt(0);;
        char segundoChar = this.fin.charAt(0);
        caracteres.add(primerChar);       
        //Colocar el caracter mas grande al inicio
        if (primerChar > segundoChar) {
            char temp = primerChar;
            primerChar = segundoChar;
            segundoChar = temp;
        }else if (primerChar == segundoChar){
            return caracteres;
        }
        
        if(primerChar <= 47 || (primerChar >= 58 && primerChar <= 64) 
           || (primerChar >= 91 && primerChar <= 96) || primerChar >= 123){
            arr_simbol = true;
        }
        
        
        for (char c = primerChar; c <= segundoChar; c++) {
            if (c != primerChar && c != segundoChar) {
                if(arr_simbol){
                    if(c == 48){
                        c = 57;
                        continue;
                    }else if(c == 65){
                        c = 90;
                        continue;
                    }else if(c == 97){
                        c = 122;
                        continue;
                    }
                    
                }
                caracteres.add(c);
            }
        }
        caracteres.add(segundoChar);
        return caracteres;
    }
}
