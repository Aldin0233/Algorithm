int lengthOfLongestSubstring(char* s) {
    int l = 0;
    int r = 0;
    int maxLen = 0;
    //문제에 영문자와 숫자, 공백, 기호가 포함된다고 함.
    int hasChar[256] = {0};
    while(s[r] != '\0') {
        unsigned char c = s[r];
        hasChar[c]++;
        
        //중복이 해결될 때까지 l을 우측으로 이동
        while(hasChar[c] > 1) {
            hasChar[(unsigned char)s[l++]]--;
        }

        //중복없는 최대 부분문자열 길이 갱신
        int len = r - l + 1;
        if(maxLen < len) {
            maxLen = len;
        }
        r++;
    }
    return maxLen;
}