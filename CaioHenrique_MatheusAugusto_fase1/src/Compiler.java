/*
    Caio Henrique Giacomelli    620297
    Matheus Augusto Thomaz      620599
*/
import AST.*;
import java.util.*;


public class Compiler {

    public Program compile( char []p_input  ) {
        Program p;
        input = p_input;
        tokenPos = 0;
        nextToken();
        p = program();
        //Program result = program();
        if ( token != '\0' )
          error();
        return p;
        }

    //  Program ::= VarDecList ':' Expr       
    private Program program() {
        Body body = null;
        char name = ' ';
      if (token == 'P')
      {          
          
          nextToken();
          
          if (token >= 'a' && token <= 'z'){
              name = Name();
            
              if (token == ':'){
                  nextToken();
                
                  body = Body();
                  if (token == 'E'){
                      nextToken();
                  }
                  else error();
              }
              else error();
          }
          else error();
      }
      else
        error();
     
      return new Program (body,name);
    }
    
   
    
    private Body Body(){
        ArrayList<Declaration> declaration = new ArrayList<Declaration>();
        ArrayList<Stmt> stmt = new ArrayList<Stmt>();
        
        Variables.declaration = new ArrayList<Declaration>();
        
        if (token == 'N' || token == 'F' || token == 'S'){
            declaration = Declaration();
            Variables.declaration = declaration;
            Variables.values = new String[Variables.declaration.size()];
           
        }
         
        if (token == 'R' || token == 'B' || token == 'I' ||
            token == 'W' || (token >= 'a' && token <= 'z'))
        {
            
           stmt.add(Stmt(declaration));
            
            while (token == 'R' || token == 'B' || token == 'I' ||
                   token == 'W' || (token >= 'a' && token <= 'z'))
            {
                stmt.add(Stmt(declaration));
            }    
        }
        else error();
        
        return new Body(declaration, stmt);
    }
    
    private ArrayList<Declaration> Declaration(){
        char nome;
        char tipo;
        ArrayList<Declaration> declaration = new ArrayList<Declaration>();
        Declaration d;
        
        if (token == 'N' || token == 'F' || token == 'S'){
            
            tipo = Type();
            if (token >= 'a' && token <= 'z'){
                nome = Name();
                
                d = new Declaration(nome, tipo);
                declaration.add(d);
                while (token == ','){
                    nextToken();
                    if (token >= 'a' && token <= 'z'){
                        nome = Name();
                        declaration.add(new Declaration(nome, tipo));
                    }
                    else error();
                }
                
                while (token == ';'){
                    nextToken();
                    
                    if (token == 'N' || token == 'F' || token == 'S'){
                        tipo = Type();
                        if (token >= 'a' && token <= 'z'){
                            nome = Name();
                            declaration.add(new Declaration(nome, tipo));
                            while (token == ','){
                                nextToken();
                                if (token >= 'a' && token <= 'z'){
                                    nome = Name();
                                    declaration.add(new Declaration(nome, tipo));
                                }
                                else error();
                            }
                          
                        }
                        else error();
                        
                    }
                    else;
                }
                
            }
            else error();
        }
        else error();
        
        return declaration;
    }
   
    private Stmt Stmt(ArrayList<Declaration> declaration){
        Stmt stmt = null;
        if (token == 'R' || token == 'B' || (token >= 'a' && token <= 'z')){
            stmt = SimpleStmt(declaration);
        }
        else if (token == 'I' || token == 'W'){
            stmt = CompoundStmt(declaration);
        }
        else error();
        
        return stmt;
    }
    
    
    private Stmt CompoundStmt(ArrayList<Declaration> declaration){
        Stmt stmt = null;
        switch (token) {
            case 'I':
                stmt = IfStmt(declaration);
                break;
            case 'W':
                stmt = WhileStmt(declaration);
                break;
            default:
                error();
                break;
        }
        
        return stmt;
    }
    
    private Stmt IfStmt(ArrayList<Declaration> declaration){
        
        ArrayList<Stmt> stmt_if = new ArrayList<Stmt>(), stmt_else = new ArrayList<Stmt>();
        Comparison comparison = null;
        
        if (token == 'I'){
            nextToken();
            comparison = Comparison('\0');
            if (token == '{'){
                nextToken();
                while (token == 'R' || token == 'B' || token == 'I' ||
                       token == 'W' || (token >= 'a' && token <= 'z'))
                {
                    stmt_if.add(Stmt(declaration));
                    
                }
                
                if (token == '}'){
                    nextToken();
                    if (token == 'L'){
                        nextToken();
                        if (token == '{'){
                            nextToken();
                            while (token == 'R' || token == 'B' || token == 'I' ||
                                   token == 'W' || (token >= 'a' && token <= 'z')) 
                            {
                               stmt_else.add(Stmt(declaration));
                            }    
                           
                            if (token == '}'){
                                nextToken();
                            }
                            else error();
                        }
                        else{
                            error();
                        }
                    }
                    else {
                        
                    }
                }
                else error();
            }
            else error();
        }
        else error();
        
        return new IfStmt(comparison, stmt_if, stmt_else, declaration);
    }
    
    private Stmt SimpleStmt(ArrayList<Declaration> declaration){
        Stmt stmt = null;
        if (token >= 'a' && token <= 'z'){
            stmt = ExprStmt(declaration);
        }
        else if (token == 'R'){
            stmt = PrintStmt(declaration);
        }
        else if (token == 'B'){
            stmt = BreakStmt(declaration);
        }
        else{
            error();
        }
        
        return stmt;
    }
    
    private Stmt PrintStmt(ArrayList<Declaration> declaration){
        ArrayList<Comparison> comparison = new ArrayList<Comparison>();
        if (token == 'R'){
            
            nextToken();
            
            comparison.add(Comparison('\0'));
            
            while (token == ','){
                nextToken();
                comparison.add(Comparison('\0'));
            }
            
            if (token == ';'){
                nextToken();
            }
            else error();
        }
        return new PrintStmt(comparison, declaration);
    }
    
    
    private Stmt ExprStmt(ArrayList<Declaration> declaration)
    {
        
        char name = ' ';
        Comparison comparison = null;
        if (token >= 'a' && token <= 'z'){
            name = Name();
            
            
            if (token == '='){
               nextToken();
               comparison = Comparison(name);
               if (token == ';'){
                   nextToken();
               }
               else{
                   error();
               }
            }
            else{
                error();
            }
        }
        
        return new ExprStmt(comparison, name, declaration);
    }
    
    private Stmt BreakStmt(ArrayList<Declaration> declaration)
    {
        if(token == 'B')
        {
            nextToken();
            if (token == ';') {
                nextToken();
            }
            else{ 
                error();
                return null;
            }
        }
        else
        {
            error();
            return null;
        }
        
        return new BreakStmt(declaration);
    }
        
    private Stmt WhileStmt(ArrayList<Declaration> declaration)
    {
        ArrayList<Stmt> stmt = new ArrayList<Stmt>();
        Comparison comparison = null;
        if(token == 'W')
        {
            nextToken();
            comparison = Comparison(' ');
            
            if(token == '{')
            {
                nextToken();
                while (token == 'R' || token == 'B' || token == 'I' ||
                       token == 'W' || (token >= 'a' && token <= 'z'))
                {
                    stmt.add(Stmt(declaration));                    
                }
                
                if (token == '}'){
                    nextToken();
                }
                else error();
            }
            else error();
        }
        else error();
        
        return new WhileStmt(comparison, stmt, declaration);
    }
    
    private Comparison Comparison(char name)
    {
        Comparison comparison;
        Expr expr1 = null, expr2 = null;
        char op = '\0';
        expr1 = Expr(name);
        
        if(token == '>' || token == '<')
        {
            op = CompOp();
            expr2 = Expr(name);
            
            comparison = new Comparison(expr1,expr2,op);
            return comparison;
        }    
        
        comparison = new Comparison(expr1);
        
        return comparison;
    }
    
    private char CompOp()
    {
        char op = token;
        if(token == '>' || token == '<')
        {
            nextToken();
            return op;
        }
        else
        {
            error();
            return '\0';
        }
    }
    
    private Expr Expr(char name)
    {
        Expr expr = null;
        char sinal = '\0';
        ArrayList<Term> term = new ArrayList<Term>();
        
        term.add(Term(name).setSinal(sinal));
        
        while(token == '+' || token == '-')
        {
            sinal = token;
            nextToken();
            term.add(Term(name).setSinal(sinal));
        }
        
        return new Expr(term);
    }
    
    
    private Term Term(char name)
    {
        Term term = null;
        char sinal = '\0';
        ArrayList<Factor> fact = new ArrayList<Factor>();
        
        fact.add(Factor(name).setSinal(sinal));

        while(token == '*' || token == '/')
        {
            sinal = token;
            nextToken();
            fact.add(Factor(name).setSinal(sinal));
        }
        
        return new Term(fact);
    }
    
    private Factor Factor(char name)
    {    
        int i = 0;
        char factor = token;
        char nome;
        String palavra;
        double numero;
        
        if(factor == 39)
        {
            int x;
            palavra = String();
            
            while(i < Variables.declaration.size() && Variables.declaration.get(i).getName() != name)
            {
                i++;
            }
            if(i < Variables.declaration.size())
            Variables.values[i] = palavra;
            return new Factor(palavra);
            //nextToken();
        }
        else if(Character.isDigit(factor) || token == '-' || token == '+' )
        {
            numero = Number();
            
            while( i < Variables.declaration.size() && Variables.declaration.get(i).getName() != name)
            {
                i++;
            }
            if(i < Variables.declaration.size())
            Variables.values[i] = String.valueOf(numero);
            return new Factor(numero);
            //nextToken();
        }
        else
        {
            nome = Name();
            while(i < Variables.declaration.size() && Variables.declaration.get(i).getName() != name)
            {
                i++;
            }
            if(i < Variables.declaration.size())
            Variables.values[i] =  String.valueOf(nome);
            
            return new Factor(nome);
            //nextToken();
        }       
    }
    
    private char Type()
    {
        char type;
        switch (token) {
            case 'N':
                type = 'N';
                nextToken();
                break;
            case 'S':
                type = 'S';
                nextToken();
                break;
            case 'F':
                type = 'F';
                nextToken();
                break;
            default:
                error();
                return '\0';
        }
        
        return type;
    }
    
    // String ::= '''.'''
    // Retorna uma String
    private String String()
    {
        String str = "";
        
        if(token == 39)
        {
            nextSnoke();
            while(token != 39)
            {
                str = str +  token;
                nextSnoke();
            }
            nextToken();
            
            return str;
        }
        
        else
        {
            error();
            return null;
        }
    }
    // Number ::= ['+'|'-']Digit['.'Digit]
    // Retorn um numero com até uma casa depois da vírgula
    private double Number()
    {
        double number;
        int x = 1;
        
        if(token == '-')
        {
            nextToken();
            x =  -1;
        }
        
        else if(token == '+')
        {
            nextToken();
        }
        
        number = Digit();
        
        if(number != -1)
        {
            if(token == '.')
            {
                
                nextToken();
                number = number + (Digit()/10);
                number = number*x;
                
                return number;
            }
            else
            {
                number = number * x;
                return number;
            }
        }
        else
        {
            error();
            return '\0';
            
        }
        
    }
    
    // Letter ::= 'a'|...|'z'
    // Retorna uma letra
    private char Name()
    {
        char letter;
        
        if(token >= 'a' && token <= 'z')
        {
            letter = token;
            nextToken();
            return letter;
        }
        else
        {
            
            error();
            return '\0'; 
       }       
    }
    
    // Digit ::= '0'|...|'9'
    // Retorna um digito
    private double Digit()
    {
        
        double digit;
        
        if(token >= '0' && token <= '9')
        {
            digit = token - '0';
            nextToken();
            return digit;
        }
        else
        {
            error();
            return -1;
        }   
    }

    
        private void nextSnoke() {
        
        if ( tokenPos < input.length ) {
          token = input[tokenPos];        
          tokenPos++;
        }
        else
          token = '\0';
        
        
    }
        
    
    private void nextToken() {
        
        while (  tokenPos < input.length && (input[tokenPos] == ' ' 
                || input[tokenPos] == '\n' || input[tokenPos] == '\t'
                || input[tokenPos] == '\r') ) 
            
          tokenPos++;
        if(input[tokenPos] == '#')
        {
            tokenPos++;
            while(input[tokenPos] != '\n' && input[tokenPos] != '\0')
            {
                tokenPos++;
            }
            
            nextToken();
            
        }
        else {
            if ( tokenPos < input.length ) {
            token = input[tokenPos];        
            tokenPos++;
            }
            else
             token = '\0';
        }
        

        
    }
    
    private void error() {
        if ( tokenPos == 0 ) 
          tokenPos = 1; 
        else 
          if ( tokenPos >= input.length )
            tokenPos = input.length - 1;
        
        String strInput = new String( input, tokenPos - 1, input.length - tokenPos + 1 );
        String strError = "Error at \"" + strInput + "\"";
        System.out.print( strError );
        throw new RuntimeException(strError);
    }
    
    private char token;
    private int  tokenPos;
    private char []input;
    
}
