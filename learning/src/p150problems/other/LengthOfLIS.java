package p150problems.other;
import java.util.*;

public class LengthOfLIS {
// dp[i] = LIS length starting from index i

// Think:
// "Agar main nums[i] se start karun,
// toh future me kis element pe jump karne se
// mujhe longest increasing subsequence milegi?"

// For every i:
//   check all j > i
//   if nums[j] > nums[i]:
//       i -> j possible
//       candidate = 1 + dp[j]
//       take best among all such j

// So:
// dp[i] = max(1, 1 + dp[j]) for all j > i where nums[j] > nums[i]

// Base:
// dp[i] = 1 (every element alone is LIS)

// Fill from right to left (because dp[i] depends on dp[j])

    // Final answer:
// max(dp[i]) over all i (LIS can start anywhere)



    // tails[len] = smallest possible tail value
//              of an increasing subsequence of length len+1

// Think:
// "Har length ke liye best (smallest) ending kya ho sakti hai?"

// Why smallest tail?
// Smaller tail => future me extend karna easier

// For each num:
//   binary search first index idx where tails[idx] >= num

//   if no such idx (num is largest):
//       append -> new longer subsequence found

//   else:
//       replace tails[idx] = num
//       (same length, but better/smaller tail)

// Important:
// tails is NOT an actual subsequence
// it is only a summary of best tails per length

    // Answer = size of tails
    public int lengthOfLIS(int[] nums) {
        int dp[]=new int[nums.length];
        Arrays.fill(dp,1);//sab akele akele present hoti toh ans 1 hota na
        for(int i=nums.length-2;i>=0;i--){
            int current = nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(current<nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);//using this(or say current) we can make bigger subsequence
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String args[]){
        int a[]= new int[]{10,9,2,5,3,7,101,18};
        System.out.println(new LengthOfLIS().lengthOfLIS(a));
    }
}
