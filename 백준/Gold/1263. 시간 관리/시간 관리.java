import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
    	public int compare(Point o1, Point o2) {
    		return o2.y - o1.y;
    	};
    });
    
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
        for(int i = 0 ; i<N; i++) {
        	st = new StringTokenizer(br.readLine().trim());
        	pq.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    private static void testProcess() {
    	result = pq.peek().y;
    	while(!pq.isEmpty()) {
    		Point lastTime = pq.poll();
    		if(result < lastTime.y) {
    			result = result - lastTime.x;
    		} else {
    			result = lastTime.y - lastTime.x;
    		}
    		
    		if(result<0) {
    			result = -1;
    			return;
    		}
    	}
    }

    private static void testOutput() {
        System.out.println(result);
    }
}