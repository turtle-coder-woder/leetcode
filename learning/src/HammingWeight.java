public class HammingWeight {
    public int hammingWeight(int n) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));
        int numOf1s = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                numOf1s++;
            }
        }
        return numOf1s;
    }
}
