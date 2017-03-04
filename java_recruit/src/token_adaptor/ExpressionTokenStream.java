package token_adaptor;

import java.io.IOException;
import java.io.InputStream;
import token_adaptor.Token;
import token_adaptor.TokenStream;
import static token_adaptor.Token.TokenType.INT;
import static token_adaptor.Token.TokenType.NONE;

public class ExpressionTokenStream  implements TokenStream{

	private byte[] buffer;
	private InputStream in;
	private Token currentToken; //�αָ꣬��ǰtoken����
	private int length = 0;
	private int tpos = 0;
	
	public ExpressionTokenStream(InputStream in) throws IOException {
		buffer = new byte[1024];
		this.in = in;
		consumeToken(); //��ǰ�α��ʼҲΪ�գ�����Ҫ��ʼ��һ��
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
		//��ʼbufferΪ�գ���in�����ֽڣ�ͬʱ��ȡ����
		if(length == 0) length = in.read(buffer);
		//����������Ϊ�գ����ؿ�
		if(length == 0) return null;
		
		while(tpos < length) {
			//���ж��ǲ��ǻس�����Ϊ����ֱ���ûس�������ֽ�
			if (buffer[tpos] == '\n')
				return new Token(NONE,"");
			//���Կո�\t��\r
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
	            	//��Ҫ�������������Ǽ���Ȼ���ٷ���
	            	while (isNumber(buffer[tpos])) {
	            		t = t * 10 + (buffer[tpos] - '0');
	            		tpos++;
	            	}
	            	return new Token(INT,t);
	            }
		}
		
		//Ĭ��return null
		return null;		
	}
	
	private boolean isSpace(char c) {
		return (c == ' ' || c == '\t' || c == '\r');
	}
	
	private boolean isNumber(byte c) {
		return (c < '9' && c > '0');
	}

}
