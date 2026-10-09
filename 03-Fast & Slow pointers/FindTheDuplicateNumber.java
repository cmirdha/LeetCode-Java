/*
 * Problem: Find the Duplicate Number
 * LeetCode: #287
 * Difficulty: Medium
 *
 * Pattern: Fast & Slow Pointers (Floyd's Cycle Detection)
 *
 * Approach:
 * 1. Treat each array value as the index of the next element.
 * 2. Initialize slow and fast pointers at index 0.
 * 3. Move slow by one step and fast by two steps until they meet.
 * 4. Reset slow to index 0 while keeping fast at the meeting point.
 * 5. Move both pointers one step at a time until they meet again.
 * 6. The meeting value is the duplicate number.
 *
 * Explanation:
 * Since each array element points to another index, the array can be
 * treated as a linked list. Because at least one number is duplicated,
 * a cycle must exist.
 *
 * In the first phase, the slow and fast pointers meet inside the cycle.
 * In the second phase, reset slow to index 0 and move both pointers
 * one step at a time. They meet at the cycle's entrance, which
 * corresponds to the duplicate number.
 *
 * This approach does not modify the array and uses constant extra space.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int findDuplicate(int[] nums) {

        int fast = 0, slow = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];

        while (fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;

        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
