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
public class Body {
    
    public Body(ArrayList<Declaration> declar, ArrayList<Stmt> stm){
        this.declaration = declar;
        this.stmt = stm;
    }
    
    public void genC(PW pw){
        int size = declaration.size(), i = 0;
        char tipo;
        boolean control = false;
        
        pw.out.println("int main(){");
        pw.out.println("");
        
        while(i < size){
            tipo = declaration.get(i).getType();
            switch (tipo) {
                case 'N':
                    pw.out.print("\tint");
                    break;
                case 'F':
                    pw.out.print("\tfloat");
                    break;
                default:
                    pw.out.print("\tchar");
                    break;
            }
            
            declaration.get(i).genC(pw);
            
            i++;
            while(i < size && tipo == declaration.get(i).getType() ){
                pw.out.print(",");
                declaration.get(i).genC(pw);          
                i++;
            }
            pw.out.println(";");
                      
        }
        
        pw.out.println("");
        
        size = stmt.size();
        
        for(i = 0; i < size; i++)
        {
            pw.out.print("\t");
            stmt.get(i).genC(pw, control);
            
        }
        
        pw.out.println("\n\treturn 0;");
        pw.out.println("}");
        
        
    }
    
    
    private ArrayList<Declaration> declaration = new ArrayList<Declaration>();
    private ArrayList<Stmt> stmt;
}
