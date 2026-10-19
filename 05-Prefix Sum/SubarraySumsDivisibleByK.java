```java
/*
 * Problem Name: Subarray Sums Divisible by K
 * LeetCode Number: 974
 * Difficulty: Medium
 * Pattern: Prefix Sum + HashMap (Remainder Frequency)
 *
 * ------------------------------------------------------------
 * APPROACH: PREFIX SUM + REMAINDER FREQUENCY MAP
 * ------------------------------------------------------------
 *
 * The objective is to count the number of contiguous,
 * non-empty subarrays whose sum is divisible by k.
 *
 * A subarray is divisible by k when:
 *
 * subarraySum % k == 0
 *
 * A brute-force approach checks every possible subarray.
 * This takes O(n^2) time.
 *
 * We can solve the problem in O(n) average time by using
 * prefix sums and a HashMap that stores the frequency of
 * each remainder.
 *
 * ------------------------------------------------------------
 * 1. UNDERSTAND PREFIX SUM
 * ------------------------------------------------------------
 *
 * Let prefixsum represent the sum of all elements from
 * index 0 through the current index.
 *
 * Suppose:
 *
 * prefixsum[i] = sum of nums[0] through nums[i]
 *
 * The sum of a subarray from index j + 1 through index i is:
 *
 * subarraySum = prefixsum[i] - prefixsum[j]
 *
 * We need this subarray sum to be divisible by k:
 *
 * (prefixsum[i] - prefixsum[j]) % k == 0
 *
 * This means:
 *
 * prefixsum[i] % k == prefixsum[j] % k
 *
 * Therefore, if two prefix sums have the same remainder
 * when divided by k, the sum of the elements between them
 * is divisible by k.
 *
 * ------------------------------------------------------------
 * 2. WHY STORE REMAINDERS INSTEAD OF PREFIX SUMS?
 * ------------------------------------------------------------
 *
 * For Subarray Sum Equals K, we searched for:
 *
 * previousPrefixSum = currentPrefixSum - k
 *
 * For this problem, the exact prefix sum does not matter.
 * We only need to know whether the current prefix sum and
 * an earlier prefix sum have the same remainder.
 *
 * Example:
 *
 * k = 5
 *
 * Prefix sum 7  -> remainder 2
 * Prefix sum 12 -> remainder 2
 *
 * Difference:
 *
 * 12 - 7 = 5
 *
 * Since 5 is divisible by 5, the subarray between these
 * prefix sums is valid.
 *
 * Thus, the map stores:
 *
 * Key   = Normalized remainder of a prefix sum.
 * Value = Number of times that remainder has appeared.
 *
 * ------------------------------------------------------------
 * 3. INITIALIZE THE MAP
 * ------------------------------------------------------------
 *
 * freqmap.put(0, 1);
 *
 * This represents an empty prefix with sum zero.
 *
 * Why is it necessary?
 *
 * If the prefix sum of the elements from index 0 through
 * the current index is itself divisible by k, its remainder
 * is zero.
 *
 * The initial remainder of zero allows such a subarray to
 * be counted even though no earlier array element exists.
 *
 * ------------------------------------------------------------
 * 4. PROCESS EACH ELEMENT
 * ------------------------------------------------------------
 *
 * STEP A: Update the prefix sum.
 *
 * prefixsum += nums[i];
 *
 * STEP B: Calculate the remainder.
 *
 * modsum = prefixsum % k;
 *
 * STEP C: Normalize negative remainders.
 *
 * Java's % operator can return a negative remainder when
 * prefixsum is negative.
 *
 * For example:
 *
 * -2 % 5 = -2
 *
 * But we want the equivalent non-negative remainder 3,
 * because -2 and 3 differ by 5.
 *
 * Therefore:
 *
 * if (modsum < 0)
 *     modsum += k;
 *
 * For positive k, this normalizes the remainder to the
 * range 0 through k - 1.
 *
 * STEP D: Count previously seen matching remainders.
 *
 * If modsum exists in the map, add its frequency to result.
 *
 * result += freqmap.get(modsum);
 *
 * Each previous occurrence identifies a different starting
 * position for a valid subarray ending at the current index.
 *
 * STEP E: Record the current remainder.
 *
 * Increase its frequency by one so future elements can use it.
 *
 * ------------------------------------------------------------
 * 5. EXAMPLE WALKTHROUGH
 * ------------------------------------------------------------
 *
 * Input:
 * nums = [4, 5, 0, -2, -3, 1]
 * k = 5
 *
 * Initial state:
 * prefixsum = 0
 * result = 0
 * freqmap = {0=1}
 *
 * Element 4:
 * prefixsum = 4
 * remainder = 4
 * Remainder 4 has not appeared.
 * result = 0
 * Map: {0=1, 4=1}
 *
 * Element 5:
 * prefixsum = 9
 * remainder = 4
 * Remainder 4 appeared once.
 * result = 0 + 1 = 1
 * Map: {0=1, 4=2}
 *
 * Element 0:
 * prefixsum = 9
 * remainder = 4
 * Remainder 4 appeared twice.
 * result = 1 + 2 = 3
 * Map: {0=1, 4=3}
 *
 * Element -2:
 * prefixsum = 7
 * remainder = 2
 * Remainder 2 has not appeared.
 * result = 3
 * Map: {0=1, 4=3, 2=1}
 *
 * Element -3:
 * prefixsum = 4
 * remainder = 4
 * Remainder 4 appeared three times.
 * result = 3 + 3 = 6
 * Map: {0=1, 4=4, 2=1}
 *
 * Element 1:
 * prefixsum = 5
 * remainder = 0
 * Remainder 0 appeared once.
 * result = 6 + 1 = 7
 *
 * Output:
 * 7
 *
 * There are seven non-empty subarrays whose sums are
 * divisible by 5.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. A subarray sum is divisible by k when the prefix sums
 *    before and after that subarray have the same remainder.
 *
 * 2. The HashMap stores remainder frequencies, not the
 *    frequencies of the full prefix sums.
 *
 * 3. A remainder may occur multiple times. Add its entire
 *    frequency to result, not just one.
 *
 * 4. Initialize remainder zero with frequency one to count
 *    valid subarrays starting at index zero.
 *
 * 5. Normalize negative remainders because Java's % operator
 *    can produce negative results.
 *
 * 6. Count matching remainders before incrementing the
 *    current remainder's frequency.
 *
 * 7. This solution assumes k is positive, as in the problem
 *    constraints.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n) average
 * Each element is processed once, with average O(1) HashMap
 * operations per element.
 *
 * Space Complexity: O(min(n, k))
 * There are at most n + 1 prefix sums and at most k distinct
 * normalized remainders for positive k.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int result = 0;
        int prefixsum = 0;

        // Store each normalized remainder and its frequency.
        Map<Integer, Integer> freqmap = new HashMap<>();

        // An empty prefix has remainder zero and occurs once.
        freqmap.put(0, 1);

        for (int i = 0; i < nums.length; i++) {

            // Step 1: Update the running prefix sum.
            prefixsum += nums[i];

            // Step 2: Find the current prefix sum's remainder.
            int modsum = prefixsum % k;

            // Step 3: Convert a negative remainder into its
            // equivalent non-negative remainder.
            if (modsum < 0) {
                modsum = modsum + k;
            }

            // Step 4: Every previous matching remainder
            // represents one valid subarray ending here.
            if (freqmap.get(modsum) != null) {
                result += freqmap.get(modsum);
            }

            // Step 5: Record this remainder for future elements.
            freqmap.put(
                    modsum,
                    freqmap.getOrDefault(modsum, 0) + 1
            );
        }

        return result;
    }
}
```
