import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int Q;
    static int problem;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order == 1) problem += Integer.parseInt(st.nextToken());
            else problem -= Integer.parseInt(st.nextToken());
            if(problem < 0) {
                System.out.println("Adios");
                return;
            }
        }
        System.out.println("See you next month");
    }

}

