
import java.io.*;
import java.util.*;

public class Main {

    static int[] info;
    static Node head;
    static Horse[] horses;
    static int ansScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        info = new int[10];
        for (int i = 0; i < 10; i++) {
            info[i] = Integer.parseInt(st.nextToken());
        }
        makeBoard();
        horses = new Horse[4];
        for(int i = 0; i < 4; i++) {
            horses[i] = new Horse(head);
        }
        simulationBoard(0);
        System.out.println(ansScore);
    }

    private static void simulationBoard(int depth) {
        if (depth == 10) {
            ansScore = Math.max(ansScore, calScore());
            return;
        }
        for (int i = 0; i < 4; i++) {
            Horse horse = horses[i];
            Node originalNode = horse.nowNode;
            int originalScore = horse.score;

            if (horse.nowNode == null) continue;

            Node destNode = calDestNode(horse.nowNode, info[depth]);
            if (!isValid(destNode)) continue;

            horse.nowNode = destNode;
            if (destNode != null) horse.score += destNode.score;

            simulationBoard(depth + 1);

            horse.nowNode = originalNode;
            horse.score = originalScore;
        }
    }

    private static int calScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            score += horses[i].score;
        }
        return score;
    }

    private static Node calDestNode(Node node, int moves) {
        Node cur = node;
        for (int i = 0; i < moves; i++) {
            if (i == 0 && cur.blueNode != null) {
                cur = cur.blueNode;
            } else if (cur.redNode != null) {
                cur = cur.redNode;
            } else {
                return null;
            }
        }
        return cur;
    }

    private static boolean isValid(Node node) {
        if (node == null) return true;
        for (int i = 0; i < 4; i++) {
            if (horses[i].nowNode == node) return false;
        }
        return true;
    }


    private static void makeBoard() {
        head = new Node(0);
        Node curNode = head;
        Node score10, score20, score30, score40;
        score10 = score20 = score30 = score40 = null;
        for(int i = 1; i <= 20; i++) {
            curNode.redNode = new Node(i * 2);
            curNode = curNode.redNode;
            if(curNode.score == 10) {
                score10 = curNode;
            } else if(curNode.score == 20) {
                score20 = curNode;
            } else if(curNode.score == 30) {
                score30 = curNode;
            } else if(curNode.score == 40) {
                score40 = curNode;
            }
        }
        score10.blueNode = new Node(13);
        score10 = score10.blueNode;
        score10.redNode = new Node(16);
        score10 = score10.redNode;
        score10.redNode = new Node(19);
        score10 = score10.redNode;
        score10.redNode = new Node(25);
        Node score25 = score10.redNode;
        score20.blueNode = new Node(22);
        score20 = score20.blueNode;
        score20.redNode = new Node(24);
        score20 = score20.redNode;
        score20.redNode = score25;
        score30.blueNode = new Node(28);
        score30 = score30.blueNode;
        score30.redNode = new Node(27);
        score30 = score30.redNode;
        score30.redNode = new Node(26);
        score30 = score30.redNode;
        score30.redNode = score25;
        score25.redNode = new Node(30);
        score25 = score25.redNode;
        score25.redNode = new Node(35);
        score25 = score25.redNode;
        score25.redNode = score40;

    }



}

class Node {
    int score;
    Node blueNode;
    Node redNode;

    public Node(int Score) {
        this.score = Score;
    }

}

class Horse {
    int score;
    Node nowNode;
    public Horse(Node nowNode) {
        this.score = 0;
        this.nowNode = nowNode;
    }
}
