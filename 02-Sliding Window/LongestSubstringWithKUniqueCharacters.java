/*
 * Problem: Longest Substring with K Unique Characters
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use a sliding window with two pointers, left and right.
 * 2. Use a HashMap to store each character and its frequency in the
 *    current window.
 * 3. Expand the window by moving the right pointer and update the
 *    character frequency.
 * 4. If the number of unique characters becomes greater than k,
 *    shrink the window by moving the left pointer.
 * 5. Remove a character from the HashMap when its frequency becomes 0.
 * 6. Whenever the window contains exactly k unique characters,
 *    calculate its length and update the maximum length.
 *
 * Explanation:
 * The HashMap keeps track of the frequency of each character inside
 * the current sliding window.
 *
 * When the window contains more than k unique characters, the left
 * pointer is moved forward until the window becomes valid again.
 *
 * A window is considered valid only when it contains exactly k unique
 * characters. For every valid window, its length is calculated as:
 *
 *     right - left + 1
 *
 * The maximum valid window length is returned.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

class Solution {

    public int longestKSubstr(String s, int k) {

        int strLen = -1;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < s.length(); right++) {

            map.put(
                    s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1
            );

            if (map.size() > k) {

                char ch = s.charAt(left);

                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }

                left++;
            }

            if (map.size() == k) {
                int total = right - left + 1;
                strLen = Math.max(total, strLen);
            }
        }

        return strLen;
    }
}