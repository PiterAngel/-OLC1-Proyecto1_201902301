
// ------------  Paquete e importaciones ------------
package analizarxd; 


import java_cup.runtime.*;

%%	
//-------> Directivas (No tocar)

%public 
%class Lexer
%cup
%char
%column
%line
%unicode
%ignorecase

%{ 
%} 

// ------> Expresiones Regulares 
op = [\/]
entero = [0-9]+
identificador = [a-zA-Z_][a-zA-Z0-9_]*
comentariomultilinea = <!([^!]|\![^\>])*!>
comentario =  (\/\/)(.+)*
cadena = [\"][^\"\n]*[\"]



%%
// ------------  Reglas Lexicas / Palabras reservadas -------------------
"conj"          {   func.Funcion.tablaT(yytext(), "RESERVADA_CONJ", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_CONJ, yycolumn, yyline, yytext()); }


//------> PARA SIMBOLOS 

"{"             { func.Funcion.tablaT(yytext(), "CORCHETEABRE", yyline, yycolumn); 
                 return new Symbol(sym.CORCHETEABRE, yycolumn, yyline, yytext()) ;   }

"}"             { func.Funcion.tablaT(yytext(), "CORCHETECIERRA", yyline, yycolumn);
                 return new Symbol(sym.CORCHETECIERRA, yycolumn, yyline, yytext()); }

"("             { func.Funcion.tablaT(yytext(), "PARENTESISABRE", yyline, yycolumn); 
                 return new Symbol(sym.PARENTESISABRE, yycolumn, yyline, yytext()) ;   }

")"             { func.Funcion.tablaT(yytext(), "PARENTESISCIERRA", yyline, yycolumn);
                 return new Symbol(sym.PARENTESISCIERRA, yycolumn, yyline, yytext()); }

":"             { func.Funcion.tablaT(yytext(), "DOSPUNTOS", yyline, yycolumn);
                return new Symbol(sym.DOSPUNTOS, yycolumn, yyline, yytext()); }

";"             { func.Funcion.tablaT(yytext(), "PUNTOYCOMA", yyline, yycolumn);
                return new Symbol(sym.PUNTOYCOMA, yycolumn, yyline, yytext()); }

"-"             { func.Funcion.tablaT(yytext(), "GUION", yyline, yycolumn);
                return new Symbol(sym.GUION, yycolumn, yyline, yytext()); }

">"             { func.Funcion.tablaT(yytext(), "MAYORQUE", yyline, yycolumn);
                return new Symbol(sym.MAYORQUE, yycolumn, yyline, yytext()); }

"~"             { func.Funcion.tablaT(yytext(), "VIRGULILLA", yyline, yycolumn);
                return new Symbol(sym.VIRGULILLA, yycolumn, yyline, yytext()); }

"."             { func.Funcion.tablaT(yytext(), "PUNTO", yyline, yycolumn);
                return new Symbol(sym.PUNTO, yycolumn, yyline, yytext()); }

"*"             { func.Funcion.tablaT(yytext(), "CERO_O_MUCHAS_VECES", yyline, yycolumn);
                return new Symbol(sym.CERO_O_MUCHAS_VECES, yycolumn, yyline, yytext()); }

"|"             { func.Funcion.tablaT(yytext(), "OR", yyline, yycolumn);
                return new Symbol(sym.OR, yycolumn, yyline, yytext()); }

"_"             { func.Funcion.tablaT(yytext(), "GUION_BAJO", yyline, yycolumn);
                return new Symbol(sym.GUION_BAJO, yycolumn, yyline, yytext()); }

"+"             { func.Funcion.tablaT(yytext(), "UNA_O_MUCHAS_VECES", yyline, yycolumn);
                return new Symbol(sym.UNA_O_MUCHAS_VECES, yycolumn, yyline, yytext()); }

"?"             { func.Funcion.tablaT(yytext(), "UNA_O_NINGUNA_VEZ", yyline, yycolumn);
                return new Symbol(sym.UNA_O_NINGUNA_VEZ, yycolumn, yyline, yytext()); }

","             { func.Funcion.tablaT(yytext(), "COMA", yyline, yycolumn);
                return new Symbol(sym.COMA, yycolumn, yyline, yytext()); }

//------> PARA EXPRESIONES REGULARES

{entero}        {   func.Funcion.tablaT(yytext(), "ENTERO", yyline, yycolumn); 
                    return new Symbol(sym.ENTERO, yycolumn, yyline, yytext()); }

{identificador} {   func.Funcion.tablaT(yytext(), "ID", yyline, yycolumn); 
                    return new Symbol(sym.ID, yycolumn, yyline, yytext()); }

{cadena}        {   func.Funcion.tablaT(yytext(), "CADENA", yyline, yycolumn); 
                    return new Symbol(sym.CADENA, yycolumn, yyline, yytext()); }


{op}            {   func.Funcion.tablaT(yytext(), "OPERACIONES", yyline, yycolumn); 
                    return new Symbol(sym.OPERACIONES, yycolumn, yyline, yytext()); }


{comentario}            {}

{comentariomultilinea}  {}


//------> Ingorados 
[ \t\r\n\f]     {/* Espacios en blanco se ignoran */}

//------> Errores Léxicos 
.           	{ func.Funcion.tablaTerrores(yytext(), "Error lexico", yyline, yycolumn);
                  System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); 
                }
