package p150problems.other;

import p150problems.CourseScheduleI;

import java.util.ArrayList;
import java.util.List;

public class DecodeWays {
    public int numDecodings(String s) {
        //base case
        if (s.equals("") || s.startsWith("0")) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 1; i < s.length(); i++) {
            List<List<Integer>> newWayToDecode = new ArrayList<>();


            //pick this as single
            int val = Integer.parseInt("" + s.charAt(i));
            if (valueInAllowedRange(val)) {
                dp[i + 1] += dp[i];
            }

            //pick this with previous
            int lastNumberInWay = Integer.parseInt("" + s.charAt(i - 1));
            if (lastNumberInWay != 0) {
                val = lastNumberInWay * 10 + val;
                if (valueInAllowedRange(val)) {
                    dp[i + 1] += dp[i - 1];
                }
            }


        }
        return dp[len];
    }


    private boolean valueInAllowedRange(int val) {
        return val <= 26 && val >= 1;
    }

    public static void main(String[] args) {
        String s = "1222";
        int ans = new DecodeWays().numDecodings(s);
        System.out.println(ans);
    }
}
