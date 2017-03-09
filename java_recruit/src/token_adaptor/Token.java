package token_adaptor;

public class Token implements Comparable<Token> {
	@Override
	public int compareTo(Token t) {
		return 0;
	}

	public enum TokenType {
	        LPAR, RPAR,
	        PLUS,
	        MINUS,
	        MULT,
	        DIV,
	        INT,
	        NONE,
	    }
	    public TokenType tokenType;
	    public Object value;

	    public Token(TokenType tt, Object v) {
	        this.tokenType = tt;
	        this.value = v;
	    }
	    
	    public String toString(){
	    	return String.valueOf(value);
	    }
}
