import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char a = '0';
        char b = (char) (a + 1);
        char aAfter = (char) (a + 31);
        char bAfter = a;
        StringBuilder A = new StringBuilder();
        StringBuilder B = new StringBuilder();
        for(int i = 0; i < N/2; i++) {
            A.append(a).append(aAfter);
            B.append(b).append(bAfter);
        }
        if(N % 2 != 0) {
            A.append(a);
            B.append(a);
        }
        System.out.println(A);
        System.out.println(B);
    }

}



