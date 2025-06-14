
import java.io.*;

public class Main {

    static int T, N;

    static boolean consistency;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            consistency = true;
            N = Integer.parseInt(br.readLine());
            PhoneBook pb = new PhoneBook();
            for(int i = 0; i < N; i++) {
                consistency = pb.addFind(pb.head, br.readLine(), 0);
                if(!consistency) {
                    for(int j = i + 1; j < N; j++) {
                        br.readLine();
                    }
                    break;
                }
            }
            ans.append(consistency ? "YES\n" : "NO\n");
        }

        System.out.println(ans);
    }


}

class PhoneBook {
    Node head;

    PhoneBook() {
        head = new Node();
    }

    boolean addFind(Node node, String phoneNumber, int depth) {
        if(node.end) {
            return false;
        }
        if(depth == phoneNumber.length()) {
            node.end = true;
            for(int i = 0; i < 10; i++) {
                if(node.child[i] != null) return false;
            }
            return true;
        }
        int num = phoneNumber.charAt(depth) - '0';
        if(node.child[num] == null) {
            node.child[num] = new Node();
        }
        return addFind(node.child[num], phoneNumber, depth + 1);
    }
}

class Node {
    boolean end;
    Node[] child;

    Node() {
        end = false;
        child = new Node[10];
    }

}

