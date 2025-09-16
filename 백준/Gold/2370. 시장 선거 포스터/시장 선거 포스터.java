import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] posters;
    static Set<Integer> pos;
    static Map<Integer, Integer> posComp;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        posters = new int[N][2];
        pos = new TreeSet<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            posters[i][0] = Integer.parseInt(st.nextToken());
            posters[i][1] = Integer.parseInt(st.nextToken());
            pos.add(posters[i][0]);
            pos.add(posters[i][1]);
        }

        //좌표 압축은 차갑고 어렵다..
        List<Integer> posList = new ArrayList<>(pos);
        Collections.sort(posList);
        List<Integer> comp = new ArrayList<>();
        comp.add(posList.get(0));
        for(int i = 1; i < posList.size(); i++) {
            if(!posList.get(i).equals(posList.get(i - 1))) {
                if(posList.get(i) > posList.get(i - 1) + 1) {
                    comp.add(posList.get(i - 1) + 1);
                }
                comp.add(posList.get(i));
            }
        }
        posComp = new HashMap<>();
        for(int i = 0; i < comp.size(); i++) {
            posComp.put(comp.get(i), i + 1);
        }

        SegmentTree seg = new SegmentTree(posComp.size());
        for(int i = 0; i < N; i++) {
            int l = posComp.get(posters[i][0]);
            int r = posComp.get(posters[i][1]);
            seg.update(1, 1, posComp.size(), l, r, i + 1);
        }

        Set<Integer> canSee = new HashSet<>();
        seg.collect(1, 1, posComp.size(), canSee);
        ans = canSee.size();
        System.out.println(ans);
    }



}

class SegmentTree {
    int[] tree, lazy;

    public SegmentTree(int size) {
        tree = new int[size * 4];
        lazy = new int[size * 4];
    }

    private void pushDown(int node) {
        if(lazy[node] != 0) {
            tree[node * 2] = lazy[node];
            tree[node * 2 + 1] = lazy[node];
            lazy[node * 2] = lazy[node];
            lazy[node * 2 + 1] = lazy[node];
            lazy[node] = 0;
        }
    }

    public void update(int node, int start, int end, int l, int r, int val) {
        if(r < start || end < l) return;
        if(l <= start && end <= r) {
            tree[node] = val;
            lazy[node] = val;
            return;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, val);
        update(node * 2 + 1, mid + 1, end, l, r, val);
    }

    public void collect(int node, int start, int end, Set<Integer> set) {
        if(tree[node] != 0 && lazy[node] != 0) {
            set.add(tree[node]);
            return;
        }
        if(start == end) {
            if(tree[node] != 0) set.add(tree[node]);
            return;
        }
        int mid = (start + end) / 2;
        collect(node * 2, start, mid, set);
        collect(node * 2 + 1, mid + 1, end, set);
    }
}






