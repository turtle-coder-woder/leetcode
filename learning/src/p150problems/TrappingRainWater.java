package p150problems;

public class TrappingRainWater {
    /*
    * We previosuly thought using stack, but found out instead of NGE problem, its a prefix sum problem
    * more likely
    * so what we can think is to keep understanding of e.g standing at i what is the tallest wall on left of i,
    *  and tallest wall in right of i, then in the end calculate waterThatCanBeHoldAtI = Math.min(tallestLeftI,tallestRitghtI)-heighti
    * so keep 2 ararys left and right
    * in the end make calculations
    * */

    public int trap(int[] height) {
        int ans=0;
        int N = height.length;
        int[] left=new int[N];
        int[] right=new int[N];

        //compute for left array
        left[0]=height[0];
        for(int i=1;i<N;i++){
            left[i]= Math.max(left[i-1],height[i]);
        }

        //compute for right array
        right[N-1]=height[N-1];
        for(int i=N-2;i>=0;i--){
            right[i]= Math.max(right[i+1],height[i]);
        }

        for(int i=1;i<N;i++){
            ans+= Math.min(left[i],right[i])-height[i];
        }
        return ans;
    }

    /*Come back to this problem
    and try to attempt without using any space as well-
    * */


    public static void main(String[] args){
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrappingRainWater().trap(height));
    }

}
