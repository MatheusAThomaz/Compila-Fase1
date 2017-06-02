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
public class PrintStmt extends Stmt {
    
    public PrintStmt(ArrayList<Comparison> comparison, ArrayList<Declaration> declaration)
    {
        this.comparison = comparison;
        this.declaration = declaration;
    }
    
    @Override
    public void genC(PW pw, boolean control){
        Variables.print_variables = new char[1000];

        int i = 0, size = comparison.size();
        control = true;
        char c = 34;
        
        pw.out.print("\n\tprintf(" + c);
        for(i = 0; i < size; i++)
        {
            comparison.get(i).genC(pw, control);
        }
        pw.out.print(c);
        
        for(i = 0; i < Variables.i; i++)
        {
            pw.out.print(", " + Variables.print_variables[i]);
        }
        
        pw.out.println(");\n");
        
        Variables.i = 0;
        
        
        
        
    }
    private ArrayList<Comparison> comparison;
    private ArrayList<Declaration> declaration;
}
