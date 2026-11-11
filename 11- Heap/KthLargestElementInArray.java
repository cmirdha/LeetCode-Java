/*
================================================================
PROBLEM: KTH LARGEST ELEMENT IN AN ARRAY
PATTERN: Min-Heap of Size k
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an array and a one-based position k, return the k-th
largest value.

For example:
arr = [3, 2, 1, 5, 6, 4]
k = 2

The values in descending order are [6, 5, 4, 3, 2, 1],
so the 2nd largest value is 5.

CORE IDEA
----------------------------------------------------------------
Keep a min-heap containing the k largest values seen so far.

A min-heap puts its smallest value at the top. Once the heap
holds k values, its top is the smallest among those k largest
values—and therefore the current k-th-largest candidate.

For every remaining array value:
- Add it to the heap.
- Remove the heap's smallest value to keep the heap size at k.

At the end, the heap's top is the k-th largest value.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
arr
 The input array.

k
 The one-based rank of the value to return.

pq
 A min-heap containing the k largest values processed so far.

i
 The index used to fill the heap and scan the remaining values.

pq.peek()
 The smallest value among the heap's k largest values. This is
 the k-th largest value overall after all values are processed.

WHY ADD, THEN REMOVE?
----------------------------------------------------------------
Adding each new value and then removing the minimum keeps only
the largest k values seen so far. If the new value is too small,
it will be the heap's minimum and will be removed. If it belongs
among the largest k, a smaller value is removed instead.

DRY RUN
----------------------------------------------------------------
arr = [3, 2, 1, 5, 6, 4]
k = 2

Add the first two values:
Heap contains [3, 2]. Remove the minimum, 2.
Heap contains [3].

Process 1:
Add 1, then remove the minimum, 1.
Heap contains [3].

Process 5:
Add 5, then remove the minimum, 3.
Heap contains [5].

Process 6:
Add 6, then remove the minimum, 5.
Heap contains [6].

Process 4:
Add 4, then remove the minimum, 4.
Heap contains [6].

Correction: after each operation the heap must retain k values.
For k = 2, the heap after processing all values is [5, 6],
so pq.peek() returns 5.

COMPLEXITY
----------------------------------------------------------------
Time: O(n log k), where n is the array length.
Each value is added to the heap, and removing its minimum
takes O(log k).

Extra space: O(k).

ALTERNATIVE: SORTING
----------------------------------------------------------------
Sort the array in ascending order and return the element at
index arr.length - k. This takes O(n log n) time and modifies
the input array.

NOTES
----------------------------------------------------------------
- k must be between 1 and arr.length, inclusive.
- Duplicate values count as separate positions.
- The sorting and heap approaches are alternatives. Only one
 should be active in the method; code after a return is
 unreachable.
- The original snippet uses nums in the sorting approach, but
 the method parameter is named arr. Use arr consistently.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public int findKthLargest(int[] arr, int k) {
        // Keep the k largest values seen so far.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Initialize the heap with the first k values.
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        // Add each remaining value, then discard the smallest
        // so the heap continues to hold only k values.
        for (int i = k; i < arr.length; i++) {
            pq.add(arr[i]);
            pq.remove();
        }

        // The smallest of the k largest values is the answer.
        return pq.peek();

 /*
 // Alternative: sort in ascending order.
 Arrays.sort(arr);
 return arr[arr.length - k];
 */
    }
}
