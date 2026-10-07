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



/*
 * Problem: Minimum Window Substring
 * LeetCode: #76
 * Difficulty: Hard
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Create a frequency array to store the required frequency of each
 *    character from string t.
 * 2. Use two pointers, left and right, to maintain a sliding window
 *    over string s.
 * 3. Expand the window by moving the right pointer.
 * 4. If the current character is still required by t, increase the
 *    count of matched characters.
 * 5. Decrease the frequency of the current character in the frequency
 *    array.
 * 6. When the window contains all characters required by t, try to
 *    shrink the window from the left.
 * 7. Store the smallest valid window found.
 * 8. When removing a character causes the window to become invalid,
 *    stop shrinking and continue expanding from the right.
 *
 * Explanation:
 * The frequency array keeps track of how many characters from t are
 * still required by the current window.
 *
 * When a character from s is added:
 * - If its required frequency is greater than 0, it contributes to
 *   satisfying the characters of t.
 * - Its frequency is then decreased.
 *
 * Once count becomes equal to the length of t, the current window
 * contains all required characters. We then move the left pointer to
 * find the smallest possible valid window.
 *
 * When a character is removed from the left and its frequency becomes
 * greater than 0, that character is required again, so count is
 * decreased.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 */

class Solution {

    public String minWindow(String s, String t) {

        int left = 0;
        int count = 0;
        String output = "";
        int minlen = Integer.MAX_VALUE;

        int[] freq = new int[264];

        int slen = s.length();
        int tlen = t.length();

        if (tlen > slen) {
            return "";
        }

        for (int i = 0; i < tlen; i++) {
            char ch = t.charAt(i);
            freq[ch]++;
        }

        for (int right = 0; right < slen; right++) {

            char ch = s.charAt(right);

            if (freq[ch] > 0) {
                count++;
            }

            freq[ch]--;

            while (count == tlen) {

                int strlen = right - left + 1;

                if (strlen < minlen) {
                    output = s.substring(left, right + 1);
                    minlen = strlen;
                }

                char ch1 = s.charAt(left);

                freq[ch1]++;

                if (freq[ch1] > 0) {
                    count--;
                }

                left++;
            }
        }

        return output;
    }
}