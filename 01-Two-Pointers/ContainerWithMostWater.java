/*
 * Problem: Container With Most Water
 * LeetCode: #11
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * 1. Start with two pointers, left and right, at the beginning and end
 *    of the array.
 * 2. Calculate the area formed between the two pointers.
 * 3. The area is calculated using:
 *
 *      Area = min(height[left], height[right]) * (right - left)
 *
 * 4. Move the pointer with the smaller height because the smaller height
 *    limits the amount of water that can be stored.
 * 5. Calculate the area after every pointer movement.
 * 6. Keep track of the maximum area found.
 *
 * Explanation:
 * The two pointers represent the two boundaries of the container.
 *
 * The width of the container is:
 *
 *      right - left
 *
 * The height of the container is limited by the smaller of the two
 * heights:
 *
 *      min(height[left], height[right])
 *
 * Therefore:
 *
 *      Area = min(height[left], height[right]) * (right - left)
 *
 * If height[left] is smaller, moving the right pointer cannot increase
 * the height of the container because height[left] is still the limiting
 * factor. Therefore, we move left forward.
 *
 * Similarly, if height[right] is smaller, we move right backward.
 *
 * This allows us to find the maximum area without checking every pair.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int area = Math.min(height[left], height[right])
                * (right - left);

        while (left < right) {

            if (height[left] <= height[right]) {

                left++;

                int total = Math.min(height[left], height[right])
                        * (right - left);

                area = Math.max(total, area);

            } else {

                right--;

                int total = Math.min(height[left], height[right])
                        * (right - left);

                area = Math.max(total, area);
            }
        }

        return area;
    }
}