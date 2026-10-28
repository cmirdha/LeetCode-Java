/*
 * Problem Name: Maximum Number of Balloons
 * LeetCode Number: 1189
 * Difficulty: Easy
 * Pattern: Array / Frequency Counting
 *
 * Approach:
 * Count the frequency of every character using an integer array
 * of size 26. Then repeatedly consume the characters needed to
 * form the word "balloon".
 *
 * Step-by-Step Logic:
 *
 * 1. Create an array of size 26 to count lowercase letters.
 *
 * 2. Traverse the input string and increment the frequency
 *    of each character.
 *
 * 3. Use the word "balloon" as the target.
 *
 * 4. Maintain an index i to track the next character needed.
 *    - Decrease the frequency of the required character.
 *    - If its frequency becomes negative, stop because there
 *      are not enough characters to form another balloon.
 *    - When the character 'n' is processed, one complete
 *      balloon has been formed. Increment count and reset i.
 *
 * 5. Return count.
 *
 * Key Observations:
 * - Each balloon requires b, a, l, l, o, o, n.
 * - 'l' and 'o' are required twice per balloon.
 * - The loop stops when a required character runs out.
 *
 * Example:
 * Input: text = "loonbalxballpoon"
 *
 * Output: 2
 *
 * Complexity Analysis:
 * Let n be the length of text.
 *
 * Time Complexity: O(n + 7 * k), where k is the number of
 * complete balloons attempted. Since each attempt consumes
 * seven characters and stops when a required character runs
 * out, this is O(n) overall.
 *
 * Space Complexity: O(1)
 * The frequency array always has 26 elements.
 *
 * Java Implementation:
 */

class Solution {
    public int maxNumberOfBalloons(String text) {

        int[] arr = new int[26];
        String balloon = "balloon";

        // Count the frequency of every character.
        for (int i = 0; i < text.length(); i++) {
            arr[text.charAt(i) - 'a']++;
        }

        boolean isover = true;
        int count = 0;
        int i = 0;

        // Try to construct one balloon at a time.
        while (isover) {

            // Consume one occurrence of the required character.
            arr[balloon.charAt(i) - 'a']--;

            // Stop if the required character is unavailable.
            if (arr[balloon.charAt(i) - 'a'] < 0) {
                break;
            }

            // One complete balloon has been constructed.
            if (balloon.charAt(i) == 'n') {
                count++;
                i = 0;
            } else {
                i++;
            }
        }

        return count;
    }
}

/*
 * Problem Name: Maximum Number of Balloons
 * LeetCode Number: 1189
 * Difficulty: Easy
 * Pattern: Hashing / Frequency Map
 *
 * Approach:
 * Count the frequency of every character using a HashMap.
 * Calculate how many balloons each required character can
 * support, then take the minimum.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a HashMap<Character, Integer>.
 *
 * 2. Traverse the input string and count each character.
 *
 * 3. Retrieve the frequencies of the required characters:
 *    - b is required once.
 *    - a is required once.
 *    - l is required twice.
 *    - o is required twice.
 *    - n is required once.
 *
 * 4. Divide the frequencies of 'l' and 'o' by two because
 *    each balloon needs two occurrences of both characters.
 *
 * 5. Find the minimum among all five available counts.
 *    This minimum represents the maximum number of balloons
 *    that can be constructed.
 *
 * Key Observations:
 * - A balloon requires 7 characters: b, a, l, l, o, o, n.
 * - Extra characters that are not in "balloon" do not matter.
 * - The character with the smallest usable frequency limits
 *   the number of balloons.
 *
 * Example:
 * Input: text = "loonbalxballpoon"
 *
 * Frequencies:
 * b = 2, a = 2, l = 4, o = 4, n = 2
 *
 * Usable balloon counts:
 * b = 2
 * a = 2
 * l = 4 / 2 = 2
 * o = 4 / 2 = 2
 * n = 2
 *
 * Minimum = 2
 * Output: 2
 *
 * Complexity Analysis:
 * Let n be the length of text and k be the number of
 * distinct characters.
 *
 * Time Complexity: O(n + k) = O(n)
 * - Count frequencies: O(n)
 * - Read the five required frequencies: O(1) average per lookup
 *
 * Space Complexity: O(k)
 * - The map stores distinct characters.
 *
 * Java Implementation:
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfBalloons(String text) {

        Map<Character, Integer> map = new HashMap<>();

        // Count the frequency of each character.
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Get the frequency of each required character.
        int bfreq = map.getOrDefault('b', 0);
        int afreq = map.getOrDefault('a', 0);
        int lfreq = map.getOrDefault('l', 0) / 2;
        int ofreq = map.getOrDefault('o', 0) / 2;
        int nfreq = map.getOrDefault('n', 0);

        // The least available required character limits the result.
        int val = Math.min(
                Math.min(bfreq, afreq),
                Math.min(Math.min(lfreq, ofreq), nfreq)
        );

        return val;
    }
}