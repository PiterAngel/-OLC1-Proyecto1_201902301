package Analizadores;
import java_cup.runtime.*;
import javax.swing.JOptionPane;

import com.mycompany.proyecto1.error;
import com.mycompany.proyecto1.Proyecto1;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaraciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
%}

//------------Directivas
%class Lexico
%cupsym sym
%public 
%cup
%char
%column
%full
%line
%unicode

//---------------Inicializar el contador de columna y fila con 1
%init{ 
    yyline = 1; 
    yychar = 1; 
%init}

//-------------Expresiones regulares
numero              = [0-9]+
letra               = [a-zA-ZñÑ]
cadena              = [\"][^\"\n]+[\"]
id                  = {letra}({letra}|{numero}|_)*

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

comentario_simple    = "//" {InputCharacter}* {LineTerminator}?


%state COMMENT


%%
//----------- Palabras Reservadas

<YYINITIAL>"ER"        { System.out.println("Reconocio "+yytext()+" pr_er"); return new Symbol(sym.pr_er, yycolumn, yyline, yytext()); }
<YYINITIAL>"CONJ"      { System.out.println("Reconocio "+yytext()+" pr_conj"); return new Symbol(sym.pr_conj, yycolumn, yyline, yytext()); }


//--------------- Simbolos

<YYINITIAL>";"         { System.out.println("Reconocio "+yytext()+" pnt_coma"); return new Symbol(sym.pnt_coma, yycolumn, yyline, yytext()); }
<YYINITIAL>"."         { System.out.println("Reconocio "+yytext()+" pnt"); return new Symbol(sym.pnt, yycolumn, yyline, yytext()); }
<YYINITIAL>"*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(sym.por, yycolumn, yyline, yytext()); }
<YYINITIAL>"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(sym.mas, yycolumn, yyline, yytext()); }
<YYINITIAL>"|"         { System.out.println("Reconocio "+yytext()+" or"); return new Symbol(sym.or, yycolumn, yyline, yytext()); }
<YYINITIAL>"("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(sym.para, yycolumn, yyline, yytext()); }
<YYINITIAL>")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(sym.parc, yycolumn, yyline, yytext()); }
<YYINITIAL>"{"         { System.out.println("Reconocio "+yytext()+" llavea"); return new Symbol(sym.llavea, yycolumn, yyline, yytext()); }
<YYINITIAL>"}"         { System.out.println("Reconocio "+yytext()+" llavec"); return new Symbol(sym.llavec, yycolumn, yyline, yytext()); }
<YYINITIAL>"\\\""      { System.out.println("Reconocio "+yytext()+" comilla"); return new Symbol(sym.comilla, yycolumn, yyline, yytext()); }
<YYINITIAL>"\\'"       { System.out.println("Reconocio "+yytext()+" simple"); return new Symbol(sym.simple, yycolumn, yyline, yytext()); }
<YYINITIAL>"\\n"       { System.out.println("Reconocio "+yytext()+" salto"); return new Symbol(sym.salto, yycolumn, yyline, yytext()); }
<YYINITIAL>"~"         { System.out.println("Reconocio "+yytext()+" rango"); return new Symbol(sym.rango, yycolumn, yyline, yytext()); }
<YYINITIAL>"-"         { System.out.println("Reconocio "+yytext()+" guion"); return new Symbol(sym.guion, yycolumn, yyline, yytext()); }
<YYINITIAL>"_"         { System.out.println("Reconocio "+yytext()+" guion_bajo"); return new Symbol(sym.guion_bajo, yycolumn, yyline, yytext()); }
<YYINITIAL>">"         { System.out.println("Reconocio "+yytext()+" ma_que"); return new Symbol(sym.ma_que, yycolumn, yyline, yytext()); }
<YYINITIAL>":"         { System.out.println("Reconocio "+yytext()+" dos_ptn"); return new Symbol(sym.dos_ptn, yycolumn, yyline, yytext()); }
<YYINITIAL>"?"         { System.out.println("Reconocio "+yytext()+" question"); return new Symbol(sym.question, yycolumn, yyline, yytext()); }
<YYINITIAL>","         { System.out.println("Reconocio "+yytext()+" coma"); return new Symbol(sym.coma, yycolumn, yyline, yytext()); }
<YYINITIAL>"!"         { System.out.println("Reconocio "+yytext()+" admiracion"); return new Symbol(sym.admiracion, yycolumn, yyline, yytext()); }



//---------- Estructura de las ER

<YYINITIAL>{numero}    { System.out.println("Reconocio "+yytext()+" numero"); return new Symbol(sym.numero, yycolumn, yyline, yytext()); }
<YYINITIAL>{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(sym.cadena, yycolumn, yyline, yytext()); }
<YYINITIAL>{id}        { System.out.println("Reconocio "+yytext()+" id"); return new Symbol(sym.id, yycolumn, yyline, yytext()); }


//--------- Espacios en blanco y comentarios 
<YYINITIAL>[ \t\r\n\f]             {/* Espacios en blanco */}
<YYINITIAL>{comentario_simple}     {System.out.println("Comentario: "+yytext()); }
<YYINITIAL> "<!"                     {System.out.println("Inicia Comentario Multilinea" + yytext());  yybegin(COMMENT);}
<COMMENT> "*"                       {}
<COMMENT> [^"!>"]                   {}
<COMMENT> "!>"                      {System.out.println("Comentario Multilinea" + yytext()); yybegin(YYINITIAL);}

//------------- Salto de linea, regresa al inicio las columnas
\n {yychar=1;}


//-------------- Errores Lexicos
. {
    System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn); 
    error nuevo = new error("Error Lexico (Recuperado)", yytext(), yyline, yycolumn);
    Proyecto1.listaErrores.add(nuevo);
}