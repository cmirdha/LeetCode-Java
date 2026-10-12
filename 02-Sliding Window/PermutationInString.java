/*
 * Problem: Permutation in String
 * LeetCode: #567
 * Difficulty: Medium
 *
 * Pattern: Sliding Window + Hashing (Character Frequency Array)
 *
 * Approach:
 * 1. Initialize i = 0 and j = s1.length() - 1 to define
 *    a window in s2 equal to the length of s1.
 * 2. Calculate the character frequencies of s1 using an array
 *    of size 26.
 * 3. Slide the window across s2 one character at a time.
 * 4. For each window, calculate its character frequencies.
 * 5. Compare the two frequency arrays using Arrays.equals().
 * 6. If both arrays match, return true because the window
 *    contains a permutation of s1.
 * 7. If no window matches, return false.
 *
 * Explanation:
 * Two strings are permutations of each other when they contain
 * exactly the same characters with the same frequencies.
 *
 * For example:
 * s1 = "ab"
 * s2 = "eidbaooo"
 *
 * When the window reaches "ba", its character frequencies
 * match those of "ab", so the method returns true.
 *
 * The frequency array uses index ch - 'a' to count each
 * lowercase English letter.
 *
 * Note: This implementation recalculates the frequency array
 * for every window. A more efficient solution updates the
 * frequency array as the window slides.
 *
 * Time Complexity: O(n * m), where n = s2.length()
 *                  and m = s1.length()
 * Space Complexity: O(1), because the frequency arrays have
 *                   a fixed size of 26.
 */

import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int i = 0, j = s1.length() - 1;
        boolean isPresent = false;

        if (s1 == s2) {
            return true;
        }

        int[] arr1 = calculateValue(s1);
        int[] arr2 = new int[26];

        while (j < s2.length()) {
            String s = s2.substring(i, j + 1);
            arr2 = calculateValue(s);

            if (Arrays.equals(arr1, arr2)) {
                return true;
            }

            i++;
            j++;
        }

        return isPresent;
    }

    public int[] calculateValue(String str) {
        int[] arr = new int[26];

        for (int i = 0; i < str.length(); i++) {
            char ch1 = str.charAt(i);
            arr[ch1 - 'a']++;
        }

        return arr;
    }
}