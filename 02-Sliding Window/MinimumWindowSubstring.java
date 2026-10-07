/*
 * Problem: Minimum Window Substring
 * LeetCode: #76
 * Difficulty: Hard
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Create a frequency map for all characters required from string t.
 * 2. Use another HashMap to maintain the frequency of characters inside
 *    the current window of string s.
 * 3. Expand the window by moving the right pointer.
 * 4. Whenever the current window contains all required characters with
 *    the required frequencies, the window becomes valid.
 * 5. While the window is valid, try to shrink it from the left to find
 *    the smallest possible valid window.
 * 6. Store the starting position and length of the smallest valid window.
 * 7. Return the minimum window substring, or an empty string if no valid
 *    window exists.
 *
 * Explanation:
 * The target string t may contain duplicate characters, so we maintain
 * the required frequency of every character in t.
 *
 * The window is valid only when it contains every character from t with
 * at least the required frequency.
 *
 * For every valid window:
 *
 *      windowSize = right - left + 1
 *
 * We update the minimum window and then move the left pointer to see
 * whether a smaller valid window can be found.
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n + m)
 *
 * n = length of s
 * m = number of distinct characters in t
 *
 * Note:
 * isValidWindow() checks all characters in t for every shrinking step,
 * which makes the current implementation O(n * m).
 */

class Solution {

    public String minWindow(String s, String t) {

        if (t.length() > s.length() || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> tmap = calculateValue(t);
        Map<Character, Integer> windowMap = new HashMap<>();

        int left = 0;

        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {

            char rightChar = s.charAt(right);

            // Add the right character to the current window
            windowMap.put(
                    rightChar,
                    windowMap.getOrDefault(rightChar, 0) + 1
            );

            // Shrink while the window remains valid
            while (isValidWindow(tmap, windowMap)) {

                int windowSize = right - left + 1;

                if (windowSize < minLength) {
                    minLength = windowSize;
                    minStart = left;
                }

                char leftChar = s.charAt(left);

                windowMap.put(
                        leftChar,
                        windowMap.get(leftChar) - 1
                );

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minStart, minStart + minLength);
    }

    public boolean isValidWindow(
            Map<Character, Integer> tmap,
            Map<Character, Integer> windowMap) {

        for (Map.Entry<Character, Integer> entry : tmap.entrySet()) {

            char character = entry.getKey();
            int requiredFrequency = entry.getValue();

            if (windowMap.getOrDefault(character, 0)
                    < requiredFrequency) {

                return false;
            }
        }

        return true;
    }

    public Map<Character, Integer> calculateValue(String str) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {

            char character = str.charAt(i);

            map.put(
                    character,
                    map.getOrDefault(character, 0) + 1
            );
        }

        return map;
    }
}