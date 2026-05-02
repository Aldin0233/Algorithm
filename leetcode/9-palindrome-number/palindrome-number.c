bool isPalindrome(int x) {
    if(x < 0) {
        return false;
    }
    else {
        long long num = x;
        long long palindrome = 0LL;
        while(x > 0) {
            palindrome *= 10;
            palindrome += x % 10;
            x /= 10;
        }
        return num == palindrome;
    } 
}