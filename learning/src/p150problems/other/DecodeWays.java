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
        List<List<Integer>> wayToDecode = new ArrayList<>();
        List<Integer> singleAns = new ArrayList<>();
        singleAns.add(Integer.parseInt("" + s.charAt(0)));
        wayToDecode.add(singleAns);

        for (int i = 1; i < s.length(); i++) {
            List<List<Integer>> newWayToDecode = new ArrayList<>();

            for (List<Integer> way : wayToDecode) {
                //pick this as single
                int val = Integer.parseInt("" + s.charAt(i));
                processValAsNewWay(newWayToDecode, way, val);

                //pick this with previous

                int lastNumberInWay = way.remove(way.size() - 1);
                if (lastNumberInWay == 0 && val == 0) {
                    return 0;
                }
                val = lastNumberInWay * 10 + val;
                processValAsNewWay(newWayToDecode, way, val);


            }
            wayToDecode = newWayToDecode;
        }
        return wayToDecode.size();
    }

    private void processValAsNewWay(List<List<Integer>> newWayToDecode, List<Integer> way, int val) {
        if (valueInAllowedRange(val)) {
            List<Integer> ansAsSinglePick = new ArrayList<>(way);
            ansAsSinglePick.add(val);
            newWayToDecode.add(ansAsSinglePick);
        }
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
