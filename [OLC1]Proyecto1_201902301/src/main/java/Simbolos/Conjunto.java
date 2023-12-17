package Simbolos;
import java.util.LinkedList;

public class Conjunto {
    private String id; 
    private LinkedList<Character> caracteres;
    
    public Conjunto(String id, LinkedList<Character> caracteres) {
        this.id = id;
        this.caracteres = caracteres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Character> getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(LinkedList<Character> caracteres) {
        this.caracteres = caracteres;
    }
     
}
