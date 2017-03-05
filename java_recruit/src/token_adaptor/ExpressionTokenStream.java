package token_adaptor;

import java.io.IOException;
import java.io.InputStream;

import static token_adaptor.Token.TokenType.INT;
import static token_adaptor.Token.TokenType.NONE;

public class ExpressionTokenStream  implements TokenStream{

	private byte[] buffer;
	private InputStream in;
	private Token currentToken; //游标，指向当前token对象
	private int length = 0;
	private int tpos = 0;
	
	public ExpressionTokenStream(InputStream in) throws IOException {
		buffer = new byte[1024];
		this.in = in;
		consumeToken(); //当前游标初始也为空，所以要初始化一下
	}
	

	@Override
	public Token getToken() throws IOException {
		return currentToken;
	}

	@Override
	public void consumeToken() throws IOException {
		currentToken = getNextToken();		
	}


	private Token getNextToken() throws IOException {
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
	            
	            if (isNumber(buffer[tpos])) {
	            	int t = 0;
	            	//需要算出输入的数字是几，然后再返回
	            	while (isNumber(buffer[tpos])) {
	            		t = t * 10 + (buffer[tpos] - '0');
	            		tpos++;
	            	}
	            	return new Token(INT,t);
	            }
		}
		
		//默认return null
		return null;		
	}
	
	private boolean isSpace(char c) {
		return (c == ' ' || c == '\t' || c == '\r');
	}
	
	private boolean isNumber(byte c) {
		return (c < '9' && c > '0');
	}

}
