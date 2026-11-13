
/*
    Problem Name: Merge K Sorted Arrays
    Pattern: Heap / Priority Queue / K-Way Merge

    PROBLEM:
    We are given multiple sorted arrays.
    We need to merge all of them into one sorted ArrayList.

    EXAMPLE:

    mat = {
        {1, 4, 7},
        {2, 5, 8},
        {3, 6, 9}
    };

    Output:
    [1, 2, 3, 4, 5, 6, 7, 8, 9]


    APPROACH: MIN HEAP

    Instead of combining everything and sorting again,
    we use a PriorityQueue.

    The PriorityQueue always gives us the smallest
    currently available element.

    We initially insert only the first element
    from every array.

    Whenever we remove an element:
    1. Add it to the result.
    2. Find the next element in the same array.
    3. Insert that next element into the PriorityQueue.
    4. Continue until the PriorityQueue becomes empty.


    CUSTOM CLASS: matrix

    Each object represents one active element.

    row = identifies which array we are using.
    cl  = identifies the current index.
    top = stores the current element's value.
    arr = stores the reference to the original array.

    WHY DO WE NEED THESE VARIABLES?

    After removing the smallest element, we must know
    which array it came from and where its next element is.


    CONSTRUCTOR:

    matrix(int row, int cl, int[] arr)

    this.row = row;
    Stores the array number.

    this.cl = cl;
    Stores the current element's index.

    this.top = arr[cl];
    Gets the actual value at that index.

    this.arr = arr;
    Keeps the original array reference.


    PRIORITY QUEUE:

    PriorityQueue<matrix> pq =
        new PriorityQueue<>((a1,a2)->a1.top-a2.top);

    This creates a Min Heap.

    The smallest top value comes out first.


    INITIALIZATION:

    int total = 0;

    We calculate the total number of elements.
    However, total is not used anywhere else in this code.

    for(int[] arr : mat)

    We visit every array.

    pq.offer(new matrix(i, 0, arr));

    Insert the first element of every array.

    i++ moves to the next row.


    MAIN LOOP:

    while(!pq.isEmpty())

    Continue until all elements have been processed.

    matrix m1 = pq.poll();

    Remove the smallest available element.

    list.add(m1.top);

    Add that element to the final result.


    NEXT ELEMENT:

    if(m1.cl < m1.arr.length - 1)

    Check whether another element exists in the same array.

    pq.offer(new matrix(m1.row, m1.cl + 1, m1.arr));

    Move one position forward and insert the next element.

    WHY cl + 1?

    Because every individual array is already sorted.
    Therefore, its next element is the next candidate
    that can be added to the result.


    COMPLEXITY ANALYSIS:

    Let:
    K = number of arrays.
    N = total number of elements across all arrays.

    Time Complexity:
    O(N log K)

    Each element is inserted and removed from a heap
    containing at most K elements.

    Space Complexity:
    O(K) auxiliary space for the PriorityQueue.

    O(N) additional space for the output ArrayList.


    IMPORTANT INTERVIEW OBSERVATIONS:

    1. This is a classic K-Way Merge problem.
    2. PriorityQueue helps efficiently find the smallest
       available element.
    3. We store row and column to track each array's position.
    4. We insert only one active element per array.
    5. This approach is more efficient than sorting all
       N elements again, which takes O(N log N).

*/

class matrix {
    int row;
    int cl;
    int top;
    int[] arr;

    matrix(int row, int cl, int[] arr) {
        this.row = row;
        this.cl = cl;
        this.top = arr[cl];
        this.arr = arr;
    }
}

class Solution {
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        // code here

        int total = 0;

        PriorityQueue<matrix> pq =
                new PriorityQueue<>((a1, a2) -> a1.top - a2.top);

        int i = 0;

        // Insert the first element of every array.
        for (int[] arr : mat) {
            total += arr.length;

            pq.offer(new matrix(i, 0, arr));

            i++;
        }

        ArrayList<Integer> list = new ArrayList<>();

        // Process elements in increasing order.
        while (!pq.isEmpty()) {

            // Remove the smallest element.
            matrix m1 = pq.poll();

            // Add it to the final result.
            list.add(m1.top);

            // Check whether the current array has another element.
            if (m1.cl < m1.arr.length - 1) {

                // Insert the next element from the same array.
                pq.offer(new matrix(m1.row, m1.cl + 1, m1.arr));
            }
        }

        return list;
    }
}
