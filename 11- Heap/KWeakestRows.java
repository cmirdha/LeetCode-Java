
/*
================================================================
PROBLEM NAME: The K Weakest Rows in a Matrix
LEETCODE NUMBER: 1337
DIFFICULTY: Easy
PATTERN: Heap / PriorityQueue / Custom Comparator
================================================================


1. UNDERSTAND THE PROBLEM (EXPLAIN LIKE I'M 10 YEARS OLD)
----------------------------------------------------------------

Imagine there are multiple classrooms in a school.

Each classroom has soldiers and civilians.

In the matrix:

    1 = Soldier
    0 = Civilian

The soldiers always appear before civilians.

For example:

    Row 0: [1, 1, 0, 0]
    Row 1: [1, 1, 1, 1]
    Row 2: [1, 0, 0, 0]
    Row 3: [1, 1, 0, 0]
    Row 4: [1, 1, 1, 0]

The classroom with fewer soldiers is considered weaker.

Our task is to find the K weakest rows.

Example:

    k = 3

We need to return the indices of the 3 weakest rows.

IMPORTANT:

There is one additional condition.

If two rows have the same number of soldiers,
the row with the smaller index is considered weaker.

For example:

    Row 0 has 2 soldiers.
    Row 3 has 2 soldiers.

Both have the same strength.

But Row 0 comes first because its index is smaller.

This is the main condition we must remember.


2. UNDERSTAND THE EXAMPLE
----------------------------------------------------------------

INPUT:

    mat = [
        [1, 1, 0, 0],
        [1, 1, 1, 1],
        [1, 0, 0, 0],
        [1, 1, 0, 0],
        [1, 1, 1, 0]
    ]

    k = 3


First, count the soldiers in every row.

    Row 0 = 2 soldiers
    Row 1 = 4 soldiers
    Row 2 = 1 soldier
    Row 3 = 2 soldiers
    Row 4 = 3 soldiers


Now arrange them from weakest to strongest.

    Row 2 = 1 soldier
    Row 0 = 2 soldiers
    Row 3 = 2 soldiers
    Row 4 = 3 soldiers
    Row 1 = 4 soldiers


Why does Row 0 come before Row 3?

Because both have 2 soldiers.

When soldier counts are equal, the smaller row index comes first.


Since k = 3:

    Answer = [2, 0, 3]


3. IDENTIFY THE PATTERN
----------------------------------------------------------------

Which data structure should we use?

We need to find the K weakest rows.

We could sort all rows.

But another useful approach is:

    PRIORITY QUEUE (MIN HEAP)


WHY PRIORITY QUEUE?

Imagine putting multiple objects inside a box.

Whenever we remove an object, we want the weakest row
to come out first.

PriorityQueue helps us retrieve elements according
to their priority.

We need two ordering conditions:

    1. Fewer soldiers should come first.

    2. If soldiers are equal, smaller row index
       should come first.


This is exactly what a MIN HEAP with a custom comparator
can achieve.


4. UNDERSTAND WHAT INFORMATION WE NEED TO STORE
----------------------------------------------------------------

We need to compare rows based on two values:

    1. Number of soldiers.
    2. Original row index.


We can create a custom class called pair.

    class pair {
        int pos;
        int sum;
        int[] arr;
    }


Understand each variable:

    pos = Original row index.

    sum = Total number of soldiers in that row.

    arr = Original row array.


WHY DO WE NEED pos?

Because the question asks us to return row indices.

For example:

    Row 2 has 1 soldier.

We must return 2, not 1.


WHY DO WE NEED sum?

Because the row with fewer soldiers is weaker.

Example:

    [1, 1, 0, 0]

    sum = 1 + 1 + 0 + 0

    sum = 2


WHY DO WE NEED arr?

It stores the original row.

However, after calculating sum, we don't actually
use arr anywhere else in this implementation.

It is optional.


5. UNDERSTAND HOW TO COUNT SOLDIERS
----------------------------------------------------------------

Consider:

    arr = [1, 1, 0, 0]

We need to calculate the total number of soldiers.

We can use a simple for-each loop.

    int total = 0;

    for(int i : arr) {
        total += i;
    }

DRY RUN:

    Initially:

        total = 0

    First element:

        total = 0 + 1 = 1

    Second element:

        total = 1 + 1 = 2

    Third element:

        total = 2 + 0 = 2

    Fourth element:

        total = 2 + 0 = 2


Finally:

    total = 2


We store this value inside the sum variable.


6. UNDERSTAND THE CONSTRUCTOR
----------------------------------------------------------------

The constructor initializes our custom object.

    pair(int pos, int[] arr) {

        this.pos = pos;
        this.arr = arr;

        int total = 0;

        for(int i : arr) {
            total += i;
        }

        this.sum = total;
    }


WHAT HAPPENS WHEN WE CREATE AN OBJECT?

Suppose we write:

    new pair(0, new int[]{1, 1, 0, 0});


The constructor receives:

    pos = 0

    arr = [1, 1, 0, 0]


It calculates:

    sum = 2


The final object contains:

    pos = 0
    sum = 2
    arr = [1, 1, 0, 0]


So every row gets converted into an object
containing its index and soldier count.


7. UNDERSTAND THE CUSTOM COMPARATOR
----------------------------------------------------------------

THIS IS THE MOST IMPORTANT PART OF THE PROBLEM.

We need to tell PriorityQueue how to compare
two pair objects.

Our ordering rules are:

    RULE 1:

        Fewer soldiers = Higher priority.

    RULE 2:

        If soldiers are equal,
        smaller row index = Higher priority.


We implement these rules using a comparator.


CASE 1: SOLDIER COUNTS ARE DIFFERENT

    return p1.sum - p2.sum;


Example:

    p1.sum = 2
    p2.sum = 4

    2 - 4 = -2


Negative result means p1 gets higher priority.

Therefore:

    Row with 2 soldiers comes before
    Row with 4 soldiers.


CASE 2: SOLDIER COUNTS ARE EQUAL

    return p1.pos - p2.pos;


Example:

    p1.pos = 0
    p2.pos = 3

    0 - 3 = -3


Negative result means p1 gets higher priority.

Therefore:

    Row 0 comes before Row 3.


FINAL COMPARATOR LOGIC:

    First compare soldier counts.

    If soldier counts are equal,
    compare row indices.


REMEMBER:

    Smaller sum = First priority.

    Smaller position = First priority
    when sums are equal.


8. UNDERSTAND INSERTING OBJECTS INTO PRIORITY QUEUE
----------------------------------------------------------------

We have:

    PriorityQueue<pair> pq = new PriorityQueue<>(comparator);


Now we need to insert every row into the heap.


    int i = 0;

    for(int[] a : mat) {

        pq.add(new pair(i++, a));

    }


UNDERSTAND THE FOR-EACH LOOP:

    for(int[] a : mat)


This visits every row in the matrix.

Initially:

    i = 0


FIRST ITERATION:

    a = [1, 1, 0, 0]

    new pair(0, a)

    pos = 0
    sum = 2


SECOND ITERATION:

    a = [1, 1, 1, 1]

    new pair(1, a)

    pos = 1
    sum = 4


THIRD ITERATION:

    a = [1, 0, 0, 0]

    new pair(2, a)

    pos = 2
    sum = 1


FOURTH ITERATION:

    a = [1, 1, 0, 0]

    new pair(3, a)

    pos = 3
    sum = 2


FIFTH ITERATION:

    a = [1, 1, 1, 0]

    new pair(4, a)

    pos = 4
    sum = 3


Now all five objects are inserted into PriorityQueue.


9. UNDERSTAND EXTRACTING THE K WEAKEST ROWS
----------------------------------------------------------------

We have:

    int[] result = new int[k];


WHY?

Because we need to return exactly K row indices.

If k = 3:

    result = [0, 0, 0]


Next:

    int j = 0;

    while(j < k) {

        result[j++] = pq.poll().pos;

    }


UNDERSTAND poll():

    pq.poll()

Removes and returns the highest-priority object.

Since our comparator creates a Min Heap,
the weakest row comes out first.


UNDERSTAND .pos:

    pq.poll().pos


We don't need the entire object.

We only need its original row index.

Therefore, we access pos.


WHY j++?

    result[j++] = pq.poll().pos;


This means:

    First store the value at result[j].

    Then increment j.


Example:

    Initially:

        j = 0

    First poll:

        result[0] = 2
        j = 1

    Second poll:

        result[1] = 0
        j = 2

    Third poll:

        result[2] = 3
        j = 3


The loop stops because:

    j < k

    3 < 3 = false


Final answer:

    [2, 0, 3]


10. COMPLETE DRY RUN
----------------------------------------------------------------

INPUT:

    mat = [
        [1, 1, 0, 0],
        [1, 1, 1, 1],
        [1, 0, 0, 0],
        [1, 1, 0, 0],
        [1, 1, 1, 0]
    ]

    k = 3


STEP 1: CALCULATE SOLDIER COUNTS

    Row 0 = 2
    Row 1 = 4
    Row 2 = 1
    Row 3 = 2
    Row 4 = 3


STEP 2: CREATE OBJECTS

    (pos=0, sum=2)

    (pos=1, sum=4)

    (pos=2, sum=1)

    (pos=3, sum=2)

    (pos=4, sum=3)


STEP 3: PRIORITY QUEUE ORDER

    (pos=2, sum=1)

    (pos=0, sum=2)

    (pos=3, sum=2)

    (pos=4, sum=3)

    (pos=1, sum=4)


This is the priority order, not necessarily
the internal array representation of the heap.


STEP 4: POLL FIRST ELEMENT

    pq.poll()

    Returns (pos=2, sum=1)

    result[0] = 2


STEP 5: POLL SECOND ELEMENT

    pq.poll()

    Returns (pos=0, sum=2)

    result[1] = 0


STEP 6: POLL THIRD ELEMENT

    pq.poll()

    Returns (pos=3, sum=2)

    result[2] = 3


FINAL RESULT:

    [2, 0, 3]


11. DRY RUN SUMMARY TABLE
----------------------------------------------------------------

    Row | Soldiers | Original Index
    --------------------------------
     0  |    2     |       0
     1  |    4     |       1
     2  |    1     |       2
     3  |    2     |       3
     4  |    3     |       4


    Priority Order:

    Position | Soldiers
    --------------------
       2     |    1
       0     |    2
       3     |    2
       4     |    3
       1     |    4


    Extract first K = 3:

    [2, 0, 3]


12. WHY PRIORITY QUEUE INSTEAD OF SORTING?
----------------------------------------------------------------

There are two valid approaches.

APPROACH 1: SORTING

    1. Count soldiers in every row.
    2. Store row index and soldier count.
    3. Sort using soldier count and row index.
    4. Return the first K indices.


APPROACH 2: PRIORITY QUEUE

    1. Count soldiers in every row.
    2. Create an object for every row.
    3. Insert objects into PriorityQueue.
    4. Poll the first K elements.


Both approaches work.

PriorityQueue is particularly useful when we need
to retrieve the K smallest or K largest elements
according to some condition.


13. COMPLEXITY ANALYSIS
----------------------------------------------------------------

Let:

    m = Number of rows.

    n = Number of columns.

    k = Number of weakest rows required.


TIME COMPLEXITY:

1. Counting soldiers:

    Every row contains n elements.

    There are m rows.

    O(m * n)


2. Inserting all objects into PriorityQueue:

    Each insertion takes O(log m).

    There are m insertions.

    O(m log m)


3. Extracting K elements:

    Each poll takes O(log m).

    We poll k times.

    O(k log m)


TOTAL TIME COMPLEXITY:

    O(m*n + m*log(m) + k*log(m))


SPACE COMPLEXITY:

    PriorityQueue stores m objects.

    Result array stores k elements.

    O(m + k)


14. IMPORTANT INTERVIEW OBSERVATIONS
----------------------------------------------------------------

1. This problem uses PriorityQueue / Min Heap.

2. A custom class allows us to store multiple
   values together.

3. A custom comparator defines the priority.

4. Fewer soldiers means a weaker row.

5. Equal soldier counts require index-based
   tie-breaking.

6. poll() removes the highest-priority element.

7. We only need to poll K times.

8. Sorting is another valid approach.

9. The heap does not need to be completely sorted
   internally. It only guarantees that poll()
   returns the highest-priority element.


15. COMMON MISTAKES TO AVOID
----------------------------------------------------------------

MISTAKE 1:

    Comparing only soldier counts.

WHY WRONG?

    Two rows can have the same number of soldiers.

    We must also compare their indices.


MISTAKE 2:

    Returning sum instead of pos.

WHY WRONG?

    The question asks for row indices,
    not soldier counts.


MISTAKE 3:

    Using a Max Heap instead of a Min Heap.

WHY WRONG?

    We need the weakest rows first.

    Fewer soldiers must have higher priority.


MISTAKE 4:

    Polling all elements unnecessarily.

WHY?

    We only need K weakest rows.

    So we only need K poll operations.


16. FINAL MEMORY TRICK
----------------------------------------------------------------

Whenever you see a problem asking for:

    K smallest elements.

    K weakest elements.

    K elements based on custom conditions.

Think about:

    PRIORITY QUEUE


For this problem, remember:

    FIRST PRIORITY:

        Smaller soldier count.


    SECOND PRIORITY:

        Smaller row index.


    INSERT:

        Add every row as an object.


    EXTRACT:

        Poll K times.


    ANSWER:

        Store the original row indices.


================================================================
JAVA IMPLEMENTATION
================================================================

*/

class pair {
    int pos;
    int sum;
    int[] arr;

    pair(int pos, int[] arr) {
        this.pos = pos;
        this.arr = arr;

        int total = 0;

        for (int i : arr) {
            total += i;
        }

        this.sum = total;
    }
}

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {

        PriorityQueue<pair> pq = new PriorityQueue<>((p1, p2) -> {

            if (p1.sum == p2.sum)
                return p1.pos - p2.pos;

            else
                return p1.sum - p2.sum;
        });

        int i = 0;

        for (int[] a : mat) {
            pq.add(new pair(i++, a));
        }

        int[] result = new int[k];

        int j = 0;

        while (j < k) {
            result[j++] = pq.poll().pos;
        }

        return result;
    }
}

