```java
/*
 * Problem: Circular Array Loop
 * LeetCode: #457
 * Difficulty: Medium
 * Pattern: Fast & Slow Pointers (Floyd's Cycle Detection)
 *
 * ============================================================
 * APPROACH
 * ============================================================
 *
 * This problem is not just about detecting a cycle. A valid
 * cycle must satisfy two conditions:
 *
 * 1. All movements must be in the same direction
 *    (all positive or all negative).
 * 2. The cycle length must be greater than 1.
 *
 * Use Floyd's Cycle Detection (Fast & Slow Pointers).
 * For every starting index i, initialize:
 *
 *     slowptr = i
 *     fastptr = i
 *
 * Move slowptr one step and fastptr two steps at a time.
 *
 * Direction is determined by nums[index], not by the index
 * itself. Always check whether the current value still has
 * the same sign as the starting direction.
 *
 * A cycle is found when:
 *
 *     slowptr == fastptr
 *
 * Before accepting a cycle, check for a self-loop:
 *
 *     next(current) == current
 *
 * If true, the cycle length is 1 and therefore invalid.
 *
 * Validate the direction for both slow and fast pointers.
 * If either pointer reaches an element whose sign differs
 * from the starting direction, stop the traversal because
 * it cannot form a valid cycle.
 *
 * If a traversal fails because of a direction change or
 * self-loop, do not return false immediately. That only
 * proves the current starting index is invalid. Continue
 * checking other starting indices.
 *
 * ============================================================
 * OPTIMIZATION
 * ============================================================
 *
 * After proving a path cannot contain a valid cycle, mark
 * all nodes visited in that traversal as 0.
 *
 * Since the problem guarantees nums[i] != 0 initially,
 * zero can safely be used as a processed marker.
 *
 * In the outer loop:
 *
 *     if (nums[i] == 0)
 *         continue;
 *
 * This prevents revisiting the same invalid path multiple
 * times.
 *
 * Without marking visited nodes:
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * With marking visited nodes as 0:
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Pick a starting index.
 * 2. Determine the direction (positive or negative).
 * 3. Run Fast & Slow pointers.
 * 4. Ensure every move stays in the same direction.
 * 5. Reject self-loops.
 * 6. If slowptr == fastptr and cycle length > 1, return true.
 * 7. Otherwise mark the failed path as 0.
 * 8. Continue with the next index.
 * 9. If no valid cycle is found, return false.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Checking fastptr < 0 or slowptr < 0 instead of checking
 *    nums[fastptr] or nums[slowptr].
 * 2. Returning true when fastptr != slowptr.
 * 3. Forgetting to reject self-loops.
 * 4. Validating only the fast pointer's direction.
 * 5. Returning false after one invalid traversal instead of
 *    checking the remaining indices.
 * 6. Forgetting to reset slowptr and fastptr to index i.
 *
 * ============================================================
 * HELPER METHOD
 * ============================================================
 *
 * calculatenext() calculates the next circular array index:
 *
 *     ((current + nums[current]) % nums.length
 *         + nums.length) % nums.length
 *
 * Adding nums.length before the second modulo ensures that
 * negative movements produce a valid, non-negative index.
 *
 * ============================================================
 * TIME AND SPACE COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(n) with visited-node marking
 * Space Complexity: O(1)
 *
 * Note: The algorithm modifies the input array by marking
 * processed elements as zero.
 */

class Solution {

    public int calculatenext(int current, int[] nums) {

        return ((current + nums[current]) % nums.length
                + nums.length) % nums.length;

    }

    public boolean circularArrayLoop(int[] nums) {

        // Use fast and slow pointers to detect valid cycles.
        // Check that every move maintains the same direction.

        if (nums.length == 1) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {

            // Skip elements already processed.
            if (nums[i] == 0)
                continue;

            int fastptr = i;
            int slowptr = i;

            // Store the initial direction.
            boolean ispos = nums[i] > 0;

            do {

                // Move fast pointer one step.
                fastptr = calculatenext(fastptr, nums);

                // Validate the direction after the first move.
                if (ispos) {
                    if (nums[fastptr] < 0)
                        break;
                } else {
                    if (nums[fastptr] > 0)
                        break;
                }

                // Move slow pointer one step.
                slowptr = calculatenext(slowptr, nums);

                // Move fast pointer its second step.
                fastptr = calculatenext(fastptr, nums);

                // Validate the direction after the second move.
                if (ispos) {
                    if (nums[fastptr] < 0)
                        break;
                } else {
                    if (nums[fastptr] > 0)
                        break;
                }

                // A cycle exists when the pointers meet.
                if (fastptr == slowptr) {

                    // Reject a cycle containing only one element.
                    int nextptr = calculatenext(slowptr, nums);

                    if (fastptr == nextptr)
                        break;
                    else
                        return true;
                }

            } while (fastptr != slowptr && nums[i] != 0);

            // Mark the invalid path as processed.
            int curr = i;

            while (nums[curr] != 0 && ((nums[curr] > 0) == ispos)) {

                int next = calculatenext(curr, nums);

                nums[curr] = 0;

                curr = next;
            }

        }

        // No valid circular loop was found.
        return false;

    }

}
```