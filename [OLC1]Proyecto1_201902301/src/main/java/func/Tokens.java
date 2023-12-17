

package func;


public class Tokens {
    
    private String lexema = "";
    private String token = "";
    private int linea = 0;
    private int columna = 0;

    
    public  void recibir_patron(String lexema, String token, int linea, int columna){
        this.setLexema(lexema);
        this.setToken(token);
        this.setLinea(linea);
        this.setColumna(columna);
    }
    

    public String getLexema() {
        return lexema;
    }

 
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getLinea() {
        return linea;
    }


    public void setLinea(int linea) {
        this.linea = linea;
    }


    public int getColumna() {
        return columna;
    }

 
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
   
    
    
    
    
    
    
    
}
