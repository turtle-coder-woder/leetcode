package p150problems.batista;

import java.util.*;

public class SubarraySumEqualK {
    /*
    prefixSum[i] = sum from 0 to i

    If:
    currentPrefix - oldPrefix = k

    then:
    subarray between them has sum = k

    Rearrange:
    oldPrefix = currentPrefix - k

    So at every index:
    1. Compute current prefix sum
    2. Find how many times (currentPrefix - k) occurred before
    3. Every occurrence gives one valid subarray
    4. Store current prefix into hashmap

    HashMap stores:
    <prefixSum, frequency>

    Why frequency?
    Because same prefix can appear multiple times,
    and every occurrence creates a different valid subarray.

    Example:
    nums = [1,-1,1]
    prefixes = [1,0,1]

    prefix 0 appears multiple times,
    so multiple subarrays with sum k become possible.

    Time: O(n)
    Space: O(n)
    */
    public int subarraySum(int[] nums, int k) {
        int N = nums.length;
        int[] prefixSums = new int[N];
        prefixSums[0] = nums[0];
        for (int i = 1; i < N; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }

        Map<Integer, Integer> mapOfPrefixSumToFrequency = new HashMap<>();
        mapOfPrefixSumToFrequency.put(0, 1); //so that if k==prefixSum value than it can be added as ans
        int count = 0;
        for (int i = 0; i < N; i++) {
            int neededPrefix = prefixSums[i] - k;
            if (mapOfPrefixSumToFrequency.containsKey(neededPrefix)) {
                count+=mapOfPrefixSumToFrequency.get(neededPrefix);
            }
            mapOfPrefixSumToFrequency.put(prefixSums[i], 1 + mapOfPrefixSumToFrequency.getOrDefault(prefixSums[i], 0));
        }


        return count;
    }
}
