#include <stdio.h>
#include <stdlib.h>

#define MAXN 500001

//34117 허수아비 
//방어력만큼 감소하고 진행
//화살이 위치 i에서 멈추거나 왼쪽에서 멈추도록 하기 위해 필요한 허수아비의 최소 개수, 
//멈출 수 없을 때 출력 -1
//힘이 최대 1억 -> 단순 DP 반복 X
//우선순위 큐? 높은 거 먼저 나오게 해서 P보다 높아지는 지 확인
//삽입이 50만이라 우선순위 큐 불가능.//다시 넣어야 하는데 그 과정에서 시간 초과
//세그 트리를 이용해 내림차순 누적합을 구해보는 느낌으로 가보자.
//1억개로 세그 트리를 만들면 트리 공간 터지니까 좌표 압축

typedef struct Data {
    long long sum; //밑의 구간의 합 //최대 50억
    int cnt; //밑의 구간에서 사용한 갯수
} Data;

Data segTree[4 * MAXN];

void update(int node, int st, int ed, int pos, int value);
int getMinCount(int node, int st, int ed, long long need, int values[]);

int cmpInt(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    if(x < y) return -1;
    else if(x > y) return 1;
    return 0;
}

int bSearch(int value, int cmpSize, int values[]);

int main() {
    int N, P;
    scanf("%d %d", &N, &P);
    int A[MAXN];
    //좌표 압축용 배열
    int sorted[N];
    int needPower[MAXN];
    for(int i = 0; i < N; i++) {
        scanf("%d", &A[i]);
        sorted[i] = A[i];
    }

    qsort(sorted, N, sizeof(int), cmpInt);
    int cmpSize = 1;
    int values[MAXN];
    values[0] = sorted[0];
    for(int i = 1; i < N; i++) {
        if(sorted[i] > values[cmpSize - 1]) values[cmpSize++] = sorted[i];
    }
    
    for(int i = 0; i < N; i++) {
        //압축된 좌표로 바로 구간에 집어넣기
        int compPos = bSearch(A[i], cmpSize, values);
        update(1, 0, cmpSize - 1, compPos, A[i]);
        //전체를 다 합쳐도 안됨
        if(segTree[1].sum < P) needPower[i] = -1;
        else needPower[i] = getMinCount(1, 0, cmpSize - 1, P, values);
    }

    for(int i = 0; i < N; i++) {
        printf("%d ", needPower[i]);
    }
    return 0;
}

int bSearch(int targetValue, int cmpSize, int values[]) {
    int l = 0;
    int r = cmpSize;        
    while(l < r) {
        int mid = (l + r) >> 1;
        if(values[mid] < targetValue) l = mid + 1;
        else r = mid;
    }
    return l;
}


void update(int node, int st, int ed, int pos, int value) {
    if(st == ed) {
        segTree[node].sum += value;
        segTree[node].cnt += 1;
        return;
    }
    int mid = (st + ed) >> 1;
    if(pos <= mid) update(node * 2, st, mid, pos, value);
    else update(node * 2 + 1, mid + 1, ed, pos, value);
    segTree[node].sum = segTree[node * 2].sum + segTree[node * 2 + 1].sum;
    segTree[node].cnt = segTree[node * 2].cnt + segTree[node * 2 + 1].cnt;
}

int getMinCount(int node, int st, int ed, long long need, int values[]) {
    if(st == ed) {
        int v = values[st];
        return (int) ((need + v - 1) / v); //올림;
    }
    
    int l = node * 2;
    int r = node * 2 + 1;
    int mid = (st + ed) >> 1;
    if(segTree[r].sum >= need) return getMinCount(r, mid + 1, ed, need, values);
    else return segTree[r].cnt + getMinCount(l, st, mid, need - segTree[r].sum, values);
}