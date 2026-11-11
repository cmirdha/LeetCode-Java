/*
================================================================
PROBLEM: TOP K FREQUENT ELEMENTS
PATTERN: Min-Heap of Size k
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an integer array, return the k values that appear most
often.

For example:
nums = [1, 1, 1, 2, 2, 3]
k = 2

The frequencies are:
1 appears 3 times
2 appears 2 times
3 appears 1 time

The two most frequent values are 1 and 2.

CORE IDEA
----------------------------------------------------------------
First count how many times each value appears in a HashMap.

Then put the distinct values into a min-heap ordered by
frequency. The value with the lowest frequency is at the top.

If the heap contains more than k values, remove its least
frequent value. After processing all distinct values, the heap
contains the k most frequent values.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
nums
 The input array.

k
 The number of frequent values to return.

map
 Maps each distinct number to its frequency.

pq
 A min-heap of distinct numbers, ordered by their frequencies.

a, b
 Values being compared by the heap. Comparing map.get(a) and
 map.get(b) orders them by frequency.

list
 Collects the values remaining in the heap for the result.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
map.put(i, map.getOrDefault(i, 0) + 1)
 Increase the count for i, using 0 if i has not appeared before.

new PriorityQueue<>((a, b) ->...)
 Order values from lower frequency to higher frequency.

for (int key : map.keySet())
 Add each distinct value to the heap.

while (pq.size() > k)
 Remove the least frequent value until only k values remain.

return...
 Return the remaining heap values as an int array.

DRY RUN
----------------------------------------------------------------
nums = [1, 1, 1, 2, 2, 3]
k = 2

Frequency map:
1 -> 3
2 -> 2
3 -> 1

Add all distinct values to the min-heap:
The least frequent value, 3, is removed because the heap has
more than k values.

The remaining values are 1 and 2, the two most frequent values.

COMPLEXITY
----------------------------------------------------------------
Let d be the number of distinct values in nums.

Counting frequencies takes O(n) time.
Heap operations take O(d log d) time in this implementation.
The map and heap use O(d) extra space.

NOTES
----------------------------------------------------------------
- The order of values in the returned array is not guaranteed.
 A heap's iteration order is not sorted.
- The original result array was unused, so it is omitted.
- The comparator uses Integer.compare to avoid possible
 subtraction overflow.
- This assumes k is between 1 and the number of distinct values,
 inclusive.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Count how many times each number appears.
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Put less frequent values closer to the heap's top.
        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a, b) ->
                        Integer.compare(map.get(a), map.get(b)));

        // Add every distinct value.
        for (int key : map.keySet()) {
            pq.add(key);
        }

        // Remove the least frequent values until only k remain.
        while (pq.size() > k) {
            pq.poll();
        }

        // Convert the remaining heap values into an array.
        int[] result = new int[k];
        int index = 0;

        while (!pq.isEmpty()) {
            result[index++] = pq.poll();
        }

        return result;
    }
}
