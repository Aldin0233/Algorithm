import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	//입력
	static int N;
	static int[][] dp;
	//변수 및 dp
	static int result;
	//출력
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		N = sc.nextInt();
		dp = new int[N][10];
		Arrays.fill(dp[0], 1);
		for(int i = 1 ; i<N; i++) {
			for(int j = 0; j<10; j++) {
				for(int k = j; k<10; k++) {
					dp[i][j] = (dp[i][j]+dp[i-1][k])%10007;
				}
			}
		} // 이전 나와 같거나 높은 모든 열을 더한다.
		for(int i = 0; i<10; i++) {
			result = (result + dp[N-1][i])%10007;
		} // 최종적으로 마지막 열을 모두 더한 값이 출력 값
		System.out.println(result); //출력
	}
	
}