#include <stdio.h>
#include <stdlib.h>

//2512 예산
/*
N은 3에서 10000이나 가능한 예산 요청값이 1에서 100,000이기 때문에,
직접 탐색하는 것은 무리다.
따라서 상한액을 이분탐색해서 만약 총예산 내 배정이 가능하다면,
그 상한액 중 최대 값을 탐색한다.
*/

#define MAXN 10001

static inline int max(int a, int b) {
    return a > b ? a : b;
}

//cap을 받아서, M 이내에서 배정 가능한지 확인
//M이 최대 10억이고 cap이 최대 100000이라 long long 갈 일 없음
int canAssign(int N, int request[MAXN], int cap, int M) {
    int curTotal = 0;
    for(int i = 0; i < N; i++) {
        curTotal += request[i] > cap ? cap : request[i];
        if(curTotal > M) return 0;
    }
    return 1;
}

//이분 탐색을 통해 가장 최대 상한액 찾기
int findMaxCap(int N, int request[MAXN], int M) {
    int l = 0;
    int r = 0;
    for(int i = 0; i < N; i++) {
        r = max(r, request[i]);
    }
    r++;
    int ans = 0;
    while(l < r) {
        int mid = (l + r) >> 1;
        if(canAssign(N, request, mid, M)) {
            ans = max(ans, mid);
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return ans;
}

int main() {
    int N;
    scanf("%d", &N);
    int request[MAXN];
    for(int i = 0; i < N; i++) {
        scanf("%d", &request[i]);
    }
    int M;
    scanf("%d", &M);
    printf("%d", findMaxCap(N, request, M));
    
    return 0;
}

