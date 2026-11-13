
/*
    Problem Name: Kth Smallest Element in a Matrix
    Pattern: Heap / Priority Queue / K-Way Merge

    PROBLEM:

    We are given a matrix where every row is sorted
    in ascending order.

    We need to find the Kth smallest element.

    EXAMPLE:

    matrix = {
        {1,  5,  9},
        {10, 11, 13},
        {12, 13, 15}
    };

    k = 8;

    Sorted order:

    1, 5, 9, 10, 11, 12, 13, 13, 15

    Answer = 13


    MAIN INTUITION:

    Imagine every row is a separate sorted list.

    Instead of collecting every element and sorting
    everything again, we use a Min Heap.

    A Min Heap always gives us the smallest available
    element.

    Initially, insert the first element of every row.

    Then repeatedly:

    1. Remove the smallest element.
    2. Store its value in result.
    3. Decrease k.
    4. Insert the next element from the same row.
    5. Stop when k becomes zero.

    The last removed element is the Kth smallest.


    CUSTOM CLASS: array

    int row;

    Stores the row number from which the current
    element belongs.

    int cl;

    Stores the current column index.

    int[] arr;

    Stores the reference to the original row.

    int top;

    Stores the current element's actual value.

    WHY DO WE NEED THIS CLASS?

    When we remove an element from the PriorityQueue,
    we need to know which row it came from.

    This helps us find and insert the next element
    from that same row.


    CONSTRUCTOR:

    array(int row, int cl, int[] arr)

    this.row = row;
    Stores the row number.

    this.cl = cl;
    Stores the current column index.

    this.arr = arr;
    Stores the original row reference.

    this.top = arr[cl];
    Gets the current element's value.


    PRIORITY QUEUE:

    PriorityQueue<array> pq = new PriorityQueue<>(...)

    We create a Min Heap based on top.

    Smaller top value gets higher priority.

    If two values are equal, your comparator
    gives priority to the smaller row index.

    This tie-breaking is optional for this problem.


    INITIALIZATION:

    int i = 0;

    We use i to track the current row.

    for(int[] mat : matrix)

    Visit every row.

    pq.offer(new array(i, 0, mat));

    Insert the first element of the current row.

    i++;

    Move to the next row.

    WHY ONLY THE FIRST ELEMENT?

    Because every row is already sorted.

    We don't need to insert all elements initially.

    Once an element is removed, we insert the next
    element from its row.


    RESULT:

    int result = matrix[0][0];

    Stores the answer.

    The initial value is overwritten inside the loop
    for valid k and a non-empty matrix.


    MAIN LOOP:

    while(k > 0)

    We only need to remove K elements.

    array a = pq.poll();

    Remove the smallest available element.

    result = a.top;

    Store the removed element.

    k--;

    One element has been processed.


    NEXT ELEMENT:

    if(a.cl < a.arr.length - 1)

    Check whether another element exists in this row.

    pq.offer(new array(a.row, (a.cl + 1), a.arr));

    Move to the next column in the same row
    and insert that element into the heap.

    WHY cl + 1?

    Because the next element in the same row
    is the next candidate for the smallest value.


    WHY STOP AFTER K ELEMENTS?

    Every poll gives us the next smallest element.

    Therefore, the Kth poll gives us the Kth smallest.

    We do not need to process the remaining elements.


    DRY RUN:

    matrix = {
        {1,  5,  9},
        {10, 11, 13},
        {12, 13, 15}
    };

    k = 8;

    Initial heap: 1, 10, 12

    Poll 1  -> result = 1
    Poll 5  -> result = 5
    Poll 9  -> result = 9
    Poll 10 -> result = 10
    Poll 11 -> result = 11
    Poll 12 -> result = 12
    Poll 13 -> result = 13
    Poll 13 -> result = 13

    K becomes zero.

    Return 13.


    COMPLEXITY ANALYSIS:

    Let:

    R = number of rows.
    K = requested position.

    Heap initialization:

    R insertions, each taking O(log R).

    Processing:

    At most K removals and K insertions.

    Time Complexity:

    O(R + K log R)

    Space Complexity:

    O(R) for the PriorityQueue.

    We maintain at most one active element
    from each row.


    IMPORTANT INTERVIEW OBSERVATIONS:

    1. This is a K-Way Merge problem.
    2. Min Heap gives the next smallest element.
    3. Store row and column to track each element.
    4. Insert only one active element per row.
    5. Stop after K removals instead of processing
       the complete matrix.
    6. This approach assumes each row is sorted
       in ascending order.

*/

class array {
    int row;
    int cl;
    int[] arr;
    int top;

    array(int row, int cl, int[] arr) {
        this.row = row;
        this.cl = cl;
        this.arr = arr;
        this.top = arr[cl];
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {

        PriorityQueue<array> pq = new PriorityQueue<>((a1, a2) -> {
            if (a1.top == a2.top)
                return a1.row - a2.row;
            else
                return a1.top - a2.top;
        });

        int i = 0;

        // Insert the first element of every row.
        for (int[] mat : matrix) {
            pq.offer(new array(i, 0, mat));
            i++;
        }

        // Stores the Kth smallest element.
        int result = matrix[0][0];

        // Process only K elements.
        while (k > 0) {

            // Remove the smallest available element.
            array a = pq.poll();

            // Store its value.
            result = a.top;

            // One element has been processed.
            k--;

            // Check whether the current row has a next element.
            if (a.cl < a.arr.length - 1)

                // Insert the next element from the same row.
                pq.offer(new array(a.row, (a.cl + 1), a.arr));
        }

        return result;
    }
}
