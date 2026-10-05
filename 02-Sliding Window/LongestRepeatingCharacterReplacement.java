/*
 * Problem: Longest Repeating Character Replacement
 * LeetCode: #424
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Use a HashMap to store the frequency of each character in the
 *    current window.
 * 3. Keep track of the character with the highest frequency in the
 *    current window.
 * 4. Calculate the number of replacements required using:
 *
 *      replacements = windowSize - maxFrequency
 *
 * 5. If the number of replacements is greater than k, shrink the
 *    window by moving the left pointer.
 * 6. For every valid window, update the maximum window length.
 *
 * Explanation:
 * The goal is to find the longest substring that can be converted
 * into a string containing the same character by replacing at most k
 * characters.
 *
 * We keep the most frequent character unchanged and replace all other
 * characters in the window.
 *
 * For example, if the window is:
 *
 *      A A B A B
 *
 * The most frequent character is A with frequency 3.
 * The window size is 5, so we need:
 *
 *      5 - 3 = 2 replacements
 *
 * If k >= 2, this window is valid.
 *
 * If replacements > k, move the left pointer to shrink the window.
 *
 * Time Complexity: O(n * m), where m is the number of distinct
 * characters in the window, because Collections.max() scans the
 * HashMap values for every right pointer.
 *
 * Space Complexity: O(m), where m is the number of distinct characters.
 */

class Solution {

    public int characterReplacement(String s, int k) {

        int left = 0;
        int output = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {

            map.put(
                    s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1
            );

            int maxFreq = Collections.max(map.values());
            int windowSize = right - left + 1;
            int replacement = windowSize - maxFreq;

            if (replacement > k) {

                map.put(
                        s.charAt(left),
                        map.get(s.charAt(left)) - 1
                );

                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }

                left++;
            }

            windowSize = right - left + 1;
            output = Math.max(windowSize, output);
        }

        return output;
    }
}