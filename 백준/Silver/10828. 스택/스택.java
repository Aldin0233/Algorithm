import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int data;
	Node next;

	public Node() {

	}

	public Node(int data) {
		this.data = data;
	}

}

class stack {
	Node head;
	int size;

	public stack() {
		head = new Node();
		size = 0;
	}

	void push(int data) {
		Node newNode = new Node(data);
		newNode.next = head.next;
		head.next = newNode;
		size++;
	}

	int pop() {
		if (size == 0) {
			return -1;
		}
		int data = head.next.data;
		head.next = head.next.next;
		size--;
		return data;
	}

	int top() {
		return size == 0 ? -1 : head.next.data;
	}

	int isEmpty() {
		return size == 0 ? 1 : 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static stack stack = new stack();

	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		for(int i = 0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			String order = st.nextToken();
			switch (order) {
			case "push": 
				stack.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop": 
				result.append(stack.pop()).append("\n");
				break;
			case "size":
				result.append(stack.size).append("\n");
				break;
			case "empty":
				result.append(stack.isEmpty()).append("\n");
				break;
			case "top": 
				result.append(stack.top()).append("\n");
				break;
			}
		}
		System.out.println(result);
	}
}