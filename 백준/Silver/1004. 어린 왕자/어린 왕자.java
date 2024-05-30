import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int T;
	static Point start, end, planet;
	static int N, r;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc<= T; tc++) {			
			test();
		}
	}

	private static void test() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		N = Integer.parseInt(br.readLine().trim());
		result = 0;
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			planet = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			r = Integer.parseInt(st.nextToken());
			if(isIt()) result++;
		}
		System.out.println(result);
	}

	private static boolean isIt() {
		boolean isIt = false; 
		if(getDist(start) < r) isIt = !isIt;
		if(getDist(end) < r) isIt = !isIt;
		return isIt;
	}
	
	static double getDist(Point point) {
		double dist = Math.sqrt(
				Math.pow(point.x- planet.x, 2) + 
				Math.pow(point.y - planet.y, 2));
		return dist;
	}
	
}