
/*
 * Problem: Find All Anagrams in a String
 * LeetCode: #438
 * Difficulty: Medium
 * Pattern: Sliding Window + Character Frequency Array
 *
 * ============================================================
 * APPROACH
 * ============================================================
 *
 * The goal is to find every starting index in string s where
 * a substring is an anagram of string p.
 *
 * Two strings are anagrams when they contain the same
 * characters with exactly the same frequencies.
 *
 * 1. Create two frequency arrays of size 26:
 *      parray -> character frequencies of p
 *      sarray -> character frequencies of the current window
 *
 * 2. If p is longer than s, return an empty list because
 *    no substring of s can be an anagram of p.
 *
 * 3. Calculate the character frequencies of p.
 *
 * 4. Initialize left = 0 and expand the window by moving
 *    right from the beginning to the end of s.
 *
 * 5. For each character s.charAt(right), increment its
 *    frequency in sarray.
 *
 * 6. If the window length is smaller than p.length(),
 *    continue expanding the window.
 *
 * 7. Once the window reaches p.length(), compare sarray
 *    with parray using Arrays.equals().
 *
 * 8. If both frequency arrays match, add left to the
 *    result list because left is the starting index of
 *    an anagram.
 *
 * 9. Remove the character at left from the window by
 *    decrementing its frequency, then move left forward.
 *
 * 10. Return the list containing all matching indices.
 *
 * ============================================================
 * WHY A FIXED-SIZE SLIDING WINDOW?
 * ============================================================
 *
 * Every anagram of p must have exactly p.length() characters.
 * Therefore, the window must always be that size when checking
 * whether a substring is an anagram.
 *
 * Once the window reaches the required size:
 *
 *     window length = right - left + 1
 *
 * After checking the current window, remove its leftmost
 * character and increment left. The next iteration adds
 * another character from the right.
 *
 * This allows us to examine every possible substring of the
 * required length without rebuilding each substring.
 *
 * ============================================================
 * EXAMPLE
 * ============================================================
 *
 * s = "cbaebabacd"
 * p = "abc"
 *
 * Required character frequencies:
 * a -> 1, b -> 1, c -> 1
 *
 * The windows "cba" and "bac" match the frequency array
 * of p, so their starting indices are added to the list.
 *
 * Output: [0, 6]
 *
 * ============================================================
 * TIME AND SPACE COMPLEXITY
 * ============================================================
 *
 * Let n = s.length() and m = p.length().
 *
 * Building the pattern frequency array: O(m)
 * Sliding through s: O(n)
 * Comparing frequency arrays: O(26) per window
 *
 * Since the frequency arrays always contain 26 elements,
 * each comparison takes constant time.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(1) auxiliary space
 *
 * The result list is not included in auxiliary space.
 * In the worst case, it can contain O(n) indices.
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Build the frequency array for the pattern.
 * 2. Expand the window using the right pointer.
 * 3. Add the incoming character to the window frequency.
 * 4. If the window is too small, continue expanding.
 * 5. Compare frequencies when the window reaches the
 *    required size.
 * 6. If frequencies match, record the left index.
 * 7. Remove the outgoing character from the frequency array.
 * 8. Move the left pointer forward.
 * 9. Return all recorded indices.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Forgetting to check whether p is longer than s.
 * 2. Comparing windows before they reach p.length().
 * 3. Forgetting to decrement the outgoing character's count.
 * 4. Forgetting to increment left after shrinking the window.
 * 5. Returning the substring instead of its starting index.
 * 6. Using a window size different from p.length().
 * 7. Forgetting that Arrays.equals() compares array contents.
 *
 * ============================================================
 * IMPLEMENTATION
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();
        int[] parray = new int[26];
        int[] sarray = new int[26];

        if (p.length() > s.length()) {
            return list;
        }

        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            parray[ch - 'a']++;
        }

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            sarray[ch - 'a']++;

            if (right - left + 1 < p.length()) {
                continue;
            } else {
                if (Arrays.equals(parray, sarray)) {
                    list.add(left);
                }
            }

            char chr = s.charAt(left);
            sarray[chr - 'a']--;
            left++;
        }

        return list;
    }
}
