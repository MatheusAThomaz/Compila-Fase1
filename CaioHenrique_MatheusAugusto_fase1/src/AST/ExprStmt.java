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
public class ExprStmt extends Stmt {
    
    public ExprStmt(Comparison comp, char nome, ArrayList<Declaration> declaration)
    {
        this.comparison = comp;
        this.name = nome;
        this.declaration = declaration;
    }
    
    @Override
    public void genC(PW pw, boolean control){
        int i = 0,x = 0, size = declaration.size();
        char c = 39;
        char c2 = 34;
        
        while(declaration.get(i).getName() != this.name && i < size) i++;
        
        if(declaration.get(i).getType() == 'S')
        {
            x = 0;
            while(Variables.declaration.get(x).getName() != this.name)
            {
                x++;
            }
            
            //System.out.println(Variables.values + "qe");
            if(Variables.values[x].length() > 1)
            {
                pw.out.print("strcpy("+this.name+", " + c2);
                comparison.genC(pw, control);
                pw.out.println(c2 + ");");
            }
            else
            {
                pw.out.print(this.name + " = ");
                comparison.genC(pw, control);
                pw.out.println(";");
            }
            
            
             
            
        }
        else{
            pw.out.print(this.name + " = ");
             comparison.genC(pw, control);
            pw.out.println(";");
        }
        
       
        
    }
    
    private char name;
    private Comparison comparison;
    private ArrayList<Declaration> declaration;
}
