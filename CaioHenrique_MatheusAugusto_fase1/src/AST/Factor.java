/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

/**
 *
 * @author matos
 */
public class Factor {
    
    
    public Factor(char nome){
        this.nome = nome;
        this.numero = 1.11;
        this.palavra = null;
    }
    
    public Factor(double numero){
        this.nome = '\0';
        this.numero = numero;
        this.palavra = null;
    }
    
    public Factor(String palavra){
        this.nome = '\0';
        this.numero = 1.11;
        this.palavra = palavra;
    }
    
    public Factor setSinal(char sinal){
        this.sinal = sinal;
        return this;
    }
    
    public void genC(PW pw, boolean control){
        char c = 34;
        
        
        
        if (this.sinal == '\0'){
            
            if (this.nome == '\0' && this.numero == 1.11){
                int i = 0;
                if(this.palavra.length() > 1)
                    pw.out.print("" + this.palavra);
                else
                {
                    c = 39;
                     pw.out.print("" + c + this.palavra + c);
                }
            }  
            
            else if (this.palavra == null && this.numero == 1.11){
                if (control){
                    int i = 0, x = 0;
                    char tipo;
                    while (Variables.declaration.get(i).getName() != this.nome){
                        i++;
                    }
                    
                    switch (Variables.declaration.get(i).getType()) {
                        case 'N':
                            pw.out.print(" %d ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                        case 'F':
                            pw.out.print(" %f ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                        default:
                            
                            while(Variables.declaration.get(x).getName() != this.nome)
                            {
                                x++;
                            }
                            if(Variables.values[x].length() > 1)
                                pw.out.print(" %s ");
                            else
                                pw.out.print(" %c ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                    }                    
                }
                else{
                    pw.out.print(this.nome);
                }
            }
            
            else{
                
                if((this.numero - (int)this.numero) != 0)
                {
                    pw.out.print(this.numero);
                }
                else
                    pw.out.print((int)this.numero);
            }
        }
        
        else{
            pw.out.print(this.sinal + " ");
            if (this.nome == '\0' && this.numero == 1.11){
                pw.out.print("'"  + this.palavra + "'");
            }  
            
            else if (this.palavra == null && this.numero == 1.11){
                if (control){
                    int i = 0, x = 0;
                    char tipo;
                    switch (Variables.declaration.get(i).getType()) {
                        case 'N':
                            pw.out.print(" %d ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                        case 'F':
                            pw.out.print(" %f ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                        default:
                            
                            while(Variables.declaration.get(x).getName() != this.nome)
                            {
                                x++;
                            }
                            if(Variables.values[x].length() > 1)
                                pw.out.print(" %s ");
                            else
                                pw.out.print(" %c ");
                            Variables.print_variables[Variables.i] = this.nome;
                            Variables.i++;
                            break;
                    }                    
                }
                else{
                    pw.out.print(this.nome);
                }
            }
            
            else{
                if((this.numero - (int)this.numero) != 0)
                {
                    pw.out.print(this.numero);
                }
                else
                    pw.out.print((int)this.numero);
            }
        }
        
    }
    
    private char sinal;
    private double numero;
    private String palavra;
    private char nome;
}
