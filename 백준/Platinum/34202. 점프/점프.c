#include <stdio.h>
#include <stdlib.h>

#define MAXN 200001

typedef struct Segment {
    int left;
    int right;
} Segment;

int N;
Segment stack[MAXN];
int idx = -1;

int isEmpty() {
    return idx == -1;
}

void push(Segment seg) {
    stack[++idx] = seg;
}

//empty 검사 끝났다 가정
Segment pop() {
    return stack[idx--];
}

//차수
int deg[MAXN];
int adj[MAXN][2];

void addEdge(int a, int b) {
    adj[a][deg[a]++] = b;
    adj[b][deg[b]++] = a;
}

int main() {
    scanf("%d", &N);

    push((Segment){1, 1});

    long long prevSegSize;
    scanf("%lld", &prevSegSize); // 1 고정
    for(int i = 2; i <= N - 1; i++) {
        long long tmp;
        scanf("%lld", &tmp);
        if(tmp > prevSegSize) {
            push((Segment){i, i});
        } else if(tmp == prevSegSize) {
            Segment cur = pop();
            addEdge(cur.right, i);
            cur.right = i;
            push(cur);
        } else {
            Segment last = pop();
            Segment lower = pop();
            addEdge(last.right, i);
            addEdge(lower.right, i);
            push((Segment){lower.left, last.left});
        }
        prevSegSize = tmp;
    }

    Segment last = pop();
    addEdge(last.right, N);

    int prev = 0;
    int cur = 1;

    while(1) {
        printf("%d ", cur);
        if(cur == N) break;
        int next = adj[cur][0] != prev ? adj[cur][0] : adj[cur][1];
        prev = cur;
        cur = next;
    }

    return 0;
}

