import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int T;
	static Point A, B;
	static int r1, r2;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc<= T; tc++) {
			testInput();
			testProcess();
			testOutput();	
		}
	}
	
	private static void testInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		A = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		r1 = Integer.parseInt(st.nextToken());
		B = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		r2 = Integer.parseInt(st.nextToken());
	}

	private static void testProcess() {
		if(A.equals(B)) { //좌표 위치가 같다면, 범위가 같으면 무한대, 아니면 경우의 수가 없다.
			if(r1==r2) result = -1;
			else result = 0;
			return;
		}
		int dist = (int) (Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2)); //소수점 이슈를 피하기 위해 처음부터 제곱을 사용한다.
		int outRange = (int) (Math.pow(r1 + r2, 2)); //외접 경우의 수
		int inRange = (int) (Math.pow(r1 - r2, 2)); //내접 경우의 수
		if(dist == outRange || dist == inRange) result = 1; //두 원이 맞닿는 한 점
		else if(dist > outRange || dist < inRange) result = 0; // 두 원이 닿지 않을때
		else result = 2; // 두 원의 교차점
	}


	private static void testOutput() {
		System.out.println(result);
	}
	
}
