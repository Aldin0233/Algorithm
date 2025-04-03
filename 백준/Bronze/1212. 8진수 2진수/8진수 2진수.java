import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(int i = 0 ; i < str.length() ; i++) {
            int num = str.charAt(i) - '0';
            StringBuilder bStr = new StringBuilder(Integer.toBinaryString(num));
            if(i != 0) {
                while(bStr.length() < 3) {
                    bStr.insert(0, "0");
                }
            }
            ans.append(bStr.toString());
        }
        System.out.println(ans);
    }

}