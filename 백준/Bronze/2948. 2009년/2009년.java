import java.util.Scanner;

public class Main {

    private static int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int D = sc.nextInt() - 1;
        int answerDay = 0;
        for(int i = 0 ; i < D; i++) {
            answerDay += monthDay[i];
        }
        answerDay += M;
        System.out.println(day[(answerDay+2)%7]);

    }
}
