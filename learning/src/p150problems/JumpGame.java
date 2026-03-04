package p150problems;

public class JumpGame {
        public boolean canJump(int[] nums) {
            return canJumpFrom(0,nums);
        }

        boolean canJumpFrom(int index,int[] nums){
            if(index >= nums.length-1){
                return true;
            }

            int jumpVal = nums[index];
            for(int i=1; i<=jumpVal;i++){
                if(canJumpFrom(i+index, nums)){
                    return true;
                }
            }
            return false;
        }
}
