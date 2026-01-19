import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cur = 0;
        int ans = 0;
        for(int i = 0; i < 4; i++) {
            cur -= sc.nextInt();
            cur += sc.nextInt();
            ans = Math.max(ans, cur);
        }
        System.out.println(ans);
    }
}