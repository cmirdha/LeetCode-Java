
/*
Problem Name: Sliding Window Median
LeetCode Number: 480
Difficulty: Hard
Pattern: Sliding Window + Two Heaps + Lazy Deletion

=========================================================
EXPLAIN LIKE I'M 10
=========================================================

Imagine a box that holds exactly k numbers.

We move this box across the array one position at a time.

At each position, we need to find the median of the
numbers currently inside the box.

Instead of sorting every window, we use TWO HEAPS.

SMALL = MAX HEAP
- Stores the smaller half of valid window elements.
- Its top is the largest number in the smaller half.

LARGE = MIN HEAP
- Stores the larger half of valid window elements.
- Its top is the smallest number in the larger half.

We maintain these rules:

1. Every valid number in SMALL must be <= every valid
   number in LARGE.

2. SMALL must have either the same number of valid
   elements as LARGE, or exactly one more.

=========================================================
WHY LAZY DELETION?
=========================================================

Java PriorityQueue does not efficiently remove an
arbitrary element from inside the heap.

Removing an arbitrary element using heap.remove(num)
takes O(N).

Instead, we use a HashMap called delayed.

When a number leaves the window:
- Mark it in delayed.
- Decrease the logical size of its heap.
- If it is at the top, remove it immediately.
- Otherwise, leave it in the heap temporarily.
- Remove it later when it reaches the top.

This is called LAZY DELETION.

=========================================================
WHY smallSize AND largeSize?
=========================================================

The actual physical heap sizes can contain stale
elements marked for deletion.

Therefore, we maintain separate logical sizes:

smallSize = valid elements in SMALL
largeSize = valid elements in LARGE

We use these logical sizes for balancing.

=========================================================
HOW ADD WORKS
=========================================================

1. Compare num with small.peek().
2. If SMALL is empty or num <= small.peek():
       Insert into SMALL.
   Otherwise:
       Insert into LARGE.
3. Update the logical size.
4. Balance the heaps.

=========================================================
HOW REMOVE WORKS
=========================================================

1. Mark num in delayed.
2. Determine which logical heap contains num.
3. Decrease that heap's logical size.
4. If num is at the top, prune immediately.
5. Balance the heaps.

=========================================================
HOW PRUNE WORKS
=========================================================

1. Look at the heap top.
2. If it is not in delayed, stop.
3. If it is in delayed:
       Decrease its delayed count.
       Remove the map entry if the count becomes zero.
       Physically remove it from the heap.
4. Repeat until the top is valid.

=========================================================
HOW BALANCE WORKS
=========================================================

If SMALL has more than one extra valid element:
    Move the largest element of SMALL to LARGE.
    Update logical sizes.
    Prune SMALL.

If LARGE has more valid elements than SMALL:
    Move the smallest element of LARGE to SMALL.
    Update logical sizes.
    Prune LARGE.

=========================================================
HOW MEDIAN WORKS
=========================================================

If k is odd:
    Median = small.peek()

If k is even:
    Median = (small.peek() + large.peek()) / 2.0

=========================================================
DRY RUN
=========================================================

nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Window 1: [1, 3, -1]
Sorted: [-1, 1, 3]
Median = 1

Window 2: [3, -1, -3]
Sorted: [-3, -1, 3]
Median = -1

Window 3: [-1, -3, 5]
Sorted: [-3, -1, 5]
Median = -1

Window 4: [-3, 5, 3]
Sorted: [-3, 3, 5]
Median = 3

Window 5: [5, 3, 6]
Sorted: [3, 5, 6]
Median = 5

Window 6: [3, 6, 7]
Sorted: [3, 6, 7]
Median = 6

Output:
[1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

=========================================================
KEY OBSERVATIONS
=========================================================

1. We use TWO heaps to efficiently find the median.

2. Lazy deletion avoids searching for arbitrary elements
   inside a PriorityQueue.

3. delayed tracks how many occurrences of each value
   must be physically removed later.

4. smallSize and largeSize track valid elements,
   not physical heap sizes.

5. prune removes stale elements only when they reach
   the top of a heap.

6. The median is always obtained from the heap tops.

=========================================================
COMPLEXITY ANALYSIS
=========================================================

Building the first window:
O(k log k)

Each sliding step:
- Add: O(log N)
- Remove: O(log N)
- Balance: O(log N)
- Pruning: amortized O(log N) per element removed
  from the heaps over the entire process.

Overall time complexity:
O(N log N)

Space complexity:
O(N)

The heaps and delayed map can store up to O(N)
elements over the execution of the algorithm.

*/

class Solution {

    PriorityQueue<Integer> small; // max heap
    PriorityQueue<Integer> large; // min heap

    Map<Integer, Integer> delayed;

    // Size of the small heap (valid elements only)
    int smallSize;

    // Size of the large heap (valid elements only)
    int largeSize;

    public double[] medianSlidingWindow(int[] nums, int k) {

        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();

        // Used for lazy deletion
        delayed = new HashMap<>();

        smallSize = 0;
        largeSize = 0;

        double[] result = new double[nums.length - k + 1];

        // Build the first window
        for (int i = 0; i < k; i++) {
            // Add the value into the heaps
            add(nums[i]);
        }

        // Calculate the median of the first window
        result[0] = median(k);

        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {

            // Add the incoming number
            add(nums[i]);

            // Remove the outgoing number
            remove(nums[i - k]);

            // Calculate the median of the current window
            result[i - k + 1] = median(k);
        }

        return result;
    }

    // Add a number to the correct heap
    private void add(int num) {

        // If SMALL is empty or num belongs to the smaller half
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }

        // Balance the heaps after insertion
        balance();
    }

    // Mark a number for deletion
    private void remove(int num) {

        // Mark the outgoing number for lazy deletion
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        // Determine which logical heap contains num
        if (num <= small.peek()) {

            // Decrease the valid size of SMALL
            smallSize--;

            // If num is at the top, physically remove it
            if (num == small.peek()) {
                prune(small);
            }

        } else {

            // Decrease the valid size of LARGE
            largeSize--;

            // If num is at the top, physically remove it
            if (!large.isEmpty() && num == large.peek()) {
                prune(large);
            }
        }

        // Balance the heaps after deletion
        balance();
    }

    // Maintain the size balance of the heaps
    private void balance() {

        // SMALL must not have more than one extra element
        if (smallSize > largeSize + 1) {

            // Move the largest element from SMALL to LARGE
            large.offer(small.poll());

            smallSize--;
            largeSize++;

            // Remove any stale elements exposed at the top
            prune(small);

        } else if (smallSize < largeSize) {

            // Move the smallest element from LARGE to SMALL
            small.offer(large.poll());

            smallSize++;
            largeSize--;

            // Remove any stale elements exposed at the top
            prune(large);
        }
    }

    // Remove stale elements from the top of a heap
    private void prune(PriorityQueue<Integer> heap) {

        while (!heap.isEmpty()) {

            int num = heap.peek();

            // If the top is valid, no further pruning needed
            if (!delayed.containsKey(num)) {
                break;
            }

            // Decrease the delayed deletion count
            delayed.put(num, delayed.get(num) - 1);

            // Remove the map entry if no more copies are pending
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }

            // Physically remove the stale top element
            heap.poll();
        }
    }

    // Calculate the median of the current window
    private double median(int k) {

        // Odd-sized window: SMALL has one extra element
        if ((k & 1) == 1) {
            return (double) small.peek();
        }

        // Even-sized window: average of the two heap tops
        return ((double) small.peek() + (double) large.peek()) / 2.0;
    }
}
