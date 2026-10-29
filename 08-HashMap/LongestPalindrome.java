
/*
 * Problem Name: Longest Palindrome
 * LeetCode Number: 409
 * Difficulty: Easy
 * Pattern: HashMap / Frequency Counting
 *
 * Approach:
 * Count the frequency of each character using a HashMap.
 *
 * Use all occurrences of characters with even frequencies.
 * For characters with odd frequencies, use frequency - 1
 * to form pairs.
 *
 * If at least one character has an odd frequency,
 * one additional character can be placed in the center.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a HashMap to store character frequencies.
 *
 * 2. Traverse the string and count each character.
 *
 * 3. Initialize length = 0 and oddpresent = false.
 *
 * 4. Iterate through the frequency values.
 *
 * 5. If a frequency is even:
 *       Add the entire frequency to length.
 *
 * 6. Otherwise:
 *       Add frequency - 1 to length.
 *       Set oddpresent = true.
 *
 * 7. If any odd frequency exists, add one character
 *    to the center of the palindrome.
 *
 * 8. Return the final length.
 *
 * Key Observations:
 *
 * - A palindrome reads the same forward and backward.
 * - Characters on either side of the center must form pairs.
 * - Even frequencies can be used completely.
 * - For odd frequencies, one occurrence is left unused
 *   when forming pairs.
 * - Only one unpaired character can be placed at the center.
 *
 * Walkthrough:
 *
 * Input: "abccccdd"
 *
 * Frequencies:
 * a = 1, b = 1, c = 4, d = 2
 *
 * Pairs used:
 * a -> 0
 * b -> 0
 * c -> 4
 * d -> 2
 *
 * One odd-frequency character can occupy the center.
 *
 * Output: 7
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Counting frequencies and iterating through the map
 * take linear time overall.
 *
 * Space Complexity: O(1)
 * The map stores at most a fixed number of ASCII
 * characters for the standard LeetCode constraints.
 */

class Solution {
    public int longestPalindrome(String s) {
        Map<Character,Integer> map = new HashMap<>();

        //int [] arr = new int[128];

        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int length =0;
        boolean oddpresent = false;

        for(int value :map.values()){
            if(value%2 ==0)
                length +=value;
            else{
                length += value-1;
                oddpresent = true;
            }

        }
        // if(oddpresent){
        //     length++;
        // }

        return oddpresent?++length:length;


    }
}



/*
 * Problem Name: Longest Palindrome
 * LeetCode Number: 409
 * Difficulty: Easy
 * Pattern: Frequency Counting
 *
 * Approach:
 * Use an integer array to count the occurrences
 * of ASCII characters.
 *
 * Add the largest even portion of each frequency
 * to the result.
 *
 * If an odd frequency exists and the current count
 * is even, add one character for the center.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an integer array of size 128.
 *
 * 2. Count the frequency of each character.
 *
 * 3. Initialize count = 0.
 *
 * 4. Iterate through the frequency array.
 *
 * 5. Add (val / 2) * 2 to count to use all possible
 *    pairs from that character.
 *
 * 6. If count is even and the current frequency is odd,
 *    add one character for the center.
 *
 * 7. Return count.
 *
 * Key Observations:
 *
 * - (val / 2) * 2 gives the largest even number
 *   less than or equal to val.
 * - Character pairs can be placed symmetrically.
 * - A single odd-frequency character can contribute
 *   one character to the center.
 * - The array approach avoids HashMap overhead.
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n + 128), which is O(n).
 * Space Complexity: O(1), because the frequency array
 * has a fixed size of 128.
 */

class Solution {
    public int longestPalindrome(String s) {
        int [] arr = new int[128];
        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            arr[ch]++;
        }
        int  count =0;

        for(int val : arr){
            count += (val/2)*2;

            if(count%2==0 && val%2==1){
                count++;
            }
        }

        return count;
    }
}
