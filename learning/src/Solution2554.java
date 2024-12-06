class Solution2554 {
    public int maxCount(int[] banned, int n, int maxSum) {
        int currSum = 0;
        int collectedNums = 0;
        Set<Integer> bannedSet = new HashSet<>();

        Arrays.stream(banned).forEach(bannedSet::add);

        for (int i = 1; i <= n; i++) {
            if (!bannedSet.contains(i) && (currSum + i <= maxSum)) {
                currSum += i;
                collectedNums++;
            }
        }

        return collectedNums;
    }
}
