public class HammingDistance {
    public int hammingDistance(int x, int y) {
        StringBuilder sb1 = new StringBuilder(Integer.toBinaryString(x));
        StringBuilder sb2 = new StringBuilder(Integer.toBinaryString(y));
        fillZeroAsPrefixYToMakeSameLengthBinaryNumber(sb1, sb2);


        int hammingDistance = 0;
        for (int i = 0; i < sb1.length(); i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    private void fillZeroAsPrefixYToMakeSameLengthBinaryNumber(StringBuilder sb1, StringBuilder sb2) {
        int numberOfZeroDiff = Math.abs(sb1.length() - sb2.length());
        if (numberOfZeroDiff == 0) {
            return;
        }


        if (sb1.length() < sb2.length()) {
            addZeroInPrefix(sb1, numberOfZeroDiff);
        } else {
            addZeroInPrefix(sb2, numberOfZeroDiff);
        }
    }

    private void addZeroInPrefix(StringBuilder sb, int numberOfZeroDiff) {
        for (int i = numberOfZeroDiff; i > 0; i--) {
            sb.insert(0, '0');
        }
    }
}
