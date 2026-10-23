
/*
 * Problem Name: Remove All Adjacent Duplicates In String
 * LeetCode Number: 1047
 * Difficulty: Easy
 * Pattern: Stack
 *
 * Approach:
 * Use a stack to store characters that have not been removed.
 *
 * If the current character matches the top character of the stack,
 * remove the top character.
 *
 * Otherwise, push the current character.
 *
 * The remaining characters form the answer in their original order.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an empty stack.
 *
 * 2. Iterate through every character in the string.
 *
 * 3. If the stack is not empty and its top character matches
 *    the current character:
 *       Pop the stack.
 *
 * 4. Otherwise:
 *       Push the current character.
 *
 * 5. Build the result by iterating through the stack.
 *
 * 6. Return the result.
 *
 * Key Observations:
 *
 * - The stack stores characters that survive duplicate removal.
 * - Only the top character needs to be checked.
 * - Removing a pair may create new adjacent duplicates.
 * - The stack automatically handles cascading removals.
 * - An empty input string returns an empty string.
 *
 * Walkthrough:
 *
 * Input: "abbaca"
 *
 * a -> Push       [a]
 * b -> Push       [a, b]
 * b -> Pop        [a]
 * a -> Pop        []
 * c -> Push       [c]
 * a -> Push       [c, a]
 *
 * Output: "ca"
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Each character is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * The stack can store up to n characters.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public String removeDuplicates(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        // Process every character in the string
        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            // If the current character matches the top,
            // remove both adjacent duplicates
            if (!stack.isEmpty() && stack.peek() == current) {

                stack.pop();

            } else {

                // Otherwise, keep the current character
                stack.push(current);
            }
        }

        // Build the final result
        StringBuilder result = new StringBuilder();

        // Stack stores the characters in reverse order
        while (!stack.isEmpty()) {

            result.append(stack.pop());
        }

        // Reverse to restore the original character order
        return result.reverse().toString();
    }
}
