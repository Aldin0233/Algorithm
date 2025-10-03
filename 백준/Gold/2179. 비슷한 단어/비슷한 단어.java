import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] words;
    static WordPair ansPair;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        ansPair = new WordPair(0, 1, 0); //모든 접두사가 0일때 감지

        Trie root = new Trie();

        for (int i = 0; i < N; i++) {
            root.insert(words[i], i);
        }

        root.dfs(0);

        System.out.println(words[ansPair.S] + "\n" + words[ansPair.T]);
    }

    static class Trie {
        Trie[] children = new Trie[26];
        int first = -1, second = -1;

        void insert(String word, int idx) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (node.children[c] == null) node.children[c] = new Trie();
                node = node.children[c];
                node.update(idx);
            }
        }

        // 인덱스 최소 두 개만 유지
        void update(int idx) {
            if (first == -1 || idx < first) {
                second = first;
                first = idx;
            } else if (second == -1 || idx < second) {
                second = idx;
            }
        }

        void dfs(int depth) {
            if (first != -1 && second != -1) {
                WordPair newPair = new WordPair(first, second, depth);
                if (ansPair == null || ansPair.compareTo(newPair) < 0) {
                    ansPair = newPair;
                }
            }
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) children[i].dfs(depth + 1);
            }
        }
    }

}

class WordPair implements Comparable<WordPair> {
    int S, T;
    int similarDegree;

    public WordPair(int i, int j, int similarDegree) {
        if (i < j) {
            this.S = i;
            this.T = j;
        } else {
            this.S = j;
            this.T = i;
        }
        this.similarDegree = similarDegree;
    }

    @Override
    public int compareTo(WordPair o) {
        if (similarDegree != o.similarDegree)
            return Integer.compare(similarDegree, o.similarDegree);
        if (S != o.S) return Integer.compare(o.S, S);
        return Integer.compare(o.T, T);
    }
}
