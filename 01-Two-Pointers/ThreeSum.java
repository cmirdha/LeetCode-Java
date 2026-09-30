/*
 * Problem: 3Sum
 * LeetCode: #15
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * 1. Sort the array.
 * 2. Fix one element using x.
 * 3. Use two pointers (y and z) to find two values
 *    whose sum is equal to -nums[x].
 * 4. Skip duplicate values to avoid duplicate triplets.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(log n) to O(n), depending on the sorting implementation.
 */

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int x = 0; x < nums.length - 2; x++) {

            // Skip duplicate values for x
            if (x > 0 && nums[x] == nums[x - 1]) {
                continue;
            }

            int y = x + 1;
            int z = nums.length - 1;

            while (y < z) {

                int sum = nums[x] + nums[y] + nums[z];

                if (sum < 0) {
                    y++;
                }
                else if (sum > 0) {
                    z--;
                }
                else {
                    result.add(Arrays.asList(
                            nums[x],
                            nums[y],
                            nums[z]
                    ));

                    // Skip duplicates for y
                    while (y < z && nums[y] == nums[y + 1]) {
                        y++;
                    }

                    // Skip duplicates for z
                    while (y < z && nums[z] == nums[z - 1]) {
                        z--;
                    }

                    y++;
                    z--;
                }
            }
        }

        return result;
    }
}