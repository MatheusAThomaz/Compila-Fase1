/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

/**
 *
 * @author matos
 */
public class Declaration {
    
    public Declaration(char nome, char tipo)
    {
        this.name = nome;
        this.type = tipo;      
    }
    
    public void genC(PW pw){
        
        if (this.type != 'S') pw.out.print(" " + this.name);
        else {
            int i = 0;
            
            while(Variables.declaration.get(i).getName() != this.name)
            {
                i++;
            }
            if(Variables.values[i].length() > 1)
                pw.out.print(" " + this.name + "[20]");
            else
                 pw.out.print(" " + this.name);
        }
        
    }
    
    public char getType(){
        return this.type;
    }
    
    public char getName(){
        return this.name;
    }
    
    private char name;
    private char type;
    
}
