import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int T;
	static int A, B, C;

	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1 ; tc <= T; tc++) {
			result.append("Case #").append(tc).append(": ");
			st = new StringTokenizer(br.readLine().trim());
			 A = Integer.parseInt(st.nextToken());
			 B = Integer.parseInt(st.nextToken());
			 C = Integer.parseInt(st.nextToken());
			 if(A >= B + C || B >= A + C || C >= A + B) {
				 result.append(triangle.invalid.getMessage());
			 } else if(A == B && A == C) {
				 result.append(triangle.equilateral.getMessage());
			 } else if(A == B || A == C || B == C) {
				 result.append(triangle.isosceles.getMessage());
			 } else {
				 result.append(triangle.scalene.getMessage());
			 }
			 result.append("\n");
		}
		System.out.println(result);
	}

}

enum triangle {
	equilateral("equilateral"), 
	isosceles("isosceles"), 
	scalene("scalene"), 
	invalid("invalid!");

	private final String message;

	triangle(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}