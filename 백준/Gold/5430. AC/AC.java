import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = Integer.parseInt(scanner.nextLine());
		StringBuilder sb = new StringBuilder();
		test:
		for (int i = 0; i < T; i++) {
			String func = scanner.nextLine();
			int N = Integer.parseInt(scanner.nextLine());
			String arrStr = scanner.nextLine();

			StringTokenizer tokenizer = new StringTokenizer(arrStr.substring(1, arrStr.length() - 1), ",");
			LinkedList list = new LinkedList();
			for (int j = 0; j < N; j++) {
				list.add(Integer.parseInt(tokenizer.nextToken()));
			}
			for (int j = 0; j < func.length(); j++) {
				if (func.charAt(j) == 'R') {
					list.setReverseDirection();
				} else {
					if (!list.delete()) {
						sb.append("error\n");
						continue test;
					}
				}
			}
			sb.append(list.toStr()).append("\n");
		}
		System.out.println(sb);
	}

}

class Node {
	int data;
	Node prev;
	Node next;

	Node(int data) {
		this.data = data;
	}
}

class LinkedList {
	Node head;
	Node tail;
	boolean isByHead;
	int size;

	LinkedList() {
		head = null;
		tail = null;
		isByHead = true;
		size = 0;
	}

	void add(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}

	boolean delete() {
		if (size == 0) {
			return false;
		} else if (size == 1) {
			head = null;
			tail = null;
			size = 0;
			return true;
		}
		if (isByHead) {
			head = head.next;
			head.prev.next = null;
			head.prev = null;
		} else {
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
		}
		size--;
		return true;
	}

	void setReverseDirection() {
		isByHead = !isByHead;
	}

	String toStr() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node cur;
		if (isByHead) {
			cur = head;
			while (cur != null) {
				sb.append(cur.data);
				sb.append(",");
				cur = cur.next;
			}
		} else {
			cur = tail;
			while (cur != null) {
				sb.append(cur.data);
				sb.append(",");
				cur = cur.prev;
			}
		}
		sb.setLength(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
}