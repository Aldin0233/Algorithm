import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1000 - sc.nextInt();
        int answer = 0;
        int[] arr = {500, 100, 50, 10, 5, 1};
        for(int i = 0; i < 6; i++) {
            answer += n/arr[i];
            n = n%arr[i];
        }
        System.out.println(answer);

    }

}