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
public class BreakStmt extends Stmt {
    
    public BreakStmt (ArrayList<Declaration> declaration){
        this.declaration = declaration;
    }
    
    @Override
    public void genC(PW pw, boolean control){
        pw.out.println("break;");
    }
    
    private ArrayList<Declaration> declaration;
}
