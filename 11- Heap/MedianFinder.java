
/*
Problem Name: Find Median from Data Stream
LeetCode Number: 295
Difficulty: Hard
Pattern: Two Heaps (Max Heap + Min Heap)

---------------------------------------------------------
EXPLAIN LIKE I'M 10
---------------------------------------------------------

Imagine numbers are coming one by one.

After every number arrives, we need to find the middle
value of all the numbers received so far.

Sorting the entire collection every time is expensive.

Instead, divide all numbers into two groups:

1. SMALL: Contains the smaller half of the numbers.
2. LARGE: Contains the larger half of the numbers.

We use two heaps:

SMALL = Max Heap
- Stores the smaller half.
- Its top is the largest number in the smaller half.

LARGE = Min Heap
- Stores the larger half.
- Its top is the smallest number in the larger half.

We maintain two rules:

RULE 1: ORDERING
Every number in SMALL must be <= every number in LARGE.

RULE 2: BALANCE
SMALL must contain either:
- The same number of elements as LARGE, OR
- Exactly one more element than LARGE.

Therefore:

small.size() == large.size()
OR
small.size() == large.size() + 1

---------------------------------------------------------
HOW addNum() WORKS
---------------------------------------------------------

STEP 1: DECIDE WHERE THE NUMBER BELONGS

If SMALL is empty, insert the number into SMALL.

Otherwise, compare the incoming number with small.peek().

If num <= small.peek():
    Insert into SMALL.

Otherwise:
    Insert into LARGE.

Why?

small.peek() is the largest number of the smaller half.

A number smaller than or equal to this boundary belongs
in the smaller half.

A number larger than this boundary belongs in the
larger half.

STEP 2: BALANCE THE HEAPS

If SMALL has more than one extra element:
    Move small.peek() into LARGE.

If LARGE has more elements than SMALL:
    Move large.peek() into SMALL.

This ensures SMALL always has either equal size or
exactly one extra element.

---------------------------------------------------------
HOW findMedian() WORKS
---------------------------------------------------------

CASE 1: EVEN NUMBER OF ELEMENTS

Both heaps have the same size.

The median is the average of:
- The largest number in SMALL.
- The smallest number in LARGE.

Median = (small.peek() + large.peek()) / 2.0

CASE 2: ODD NUMBER OF ELEMENTS

SMALL contains one extra element.

The median is small.peek().

---------------------------------------------------------
DRY RUN
---------------------------------------------------------

Incoming numbers: 5, 2, 10, 1, 7

Insert 5:
SMALL = [5]
LARGE = []
Median = 5

Insert 2:
2 <= 5, so insert into SMALL.
SMALL = [5, 2]
LARGE = []

SMALL has too many elements.
Move 5 from SMALL to LARGE.

SMALL = [2]
LARGE = [5]
Median = (2 + 5) / 2 = 3.5

Insert 10:
10 > 2, so insert into LARGE.

SMALL = [2]
LARGE = [5, 10]

LARGE is too large.
Move 5 from LARGE to SMALL.

SMALL = [5, 2]
LARGE = [10]
Median = 5

Insert 1:
1 <= 5, so insert into SMALL.

SMALL = [5, 2, 1]
LARGE = [10]

SMALL is too large.
Move 5 from SMALL to LARGE.

SMALL = [2, 1]
LARGE = [5, 10]
Median = (2 + 5) / 2 = 3.5

Insert 7:
7 > 2, so insert into LARGE.

SMALL = [2, 1]
LARGE = [5, 7, 10]

LARGE is too large.
Move 5 from LARGE to SMALL.

SMALL = [5, 2, 1]
LARGE = [7, 10]

Median = 5

---------------------------------------------------------
KEY OBSERVATIONS
---------------------------------------------------------

1. We never need to sort the entire stream.
2. We only need the boundary elements of two halves.
3. Max Heap gives the largest element of the smaller half.
4. Min Heap gives the smallest element of the larger half.
5. Balancing ensures the median is always available at
   the top of the heap(s).
6. SMALL is deliberately allowed to have one extra element.

---------------------------------------------------------
COMPLEXITY ANALYSIS
---------------------------------------------------------

addNum(num):
- Heap insertion: O(log N)
- Heap balancing: O(log N)
- Overall: O(log N)

findMedian():
- Accessing heap tops: O(1)
- Overall: O(1)

Space:
- Both heaps store all inserted numbers.
- Overall: O(N)

*/

class MedianFinder {

    // Max Heap:
    // Stores the smaller half of the numbers.
    // Its top is the largest number in the smaller half.
    PriorityQueue<Integer> small =
            new PriorityQueue<>(Collections.reverseOrder());

    // Min Heap:
    // Stores the larger half of the numbers.
    // Its top is the smallest number in the larger half.
    PriorityQueue<Integer> large =
            new PriorityQueue<>();

    public MedianFinder() {
    }

    public void addNum(int num) {

        // STEP 1: Decide which heap should receive the number.

        // If SMALL is empty, or the number is <= the
        // largest element of the smaller half,
        // insert into SMALL.
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
        }
        else {
            // Otherwise, the number belongs to LARGE.
            large.offer(num);
        }

        // STEP 2: Balance the two heaps.

        // If SMALL has more than one extra element,
        // move its largest element to LARGE.
        if (small.size() > large.size() + 1) {
            large.offer(small.poll());
        }

        // If LARGE has more elements than SMALL,
        // move its smallest element to SMALL.
        else if (large.size() > small.size()) {
            small.offer(large.poll());
        }
    }

    public double findMedian() {

        // CASE 1: Even number of elements.
        // Both heaps have equal sizes.
        // Median is the average of the two boundary values.
        if ((small.size() + large.size()) % 2 == 0) {
            return (double) (small.peek() + large.peek()) / 2.0;
        }

        // CASE 2: Odd number of elements.
        // SMALL contains one extra element.
        // Its top is the median.
        else {
            return (double) small.peek();
        }
    }
}
