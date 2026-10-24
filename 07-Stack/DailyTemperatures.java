
/*
 * Problem Name: Daily Temperatures
 * LeetCode Number: 739
 * Difficulty: Medium
 * Pattern: Monotonic Stack
 *
 * Approach:
 * Use a stack to store the indices of temperatures.
 *
 * Traverse the temperatures array from right to left.
 *
 * Compare the current temperature with the temperatures
 * stored at the indices in the stack.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a stack to store indices.
 *
 * 2. Initialize the last index in the stack.
 *
 * 3. Traverse the array from right to left.
 *
 * 4. While the stack is not empty and the temperature
 *    at the top index is less than or equal to the
 *    current temperature:
 *       Pop the stack.
 *
 * 5. If the stack becomes empty:
 *       Store 0 in the result.
 *
 * 6. Otherwise:
 *       Calculate the difference between the index
 *       at the top of the stack and the current index.
 *
 * 7. Push the current index into the stack.
 *
 * 8. Return the result array.
 *
 * Key Observations:
 *
 * - The stack stores indices instead of temperatures.
 * - The stack helps find the next warmer temperature.
 * - Temperatures less than or equal to the current
 *   temperature are removed.
 * - The difference between indices gives the number
 *   of days to wait.
 * - If no warmer temperature exists, the result is 0.
 *
 * Walkthrough:
 *
 * Input:
 * [73,74,75,71,69,72,76,73]
 *
 * Output:
 * [1,1,4,2,1,1,0,0]
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Each index is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * The stack and result array can store up to n elements.
 */

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int n = temperatures.length-1;
        int [] result = new int[n+1];

        stack.push(n);
        result[n]=0;
        //[73,74,75,71,69,72,76,73]
        //[0 , 1, 2, 3, 4, 5, 6, 7]


        for(int i= n-1;i>=0;i--){
            while(!stack.isEmpty() &&  temperatures[stack.peek()]<= temperatures[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i] = 0;
            }
            else{
                result[i]= stack.peek()-i;
            }
            stack.push(i);
        }


        return result;
    }
}
