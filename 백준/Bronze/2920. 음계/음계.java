import java.io.*;
import java.util.*;

public class Main {

    static final int NUM = 8;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean ascending = true;
        boolean descending = true;
        for(int i = 1; i <= NUM; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(i != num) {
                ascending = false;
            }
            if(i != NUM - num + 1) {
                descending = false;
            }
        }
        if(ascending) {
            System.out.println("ascending");
        } else if(descending) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

    }
    
}
