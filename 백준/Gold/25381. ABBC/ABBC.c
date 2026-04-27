#include <stdio.h>
#include <stdlib.h>

//25381 ABBC
/*
A와 뒤에 있는 B중 아무거나 하나를 지운다. 
B와 뒤에 있는 C중 아무거나 하나를 지운다. 
최대한 많이 지우기 위해서는 뒤에 있는 C에서 조건을 만족하는 B를 찾고,
(이때 C로 지운 B 때문에 A 조건이 만족하지 못하는 경우가 발생해도 총 시행 횟수가 같다) 
A를 지울때는 최대한 많이 지우면 된다.
*/

#define MAXLEN 300001

char S[MAXLEN];
int used[MAXLEN];
int queue[MAXLEN];

int main() {
    scanf("%s", S);
    int sLen = 0;
    while(S[sLen] != '\0') {
        sLen++;
    }
    int ans = 0;

    int front = 0;
    int rear = 0;

    for(int i = 0; i < sLen; i++) {
        if(S[i] == 'B') {
            queue[rear++] = i;
        } else if(S[i] == 'C') {
            if(front < rear) {
                int bIdx = queue[front++];
                used[bIdx] = 1;
                ans++;
            }
        }
    }
    int aCnt = 0;
    for(int i = 0; i < sLen; i++) {
        if(S[i] == 'A') {
            aCnt++;
        } else if(S[i] == 'B' && !used[i]) {
            if(aCnt > 0) {
                aCnt--;
                ans++;
            }
        }
    }

    printf("%d", ans);

    return 0;
}

