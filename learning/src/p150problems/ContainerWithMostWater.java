package p150problems;

import java.util.Arrays;

public class ContainerWithMostWater {

    /*
    Take left and right pointers
    consider all container probability without worrying about big blocks between left and right
    only think a container can be made only if min(leftHeight,rightHeigh)*distanceBetweenThem
    whichever is less height, increase that pointer
    * */
    public int maxArea(int[] height) {
        int ans = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int distance = right - left;
            int containerMaxWaterCapabity = distance * (Math.min(height[right], height[left]));
            ans = Math.max(ans, containerMaxWaterCapabity);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return ans;
    }

    public static void main(String args[]) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new ContainerWithMostWater().maxArea(height));
    }


}

