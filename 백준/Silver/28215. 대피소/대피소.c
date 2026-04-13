#include <stdio.h>
#include <stdlib.h>

//28215 대피소
//N개의 집 중 K개의 대피소를 고를 건데, 대피소가 최대 3개까지인 문제. 
//최대 N 50 중 3개를 골라도 경우의 수가 약 50^3 밖에 되지 않는다.
//따라서 50개 중 3개를 고르는 조합을 전부 테스트 해보기로 한다.

#define MAXDIST 200001

// 절대값으로 바꿔주는 함수
static inline int iabs(int num) {
    return num >= 0 ? num : -num;
}

// |x1 - x2| + |y1 - y2| 를 맨하탄 거리라고 합니다. 
static inline int calManhattanDistance(const int A[2], const int B[2]) {
    int xDiff = A[0] - B[0];
    int yDiff = A[1] - B[1];
    return iabs(xDiff) + iabs(yDiff);
}

//더 작은 값 찾기
static inline int min(int a, int b) {
    return a < b ? a : b;
}

static inline int max(int a, int b) {
    return a > b ? a : b;
}

int dfs(int idx, int N, int K, int picked[K], int pickCnt, int houseDists[N][N]);

int calCombResult(int K, int N, int picked[K], int houseDists[N][N]);

int main() {
    int N, K;
    scanf("%d %d", &N, &K);
    int address[N][2];
    for(int i = 0; i < N; i++) {
        scanf("%d %d", &address[i][0], &address[i][1]);
    }
    //서로간 거리 구해두기
    int houseDists[N][N];
    for(int i = 0; i < N; i++) {
        houseDists[i][i] = 0;
        for(int j = i + 1; j < N; j++) {
            int dist = calManhattanDistance(address[i], address[j]);
            houseDists[i][j] = dist;
            houseDists[j][i] = dist;
        }
    }
    int picked[K];
    int minResult = dfs(0, N, K, picked, 0, houseDists);
    printf("%d", minResult);
    return 0;
}

int dfs(int idx, int N, int K, int picked[K], int pickCnt, int houseDists[N][N]) {
    if(pickCnt >= K) return calCombResult(K, N, picked, houseDists);
    if(idx >= N) return MAXDIST;
    int result = MAXDIST;
    for(int i = idx; i < N; i++) {
        picked[pickCnt] = i;
        result = min(result, dfs(i + 1, N, K, picked, pickCnt + 1, houseDists));
    }
    return result;
}

int calCombResult(int K, int N, int picked[K], int houseDists[N][N]) {
    int maxDistEach = 0;
    for(int i = 0; i < N; i++) {
        int minDist = MAXDIST;
        for(int j = 0; j < K; j++) {
            minDist = min(minDist, houseDists[picked[j]][i]);
        }
        maxDistEach = max(maxDistEach, minDist);
    }
    return maxDistEach;
}
