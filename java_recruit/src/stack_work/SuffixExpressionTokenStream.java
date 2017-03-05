package stack_work;

import token_adaptor.Token;
import token_adaptor.TokenStream;

import java.io.IOException;
import java.io.InputStream;

import static token_adaptor.Token.TokenType.NONE;

/**
 * Created by Zhang on 2017/3/5.
 */
public class SuffixExpressionTokenStream  implements TokenStream{
    private byte[] buffer;
    private InputStream in;
    private Token currentToken; //游标，指向当前token对象
    private int length = 0;
    private int tpos = 0;

    public SuffixExpressionTokenStream (InputStream in) throws IOException {
        buffer = new byte[1024];
        this.in = in;
        //当前游标初始也为空，所以要初始化一下
        consumeToken();
    }

    @Override
    public Token getToken() throws IOException {
        return currentToken;
    }

    @Override
    public void consumeToken() throws IOException {
        currentToken = getNextToken();
    }

    private Token getNextToken() throws IOException{
        //初始buffer为空，由in读入字节，同时获取长度
        if(length == 0) length = in.read(buffer);
        //如果读入后仍为空，返回空
        if(length == 0) return null;

        while(tpos < length) {
            //先判断是不是回车，因为可能直接敲回车输入空字节
            if (buffer[tpos] == '\n')
                return new Token(NONE,"");
            //忽略空格、\t、\r
            while (isSpace((char) buffer[tpos])) {
                tpos++;
            }

            if (buffer[tpos] == '(') {
                tpos++;
                return new Token(Token.TokenType.LPAR, "(");
            }

            if (buffer[tpos] == ')') {
                tpos++;
                return new Token(Token.TokenType.RPAR, ")");
            }

            if (buffer[tpos] == '+') {
                tpos++;
                return new Token(Token.TokenType.PLUS, "+");
            }

            if (buffer[tpos] == '-') {
                tpos++;
                return new Token(Token.TokenType.MINUS, "-");
            }

            if (buffer[tpos] == '*') {
                tpos++;
                return new Token(Token.TokenType.MULT, "*");
            }

            if (buffer[tpos] == '/') {
                tpos++;
                return new Token(Token.TokenType.DIV, "/");
            }

            if (buffer[tpos] <= '9' && buffer[tpos] >= '0') {
                int t;
                t = buffer[tpos] - '0';
                tpos++;
                return new Token(Token.TokenType.INT, t);

            }

        }

        return null;
    }

    private boolean isSpace(char c){ return (c == ' ' || c == '\t' || c == '\r'); }

}
