
// ------------  Paquete e importaciones ------------
package compi; 

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

entero = [0-9]+
identificador = [a-zA-Z_][a-zA-Z0-9_]*
cadena = [\"][^\"\n]*[\"]
decimales = [0-9]+\.[0-9]+
charr = \'([0-9]|[Aa-zZ])\'
comentario =  (\/\/)(.+)*
comentariomultilinea = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%
// ------------  Reglas Lexicas / Palabras reservadas -------------------

"("             {   func.Funcion.tabla_T_stat(yytext(), "PARENTESISABRE", yyline, yycolumn); 
                    return new Symbol(sym.PARENTESISABRE, yycolumn, yyline, yytext()); }

")"             {   func.Funcion.tabla_T_stat(yytext(), "PARENTESISCIERRA", yyline, yycolumn); 
                    return new Symbol(sym.PARENTESISCIERRA, yycolumn, yyline, yytext()); }

"{"             {   func.Funcion.tabla_T_stat(yytext(), "LLAVEABRE", yyline, yycolumn); 
                    return new Symbol(sym.LLAVEABRE, yycolumn, yyline, yytext()); }

"}"             {   func.Funcion.tabla_T_stat(yytext(), "LLAVECIERRA", yyline, yycolumn); 
                    return new Symbol(sym.LLAVECIERRA, yycolumn, yyline, yytext()); }

";"             {   func.Funcion.tabla_T_stat(yytext(), "PUNTOYCOMA", yyline, yycolumn); 
                    return new Symbol(sym.PUNTOYCOMA, yycolumn, yyline, yytext()); }

"*"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOPOR", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOPOR, yycolumn, yyline, yytext()); }

"+"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMAS", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMAS, yycolumn, yyline, yytext()); }

"-"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMENOS", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMENOS, yycolumn, yyline, yytext()); }

"/"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNODIVISION", yyline, yycolumn); 
                    return new Symbol(sym.SIGNODIVISION, yycolumn, yyline, yytext()); }

"="             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOIGUAL", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOIGUAL, yycolumn, yyline, yytext()); }

">"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMAYOR", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMAYOR, yycolumn, yyline, yytext()); }

"<"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMENOR", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMENOR, yycolumn, yyline, yytext()); }

">="            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMAYORIGUAL", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMAYORIGUAL, yycolumn, yyline, yytext()); }

"<="            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOMENORIGUAL", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOMENORIGUAL, yycolumn, yyline, yytext()); }

"=="            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOIGUALIGUAL", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOIGUALIGUAL, yycolumn, yyline, yytext()); }

"."             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOPUNTO", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOPUNTO, yycolumn, yyline, yytext()); }

","             {   func.Funcion.tabla_T_stat(yytext(), "SIGNOCOMA", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOCOMA, yycolumn, yyline, yytext()); }

"!="            {   func.Funcion.tabla_T_stat(yytext(), "SIGNODIFERENTE", yyline, yycolumn); 
                    return new Symbol(sym.SIGNODIFERENTE, yycolumn, yyline, yytext()); }

"++"            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOINCREMENTO", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOINCREMENTO, yycolumn, yyline, yytext()); }

"&&"            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOAND", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOAND, yycolumn, yyline, yytext()); }


"||"            {   func.Funcion.tabla_T_stat(yytext(), "SIGNOOR", yyline, yycolumn); 
                    return new Symbol(sym.SIGNOOR, yycolumn, yyline, yytext()); }

"!"             {   func.Funcion.tabla_T_stat(yytext(), "SIGNONOT", yyline, yycolumn); 
                    return new Symbol(sym.SIGNONOT, yycolumn, yyline, yytext()); }

"$"             {   func.Funcion.tabla_T_stat(yytext(), "DOLAR", yyline, yycolumn); 
                    return new Symbol(sym.DOLAR, yycolumn, yyline, yytext()); }

"["             {   func.Funcion.tabla_T_stat(yytext(), "CORCHETEABRE", yyline, yycolumn); 
                    return new Symbol(sym.CORCHETEABRE, yycolumn, yyline, yytext()); }

"]"             {   func.Funcion.tabla_T_stat(yytext(), "CORCHETECIERRA", yyline, yycolumn); 
                    return new Symbol(sym.CORCHETECIERRA, yycolumn, yyline, yytext()); }

":"             {   func.Funcion.tabla_T_stat(yytext(), "DOSPUNTOS", yyline, yycolumn); 
                    return new Symbol(sym.DOSPUNTOS, yycolumn, yyline, yytext()); }



// ============ reservadas ============
"int"           {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_INT", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_INT, yycolumn, yyline, yytext()); }

"double"        {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_DOUBLE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_DOUBLE, yycolumn, yyline, yytext()); }

"char"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_CHAR", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_CHAR, yycolumn, yyline, yytext()); }

"bool"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_BOOL", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_BOOL, yycolumn, yyline, yytext()); }

"true"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_TRUE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_TRUE, yycolumn, yyline, yytext()); }

"false"         {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_FALSE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_FALSE, yycolumn, yyline, yytext()); }

"string"        {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_STRING", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_STRING, yycolumn, yyline, yytext()); }

"void"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_VOID", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_VOID, yycolumn, yyline, yytext()); }

"main"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_MAIN", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_MAIN, yycolumn, yyline, yytext()); }

"if"            {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_IF", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_IF, yycolumn, yyline, yytext()); }

"console"       {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_CONSOLE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_CONSOLE, yycolumn, yyline, yytext()); }

"write"         {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_WRITE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_WRITE, yycolumn, yyline, yytext()); }

"else"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_ELSE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_ELSE, yycolumn, yyline, yytext()); }

"definirglobales"  {func.Funcion.tabla_T_stat(yytext(), "RESERVADA_DEFINIRG", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_DEFINIRG, yycolumn, yyline, yytext()); }

"for"           {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_FOR", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_FOR, yycolumn, yyline, yytext()); }

"while"         {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_WHILE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_WHILE, yycolumn, yyline, yytext()); }

"break"         {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_BREAK", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_BREAK, yycolumn, yyline, yytext()); }

"newvalor"      {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_NEWVALOR", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_NEWVALOR, yycolumn, yyline, yytext()); }

"graficabarras" {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_GRAFICARBARRAS", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_GRAFICARBARRAS, yycolumn, yyline, yytext()); }

"titulox"       {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_TITULOX", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_TITULOX, yycolumn, yyline, yytext()); }

"tituloy"       {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_TITULOY", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_TITULOY, yycolumn, yyline, yytext()); }

"titulo"        {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_TITULO", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_TITULO, yycolumn, yyline, yytext()); }

"ejex"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_EJEX", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_EJEX, yycolumn, yyline, yytext()); }

"valores"       {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_VALORES", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_VALORES, yycolumn, yyline, yytext()); }

"do"            {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_DO", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_DO, yycolumn, yyline, yytext()); }

"switch"        {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_SWITCH", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_SWITCH, yycolumn, yyline, yytext()); }

"case"          {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_CASE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_CASE, yycolumn, yyline, yytext()); } 

"default"       {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_DEFAULT", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_DEFAULT, yycolumn, yyline, yytext()); } 

"graficapie"    {   func.Funcion.tabla_T_stat(yytext(), "RESERVADA_GRAFICARPIE", yyline, yycolumn); 
                    return new Symbol(sym.RESERVADA_GRAFICARPIE, yycolumn, yyline, yytext()); } 


{entero}        {   func.Funcion.tabla_T_stat(yytext(), "ENTERO", yyline, yycolumn); 
                    return new Symbol(sym.ENTERO, yycolumn, yyline, yytext()); }

{identificador} {   func.Funcion.tabla_T_stat(yytext(), "ID", yyline, yycolumn); 
                    return new Symbol(sym.ID, yycolumn, yyline, yytext()); }

{cadena}        {   func.Funcion.tabla_T_stat(yytext(), "CADENA", yyline, yycolumn); 
                    return new Symbol(sym.CADENA, yycolumn, yyline, yytext()); }

{charr}         {   func.Funcion.tabla_T_stat(yytext(), "CHARR", yyline, yycolumn); 
                    return new Symbol(sym.CHARR, yycolumn, yyline, yytext()); }

{decimales}     {   func.Funcion.tabla_T_stat(yytext(), "DECIMALES", yyline, yycolumn); 
                    return new Symbol(sym.DECIMALES, yycolumn, yyline, yytext()); }

{comentario}            {}
{comentariomultilinea}  {}

//------> Ingorados 
[ \t\r\n\f]     {/* Espacios en blanco se ignoran */}

//------> Errores Léxicos 
.           	{ func.Funcion.tabla_T_errores_stat(yytext(), "ERROR LEXICO", yyline, yycolumn); 
                  System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); 
                }