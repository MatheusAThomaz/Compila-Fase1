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
public class WhileStmt extends Stmt {
    
    public WhileStmt(Comparison comparison, ArrayList<Stmt> stmt, ArrayList<Declaration> declaration){
        
        this.comparison = comparison;
        this.stmt = stmt;
        this.declaration = declaration;
    }
    @Override
    public void genC(PW pw, boolean control){
        int i, size = stmt.size();
        
        pw.out.print("while(");
        comparison.genC(pw, control);
        pw.out.println("){");
        pw.out.println("");
        
        for(i = 0; i < size; i++)
        {
            pw.out.print("\t");
            stmt.get(i).genC(pw, control);
        }
        pw.out.println("\t}");
    }
    
    private Comparison comparison;
    private ArrayList<Stmt> stmt;
    private ArrayList<Declaration> declaration;
}   
