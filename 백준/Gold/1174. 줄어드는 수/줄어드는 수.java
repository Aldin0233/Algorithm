import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long num = 0L;
        for(int i = 0; i <= 9; i++) {
            dfs(i, i);
        }
        Collections.sort(list);
        if(N > list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N - 1));
        }
    }

    static void dfs(int lastDigit, long cur) {
        list.add(cur);
        for(int next = 0; next < lastDigit; next++) {
            dfs(next, cur * 10 + next);
        }
    }

}
