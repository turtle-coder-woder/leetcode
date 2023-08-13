public class RomanToInt {
    public int romanToInt(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int dp[] = new int[len];
        dp[0] = getNumericValueFromRomanCharacter(s.charAt(0));
        ;

        for (int i = 1; i < len; i++) {
            int valForRomanAtPlaceI = getNumericValueFromRomanCharacter(s.charAt(i));
            dp[i] = dp[i - 1] + valForRomanAtPlaceI - giveDenominationIfPossible(s.charAt(i - 1), s.charAt(i));
        }

        return dp[len - 1];
    }

    int giveDenominationIfPossible(Character prev, Character curr) {
        if (prev.equals('I')) {
            if (curr.equals('V') || curr.equals('X')) {
                return 2 * 1;
            }
        } else if (prev.equals('X')) {
            if (curr.equals('L') || curr.equals('C')) {
                return 2 * 10;
            }
        } else if (prev.equals('C')) {
            if (curr.equals('D') || curr.equals('M')) {
                return 2 * 100;
            }
        }
        return 0;
    }

    int getNumericValueFromRomanCharacter(Character c) {
        int val = 0;
        switch (c) {
            case 'I':
                val = 1;
                break;
            case 'V':
                val = 5;
                break;
            case 'X':
                val = 10;
                break;
            case 'L':
                val = 50;
                break;
            case 'C':
                val = 100;
                break;
            case 'D':
                val = 500;
                break;
            case 'M':
                val = 1000;
                break;
            default:
                val = 0;
        }
        return val;
    }
}
