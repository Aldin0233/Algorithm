import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] avg;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = Integer.bitCount(Integer.parseInt(br.readLine()));
        System.out.println(ans);
    }

}
