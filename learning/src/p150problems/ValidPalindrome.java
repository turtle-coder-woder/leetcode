package p150problems;

public class ValidPalindrome {
    //refine string , make it all lowercase, remove spaces, remove special chars, only keep alphanumeric  chars
    //now from refined string , make it to charArray & try reading from 2 pointers left and right
    //keep check character at left and right index, if they are not same retrun false
    // else keep on incrementing and decrementing right till we have left<right
    public boolean isPalindrome(String s) {
        char sChar[] = refineSString(s);
        int left = 0;
        int right = sChar.length - 1;
        while (left < right) {
            if (sChar[left++] != sChar[right--]) {
                return false;
            }
        }
        return true;
    }

    private char[] refineSString(String s) {
        char sChar[] = s.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : sChar) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
        }
        return sb.toString().toCharArray();
    }

    public static void main(String args[]) {
        String input = "txxxxct";
        System.out.println(new ValidPalindrome().isPalindrome(input));
    }
}
