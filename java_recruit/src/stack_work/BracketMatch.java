package stack_work;

import java.io.IOException;

public class BracketMatch {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] buf = new byte[128];
        int length = System.in.read(buf);
        int i = 0;
        Stack<Byte>s = new Stack<>(128);
        
        for ( ; i < length; i++) {
            if (buf[i] == '(' || buf[i] == '[' || buf[i] == '{') {
                s.push(buf[i]);
            }    
            else if (!s.isEmpty()){
            if (buf[i] == ')') {
                if (s.getTop() == '(')
                    s.pop();
                else {
                    System.out.println("1 unmatch!");
                    System.exit(1);
                }   
            }   
            else if (buf[i] == ']') {
                if (s.getTop() == '[')
                    s.pop();
                else {
                    System.out.println("2 unmatch!");
                    System.exit(1);
                }   
           }
           else if (buf[i] == '}') {
                if (s.getTop() == '{')
                    s.pop();
                else {
                    System.out.println("3 unmatch!");
                    System.exit(1);
                }
            }
          } else if(buf[i] == ')'|| buf[i]=='}' ||buf[i] == ']'){
        	  System.out.println("4 unmatch!");
        	  System.exit(1);
          } else {
        	  System.out.println("all matched!!!");
        	  System.exit(1);
          }
         }
      }
 
}


