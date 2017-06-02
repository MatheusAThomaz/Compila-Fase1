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
public class Term {
    
    public Term(ArrayList<Factor> factinho){
        this.fact = factinho;
    }
    
    public Term setSinal(char sinal){
        this.sinal = sinal;
        return this;
    }
    
    public void genC(PW pw, boolean control){
        int size = fact.size();
        
        if (sinal == '\0'){
            
            for (int i = 0; i < size; i++){
                fact.get(i).genC(pw, control);
            }
        }
        else{
            
            for (int i = 0; i < size; i++){
                pw.out.print(this.sinal);
                fact.get(i).genC(pw, control);
            }           
        }
    }

    private ArrayList<Factor> fact;
    private char sinal;    
}
