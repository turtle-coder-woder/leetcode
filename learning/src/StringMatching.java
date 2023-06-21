public class StringMatching {
    String mainString, pattern;
    int[] pi;

    public StringMatching(String mainString, String pattern) {
        this.mainString = mainString;
        this.pattern = pattern;
        createPiTable();
    }

    public int find() {
        for (int i = 0, j = 0; i < mainString.length(); ) {
            if (mainString.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    return i - pattern.length();
                }
            } else if (j > 0) {
                j = pi[j - 1];
            } else {
                i++;
            }

        }
        return -1;
    }

    private void createPiTable() {
        pi = new int[pattern.length()];
        for (int prev = 0, j = 1; j < pi.length; j++) {
            if (pattern.charAt(prev) == pattern.charAt(j)) {
                prev++;
                pi[j] = prev;
            } else if (prev > 0) {
                prev = pi[prev - 1];
                j--; // to resume checking j again
            }
        }
    }
}