/*
 * Problem: 3Sum Closest
 * LeetCode: #16
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * Sort the array and fix one element.
 * Use two pointers for the remaining two elements.
 * Track the sum closest to the target.
 *
 * If the current sum is smaller than the target,
 * move the left pointer to increase the sum.
 *
 * If the current sum is greater than the target,
 * move the right pointer to decrease the sum.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(1) auxiliary
 */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = nums[0] + nums[1] + nums[2];

        for(int x =0;x<nums.length-2;x++){
            int y=x+1;
            int z=nums.length-1;
            while(y<z){
                int sum = nums[x]+nums[y]+nums[z];
                int diff = target - sum;
                if (Math.abs(target -result) > Math.abs(diff)){
                    result = sum;
                }
                else if(sum<target){
                    y++;
                }
                else {
                    z--;
                }
            }

        }
        return result;

    }
}