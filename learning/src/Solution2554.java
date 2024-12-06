class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        int currSum = 0;
        int collectedNums = 0;
        Set<Integer> set = new HashSet<>();
        Arrays.stream(banned).forEach(x -> set.add(x));
        
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i) && (currSum + i <= maxSum)) {
                currSum += i;
                collectedNums++;
            }
        }
        return collectedNums;
    }
}
