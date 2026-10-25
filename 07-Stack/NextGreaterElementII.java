
/*
 * Problem Name: Next Greater Element II
 * LeetCode Number: 503
 * Difficulty: Medium
 * Pattern: Monotonic Stack
 *
 * Approach:
 * Use a stack to find the next greater element
 * in a circular array.
 *
 * Traverse the array twice to simulate circular
 * behavior.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an empty stack.
 *
 * 2. Create a result array of size n.
 *
 * 3. Traverse the array from (2*n)-1 to 0.
 *
 * 4. Use i % n to access elements circularly.
 *
 * 5. While the stack is not empty and the current
 *    element is greater than or equal to the
 *    element at the top of the stack:
 *       Pop the stack.
 *
 * 6. If i < n:
 *       If the stack is empty:
 *           Store -1 in the result.
 *
 *       Otherwise:
 *           Store the top element of the stack.
 *
 * 7. Push the current element into the stack.
 *
 * 8. Return the result array.
 *
 * Key Observations:
 *
 * - The array is circular.
 * - Each element can find its next greater element
 *   beyond the end of the array.
 * - Traversing twice simulates circular behavior.
 * - i % n gives the circular array index.
 * - Elements less than or equal to the current
 *   element are removed from the stack.
 * - The stack stores potential next greater elements.
 * - If no greater element exists, return -1.
 *
 * Walkthrough:
 *
 * Input:
 * nums = [1,2,1]
 *
 * Output:
 * [2,-1,2]
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Each element is pushed and popped at most once
 * across the traversal.
 *
 * Space Complexity: O(n)
 * The stack and result array require additional space.
 */

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int [] result = new int[n];

        for(int i = (2*n)-1; i>=0;i--){
            while(!stack.isEmpty() && nums[i%n]>= stack.peek()){
                stack.pop();
            }

            if(i<n){
                result[i] = stack.isEmpty() ? -1:stack.peek();
            }
            stack.push(nums[i%n]);
        }

        return result;
    }
}

