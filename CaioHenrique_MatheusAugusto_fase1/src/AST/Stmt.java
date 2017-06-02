/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

/**
 *
 * @author matos
 */
public abstract class Stmt {
    
    public abstract void genC(PW pw, boolean control);
} 
