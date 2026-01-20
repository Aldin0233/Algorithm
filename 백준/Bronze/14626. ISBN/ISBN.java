import java.util.Scanner;

public class Main {

    static final int[] triple = {0, 7, 4, 1, 8, 5, 2, 9, 6, 3};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean isEnd = false;
        boolean isOne = false;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                if(i == s.length() - 1) isEnd = true;
                else if((i & 1) == 0) isOne = true;
            } else {
                //13번째는 알아서 짝수 처리로 * 1배 됨
                sum += (c - '0') * ((i & 1) == 0 ? 1 : 3);
            }
        }
        int num = (10 - (sum % 10)) % 10;
        if(isEnd || isOne) {
            System.out.println(num);
        } else {
            System.out.println(triple[num]);
        }
    }
}
