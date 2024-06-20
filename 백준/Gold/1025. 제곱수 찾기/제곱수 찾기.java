import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	static int N, M;
	static int[][] arr;
	static boolean find;

	static int result;

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = sc.nextLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = tmp.charAt(j) - '0';
			}
		}
	}

	private static void testProcess() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check(i, j);
			}
		}
	}

	private static void check(int i, int j) {
		if (result <= arr[i][j] && isSquare(arr[i][j])) {
			result = arr[i][j];
			find = true;
		}
		for (int di = -N; di < N; di++) {
			for (int dj = -M; dj < M; dj++) {
				if (di == 0 && dj == 0)
					continue;
				squareCheck(i, j, di, dj);
			}
		}
	}

	private static void squareCheck(int i, int j, int di, int dj) {
		int ni = i;
		int nj = j;
		int num = 0;
		while (ni >= 0 && ni < N && nj >= 0 && nj < M) {
			num = num * 10 + arr[ni][nj];
			if (num > result && isSquare(num)) {
				result = num;
				find = true;
			}
			ni += di;
			nj += dj;
		}
	}

	private static boolean isSquare(int num) {
		int sqrt = (int) Math.sqrt(num);
		return num == sqrt * sqrt;
	}

	private static void testOutput() {
		if (!find) {
			System.out.println(-1);
			return;
		}
		System.out.println(result);
	}
}
