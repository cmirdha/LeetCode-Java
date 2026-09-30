/*
 * Problem: 4Sum
 * LeetCode: #18
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * 1. Sort the array.
 * 2. Fix the first number using a.
 * 3. Fix the second number using b.
 * 4. Use two pointers c and d to find the remaining two numbers.
 * 5. Skip duplicate values to avoid duplicate quadruplets.
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(1) excluding the output.
 */

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int a = 0; a < nums.length - 3; a++) {

            // Skip duplicate first values
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            for (int b = a + 1; b < nums.length - 2; b++) {

                // Skip duplicate second values
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                int c = b + 1;
                int d = nums.length - 1;

                while (c < d) {

                    long sum = (long) nums[a]
                            + nums[b]
                            + nums[c]
                            + nums[d];

                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[a],
                                nums[b],
                                nums[c],
                                nums[d]
                        ));

                        // Skip duplicates for c
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }

                        // Skip duplicates for d
                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }

                        c++;
                        d--;

                    } else if (sum < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
            }
        }

        return result;
    }
}