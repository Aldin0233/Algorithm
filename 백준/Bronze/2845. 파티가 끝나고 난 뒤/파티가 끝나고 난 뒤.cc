#include <stdio.h>

int main() {
    int L, P;
    int arr[5];
    scanf("%d %d", &L, &P);
    for(int i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }
    int multi = L * P;
    for(int i = 0; i < 5; i++) {
        printf("%d ", arr[i] - multi);
    }
    
    return 0;
}