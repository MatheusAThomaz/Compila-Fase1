/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

import java.util.ArrayList;

/**
 *
 * @author matos
 */
public class Expr {
    
    public Expr(ArrayList<Term> terminho){
        this.term = terminho;
    }
    
    public void genC(PW pw, boolean control){
        int size = term.size();        
        
            for (int i = 0; i < size; i++){
                term.get(i).genC(pw, control);               
            }
       
    }
    private ArrayList<Term> term;
}
