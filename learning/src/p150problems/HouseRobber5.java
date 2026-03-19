package p150problems;

public class HouseRobber5 {
    /*
    House Robber V is NOT reducible by merging same-color adjacent houses.

    Restriction depends on:
    1) previous house was robbed or not
    2) whether current and previous colors are same

    If prev house was robbed AND same color -> cannot take current
    Else current can be taken

    So use DP on index + prevTaken.

    use skip and take to implement this
    State:
    take = best if current house is taken
    skip = best if current house is skipped

    Transitions:
    skip = max(prevTake, prevSkip)

    take = nums[i] + prevSkip
    if colors[i] != colors[i-1]:
        take = max(take, nums[i] + prevTake)

    Answer = max(take, skip)

    Key insight:
    Skip means "ignore current", so we take best of all previous states.
    */
    public long rob(int[] nums, int[] colors) {
        long skip = 0;
        long take = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int currentHouseValue = nums[i];
            boolean isColorSameAsPrevHouse = colors[i] == colors[i - 1];

            long newSkip = Math.max(take, skip); //from last take or last skip cz we aren't using current house at all
            long newTake = currentHouseValue + skip; //for varaition without color we can safely take current and last skip
            if (!isColorSameAsPrevHouse) {
                newTake = Math.max(newTake, currentHouseValue + take); //if color are not same , we can choose to take last home as well in our comparison
            }

            skip = newSkip;
            take = newTake;
        }

        return Math.max(skip, take);
    }
}
