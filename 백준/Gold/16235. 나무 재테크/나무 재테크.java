

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] field, nourishment;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][N];

        for(int[] row : field) {
            Arrays.fill(row, 5);
        }

        nourishment = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                nourishment[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Tree> pq = new PriorityQueue<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            pq.add(new Tree(x, y, age));
        }

        int year = 0;
        while(year < K) {
            pq = treeGrown(pq);
            year++;
        }

        ans = pq.size();

        System.out.println(ans);

    }

    private static PriorityQueue<Tree> treeGrown(PriorityQueue<Tree> pq) {
        PriorityQueue<Tree> nextYearTree = new PriorityQueue<>();
        int[][] deadTreeNourishment = new int[N][N];
        while(!pq.isEmpty()) {
            Tree tree = pq.poll();
            if(tree.age <= field[tree.x][tree.y]) {
                field[tree.x][tree.y] -= tree.age;
                nextYearTree.add(new Tree(tree.x, tree.y, tree.age + 1));
                if(tree.isReproduction()) {
                    reproduction(tree, nextYearTree);
                }
            } else {
                deadTreeNourishment[tree.x][tree.y] += tree.age / 2;
            }
        }
        addNourishment(deadTreeNourishment);
        return nextYearTree;
    }

    private static void reproduction(Tree tree, PriorityQueue<Tree> nextYearTree) {
        for(int d = 0; d < 8; d++) {
            int nx = tree.x + dr[d];
            int ny = tree.y + dc[d];
            if(isValid(nx, ny)) {
                nextYearTree.add(new Tree(nx, ny, 1));
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void addNourishment(int[][] deadTreeNourishment) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                field[i][j] += nourishment[i][j] + deadTreeNourishment[i][j];
            }
        }
    }


}

class Tree implements Comparable<Tree> {
    int x, y, age;
    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    public boolean isReproduction() {
        return (age + 1) % 5 == 0;
    }

    @Override
    public int compareTo(Tree o) {
        return age - o.age;
    }
}


