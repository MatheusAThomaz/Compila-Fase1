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
public class IfStmt extends Stmt {
    
    public IfStmt(Comparison comp, ArrayList<Stmt> stmt_if, ArrayList<Stmt> stmt_else, ArrayList<Declaration> declaration)
    {
        this.comparison = comp;
        this.Stmt_IF = stmt_if;
        this.Stmt_ELSE = stmt_else;
        this.declaration = declaration;
    }
    @Override
    public void genC(PW pw, boolean control){
        int i, size = Stmt_IF.size();
        pw.out.print("if(");
        comparison.genC(pw, control);
        pw.out.println("){");
        
        for(i = 0; i < size; i++)
        {
            pw.out.print("\t\t");
            Stmt_IF.get(i).genC(pw, control);
        }
        pw.out.println("\t}\n");
        
        if(Stmt_ELSE.size() > 0)
        {
            pw.out.println("\telse{");
             for(i = 0; i < Stmt_ELSE.size(); i++)
                {
                    pw.out.print("\t");
                    Stmt_ELSE.get(i).genC(pw, control);
                }
             pw.out.println("\t}\n");
        }
    }
    
    private Comparison comparison;
    private ArrayList<Stmt> Stmt_IF;
    private ArrayList<Stmt> Stmt_ELSE;
    private ArrayList<Declaration> declaration;
}
