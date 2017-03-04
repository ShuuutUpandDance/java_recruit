package token_adaptor;

import java.io.IOException;

public interface TokenStream {
	public Token getToken()  throws IOException;
    public void consumeToken() throws IOException;
}
