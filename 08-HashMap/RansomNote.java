/*
 * Problem Name: Ransom Note
 * LeetCode Number: 383
 * Difficulty: Easy
 * Pattern: Array / Frequency Counting
 *
 * Approach:
 * Use an integer array to count the frequency of characters
 * available in the magazine. Then consume those characters
 * while checking whether the ransomNote can be constructed.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an integer array of size 128 to store character
 *    frequencies using their ASCII values as indexes.
 *
 * 2. If ransomNote.length() > magazine.length(), return false.
 *    There are not enough characters available to construct
 *    the ransom note.
 *
 * 3. Traverse the magazine and increment the frequency of
 *    each character.
 *
 * 4. Traverse ransomNote:
 *    - Decrease the frequency of the required character.
 *    - If its frequency becomes negative, return false because
 *      the magazine does not contain enough occurrences of it.
 *
 * 5. If every character is available, return true.
 *
 * Key Observations:
 * - Every character in ransomNote must be available in magazine.
 * - Each magazine character can be used only once.
 * - Character frequencies matter, not just character presence.
 * - For example, "aa" cannot be constructed from "ab".
 *
 * Example:
 * Input:
 * ransomNote = "aa"
 * magazine = "aab"
 *
 * Magazine frequencies:
 * a -> 2
 * b -> 1
 *
 * Process:
 * First 'a': frequency becomes 1.
 * Second 'a': frequency becomes 0.
 *
 * No frequency becomes negative.
 *
 * Output: true
 *
 * Complexity Analysis:
 * Let n = magazine.length()
 * Let m = ransomNote.length()
 *
 * Time Complexity: O(n + m)
 * - Count magazine characters: O(n)
 * - Check ransomNote characters: O(m)
 *
 * Space Complexity: O(1)
 * - The array always has 128 elements.
 *
 * Java Implementation:
 */

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] arr = new int[128];

        // If the ransom note is longer than the magazine,
        // there are not enough characters to construct it.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Count the frequency of each character in magazine.
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i)]++;
        }

        // Consume the characters needed by ransomNote.
        for (int i = 0; i < ransomNote.length(); i++) {

            arr[ransomNote.charAt(i)]--;

            // A negative frequency means the required character
            // is unavailable or has already been used.
            if (arr[ransomNote.charAt(i)] < 0) {
                return false;
            }
        }

        return true;
    }
}