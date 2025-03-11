import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServiceNow1 {
    public int longestLength(int[] ar) {

        int maxLength = 0;

        if (ar == null || ar.length == 0) {
            return maxLength;
        }

        if (ar.length == 1) {
            return 1;
        }

        Map<Integer, Integer> mapOfNumberToMaxLength = new HashMap<>();

        for (int i = 0; i < ar.length; i++) {
            mapOfNumberToMaxLength.put(ar[i], 1);
        }

        for (int i = 0; i < ar.length; i++) {
            maxLength = Math.max(maxLength, countFreq(ar[i], mapOfNumberToMaxLength));
        }
        return maxLength;
    }

    private Integer countFreq(int currentVal, Map<Integer, Integer> mapOfNumberToMaxLength) {
        Integer currentFreq = mapOfNumberToMaxLength.get(currentVal);
        if (currentFreq > 1) {
            return currentFreq;
        }
        Integer nextFreq = mapOfNumberToMaxLength.get(currentVal + 1);
        if (nextFreq != null) {

            mapOfNumberToMaxLength.put(currentVal, currentFreq + countFreq(currentVal + 1, mapOfNumberToMaxLength));
        }
        return mapOfNumberToMaxLength.get(currentVal);
    }


    public void dummy() {
//        Arrays.sort(ar);
//        maxLength = 1;
//
//        for (int i = 1; i < ar.length; i++) {
//            int prev = i - 1;
//            int curr = i;
//            int localMax = 1;
//            while (curr < ar.length && (ar[prev] == (ar[curr] - 1))) {
//                localMax++;
//                prev++;
//                curr++;
//            }
//            i = curr;
//            maxLength = Math.max(localMax, maxLength);
//        }
    }
}
