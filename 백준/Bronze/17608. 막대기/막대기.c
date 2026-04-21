#include <stdio.h>
#include <stdlib.h>

//17608 막대기
//배열의 뒤에서부터(스택처럼) 보면서
//지금 까지 봤던 것보다 큰 수가 나타나면 보이는 막대로 치고 큰 수 갱신

#define MAXN 100000

int arr[MAXN];

int main() {
    int N;
    scanf("%d", &N);
    for(int i = 0 ; i < N; i++) {
        scanf("%d", &arr[i]);
    }
    //현재 보였던 막대 중 최고 높이
    int max = -1;
    //보이는 막대 갯수
    int canSee = 0;
    //오른쪽에서부터 보면서
    for(int i = N - 1; i >= 0; i--) {
        if(arr[i] > max) {
            max = arr[i];
            canSee++;
        }
    }
    printf("%d", canSee);
    return 0;
}