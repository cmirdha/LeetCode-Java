/*
 * Problem Name: First Unique Character in a String
 * LeetCode Number: 387
 * Difficulty: Easy
 * Pattern: Hashing / Frequency Map
 *
 * Approach:
 * Use a HashMap<Character, Integer> to store the first index of each
 * character. If a character appears more than once, mark its value
 * as Integer.MAX_VALUE.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a HashMap to store each character and its first index.
 *
 * 2. Traverse the string:
 *    - If the character is not present, store its index.
 *    - If the character is already present, mark it as repeated by
 *      assigning Integer.MAX_VALUE.
 *
 * 3. Initialize result to Integer.MAX_VALUE.
 *
 * 4. Iterate through the map values and find the minimum index.
 *    Repeated characters have Integer.MAX_VALUE, so they will not
 *    affect the minimum if a unique character exists.
 *
 * 5. If result is still Integer.MAX_VALUE, return -1.
 *    Otherwise, return result.
 *
 * Key Observations:
 * - Integer.MAX_VALUE acts as a marker for repeated characters.
 * - The minimum stored index identifies the first unique character.
 * - A character that appears three or more times remains marked
 *   as repeated.
 *
 * Example:
 * Input: s = "leetcode"
 *
 * First traversal:
 * l -> 0
 * e -> 1
 * e -> Integer.MAX_VALUE
 * t -> 3
 * c -> 4
 * o -> 5
 * d -> 6
 *
 * Final map values include:
 * l -> 0
 * e -> Integer.MAX_VALUE
 * t -> 3
 * c -> 4
 * o -> 5
 * d -> 6
 *
 * Minimum value = 0
 * Output: 0
 *
 * Complexity Analysis:
 * Time Complexity: O(n)
 * - First traversal: O(n)
 * - Map traversal: O(k), where k is the number of distinct characters
 *
 * Space Complexity: O(k)
 * - Stores at most k distinct characters in the HashMap.
 *
 * Java Implementation:
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int firstUniqChar(String s) {

        Map<Character, Integer> map = new HashMap<>();

        // Store the first index of each character.
        // Mark repeated characters with Integer.MAX_VALUE.
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (map.containsKey(ch)) {
                map.put(ch, Integer.MAX_VALUE);
            } else {
                map.put(ch, i);
            }
        }

        int result = Integer.MAX_VALUE;

        // Find the smallest index among unique characters.
        for (Integer value : map.values()) {
            result = Math.min(result, value);
        }

        // No unique character was found.
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
