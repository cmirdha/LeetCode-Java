
/*
 * Problem Name: Next Greater Element I
 * LeetCode Number: 496
 * Difficulty: Easy
 * Pattern: Monotonic Stack
 *
 * Approach:
 * Use a stack to find the next greater element for
 * each element in nums1 using nums2.
 *
 * Traverse nums2 from right to left.
 *
 * Store elements in the stack and remove elements
 * that are less than or equal to the current element.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a result array of size nums1.length.
 *
 * 2. Create a stack to store elements from nums2.
 *
 * 3. Initialize the stack with the last element of nums2.
 *
 * 4. Traverse nums2 from right to left.
 *
 * 5. Find whether the current element exists in nums1
 *    using the isPresent() method.
 *
 * 6. If the element is not present in nums1:
 *       Push the current element into the stack.
 *       Continue to the next iteration.
 *
 * 7. Otherwise:
 *       Remove elements from the stack that are
 *       less than or equal to the current element.
 *
 * 8. If the stack becomes empty:
 *       Store -1 in the result.
 *
 * 9. Otherwise:
 *       Store the top element of the stack
 *       in the result.
 *
 * 10. Push the current element into the stack.
 *
 * 11. Return the result array.
 *
 * Key Observations:
 *
 * - The stack stores elements from nums2.
 * - The stack helps find the next greater element.
 * - Elements less than or equal to the current
 *   element are removed.
 * - The isPresent() method finds the position
 *   of the current element in nums1.
 * - If no greater element exists, return -1.
 *
 * Walkthrough:
 *
 * Input:
 * nums1 = [4,1,2]
 * nums2 = [1,3,4,2]
 *
 * Output:
 * [-1,3,-1]
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n * m)
 * Stack processing is O(n), but isPresent()
 * takes O(m) for each element.
 *
 * n = nums2.length
 * m = nums1.length
 *
 * Space Complexity: O(n + m)
 * Stack and result array require additional space.
 */

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int [] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums2.length;
        stack.push(nums2[n-1]);

        for(int i =n-1;i>=0;i--){
            int pos = isPresent(nums1, nums2[i]);
            if (pos == -1){
                stack.push(nums2[i]);
                continue;
            }

            while(!stack.isEmpty() && stack.peek()<=nums2[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[pos] = -1;
            }
            else{
                result[pos] = stack.peek();
            }
            stack.push(nums2[i]);

        }
        return result;


    }

    public int isPresent(int[] nums,int val){

        for(int i =0;i<nums.length;i++){
            if(nums[i]==val){
                return i;
            }
        }
        return -1;

    }
}
