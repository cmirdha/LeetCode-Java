
/*
    Problem Name: Last Stone Weight
    LeetCode Number: 1046
    Difficulty: Easy
    Pattern: Heap / Priority Queue / Max Heap


    PROBLEM:

    We are given an array of stones.

    Each stone has a weight.

    Every time, we select the two heaviest stones
    and smash them together.

    There are two possible cases:

    CASE 1: Both stones have equal weight.

    Example:
    8 and 8

    Both stones are destroyed.

    CASE 2: Both stones have different weights.

    Example:
    8 and 7

    The smaller stone is destroyed.
    The heavier stone survives with the difference.

    Remaining weight = 8 - 7 = 1

    We repeat this process until at most one stone
    remains.

    Return the remaining stone's weight.
    If no stone remains, return 0.


    EXAMPLE:

    stones = [2, 7, 4, 1, 8, 1]

    Output = 0


    MAIN INTUITION:

    We always need the two heaviest stones.

    Instead of sorting the array repeatedly,
    we use a Max Heap.

    A Max Heap gives us the largest element first.

    Every iteration:

    1. Remove the largest stone.
    2. Remove the second-largest stone.
    3. Compare their weights.
    4. If equal, destroy both.
    5. Otherwise, insert their difference.
    6. Continue until at most one stone remains.


    STEP 1: CREATE A MAX HEAP

    PriorityQueue<Integer> q =
        new PriorityQueue<>(Collections.reverseOrder());

    Normally, Java PriorityQueue creates a Min Heap.

    Collections.reverseOrder() reverses the ordering.

    Now the largest element comes out first.

    IMPORTANT:

    q.poll() = removes and returns the largest element.
    q.peek() = returns the largest element without removing.
    q.offer(x) = inserts x into the heap.


    STEP 2: INSERT ALL STONES

    for (int i = 0; i < stones.length; i++) {
        q.offer(stones[i]);
    }

    We insert every stone into the Max Heap.

    After insertion, the largest stone is available
    at the top of the heap.


    STEP 3: MAIN LOOP

    while (q.size() > 1)

    Why greater than 1?

    Because we need at least two stones to smash.

    If only one stone remains, we cannot perform
    another operation.

    If no stones remain, the loop also stops.


    STEP 4: REMOVE THE TWO HEAVIEST STONES

    int max1 = q.poll();
    int max2 = q.poll();

    Since we are using a Max Heap:

    max1 = largest stone.
    max2 = second-largest stone.

    We remove both because they are being smashed.


    STEP 5: CHECK WHETHER THEY ARE EQUAL

    if (max1 == max2) continue;

    If both stones have equal weight,
    they destroy each other.

    Example:

    max1 = 8
    max2 = 8

    Both stones disappear.

    continue skips the remaining statements
    and starts the next loop iteration.

    We do not insert anything back into the heap.


    STEP 6: INSERT THE REMAINING WEIGHT

    q.offer(max1 - max2);

    This statement executes only when the two
    stones have different weights.

    Example:

    max1 = 8
    max2 = 7

    Remaining weight = 8 - 7 = 1

    We insert the new stone into the Max Heap.

    The heap automatically maintains its ordering.


    STEP 7: RETURN THE FINAL ANSWER

    return q.isEmpty() ? 0 : q.peek();

    If the heap is empty:

    No stone remains, so return 0.

    Otherwise:

    One stone remains, so return its weight.

    We use peek() because we only need to read
    the remaining stone, not remove it.


    COMPLETE DRY RUN:

    Input:

    stones = [2, 7, 4, 1, 8, 1]

    Initially, the Max Heap contains all stones.

    ROUND 1:

    max1 = 8
    max2 = 7

    They are different.

    8 - 7 = 1

    Insert 1.

    ROUND 2:

    max1 = 4
    max2 = 2

    They are different.

    4 - 2 = 2

    Insert 2.

    ROUND 3:

    max1 = 2
    max2 = 1

    They are different.

    2 - 1 = 1

    Insert 1.

    ROUND 4:

    max1 = 1
    max2 = 1

    They are equal.

    Both are destroyed.

    Heap becomes empty.

    Final answer = 0.


    COMPLEXITY ANALYSIS:

    Let N = number of stones.

    TIME COMPLEXITY:

    Building the heap using N insertions:

    O(N log N)

    Each iteration removes two elements
    and may insert one new element.

    There can be at most N - 1 iterations.

    Each heap operation takes O(log N).

    Overall Time Complexity:

    O(N log N)


    SPACE COMPLEXITY:

    O(N)

    The PriorityQueue can store at most N stones.


    IMPORTANT INTERVIEW OBSERVATIONS:

    1. Repeatedly finding the largest two elements
       is a strong indication to consider Max Heap.

    2. Collections.reverseOrder() converts the
       default Min Heap ordering into Max Heap ordering.

    3. We use poll() twice to get the two largest stones.

    4. Equal stones disappear, so we use continue.

    5. Different stones produce a new stone with
       weight max1 - max2.

    6. The loop condition q.size() > 1 ensures
       that two stones are available before smashing.

    7. This problem is a good introduction to
       PriorityQueue in Java.
*/


class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> q =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < stones.length; i++) {
            q.offer(stones[i]);
        }

        while (q.size() > 1) {

            // Remove the two heaviest stones.
            int max1 = q.poll();
            int max2 = q.poll();

            // Equal stones destroy each other.
            if (max1 == max2) continue;

            // Insert the remaining weight.
            q.offer(max1 - max2);
        }

        // Return 0 if no stone remains.
        // Otherwise, return the remaining stone's weight.
        return q.isEmpty() ? 0 : q.peek();
    }
}
