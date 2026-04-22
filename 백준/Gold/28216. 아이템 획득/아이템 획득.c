#include <stdio.h>
#include <stdlib.h>

//28216 아이템 획득
//N이 20만, 좌표도 20만임으로 단순 누적합으로 풀수 없다.
//따라서 좌표 압축을 진행하고, 정렬해서 idx로 접근하면 기존 idx -> 이동 idx로 접근할 수 있다.
//각 좌표별로 배열을 만들어주면 메모리 부담이 심하기 때문에
//X, Y를 기준으로 먼저 정렬 후 반대 좌표를 다시 정렬한다.

#define MAXN 200000

typedef struct Box 
{
    int x, y, w;
} Box;

//각 X, Y에 대해 정렬 후 내부에서 추가 정렬
Box byXSort[MAXN];
Box byYSort[MAXN];

//정렬 후 계산할 누적합 값
long long prefixX[MAXN + 1];
long long prefixY[MAXN + 1];

typedef struct RangeInfo
{
    int start, end;
} RangeInfo;

RangeInfo xStEd[MAXN + 1];
RangeInfo yStEd[MAXN + 1];

int cmpByX(const void *a, const void *b) {
    Box p = *(const Box *)a;
    Box q = *(const Box *)b;
    if(p.x != q.x) return p.x - q.x;
    return p.y - q.y;
}

int cmpByY(const void *a, const void *b) {
    Box p = *(const Box *)a;
    Box q = *(const Box *)b;
    if(p.y != q.y) return p.y - q.y;
    return p.x - q.x;
}

void calcAndSetPrefix(int N) {
    prefixX[0] = 0;
    prefixY[0] = 0;
    for(int i = 0; i < N; i++) {
        prefixX[i + 1] = byXSort[i].w + prefixX[i];
        prefixY[i + 1] = byYSort[i].w + prefixY[i];
    }
}

void initRangeInfo() {
    for(int i = 0; i <= MAXN; i++) {
        xStEd[i].start = xStEd[i].end = -1;
        yStEd[i].start = yStEd[i].end = -1;
    }
}

void setRangeInfo(int N) {
    initRangeInfo();
    for(int i = 0; i < N;) {
        int x = byXSort[i].x;
        xStEd[x].start = i;
        int j = i;
        while(j < N && byXSort[j].x == x) j++;
        xStEd[x].end = j;
        i = j;
    }
    for(int i = 0; i < N;) {
        int y = byYSort[i].y;
        yStEd[y].start = i;
        int j = i;
        while(j < N && byYSort[j].y == y) j++;
        yStEd[y].end = j;
        i = j;
    }
}

int cmpTarget(int value, int target, int isUpper) {
    return isUpper ? value <= target : value < target;
}

int binarySearch(Box arr[], int l, int r, int target, int useX, int isUpper) {
    while(l < r) {
        int mid = (l + r) >> 1;
        int value = useX ? arr[mid].x : arr[mid].y;
        if(cmpTarget(value, target, isUpper)) l = mid + 1;
        else r = mid;
    }
    return l;
}

//(d & 1) == 0 : X좌표 변화
//d < 2 : 좌표가 증가하는 방향으로
long long move(int *curX, int *curY, int d, int v) {
    int isX = ((d & 1) == 0);
    int isInc = (d < 2);

    int cur = isX ? *curX : *curY;
    int next = isInc ? cur + v : cur - v;

    RangeInfo range = isX ? yStEd[*curY] : xStEd[*curX];
    if(range.start == -1) {
        if(isX) *curX = next;
        else *curY = next;
        return 0LL;
    }

    Box *arr = isX ? byYSort : byXSort;
    int curIdx = binarySearch(arr, range.start, range.end, cur, isX, isInc);
    int nextIdx = binarySearch(arr, range.start, range.end, next, isX, isInc);
    
    long long *prefix = isX ? prefixY : prefixX;
    int l, r;
    l = isInc ? curIdx : nextIdx;
    r = isInc ? nextIdx : curIdx;
    long long gainItem = prefix[r] - prefix[l];

    if(isX) *curX = next;
    else *curY = next;
    return gainItem;
}

int main() {
    int N, Q;
    scanf("%d %d", &N, &Q);
    for(int i = 0; i < N; i++) {
        Box box;
        scanf("%d %d %d", &box.x, &box.y, &box.w);
        byXSort[i] = box;
        byYSort[i] = box;
    }
    qsort(byXSort, N, sizeof(Box), cmpByX);
    qsort(byYSort, N, sizeof(Box), cmpByY);
    calcAndSetPrefix(N);
    setRangeInfo(N);
    
    int curX = 1, curY = 1;
    int d, v;
    long long ans = 0;
    for(int i = 0; i < Q; i++) {
        scanf("%d %d", &d, &v);
        ans += move(&curX, &curY, d, v);
    }

    printf("%lld", ans);
    return 0;
}

