import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOf2Array {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq1 = new HashMap<>();
        int[] lowerNum = nums1.length > nums2.length ? nums2 : nums1;
        for (int i = 0; i < lowerNum.length; i++) {
            int key = lowerNum[i];
            freq1.put(key, (freq1.getOrDefault(key, 0) + 1));
        }

        int[] higherNum = nums1.length > nums2.length ? nums1 : nums2;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < higherNum.length; i++) {
            int key = higherNum[i];
            Integer freq = freq1.get(key);
            if (freq != null && freq > 0) {
                ans.add(key);
                freq1.put(key, freq - 1);
            }
        }

        return ans.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }


}
