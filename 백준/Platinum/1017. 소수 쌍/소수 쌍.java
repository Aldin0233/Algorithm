import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer>[] edges;
    static int[] visitedMark;
    static int visitedToken;
    static boolean[] isPrime;
    static List<Integer> evenIdx, oddIdx;
    static List<Integer> firstFair;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        preFindPrime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //중복되지 않는 자연수의 합이 최소 2는 나올 수 없기 때문에
        //홀 짝으로 나눠서 더해야지만 홀수인 소수를 찾을 수 있음
        checkEvenOrOdd();
        //경로 탐색해두기
        findEdges();
        if(evenIdx.size() != oddIdx.size()) {
            ans.append(-1);
        } else {
            firstFair = new ArrayList<>();
            checkCandidates();
            if(firstFair.isEmpty()) ans.append(-1);
            else {
                Collections.sort(firstFair);
                for(int i : firstFair) {
                    ans.append(i).append(' ');
                }
            }
        }
        System.out.println(ans);
    }

    private static void checkCandidates() {
        if(arr[0] % 2 == 0) {
            for(int v : edges[0]) if(tryMatching(0, v)) firstFair.add(arr[v]);
        } else {
            for(int v : edges[0]) if(tryMatching(v, 0)) firstFair.add(arr[v]);
        }
    }

    private static boolean tryMatching(int a, int b) {
        int[] match = new int[N];
        Arrays.fill(match, -1);
        match[a] = b;
        match[b] = a;
        visitedMark = new int[N];
        visitedToken = 0;
        for(int u : evenIdx) {
            if(u == a) continue;
            visitedToken++;
            if(!dfs(u, b, match)) return false; //한번이라도 완전히 밀어내기가 실패하면 해당 경로는 답이 없음
        }
        return true;
    }

    private static void checkEvenOrOdd() {
        evenIdx = new ArrayList<>();
        oddIdx = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (arr[i] % 2 == 0) evenIdx.add(i);
            else oddIdx.add(i);
        }
    }

    private static boolean dfs(int u, int matched, int[] match) {
        for(int v : edges[u]) {
            if(visitedMark[v] == visitedToken) continue; //이번 방문에서 이미 방문
            if(v == matched) continue; //0번에 강제 매칭된 페어 혹은 0번
            visitedMark[v] = visitedToken;
            if(match[v] == -1 || dfs(match[v], matched, match)) {
                match[u] = v;
                match[v] = u;
                return true;
            }
        }
        return false;
    }

    private static void findEdges() {
        edges = new ArrayList[N];
        for(int i = 0; i < N; i++) edges[i] = new ArrayList<>();
        for(int i : evenIdx) {
            for(int j : oddIdx) {
                if(isPrime[arr[i] + arr[j]]) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }
    }

    private static void preFindPrime() {
        isPrime = new boolean[2000]; //1000보다 작거나 같고 중복 X; 최대 합 1999
        Arrays.fill(isPrime, true);
        for(int i = 2; i < 2000; i++) {
            if(isPrime[i]) {
                for (int j = i * i; j < 2000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

}



