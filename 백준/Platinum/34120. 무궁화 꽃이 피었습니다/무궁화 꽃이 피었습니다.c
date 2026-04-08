#include <stdio.h>
#include <stdlib.h>

#define MAX 100001
#define MAXN 2001
#define MAXM 4001
#define MAXCM 4000001
#define INF ((long long) 4e18)

int N, M;
int A, B;

typedef struct State {
    int curPos;
    long long time;
} State;

typedef struct PriorityQueue {
    State heap[MAX];
    int size;
} PriorityQueue;

void init(PriorityQueue *pq) {
    pq -> size = 0;
}

int isEmpty(PriorityQueue *pq) {
    return pq -> size == 0;
}

void swap(State *a, State *b) {
    State temp = *a;
    *a = *b;
    *b = temp;
}

void push(PriorityQueue *pq, State item) {
    int idx = ++pq -> size;
    pq -> heap[idx] = item;

    while(idx > 1 && pq -> heap[idx].time < pq -> heap[idx / 2].time) {
        swap(&pq -> heap[idx], &pq -> heap[idx/2]);
        idx /= 2;
    }
}

State pop(PriorityQueue *pq) {
    State result = pq -> heap[1];

    pq -> heap[1] = pq -> heap[pq-> size--];
    int idx = 1;
    while(1) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        int smallest = idx;

        if(left <= pq -> size && pq -> heap[left].time < pq -> heap[smallest].time) {
            smallest = left;
        }

        if(right <= pq -> size && pq -> heap[right].time < pq -> heap[smallest].time) {
            smallest = right;
        }

        if(smallest == idx) break;

        swap(&pq -> heap[idx], &pq -> heap[smallest]);
        idx = smallest;
    }

    return result;
}

typedef struct Edge {
    int to;
    int next;
    long long cost;
} Edge;

int head[MAXN];
Edge edges[MAXM];
int edgeCnt;

void initEdgeList(int n) {
    for(int i = 1; i <= n; i++) {
        head[i] = -1;
    }
    edgeCnt = 0;
}

void addEdge(int from, int to, long long cost) {
    edges[edgeCnt].to = to;
    edges[edgeCnt].cost = cost;
    edges[edgeCnt].next = head[from];
    head[from] = edgeCnt++;
}

int compHead[MAXN];
Edge compEdges[MAXCM];
int compCnt;

int safeId[MAXN];
int safeNodes[MAXN];
int safeCnt;

int hasWindow[MAXN];

void initCompHead(int safeCnt) {
    for(int i = 0; i < safeCnt; i++) compHead[i] = -1;
    compCnt = 0;
}

void initCompEdgeList() {
    safeCnt = 0;
    for(int i = 1; i <= N; i++) {
        if(!hasWindow[i]) {
            safeId[i] = safeCnt;
            safeNodes[safeCnt++] = i;
        } else {
            safeId[i] = -1;
        }
    }
    initCompHead(safeCnt);
}

void addCompEdge(int from, int to, long long cost) {
    compEdges[compCnt].to = to;
    compEdges[compCnt].cost = cost;
    compEdges[compCnt].next = compHead[from];
    compHead[from] = compCnt++;
}

long long dist[MAXN];

void buildCompEdge(int src) {
    for(int i = 1; i <= N; i++) dist[i] = INF;
    PriorityQueue pq;
    init(&pq);
    dist[src] = 0;
    push(&pq, (State) {src, 0});

    while(!isEmpty(&pq)) {
        State cur = pop(&pq);
        int u = cur.curPos;
        long long d = cur.time;

        if(d != dist[u]) continue; //최소 간선 아님 방문 완료

        if(u != src && !hasWindow[u]) {
            addCompEdge(safeId[src], safeId[u], d);
            continue;
        }
        
        for(int e = head[u]; e != -1; e = edges[e].next) {
            int v = edges[e].to;
            long long nd = d + edges[e].cost;
            if(nd > A) continue;
            if(nd < dist[v]) {
                dist[v] = nd;
                push(&pq, (State){v, nd});
            }
        }
    }
}

static inline long long calNextTime(long long curTime, long long w) {
    long long P = (long long) A + B;
    long long phase = curTime % P;

    if(phase < A && w <= A - phase) {
        return curTime + w;
    }
    return curTime + (P - phase) + w;
}

int main() {
    scanf("%d %d", &N, &M);
    initEdgeList(N);
    for(int i = 0; i < M; i++) {
        int from, to;
        long long cost;
        scanf("%d %d %lld", &from, &to, &cost);
        addEdge(from, to, cost);
    }
    for(int i = 1; i <= N; i++) {
        scanf("%d", &hasWindow[i]);
    }
    scanf("%d %d", &A, &B);

    initCompEdgeList();
    for(int i = 0; i < safeCnt; i++) {
        buildCompEdge(safeNodes[i]);
    }
    long long period = A + B;
    PriorityQueue pq;
    init(&pq);
    for(int i = 0; i < safeCnt; i++) dist[i] = INF;
    int start = safeId[1];
    int target = safeId[N];
    dist[start] = 0;
    push(&pq, (State) {start, 0});
    while(!isEmpty(&pq)) {
        State cur = pop(&pq);
        int u = cur.curPos;
        if(u == target) {
            printf("%lld", cur.time);
            return 0;
        }
        long long t = cur.time;
        if(t != dist[u]) continue;
        for(int e = compHead[u]; e != -1; e = compEdges[e].next) {
            int v = compEdges[e].to;
            long long nt = calNextTime(t, compEdges[e].cost);
            if(nt < dist[v]) {
                dist[v] = nt;
                push(&pq, (State) {v, nt});
            }
        }
    }
    printf("%d", -1);

    return 0;
}

