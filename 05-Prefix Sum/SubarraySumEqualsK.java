```java
/*
 * Problem Name: Subarray Sum Equals K
 * LeetCode Number: 560
 * Difficulty: Medium
 * Pattern: Prefix Sum + HashMap (Frequency Map)
 *
 * ------------------------------------------------------------
 * APPROACH: PREFIX SUM + FREQUENCY MAP
 * ------------------------------------------------------------
 *
 * The goal is to count the number of contiguous subarrays
 * whose sum equals k.
 *
 * A brute-force solution checks every possible subarray,
 * which takes O(n^2) time.
 *
 * We can optimize this to O(n) using prefix sums and a
 * HashMap that stores how many times each prefix sum has
 * appeared.
 *
 * ------------------------------------------------------------
 * 1. UNDERSTAND THE PREFIX SUM
 * ------------------------------------------------------------
 *
 * A prefix sum is the sum of all elements from the beginning
 * of the array up to the current position.
 *
 * Let:
 *
 * prefixSum = nums[0] + nums[1] + ... + nums[i]
 *
 * Suppose a subarray from index j + 1 to index i has sum k.
 *
 * Its sum can be expressed as:
 *
 * subarraySum = prefixSum[i] - prefixSum[j]
 *
 * We want:
 *
 * prefixSum[i] - prefixSum[j] = k
 *
 * Rearranging:
 *
 * prefixSum[j] = prefixSum[i] - k
 *
 * Therefore, at each position, if we have previously seen
 * the prefix sum (prefixSum - k), it means a subarray ending
 * at the current position has sum k.
 *
 * ------------------------------------------------------------
 * 2. UNDERSTAND THE FREQUENCY MAP
 * ------------------------------------------------------------
 *
 * The HashMap stores:
 *
 * Key   = A prefix sum encountered earlier.
 * Value = The number of times that prefix sum has appeared.
 *
 * Example:
 *
 * freqMap = {
 *     0  -> 1,
 *     3  -> 2,
 *     5  -> 1
 * }
 *
 * This means:
 * - Prefix sum 0 has appeared once.
 * - Prefix sum 3 has appeared twice.
 * - Prefix sum 5 has appeared once.
 *
 * Why store frequencies instead of just checking whether
 * a prefix sum exists?
 *
 * Because the same prefix sum can appear multiple times.
 * Each occurrence represents a different starting position
 * for a valid subarray.
 *
 * Therefore, we add the frequency to count rather than
 * increasing count by only one.
 *
 * ------------------------------------------------------------
 * 3. INITIALIZE THE EMPTY PREFIX
 * ------------------------------------------------------------
 *
 * freqMap.put(0, 1);
 *
 * This represents an empty prefix with sum zero before
 * processing any array elements.
 *
 * Why is this necessary?
 *
 * Consider:
 *
 * nums = [2, 3]
 * k = 5
 *
 * At the second element:
 * prefixSum = 5
 * prefixSum - k = 0
 *
 * The map already contains 0 with frequency 1, so the
 * complete subarray [2, 3] is counted.
 *
 * Without this initialization, subarrays starting at index
 * zero could be missed.
 *
 * ------------------------------------------------------------
 * 4. PROCESS EACH ELEMENT
 * ------------------------------------------------------------
 *
 * For every number in nums, perform these steps:
 *
 * STEP A: Update the running prefix sum.
 *
 * prefixSum += num
 *
 * STEP B: Calculate the prefix sum needed to form a
 * subarray whose sum equals k.
 *
 * remainingSum = prefixSum - k
 *
 * STEP C: Check whether remainingSum exists in the map.
 *
 * If it exists, add its frequency to count:
 *
 * count += freqMap.get(remainingSum)
 *
 * Each previous occurrence represents one valid subarray
 * ending at the current element.
 *
 * STEP D: Record the current prefix sum.
 *
 * freqMap.put(
 *     prefixSum,
 *     freqMap.getOrDefault(prefixSum, 0) + 1
 * );
 *
 * This increments the frequency if the sum already exists
 * or initializes its frequency to one.
 *
 * IMPORTANT ORDER:
 *
 * Check prefixSum - k before inserting the current prefix
 * sum. This ensures the current prefix is matched only with
 * earlier prefixes, preserving the intended subarray logic.
 *
 * ------------------------------------------------------------
 * 5. EXAMPLE WALKTHROUGH
 * ------------------------------------------------------------
 *
 * Input:
 * nums = [1, 1, 1]
 * k = 2
 *
 * Initial state:
 * prefixSum = 0
 * count = 0
 * freqMap = {0=1}
 *
 * First element: 1
 * prefixSum = 1
 * remainingSum = 1 - 2 = -1
 * -1 is not in the map.
 * Record prefix sum 1.
 * Map: {0=1, 1=1}
 * count = 0
 *
 * Second element: 1
 * prefixSum = 2
 * remainingSum = 2 - 2 = 0
 * Frequency of 0 is 1.
 * count = 0 + 1 = 1
 * Record prefix sum 2.
 * Map: {0=1, 1=1, 2=1}
 *
 * Third element: 1
 * prefixSum = 3
 * remainingSum = 3 - 2 = 1
 * Frequency of 1 is 1.
 * count = 1 + 1 = 2
 * Record prefix sum 3.
 *
 * Output:
 * 2
 *
 * The two valid subarrays are [1, 1] at indices [0, 1]
 * and [1, 1] at indices [1, 2].
 *
 * ------------------------------------------------------------
 * 6. WHY THIS WORKS WITH NEGATIVE NUMBERS
 * ------------------------------------------------------------
 *
 * A sliding window based on a growing sum generally cannot
 * solve this problem reliably when negative numbers exist.
 * Adding a negative number can decrease the sum, so the
 * usual expand/shrink conditions are not dependable.
 *
 * Prefix sum + HashMap works with positive numbers, zero,
 * and negative numbers because it checks the mathematical
 * relationship between two prefix sums.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Subarray sum = Current prefix sum - Previous prefix sum.
 *
 * 2. To find a subarray with sum k, look for:
 *    prefixSum - k
 *
 * 3. Store frequencies because the same prefix sum can occur
 *    multiple times and each occurrence can form a different
 *    valid subarray.
 *
 * 4. Initialize the map with {0=1} to count subarrays that
 *    begin at index zero.
 *
 * 5. Update the count before recording the current prefix sum.
 *
 * 6. This approach counts all valid subarrays, not just the
 *    number of distinct prefix sums.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n) average
 * Each element performs a constant number of HashMap
 * operations, which take O(1) time on average.
 *
 * Space Complexity: O(n)
 * In the worst case, the map stores up to O(n) distinct
 * prefix sums.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {

        int prefixSum = 0;
        int count = 0;

        // Store each prefix sum and its frequency.
        Map<Integer, Integer> freqMap = new HashMap<>();

        // An empty prefix has sum 0 and occurs once.
        // This allows us to count subarrays starting at index 0.
        freqMap.put(0, 1);

        for (int num : nums) {

            // Step 1: Calculate the current prefix sum.
            prefixSum += num;

            // Step 2: Find the earlier prefix sum needed
            // to make the current subarray sum equal to k.
            int remainingSum = prefixSum - k;

            // Step 3: Every occurrence of remainingSum
            // represents one valid subarray ending here.
            if (freqMap.containsKey(remainingSum)) {
                count += freqMap.get(remainingSum);
            }

            // Step 4: Record the current prefix sum so that
            // future elements can use it.
            freqMap.put(
                    prefixSum,
                    freqMap.getOrDefault(prefixSum, 0) + 1
            );
        }

        // Return the total number of subarrays whose sum is k.
        return count;
    }
}
```
