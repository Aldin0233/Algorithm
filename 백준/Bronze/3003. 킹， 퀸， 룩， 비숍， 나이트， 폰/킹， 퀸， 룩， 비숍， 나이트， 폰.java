import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			int tmp = sc.nextInt();
			if (i < 2) {
				System.out.printf("%d ", 1 - tmp);
			} else if (i < 5) {
				System.out.printf("%d ", 2 - tmp);
			} else {
				System.out.printf("%d", 8 - tmp);
			}
		}
	}
}