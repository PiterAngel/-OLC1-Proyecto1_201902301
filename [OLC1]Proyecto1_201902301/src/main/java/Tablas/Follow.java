package Tablas;

import java.util.ArrayList;

public class Follow {
    
    public void append(int id, String lexema, ArrayList flwList, ArrayList<ArrayList> tabla){
        for (ArrayList item : tabla){
            if( (int) item.get(0) == id && item.get(1) == lexema ){
                for (Object flwItem : flwList){
                    if(! ((ArrayList)item.get(2)).contains((int)flwItem) ){
                       ((ArrayList)item.get(2)).add(flwItem);
                    }
                }
                return;
            }
        }
        ArrayList dato = new ArrayList();
        dato.add(id);
        dato.add(lexema);
        dato.add(flwList);
        
        tabla.add(dato);
    }
    
    public ArrayList next(int numNode, ArrayList<ArrayList> table){
        ArrayList result = new ArrayList();
        for(ArrayList item : table){
            if( (int) item.get(0) == numNode ){
                result.add(item.get(1));
                result.add(((ArrayList)item.get(2)).clone());
                return result;
            }
        }
        result.add("");
        result.add(new ArrayList());
    return result;
    }
    
    public ArrayList extra(String name,int numNode, ArrayList<ArrayList> table){
        ArrayList result = new ArrayList();
        for(ArrayList item : table){
            if( item.get(1).equals(name) &&(int) item.get(0) == numNode ){
                result.add(item.get(1));
                result.add(((ArrayList)item.get(2)).clone());
                return result;
            }
        }
        result.add("");
        result.add(new ArrayList());
    return result;
    }
    
    public String printTable(ArrayList<ArrayList> table){
        String result = "";
        for(ArrayList item : table){
            result += "<tr>\n" +
"            <td>" + item.get(0) + "</td>\n" +
"            <td>" + item.get(1) +"</td>\n" +
"            <td>" + item.get(2) +"</td>\n" +
"        </tr>";
            System.out.println(item.get(0) + " - " + item.get(1) + " - " + item.get(2) );
        }
        return result;
    }
}
