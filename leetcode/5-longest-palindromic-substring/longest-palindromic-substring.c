int palindromeLen(char* s, int* l, int r) {
    while(*l >= 0 && s[r] != '\0' && s[*l] == s[r]) {
        (*l)--; r++;
    }
    //실패 위치까지 갔음, 따라서 길이에서 -1 해줘야 정상 길이
    return r - *l - 1;
}

char* longestPalindrome(char* s) {
    int maxLen = 0;
    int st = 0;
    for(int i = 0; s[i] != '\0'; i++) {
        int l = i;
        int r = i;
        //홀수 펠린드롬
        int len = palindromeLen(s, &l, r);
        if(len > maxLen) {
            maxLen = len;
            st = l + 1;
        }
        l = i;
        r = i + 1;
        len = palindromeLen(s, &l, r);
        if(len > maxLen) {
            maxLen = len;
            st = l + 1;
        }
    }
    char* result = malloc(sizeof(char) * (maxLen + 1));
    result[maxLen] = '\0';
    for(int i = 0; i < maxLen; i++) {
        result[i] = s[st++];
    }
    return result;
}