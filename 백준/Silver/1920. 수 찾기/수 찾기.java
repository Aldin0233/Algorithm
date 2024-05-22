
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[] A;
	
	static StringBuilder result = new StringBuilder();
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
		st = new StringTokenizer(br.readLine().trim());
		A = new int[N];
		for(int i = 0 ; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
	}
	private static void testProcess() {
		Arrays.sort(A);
		for(int i = 0; i<M; i++) {
			check(Integer.parseInt(st.nextToken()));
		}
	}
	private static void check(int i) {
		int start = 0 ;
		int end = N-1;
		while(start<=end) {
			int mid = (start+end) /2;
			if(A[mid] == i) {
				result.append(1).append("\n");
				return;
			} else if(A[mid] < i) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		result.append(0).append("\n");
	}
	private static void testOutput() {
		System.out.println(result);
	}
}
