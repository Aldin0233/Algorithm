import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static String A, B, C;
    static int num = 0;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        C = br.readLine();
        if(A.contains("z")) {
            if(B.contains("z")) {
                calPrint(Integer.parseInt(C) + 1);
            } else {
                calPrint(Integer.parseInt(B) + 2);
            }
        } else {
            calPrint(Integer.parseInt(A) + 3);
        }



        System.out.println(ans);
    }

    private static void calPrint(int num) {
        boolean multy3 = num % 3 == 0;
        boolean multy5 = num % 5 == 0;
        if(multy3 && multy5) {
            ans.append("FizzBuzz");
        } else if(multy3) {
            ans.append("Fizz");
        } else if(multy5) {
            ans.append("Buzz");
        } else {
            ans.append(num);
        }
    }

}
