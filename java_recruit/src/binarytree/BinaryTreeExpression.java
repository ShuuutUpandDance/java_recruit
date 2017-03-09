package binarytree;

/**
 * Created by Zhang on 2017/3/8.
 */
import token_adaptor.*;
import static token_adaptor.Token.TokenType.*;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.util.Stack;

public class BinaryTreeExpression {
    public TokenStream ts;

    public static void main(String args[]) throws IOException{
        BinaryTreeExpression e = new BinaryTreeExpression();
        BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
        bst.root = e.expression();
        bst.postOrder(bst.root);
        System.out.print("\n");
        bst.midOrder(bst.root);
    }

    public BinaryTreeExpression() throws IOException{
        ts = new ExpressionTokenStream(new BufferedInputStream(System.in));
    }

    public Node<Token> expression() {
        Node<Token> left = term();

        try {
            if (match(PLUS)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.PLUS, "+"));
                root.left = left;
                root.right = term();
                return root;
            }
            else if (match(MINUS)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.MINUS, "-"));
                root.left = left;
                root.right = term();
                return root;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return left;
    }

    private Node<Token> term() {
        Node<Token> left = factor();

        try {
            if (match(MULT)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.MULT, "*"));
                root.left = left;
                root.right = factor();
                return root;
            }
            else if(match(DIV)){
                Node<Token> root = new Node<>(new Token(Token.TokenType.DIV, "/"));
                root.left = left;
                root.right = factor();
                return root;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return left;
    }

    private Node<Token> factor() {
        Token t = null;
        Node<Token> left;
        try {
            t = ts.getToken();

            if (t.tokenType == INT) {
                ts.consumeToken();
                return new Node<Token>(t);
            }
            else if (match(LPAR)) {
                Node<Token> v = expression();
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
        return null;
    }

    private boolean match(Token.TokenType tt) throws IOException {
        if (ts.getToken().tokenType == tt) {
            ts.consumeToken();
            return true;
        }
        return false;
    }
}
