class Solution2337 {
    public static final Character LEFT = 'L';
    public static final Character RIGHT = 'R';
    public static final Character SPACE = '_';

    public boolean canChange(String start, String target) {
        int j = 0;
        int i = 0;
        for (; i < start.length(); i++) {
            Character startC = start.charAt(i);
            if (startC.equals(LEFT) || startC.equals(RIGHT)) {
                int newJ = nextCharacterInEndFollowsLaw(startC, i, j, target);
                if (newJ != -1) {
                    j = newJ;
                } else {
                    return false;
                }
            }
        }
        return i == start.length() && targetShouldNotHaveMoreChar(j, target);
    }

    boolean targetShouldNotHaveMoreChar(int j, String target) {
        while (j < target.length()) {
            if (!SPACE.equals(target.charAt(j))) {
                return false;
            }
            j++;
        }
        return true;
    }

    int nextCharacterInEndFollowsLaw(Character startC, int i, int j, String target) {
        while (j < target.length()) {
            Character targetC = target.charAt(j);
            if (targetC.equals(LEFT) || targetC.equals(RIGHT)) {
                if (targetC.equals(startC)) {
                    if (targetC.equals(LEFT) && j <= i) {
                        return j + 1;
                    } else if (targetC.equals(RIGHT) && j >= i) {
                        return j + 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
            j++;
        }
        return -1;
    }
}
