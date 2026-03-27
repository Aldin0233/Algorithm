#include <stdio.h>
#include <stdlib.h>

typedef struct 
{
    int x, y;
} Point;

typedef struct 
{
   Point *midAirports;
   int len;
   int k;
} Info;


int bfs(Info *info, int fuelSize);
int canDist(Point A, Point B, int nowFuel);
void arrayFill(int *arr, int len, int value);


int main() {
    Info info;
    scanf("%d %d", &info.len, &info.k);
    info.midAirports = malloc(sizeof(Point) * (info.len + 2));
    info.midAirports[0].x = 0;
    info.midAirports[0].y = 0;
    info.midAirports[info.len + 1].x = 10000;
    info.midAirports[info.len + 1].y = 10000;
    for(int i = 1; i <= info.len; i++) {
        scanf("%d %d", &info.midAirports[i].x, &info.midAirports[i].y);
    }

    int l = 0;
    int r = 2000;
    while(l < r) {
        int mid = (l + r) >> 1;
        if(bfs(&info, mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    printf("%d", l);
    free(info.midAirports);
    return 0;
}

int bfs(Info *info, int fuelSize) {
    int len = info -> len + 2; //시작 점과 도착 점
    int k = info -> k;
    Point *midAirports = info -> midAirports;
    int visited[len];
    int queue[len];
    int idx = 0;
    int limit = 1;
    queue[0] = 0;
    arrayFill(visited, len, 0);
    visited[0] = 1;
    for(int i = 0; i <= k; i++) {
        int nowSize = limit - idx;
        for(int size = 0; size < nowSize; size++) {
            int cur = queue[idx++];
            for(int j = 0; j < len; j++) {
                if(visited[j]) continue;
                if(canDist(midAirports[cur], midAirports[j], fuelSize)) {
                    if(j == len - 1) return 1;
                    visited[j] = 1;
                    queue[limit++] = j;
                }
            }            
        }
    }
    return visited[len - 1];
}

void arrayFill(int *visited, int len, int value) {
    for(int i = 0; i < len; i++) {
        visited[i] = value;
    }
}

int canDist(Point A, Point B, int nowFuel) {
    int dx = A.x - B.x;
    int dy = A.y - B.y;
    int maxDist = nowFuel * 10;
    return dx * dx + dy * dy <= maxDist * maxDist;
}




