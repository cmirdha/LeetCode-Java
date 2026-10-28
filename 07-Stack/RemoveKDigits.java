
/*
 * Problem Name: Remove K Digits
 * LeetCode Number: 402
 * Difficulty: Medium
 * Pattern: Monotonic Stack
 *
 * Approach:
 * Use a stack to remove digits that are greater than
 * the current digit while k > 0.
 *
 * This helps construct the smallest possible number
 * after removing k digits.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a stack to store digits.
 *
 * 2. Convert the input number into a StringBuilder.
 *
 * 3. Push the first digit into the stack.
 *
 * 4. Iterate through the remaining digits.
 *
 * 5. While the stack is not empty, k > 0, and the
 *    top digit is greater than the current digit:
 *       Pop the stack and decrement k.
 *
 * 6. Push the current digit into the stack.
 *
 * 7. If k is still greater than 0 after processing
 *    all digits:
 *       Remove digits from the end of the stack.
 *
 * 8. Build the result by popping digits from the stack
 *    and inserting them at the beginning.
 *
 * 9. Remove leading zeros from the result.
 *
 * 10. If the result becomes empty, return "0".
 *     Otherwise, return the resulting number.
 *
 * Key Observations:
 *
 * - Removing a larger digit before a smaller digit
 *   can produce a smaller number.
 * - The stack maintains the digits needed to construct
 *   the smallest possible number.
 * - If the digits are already in increasing order,
 *   remaining removals happen from the end.
 * - Leading zeros must be removed.
 * - If all digits are removed or only zeros remain,
 *   the answer is "0".
 *
 * Walkthrough:
 *
 * Input:
 * num = "1432219", k = 3
 *
 * Remove 4, 3, and 2 where they make the number smaller.
 *
 * Output:
 * "1219"
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n^2)
 * Stack operations are O(n), but repeated insertion
 * at index 0 and deletion at index 0 can take O(n^2).
 *
 * Space Complexity: O(n)
 * The stack and StringBuilder store up to n digits.
 */

class Solution {
    public String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(num);
        stack.push(sb.charAt(0));

        for(int i =1;i<sb.length();i++){
            while(!stack.isEmpty()
                    && k>0
                    && stack.peek()>sb.charAt(i)){

                stack.pop();
                k--;
            }
            stack.push(sb.charAt(i));
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder result = new StringBuilder();

        while(!stack.isEmpty()){
            result.insert(0,stack.pop());
        }

        while(result.length() > 0 && result.charAt(0) == '0'){
            result.deleteCharAt(0);
        }



        return result.length()==0? "0":result.toString();
    }
}
