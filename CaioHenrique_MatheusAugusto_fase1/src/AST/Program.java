/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

import java.util.*;

public class Program {
    public Program(Body bd, char nome) {
        this.body = bd;
        this.name = nome;
    }
    public void genC(PW pw) {        
        pw.out.println("#include <stdio.h>");
        for(int i = 0; i < Variables.declaration.size(); i++)
        {
            if(Variables.declaration.get(i).getType() == 'S')
            {
                if(Variables.values[i].length() > 1)
                {
                    pw.out.println("#include <string.h>");
                    break;
                }
                    
            }
        }
        pw.out.println("");
        body.genC(pw);
    }                             
    
    private char name;
    private Body body;
    
}