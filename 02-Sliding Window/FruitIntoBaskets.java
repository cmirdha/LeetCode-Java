/*
 * Problem: Fruit Into Baskets
 * LeetCode: #904
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Use a HashMap to store each fruit type and its frequency in the
 *    current window.
 * 3. Expand the window by moving the right pointer and add the current
 *    fruit to the HashMap.
 * 4. If the window contains more than 2 different fruit types, shrink
 *    the window by moving the left pointer.
 * 5. Decrease the frequency of the fruit leaving the window.
 * 6. Remove the fruit from the HashMap when its frequency becomes 0.
 * 7. Calculate the maximum valid window length whenever the window
 *    contains at most 2 different fruit types.
 *
 * Explanation:
 * The problem allows us to pick fruits using two baskets, where each
 * basket can contain only one type of fruit. Therefore, a valid window
 * can contain at most 2 distinct fruit types.
 *
 * The HashMap tracks the frequency of each fruit type in the current
 * window. Whenever a third fruit type is added, the left pointer moves
 * forward until the window contains at most 2 distinct types again.
 *
 * The length of every valid window is:
 *
 *     right - left + 1
 *
 * The maximum valid window length is the answer.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int totalFruit(int[] fruits) {

        int len = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < fruits.length; right++) {

            map.put(
                    fruits[right],
                    map.getOrDefault(fruits[right], 0) + 1
            );

            if (map.size() > 2) {

                map.put(
                        fruits[left],
                        map.get(fruits[left]) - 1
                );

                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            if (map.size() <= 2) {
                int total = right - left + 1;
                len = Math.max(total, len);
            }
        }

        return len;
    }
}