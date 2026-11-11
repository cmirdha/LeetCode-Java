/*
================================================================
PROBLEM: KTH SMALLEST ELEMENT IN AN ARRAY
PATTERN: Max-Heap Using Negated Values
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an array and a one-based position k, return the k-th
smallest value.

For example:
 arr = [7, 10, 4, 3, 20, 15]
 k = 3

The sorted values are [3, 4, 7, 10, 15, 20], so the answer is 7.

CORE IDEA
----------------------------------------------------------------
Java's PriorityQueue is a min-heap by default. This approach
stores the negative of each candidate value in the min-heap.

Negating values reverses their ordering:
- The largest original value becomes the smallest negative value.
- Therefore, the heap's top represents the largest original
 value among the current k candidates.

Keep the k smallest values seen so far in the heap. When a new
value is smaller than the largest candidate, replace that
candidate. At the end, the largest among the k smallest values
is the k-th smallest.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
arr
 The input array.

k
 The one-based rank of the value to return.

pq
 A min-heap storing negated values. The negation makes its top
 correspond to the largest original value in the heap.

i
 The index used to fill the heap and scan the remaining values.

pq.peek()
 The smallest negated value, which corresponds to the largest
 original value currently among the k candidates.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
for (int i = 0; i < k; i++)
 Add the first k values as initial candidates, storing each as
 its negative.

if (pq.peek() < -arr[i])
 Compare the new value with the largest original candidate.
 In negated form, a smaller heap-top value means a larger
 original value. If this condition is true, arr[i] is smaller
 than that candidate and should replace it.

pq.remove()
 Remove the largest original candidate (the smallest negated
 value in the heap).

pq.add(-arr[i])
 Add the new, smaller candidate in negated form.

return -pq.peek()
 Convert the heap's top back to its original value. It is the
 largest among the k smallest values, so it is the k-th smallest.

DRY RUN
----------------------------------------------------------------
arr = [7, 10, 4, 3, 20, 15]
k = 3

Add the first three values as negatives:
 Heap stores -7, -10, -4
 Heap top is -10, representing 10—the largest candidate.

Process 3:
 -10 < -3 is true.
 Remove -10 and add -3.
 The heap now represents [7, 4, 3], and its top is -7.

Process 20:
 -7 < -20 is false, so ignore 20.

Process 15:
 -7 < -15 is false, so ignore 15.

Return -(-7) = 7.

COMPLEXITY
----------------------------------------------------------------
Time: O(n log k), where n is the array length.
The heap holds at most k values, and each replacement takes
O(log k).

Extra space: O(k).

ASSUMPTIONS AND NOTES
----------------------------------------------------------------
- k must be between 1 and arr.length, inclusive. This code does
 not validate that condition.
- Duplicate values are counted separately.
- This approach does not modify arr.
- Negating an int has an edge case: negating Integer.MIN_VALUE
 overflows. For unrestricted int inputs, use the direct
 max-heap approach shown below instead.
- The sort and direct max-heap alternatives are commented out.
 In Java, statements after the first return are unreachable,
 and the alternatives also redeclare pq in the same scope.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public int kthSmallest(int[] arr, int k) {
        // Java's default PriorityQueue is a min-heap. Store
        // negatives so the top represents the largest original
        // value among the current k candidates.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add the first k values as negated candidates.
        for (int i = 0; i < k; i++) {
            pq.add(-arr[i]);
        }

        // Keep the k smallest values seen so far.
        for (int i = k; i < arr.length; i++) {
            // A smaller incoming value should replace the
            // largest original candidate in the heap.
            if (pq.peek() < -arr[i]) {
                pq.remove();
                pq.add(-arr[i]);
            }
        }

        // Convert the heap's top back to its original value.
        return -pq.peek();

 /*
 // Alternative 1: Sort the array.
 Arrays.sort(arr);
 return arr[k - 1];

 // Alternative 2: Use a max-heap directly.
 PriorityQueue<Integer> pq =
 new PriorityQueue<>(Collections.reverseOrder());

 for (int i = 0; i < k; i++) {
 pq.add(arr[i]);
 }

 for (int i = k; i < arr.length; i++) {
 if (pq.peek() > arr[i]) {
 pq.remove();
 pq.add(arr[i]);
 }
 }

 return pq.peek();
 */
    }
}
