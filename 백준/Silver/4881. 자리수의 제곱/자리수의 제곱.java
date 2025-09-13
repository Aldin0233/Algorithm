import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B;
    static Map<Integer, Integer> aList, bList;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        do {
            aList = new HashMap<>();
            int num = A, step = 1;
            while(!aList.containsKey(num)) {
                aList.put(num, step++);
                num = digitSquare(num);
            }
            bList = new HashMap<>();
            int res = Integer.MAX_VALUE;
            num = B;
            step = 1;
            while(!bList.containsKey(num)) {
                bList.put(num, step);
                if(aList.containsKey(num)) {
                    res = Math.min(res, aList.get(num) + step);
                }
                num = digitSquare(num);
                step++;
            }
            if(res == Integer.MAX_VALUE) res = 0;
            ans.append(String.format("%d %d %d\n", A, B, res));

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
        } while(A != 0 && B != 0);


        System.out.println(ans);
    }

    private static int digitSquare(int num) {
        int res = 0;
        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            res += digit * digit;
        }
        return res;
    }

}

