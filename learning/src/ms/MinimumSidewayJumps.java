package ms;

public class MinimumSidewayJumps {
    public int minSideJumps(int[] obstacles) {
        int jump = 0;
        int currentLane = 2;
        for(int destinationPos=1;destinationPos<obstacles.length;destinationPos++){
            long[] cost = calculateCost(currentLane,destinationPos,obstacles);

            long min = Integer.MAX_VALUE;

            for(int i=1;i<=3;i++){
                long costVal = cost[i];
                if(costVal<min){
                    min = costVal;
                    currentLane = i;
                }
            }
            jump+=min;
        }
        return jump;

    }

    private long[] calculateCost(int currentLane, int destinationPos, int[] obstacles) {
        long[] cost =new long[4];
        for(int i=1;i<=3;i++){
            int newLane = i;
            cost[i] = costAt(destinationPos, obstacles, newLane);
            if(newLane!=currentLane && cost[i]!=Integer.MAX_VALUE){
                cost[i] +=1;
                cost[i] += costAt(destinationPos-1, obstacles, newLane);
            }

        }
        return cost;
    }

    private long costAt(int destinationPos, int[] obstacles, int newLane) {
        return obstacles[destinationPos] == newLane ? Integer.MAX_VALUE : 0;
    }
}
