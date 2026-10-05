/*
 * Problem: Longest Substring Without Repeating Characters
 * LeetCode: #3
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Use a HashMap to store the frequency of each character in the
 *    current window.
 * 3. Expand the window by moving the right pointer and add the current
 *    character to the HashMap.
 * 4. If the current character appears more than once, shrink the
 *    window by moving the left pointer.
 * 5. Decrease the frequency of each character leaving the window and
 *    remove it from the HashMap when its frequency becomes 0.
 * 6. For every valid window, calculate its length and update the
 *    maximum length.
 *
 * Explanation:
 * The window must contain only unique characters. Whenever a duplicate
 * character is found, move the left pointer forward until the duplicate
 * is removed from the current window.
 *
 * The length of the current valid window is:
 *
 *     right - left + 1
 *
 * Keep updating the maximum length to find the longest substring without
 * repeating characters.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, charset))
 */

class Solution {

    public int lengthOfLongestSubstring(String s) {

        int strLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        if (s.length() == 1) {
            return 1;
        }

        for (int right = 0; right < s.length(); right++) {

            map.put(
                    s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1
            );

            while (map.get(s.charAt(right)) != 1 && left <= right) {

                map.put(
                        s.charAt(left),
                        map.getOrDefault(s.charAt(left), 0) - 1
                );

                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }

                left++;
            }

            int total = right - left + 1;
            strLen = Math.max(total, strLen);
        }

        return strLen;
    }
}