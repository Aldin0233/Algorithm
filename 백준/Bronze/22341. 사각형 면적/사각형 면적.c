#include <stdio.h>
#include <stdlib.h>

//22341 사각형 면적
//처음 좌표 N N을 x, y로 두고, 못 자르는 점이 들어왔을 때는 무시하고, 
//그 외의 경우 더 넓은 쪽이 될 수 있게 점을 기준으로 직선으로 자른다.
//만약 넓이가 같다면(즉 같거나 크다면) 가로를 우선으로 한다(들어오는 점중 행 값을 갱신한다.)

static inline int canCut(int x, int y, int a, int b) {
    return a < x && b < y; //경계 위에 있으면 가장 넓은 넓이가 되기 위해 어차피 자르지 않는다.
}

static inline int calcSize(int row, int col) {
    return row * col;
}

static inline void updateXY(int *x, int *y, int a, int b) {
    if(!canCut(*x, *y, a, b)) return;
    int rowCutSize = calcSize(a, *y);
    int colCutSize = calcSize(*x, b);
    if(rowCutSize >= colCutSize) *x = a;
    else *y = b;
}

int main() {
    int N, C;
    scanf("%d %d", &N, &C);
    int X = N, Y = N;
    int cx, cy;
    for(int i = 0; i < C; i++) {
        scanf("%d %d", &cx, &cy);
        updateXY(&X, &Y, cx, cy);
    }
    printf("%d", X * Y);
    return 0;
}
