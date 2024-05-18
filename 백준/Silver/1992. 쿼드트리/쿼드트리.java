import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static String[] video;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		result.append(testProcess(0, 0, N-1, N-1));
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		video = new String[N];
		for(int i = 0 ; i<N; i++) {
			video[i] = br.readLine().trim();
		}
	}

	private static String testProcess(int lr, int lc, int rr, int rc) {
		String tmp = "";
		if(lr==rr && lc==rc) {
			return tmp += video[lr].charAt(lc);
		}
		int midR = (lr+rr)/2; int midC = (lc+rc)/2;
		String A = testProcess(lr, lc, midR, midC);
		String B = testProcess(lr, midC+1, midR, rc);
		String C = testProcess(midR+1, lc, rr, midC);
		String D = testProcess(midR+1, midC+1, rr, rc);
		if(A.length()+B.length()+C.length()+D.length()==4) {
			if(A.equals(B) && A.equals(C) && A.equals(D)) {
				return A;
			} 
		}
		return tmp = "(" + A + B + C + D + ")";
		
	}

	private static void testOutput() {
		System.out.println(result);
	}
}