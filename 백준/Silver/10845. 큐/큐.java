import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue q = new Queue();
        StringBuilder ans = new StringBuilder();
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch(order) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                    case "pop":
                        ans.append(q.pop()).append("\n");
                        break;
                        case "front":
                            ans.append(q.front()).append("\n");
                            break;
                            case "back":
                                ans.append(q.rear()).append("\n");
                                break;
                case "size":
                    ans.append(q.size()).append("\n");
                    break;
                case "empty":
                    ans.append(q.isEmpty()).append("\n");
                    break;
            }
        }
        System.out.println(ans);
    }

}

class Queue {
    Node head;
    Node tail;
    int size;
    public Queue() {
        head = null;
        tail = null;
    }

    public int front() {
        if(head == null) return -1;
        return head.data;
    }

    public int rear() {
        if(head == null) return -1;
        return tail.data;
    }

    public void push(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public int pop() {
        if (head == null) {
            return -1;
        }
        int x = head.data;
        head = head.next;
        size--;
        return x;
    }

    public int isEmpty() {
        return size == 0 ? 1 : 0;
    }
    public int size() {
        return size;
    }
}

class Node {
    int data;
    Node prev;
    Node next;
    Node(int data){
        this.data = data;
    }
}