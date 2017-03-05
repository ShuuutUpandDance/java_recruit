package function_call;

import token_adaptor.*;
import static token_adaptor.Token.TokenType.*;

import java.io.BufferedInputStream;
import java.io.IOException;


/**
 * Created by Zhang on 2017/3/5.
 */
public class ComplexExpression { //homework_1,能节省一半的函数栈
    public TokenStream ts;

    public static void main(String args[]) throws IOException {
        ComplexExpression e = new ComplexExpression();
        System.out.println(e.expression());
    }

    public ComplexExpression() throws IOException {
        ts = new ExpressionTokenStream(new BufferedInputStream(System.in));
    }

    public int expression() throws IOException{
        int t = term();

        try {
            if (match(PLUS)) {
                return t + term();
            }
            else if (match(MINUS)) {
                return t - term();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int term() throws IOException{
        int t = factor();

        try {
            if (match(MULT)) {
                return t * factor();
            }
            else if(match(DIV)){
                return t / factor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int factor() throws IOException{
        Token t ;
        try {
            t = ts.getToken();

            if (t.tokenType == MINUS){  //homework_2
                ts.consumeToken();
                return - factor();
            }

            else if (t.tokenType == INT) {
                ts.consumeToken();
                return (((Integer) t.value).intValue());
            }
            else if (match(LPAR)) {
                int v = expression();
                if (!match(RPAR))
                    assert false;
                return v;
            }
            else {
                throw new IOException("Illegal Expression!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // should not reach here
        return 0;
    }

    private boolean match(Token.TokenType tt) throws IOException {
        if (ts.getToken().tokenType == tt) {
            ts.consumeToken();
            return true;
        }
        return false;
    }
}
