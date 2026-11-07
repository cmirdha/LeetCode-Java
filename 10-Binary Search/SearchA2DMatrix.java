
/*
================================================================
PROBLEM NAME: Search a 2D Matrix
LEETCODE NUMBER: 74
DIFFICULTY: Medium
PATTERN: Binary Search + 2D to 1D Index Conversion
================================================================


1. UNDERSTAND THE PROBLEM (EXPLAIN LIKE I'M 10 YEARS OLD)
----------------------------------------------------------------

Imagine you have a classroom with students sitting in rows
and columns.

Each student has a number.

The numbers are arranged in increasing order:

    From left to right in every row.

    The first number of every new row is greater than
    the last number of the previous row.

Example:

    matrix = [
        [ 1,  3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 60]
    ]

Suppose someone asks:

    Is 16 present in this matrix?

We could check every element one by one.

But that would be slow.

Look carefully:

    Row 1: 1, 3, 5, 7
    Row 2: 10, 11, 16, 20
    Row 3: 23, 30, 34, 60

The entire matrix is sorted.

Imagine removing the row boundaries:

    [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]

Now it behaves exactly like a normal sorted 1D array!

We can use Binary Search.

BUT:

We don't actually create another 1D array.

Instead, we calculate the original row and column
whenever Binary Search gives us a virtual 1D index.

That is the main trick in this problem.


2. UNDERSTAND THE MATRIX DIMENSIONS
----------------------------------------------------------------

    int m = matrix.length;
    int n = matrix[0].length;

Here:

    m = Number of rows.
    n = Number of columns.

Example:

    matrix = [
        [1, 3, 5, 7],
        [10, 11, 16, 20],
        [23, 30, 34, 60]
    ]

    m = 3
    n = 4

Total elements:

    m * n = 3 * 4 = 12

The virtual 1D array would have 12 elements.


3. UNDERSTAND THE VIRTUAL 1D INDEXING
----------------------------------------------------------------

Imagine numbering every matrix element from left to right,
then moving to the next row.

Original matrix:

    [
        [ 1,  3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 60]
    ]


Virtual 1D indexing:

    [
        [ 1,  3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 60]
    ]

    [
        [ 0,  1,  2,  3],
        [ 4,  5,  6,  7],
        [ 8,  9, 10, 11]
    ]

For example:

    Index 0  -> Value 1
    Index 1  -> Value 3
    Index 2  -> Value 5
    Index 3  -> Value 7
    Index 4  -> Value 10
    Index 5  -> Value 11
    Index 6  -> Value 16
    Index 7  -> Value 20
    Index 8  -> Value 23
    Index 9  -> Value 30
    Index 10 -> Value 34
    Index 11 -> Value 60

We can now perform Binary Search between index 0 and 11.


4. UNDERSTAND LOW AND HIGH
----------------------------------------------------------------

    int low = 0;
    int high = (m*n)-1;

Why low = 0?

Because the first element of our virtual 1D array
has index 0.

Why high = (m*n)-1?

Because there are m*n total elements.

For 12 elements:

    Valid indices = 0 to 11

Therefore:

    low = 0
    high = 11

IMPORTANT:

Array indexing starts from ZERO.

So the last index is always:

    Total elements - 1


5. UNDERSTAND THE MIDPOINT
----------------------------------------------------------------

    int mid = (low+high)/2;

Binary Search finds the middle INDEX.

Remember:

    mid is NOT the actual matrix value.

    mid represents a virtual 1D index.

For example:

    mid = 6

This means we want to access the element at virtual
index 6.

But our original data is still stored in a 2D matrix.

So we need to convert index 6 into its row and column.


6. MOST IMPORTANT CONCEPT:
   CONVERT 1D INDEX INTO 2D ROW AND COLUMN
----------------------------------------------------------------

We have:

    int row = mid / n;
    int col = mid % n;

Why division?

    row = mid / numberOfColumns

Why?

Every complete group of n elements represents one row.

Our matrix has 4 columns.

Virtual indices:

    0, 1, 2, 3   -> Row 0
    4, 5, 6, 7   -> Row 1
    8, 9, 10, 11 -> Row 2

Let's calculate:

    mid = 5

    row = 5 / 4
        = 1

Integer division removes the decimal part.

Therefore, row = 1.


Why remainder?

    col = mid % numberOfColumns

The remainder tells us the position inside that row.

    mid = 5

    col = 5 % 4
        = 1

Therefore:

    row = 1
    col = 1

Access:

    matrix[1][1] = 11


ANOTHER EXAMPLE:

    mid = 6

    row = 6 / 4 = 1
    col = 6 % 4 = 2

Access:

    matrix[1][2] = 16


REMEMBER THESE TWO FORMULAS:

    row = mid / n

    col = mid % n

Where n is the NUMBER OF COLUMNS.

This is how we search a 2D matrix using Binary Search
without creating a separate 1D array.


7. UNDERSTAND THE BINARY SEARCH COMPARISON
----------------------------------------------------------------

After converting mid into row and column,
we can access the actual matrix value:

    matrix[row][col]


CASE 1: TARGET FOUND

    if(matrix[row][col] == target)

If the current matrix value equals the target,
we have found the number.

Return:

    true


CASE 2: CURRENT VALUE IS GREATER THAN TARGET

    if(matrix[row][col] > target)

Example:

    Current value = 23
    Target = 16

Since the matrix is sorted, every element after 23
in virtual 1D order will also be greater than 23.

So the target cannot be on the right.

Search LEFT:

    high = mid - 1;


CASE 3: CURRENT VALUE IS SMALLER THAN TARGET

    else

Example:

    Current value = 11
    Target = 16

Since the matrix is sorted, everything before 11
is also smaller than 16.

So the target cannot be on the left.

Search RIGHT:

    low = mid + 1;


8. COMPLETE DRY RUN
----------------------------------------------------------------

INPUT:

    matrix = [
        [ 1,  3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 60]
    ]

    target = 16

INITIAL VALUES:

    m = 3
    n = 4

    low = 0
    high = (3 * 4) - 1

    high = 11


ITERATION 1:

    mid = (0 + 11) / 2
        = 5

Convert virtual index 5:

    row = 5 / 4 = 1
    col = 5 % 4 = 1

Access:

    matrix[1][1] = 11

Compare:

    11 < 16

Target is greater.

Search RIGHT:

    low = mid + 1

    low = 6


ITERATION 2:

    low = 6
    high = 11

    mid = (6 + 11) / 2
        = 8

Convert virtual index 8:

    row = 8 / 4 = 2
    col = 8 % 4 = 0

Access:

    matrix[2][0] = 23

Compare:

    23 > 16

Target is smaller.

Search LEFT:

    high = mid - 1

    high = 7


ITERATION 3:

    low = 6
    high = 7

    mid = (6 + 7) / 2
        = 6

Convert virtual index 6:

    row = 6 / 4 = 1
    col = 6 % 4 = 2

Access:

    matrix[1][2] = 16

Compare:

    16 == 16

TARGET FOUND!

Return:

    true


9. DRY RUN SUMMARY TABLE
----------------------------------------------------------------

    Iteration | low | high | mid | row | col | value
    ------------------------------------------------
       1      |  0  |  11  |  5  |  1  |  1  |  11
       2      |  6  |  11  |  8  |  2  |  0  |  23
       3      |  6  |   7  |  6  |  1  |  2  |  16

    Target = 16

    Found at matrix[1][2].

    Return true.


10. WHAT IF THE TARGET DOES NOT EXIST?
----------------------------------------------------------------

Suppose:

    target = 13

Binary Search keeps reducing the search range.

Eventually:

    low > high

This means there are no more elements to check.

Therefore:

    return false;


11. WHY IS THIS BETTER THAN NORMAL SEARCH?
----------------------------------------------------------------

Normal linear search:

    We may have to check every element.

    Time Complexity = O(m * n)


Our Binary Search:

    We check only the middle element in each iteration.

    Time Complexity = O(log(m * n))


We do not create a new 1D array.

    Space Complexity = O(1)


12. COMPLEXITY ANALYSIS
----------------------------------------------------------------

Let:

    m = Number of rows
    n = Number of columns

TOTAL ELEMENTS:

    m * n

TIME COMPLEXITY:

    O(log(m * n))

SPACE COMPLEXITY:

    O(1)

Why O(1)?

    We only use a few variables:

        m, n, low, high, mid, row, col

    We do not create another array.

    We do not copy the matrix.

    We directly access the original matrix.


13. IMPORTANT INTERVIEW OBSERVATIONS
----------------------------------------------------------------

1. The matrix must be sorted row-wise.

2. The first element of each row must be greater
   than the last element of the previous row.

3. We are treating the 2D matrix as a virtual 1D array.

4. We are NOT actually converting or flattening it.

5. Division gives the row.

6. Modulo gives the column.

7. Number of columns, not number of rows, is used
   in both conversion formulas.

8. This solution is O(log(m*n)), not O(m*n).


FINAL MEMORY TRICK:

    1D INDEX -> 2D POSITION

    row = mid / numberOfColumns

    col = mid % numberOfColumns

    DIVISION = ROW
    MODULO   = COLUMN


================================================================
JAVA IMPLEMENTATION
================================================================

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // Number of rows in the matrix.
        int m = matrix.length;

        // Number of columns in the matrix.
        int n = matrix[0].length;

        // Treat the complete matrix as one virtual 1D array.
        // First virtual index = 0.
        int low = 0;

        // Last virtual index = total elements - 1.
        int high =(m*n)-1;

        // Normal Binary Search on the virtual 1D array.
        while (low<=high){

            // Find the middle virtual index.
            int mid = (low+high)/2;

            // Convert the virtual 1D index into a matrix row.
            // Division by number of columns gives the row.
            int row = mid / n;

            // Convert the virtual 1D index into a matrix column.
            // Remainder gives the position inside that row.
            int col = mid % n;

            // If the element equals target, we found it.
            if(matrix[row][col]==target)
                return true;

            // Current value is greater than target.
            // Because the matrix is sorted, search the left half.
            if(matrix[row][col]>target)
                high = mid-1;

                // Current value is smaller than target.
                // Search the right half.
            else
                low = mid+1;

        }

        // The search range is empty.
        // Target does not exist in the matrix.
        return false;
    }
}

