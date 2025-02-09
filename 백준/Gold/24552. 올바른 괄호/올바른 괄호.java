import java.io.*;
import java.util.*;

public class Main {

    private static String str;
    private static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        int balance = 0;
        for(int i = 0 ; i < str.length() ; i++) {
            if(str.charAt(i) == '(') {
                balance++;
                a++;
            } else {
                balance--;
            }
            if(balance == 0) {
                a = 0;
            }
        }
        balance = 0;
        for(int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) == ')') {
                balance++;
                b++;
            } else {
                balance--;
            }
            if(balance == 0) {
                b = 0;
            }
        }
        System.out.println(Math.max(a, b));
    }

}
