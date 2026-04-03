#include <stdio.h>
#include <stdlib.h>

static inline long long comb2(long long x) {
    return x * (x - 1) / 2;
}

int main() {
    int N;
    scanf("%d", &N);

    //무방향으로 저장
    int edgeCount = 2 * (N - 1);

    int *head = malloc(sizeof(int) * (N + 1));
    int *to = malloc(sizeof(int) * edgeCount);
    int *next = malloc(sizeof(int) * edgeCount);

    for(int i = 1; i <= N; i++) {
        head[i] = -1;
    }

    int idx = 0;
    for(int i = 0; i < N - 1; i++) {
        int a, b;
        scanf("%d %d", &a, &b);

        to[idx] = b;
        next[idx] = head[a];
        head[a] = idx++;

        to[idx] = a;
        next[idx] = head[b];
        head[b] = idx++;
    }

    int *parent = calloc(N + 1, sizeof(int));
    int *order = malloc(sizeof(int) * N);
    int *stack = malloc(sizeof(int) * N);
    long long *subtree = malloc(sizeof(long long) * (N + 1));

    for(int i = 1; i <= N; i++) {
        subtree[i] = 1;
    }

    int top = 0;
    int ordSize = 0;
    stack[top++] = 1;
    parent[1] = -1;

    while(top > 0) {
        int cur = stack[--top];
        order[ordSize++] = cur;

        //인접 간선 탐색
        for(int e = head[cur]; e != -1; e = next[e]) {
            int nxt = to[e];
            //먼저 들른 쪽이 부모
            if(nxt == parent[cur]) continue;
            parent[nxt] = cur;
            stack[top++] = nxt;
        }
    }

    for(int i = ordSize - 1; i > 0; i--) {
        int v = order[i];
        subtree[parent[v]] += subtree[v];
    }

    long long total = comb2((long long)N);
    long long result = 0LL;

    for(int v = 2; v <= N; v++) {
        //내 노드에서 겹칠 수 있는 경로를 제외한 다른 쌍들에 대하여 더하기
        long long outside = N - subtree[v];
        result += total - comb2(outside);
    }

    printf("%lld", result);

    free(head); free(to); free(next);
    free(parent); free(order); free(stack); free(subtree);

    return 0;
}