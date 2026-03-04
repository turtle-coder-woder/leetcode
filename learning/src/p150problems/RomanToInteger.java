package p150problems;

public class RomanToInteger {
    enum Roman {
        I('I', 1),
        V('V', 5),
        X('X', 10),
        L('L', 50),
        C('C', 100),
        D('D', 500),
        M('M', 1000);

        public char c;
        public int value;

        Roman(char c, int value) {
            this.c = c;
            this.value = value;
        }

        public static Roman getRomanByChar(Character c) {
            for (Roman r : Roman.values()) {
                if (r.c == c) {
                    return r;
                }
            }
            return null;
        }


    }

    public int romanToInt(String s) {
        int ans = 0;
        Character previous = null;
        for (int i = s.length() - 1; i >= 0; i--) {
            Character current = s.charAt(i);
            Roman roman = Roman.getRomanByChar(current);
            ans += roman.value;
            if(previous!=null){
                Roman preRoman = Roman.getRomanByChar(previous);
                if(preRoman.value>roman.value){
                    ans = ans - 2* roman.value;
                }
            }
            previous=current;
        }
        return ans;
    }
}
