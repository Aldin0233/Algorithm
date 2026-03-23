#include <stdio.h>

typedef struct State {
    int isX;
    int cnt;
} State;

int main() {
    char str[51];
    scanf("%s", str);
    State arr[51];
    int idx = 0;
    int curIsX = (str[0] == 'X');
    int curCnt = 1;
    for(int i = 1; str[i] != '\0'; i++) {
        int nowIsX = (str[i] == 'X');
        if(nowIsX == curIsX) {
            curCnt++;
        } else {
            arr[idx].isX = curIsX;
            arr[idx].cnt = curCnt;
            idx++;

            curIsX = nowIsX;
            curCnt = 1;
        }
    }
    arr[idx].isX = curIsX;
    arr[idx].cnt = curCnt;
    idx++;

    for(int i = 0; i < idx; i++) {
        if(arr[i].isX && arr[i].cnt % 2 == 1) {
            printf("-1");
            return 0;
        } 
    }
    for(int i = 0; i < idx; i++) {
        if(arr[i].isX) {
            for(int j = 0; j < arr[i].cnt / 4; j++) {
                printf("AAAA");
            }
            if(arr[i].cnt % 4 == 2) printf("BB");
        } else {
            for(int j = 0; j < arr[i].cnt; j++) {
                printf(".");
            }
        }
    }
    
    return 0;
}