
/*
 * Problem Name: Contiguous Array
 * LeetCode Number: 525
 * Difficulty: Medium
 * Pattern: Prefix Sum + HashMap
 *
 * ------------------------------------------------------------
 * APPROACH: PREFIX DIFFERENCE + HASHMAP
 * ------------------------------------------------------------
 *
 * The objective is to find the maximum length of a contiguous
 * subarray containing an equal number of zeros and ones.
 *
 * A brute-force approach checks every possible subarray and
 * counts its zeros and ones, taking O(n^2) time.
 *
 * We can solve this in O(n) average time by transforming the
 * array and using a prefix difference with a HashMap.
 *
 * ------------------------------------------------------------
 * 1. TRANSFORM THE PROBLEM
 * ------------------------------------------------------------
 *
 * The original array contains only 0s and 1s.
 *
 * Replace their contribution conceptually as follows:
 *
 * 1 -> +1
 * 0 -> -1
 *
 * Now consider any subarray:
 *
 * - If it contains more ones, its sum is positive.
 * - If it contains more zeros, its sum is negative.
 * - If it contains equal zeros and ones, its sum is zero.
 *
 * Therefore, the original problem becomes:
 *
 * Find the longest subarray whose transformed sum is zero.
 *
 * We do not need to modify the input array. We can calculate
 * the transformed contribution while traversing it.
 *
 * ------------------------------------------------------------
 * 2. TRACK THE PREFIX DIFFERENCE
 * ------------------------------------------------------------
 *
 * Variable:
 * - diff: Running prefix sum after transforming 1 to +1
 *   and 0 to -1.
 *
 * Update:
 *
 * diff += (nums[i] == 1) ? 1 : -1;
 *
 * Example:
 *
 * nums = [0, 1, 0, 1]
 *
 * Index:       0   1   2   3
 * Value:       0   1   0   1
 * Contribution:-1  +1  -1  +1
 * diff:       -1   0  -1   0
 *
 * When diff becomes zero, the entire prefix from index 0
 * through the current index contains equal zeros and ones.
 *
 * Its length is:
 *
 * i + 1
 *
 * ------------------------------------------------------------
 * 3. WHY A REPEATED DIFFERENCE MATTERS
 * ------------------------------------------------------------
 *
 * Suppose the same prefix difference appears at indices j
 * and i, where j < i.
 *
 * The transformed sum of the subarray from j + 1 through i
 * is:
 *
 * diff[i] - diff[j]
 *
 * Since the two differences are equal:
 *
 * diff[i] - diff[j] = 0
 *
 * That subarray must contain an equal number of zeros and
 * ones.
 *
 * Its length is:
 *
 * i - j
 *
 * Therefore, whenever diff appears again in the HashMap,
 * calculate the subarray length using the earliest index
 * where that difference occurred.
 *
 * ------------------------------------------------------------
 * 4. UNDERSTAND THE HASHMAP
 * ------------------------------------------------------------
 *
 * The map stores:
 *
 * Key   = A prefix difference.
 * Value = The earliest index where that difference occurred.
 *
 * Example:
 *
 * diffmap = {
 *     -1 -> 0,
 *      0 -> 1,
 *      1 -> 2
 * }
 *
 * If diff = -1 appears again at index 4 and its earliest
 * index was 0, the subarray length is:
 *
 * 4 - 0 = 4
 *
 * Using the earliest index maximizes the possible subarray
 * length for that difference.
 *
 * Your implementation uses:
 *
 * diffmap.put(
 *     diff,
 *     Math.min(
 *         diffmap.getOrDefault(diff, Integer.MAX_VALUE),
 *         i
 *     )
 * );
 *
 * This keeps the minimum index, so a later occurrence
 * cannot overwrite the earliest index.
 *
 * ------------------------------------------------------------
 * 5. HANDLE A ZERO PREFIX DIFFERENCE
 * ------------------------------------------------------------
 *
 * When diff == 0, the subarray from index 0 through index i
 * already contains equal zeros and ones.
 *
 * Its length is:
 *
 * i + 1
 *
 * Your code handles this explicitly:
 *
 * if (diff == 0) {
 *     result = Math.max(result, i + 1);
 * }
 *
 * An alternative approach is to initialize the map with
 * diffmap.put(0, -1). Then every matching difference can
 * use the same length calculation i - previousIndex.
 *
 * Your current approach is valid without that initialization
 * because it handles diff == 0 separately.
 *
 * ------------------------------------------------------------
 * 6. EXAMPLE WALKTHROUGH
 * ------------------------------------------------------------
 *
 * Input:
 * nums = [0, 1, 0, 1, 1, 0]
 *
 * Initial state:
 * diff = 0
 * result = 0
 * diffmap = {}
 *
 * Index 0, value 0:
 * diff = -1
 * Difference -1 has not appeared.
 * Store -1 -> 0.
 *
 * Index 1, value 1:
 * diff = 0
 * The prefix [0, 1] has equal zeros and ones.
 * Length = 1 + 1 = 2.
 * result = 2.
 * Store 0 -> 1.
 *
 * Index 2, value 0:
 * diff = -1
 * Difference -1 first appeared at index 0.
 * Length = 2 - 0 = 2.
 * result remains 2.
 *
 * Index 3, value 1:
 * diff = 0
 * Prefix length = 3 + 1 = 4.
 * result = 4.
 *
 * Index 4, value 1:
 * diff = 1
 * Difference 1 has not appeared.
 * Store 1 -> 4.
 *
 * Index 5, value 0:
 * diff = 0
 * Prefix length = 5 + 1 = 6.
 * result = 6.
 *
 * Output:
 * 6
 *
 * The entire array contains three zeros and three ones.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Transforming 0 to -1 and 1 to +1 converts the equal-count
 *    condition into a zero-sum subarray problem.
 *
 * 2. Equal prefix differences mean the subarray between those
 *    prefix positions has a transformed sum of zero.
 *
 * 3. Store the earliest index for every difference to maximize
 *    the length of the matching subarray.
 *
 * 4. If diff == 0, the valid subarray begins at index 0 and
 *    has length i + 1.
 *
 * 5. Do not overwrite an earlier index with a later one.
 *
 * 6. The original nums array does not need to be modified.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n) average
 * Each element is processed once, with average O(1) HashMap
 * operations.
 *
 * Space Complexity: O(n)
 * In the worst case, the map stores up to O(n) distinct
 * prefix differences.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        int diff = 0;
        int result = 0;

        // Map each prefix difference to its earliest index.
        Map<Integer, Integer> diffmap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            // Treat 1 as +1 and 0 as -1.
            // Equal numbers of zeros and ones produce sum zero.
            diff += (nums[i] == 1) ? 1 : -1;

            // A zero difference means the entire prefix is valid.
            if (diff == 0) {
                result = Math.max(result, i + 1);
            }

            // A repeated difference means the elements between
            // its earliest occurrence and this index form a
            // subarray with equal numbers of zeros and ones.
            else if (diffmap.get(diff) != null) {
                result = Math.max(result, i - diffmap.get(diff));
            }

            // Preserve the earliest index for this difference.
            diffmap.put(
                    diff,
                    Math.min(
                            diffmap.getOrDefault(diff, Integer.MAX_VALUE),
                            i
                    )
            );
        }

        return result;
    }
}

