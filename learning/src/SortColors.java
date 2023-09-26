import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SortColors {
    public void sortColors(int[] nums) {

        List<Integer> freqList = getFreqListOf012(nums);
        Iterator<Integer> it = freqList.iterator();
        int numToBeFilled = 0;
        int totalOperations = 0;
        while (it.hasNext()) {
            Integer freq = it.next();
            for (int i = 0; i < freq; i++) {
                nums[totalOperations++] = numToBeFilled;
            }
            numToBeFilled++;
        }
    }

    private List<Integer> getFreqListOf012(int[] nums) {
        Integer freq0 = 0;
        Integer freq1 = 0;
        Integer freq2 = 0;

        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0:
                    freq0++;
                    break;
                case 1:
                    freq1++;
                    break;
                case 2:
                    freq2++;
                    break;
            }
        }
        return Arrays.asList(freq0, freq1, freq2);
    }
}
