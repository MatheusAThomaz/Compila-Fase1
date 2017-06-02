/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
package AST;

/**
 *
 * @author matos
 */
public class Comparison {
    public Comparison(Expr expr1, Expr expr2, char comp)
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.compOp = comp;
    }
    
    public Comparison(Expr expr1)
    {
        this.expr1 = expr1;
        this.expr2 = null;
        this.compOp = '\0';
    }
    public void genC(PW pw, boolean control){
        
        if(expr2 == null)
        {
            expr1.genC(pw, control);
        }
        else{
            expr1.genC(pw, control);
            pw.out.print(compOp);
            expr2.genC(pw, control);
        }
    }
    
    private Expr expr1;
    private Expr expr2;
    private char compOp;
}
