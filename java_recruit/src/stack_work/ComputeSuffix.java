package stack_work;

import token_adaptor.Token;

import java.io.IOException;

/**
 * Created by Zhang on 2017/3/5.
 */
public class ComputeSuffix {
    public static void main(String[] args) throws IOException{
        System.out.print("Please enter suffix expression: ");
        SuffixExpressionTokenStream expression = new SuffixExpressionTokenStream(System.in);
        Stack<Token>s = new Stack<>(128);

        while (expression.getToken().tokenType != Token.TokenType.NONE) { //读到“回车”停止
            if (expression.getToken().tokenType == Token.TokenType.INT) {//整数则入栈
                s.push(expression.getToken());
                expression.consumeToken(); //get完了别忘了consume
            } else {  //是运算符，则出栈两个数，算出结果再入栈
                int num_2 = Integer.parseInt(s.pop().toString());
                int num_1 = Integer.parseInt(s.pop().toString());
                int result;

                switch (expression.getToken().tokenType){
                    case DIV:
                        result = num_1 / num_2;
                        s.push(new Token(Token.TokenType.INT, result));
                        break;
                    case MINUS:
                        result = num_1 - num_2;
                        s.push(new Token(Token.TokenType.INT, result));
                        break;
                    case PLUS:
                        result = num_1 + num_2;
                        s.push(new Token(Token.TokenType.INT, result));
                        break;
                    case MULT:
                        result = num_1 * num_2;
                        s.push(new Token(Token.TokenType.INT, result));
                        break;
                }
                expression.consumeToken();
            }
        }
        System.out.println("The result is : " + s.pop().toString());
    }
}
