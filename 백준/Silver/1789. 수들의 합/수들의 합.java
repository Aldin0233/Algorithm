import java.io.*;
import java.util.*;

class Main {

    static long N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        int now = 1;
        while(N > 0) {
            if(N >= now) N -= now;
            else break;
            now++;
        }
        System.out.println(now - 1);
    }

}