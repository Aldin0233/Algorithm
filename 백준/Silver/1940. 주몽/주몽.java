import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[] arr;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		arr = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for(int i = 0 ; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void testProcess() {
		Arrays.sort(arr);
		int l = 0;
		int r = N-1;
		while(l<r) {
			int sum = arr[l] + arr[r];
			if(sum > M) {
				r--;
			} else if(sum < M) {
				l++;
			} else {
				result++;
				l++; r--;
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
