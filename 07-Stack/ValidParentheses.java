
/*
 * Problem Name: Valid Parentheses
 * LeetCode Number: 20
 * Difficulty: Easy
 * Pattern: Stack
 *
 * Approach:
 * Use a stack to store opening brackets.
 *
 * Compare the current character with the top element
 * of the stack to determine whether the brackets match.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an empty stack.
 *
 * 2. Iterate through the string.
 *
 * 3. If the stack is not empty and the current character
 *    matches the opening bracket at the top:
 *       Pop the stack.
 *
 * 4. Otherwise:
 *       Push the current character into the stack.
 *
 * 5. After processing all characters:
 *       Check whether the stack is empty.
 *
 * Key Observations:
 *
 * - Stack follows LIFO.
 * - The top element represents the most recent character.
 * - Matching brackets should be removed.
 * - The string is valid if all brackets are matched.
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Each character is processed once.
 *
 * Space Complexity: O(n)
 * The stack can store up to n characters.
 */

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for(int i =0;i<s.length();i++){
            if(!stack.isEmpty() && (stack.peek() == s.charAt(i)- 1 ||stack.peek() == s.charAt(i)-2  ) ){
                stack.pop();
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        return stack.size()==0;

    }
}
