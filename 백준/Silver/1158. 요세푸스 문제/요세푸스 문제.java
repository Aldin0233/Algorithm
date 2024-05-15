import java.util.Scanner;

class Node{
	int value;
	Node next;
	Node prev;
	public Node(int value) {
		this.value = value;
	}
}

class Ququ{
	Node head;
	Node tail;
	int size;
	Ququ(){
		head = null;
		tail = null;
	}
	void add(int value){
		if(head==null) {
			head = new Node(value);
			tail = head;
		} else {			
			tail.next = new Node(value);
			tail.next.prev = tail;
			tail = tail.next;
		}
		tail.next = head;
		head.prev = tail;
		size++;
	}
	int pollK(int k) {
		Node node = head;
		for(int i = 1; i<k; i++) {
			node = node.next;
		}
		int tmp = node.value;
		head = node.next;
		tail = node.prev;
		head.prev = tail;
		tail.next = head;
		size--;
		return tmp;
	} //k번째 수를 제거하고, 헤드를 재설정한다.
	
	boolean isEmpty() {
		if(size==0) {
			return true;
		} else {
			return false;
		}
	}
}

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Ququ q;
	static int N, K;
	static StringBuilder result = new StringBuilder();
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
		K = sc.nextInt();
		q = new Ququ();
	}
	private static void testProcess() {
		for(int i = 1 ; i <=N; i++) {
			q.add(i);
		}
		while(!q.isEmpty()) {
			result.append(q.pollK(K) + ", ");
		}
	}
	private static void testOutput() {
		result.setLength(result.length()-2);
		System.out.println("<" + result + ">");
	}
}