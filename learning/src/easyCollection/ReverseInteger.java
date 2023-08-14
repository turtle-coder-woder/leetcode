package easyCollection;

public class ReverseInteger {
    public int reverse(int x) {
        boolean isNegative = x <= -1;
        if (isNegative) {
            x = x * -1;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        if (sb.length() > 10) {
            return 0;
        }
        sb = reverseString(sb);


        if (isNegative) {
            sb.insert(0, '-');
            return compareAndReturn(sb, Integer.MIN_VALUE);
        }

        return compareAndReturn(sb, Integer.MAX_VALUE);
    }

    public int compareAndReturn(StringBuilder sb, Integer comparer) {
        String reversed = sb.toString();
        String comparerString = String.valueOf(comparer);
        if (sb.length() == comparerString.length()) {
            int cmpString = reversed.compareTo(comparerString);
            if (cmpString > 0) {
                return 0;
            }
        }
        return Integer.parseInt(reversed);
    }

    public StringBuilder reverseString(StringBuilder s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            char temp = s.charAt(i);
            s.setCharAt(i, s.charAt(j));
            s.setCharAt(j, temp);
        }
        return s;
    }
}
