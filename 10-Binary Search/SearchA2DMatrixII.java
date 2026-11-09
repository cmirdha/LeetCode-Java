/*
================================================================
PROBLEM NAME: Search a 2D Matrix
LEETCODE NUMBER: Depends on the exact problem statement
DIFFICULTY: Depends on the exact problem statement
PATTERN: Binary Search on Each Sorted Column
================================================================

1. UNDERSTAND THE PROBLEM (EXPLAIN LIKE I'M 10 YEARS OLD)
----------------------------------------------------------------

We are given a grid of numbers and a target number.

We need to find out whether the target appears anywhere
in the grid.

This solution assumes that each column is sorted from
top to bottom in increasing order.

Example:

 matrix = [
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
 ]

The columns are sorted:

 Column 0: 1, 2, 3
 Column 1: 4, 5, 6
 Column 2: 7, 8, 9

If target = 6, the answer is true because 6 appears
at matrix[2][1].

REAL-LIFE ANALOGY

Imagine each column is a shelf of books. The book numbers
increase from the top shelf to the bottom shelf.

To find a book, we can use Binary Search on one column
instead of checking every book on that column one by one.

This code checks columns from right to left.

2. IMPORTANT MATRIX CONDITION
----------------------------------------------------------------

Every column must be sorted from top to bottom.

For example, this matrix satisfies the condition:

 [
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
 ]

Each column increases as we move downward.

Binary Search depends on this ordering. If a column is
not sorted, the code cannot reliably decide which half
of that column to search next.

The code also assumes the matrix is non-empty, because it
accesses matrix[0].

3. WHY THIS APPROACH WORKS
----------------------------------------------------------------

The code searches one column at a time.

For each column:

 1. It checks the top value.
 2. If that value is greater than target, the whole
 column can be skipped.
 3. Otherwise, it uses Binary Search to search the
 column from top to bottom.

Because each column is sorted, Binary Search can eliminate
about half of the remaining rows after each comparison.

If the matrix has R rows and C columns:

 Searching one column takes O(log R).
 There can be up to C columns.

Therefore:

 Time Complexity = O(C * log R)

4. UNDERSTAND THE VARIABLES
----------------------------------------------------------------

 int rows = matrix.length - 1;

This stores the index of the last row.

For a matrix with 3 rows, the row indices are 0, 1, and 2,
so rows equals 2.

In this code, rows is declared but never used.

 int cols = matrix[0].length - 1;

This stores the index of the last column.

For a matrix with 3 columns, the column indices are 0, 1,
and 2, so cols equals 2.

 int r = 0;

This is the first row used by Binary Search.

The search starts at row 0 and r remains 0 throughout
the code.

 int c = cols;

This stores the current column.

It starts at the rightmost column. After a column is skipped
or searched, c-- moves to the column on its left.

 int low = r;

This is the first row still being considered by Binary Search.
Initially, it is 0.

 int high = matrix.length - 1;

This is the last row still being considered by Binary Search.

For a matrix with 3 rows:

 high = 3 - 1
 = 2

 int midrow = (low + high) / 2;

This is the middle row of the remaining search range.

The code checks:

 matrix[midrow][c]

The row changes during Binary Search. The column stays fixed
until that column's search finishes.

5. UNDERSTAND THE OUTER LOOP
----------------------------------------------------------------

 while (c >= 0)

This loop continues while c is a valid column index.

Columns are checked from right to left:

 last column, then the column to its left, and so on.

When c becomes -1, there are no columns left to check,
so the loop ends.

6. UNDERSTAND THE TOP-VALUE CHECK
----------------------------------------------------------------

 if (matrix[0][c] > target)

This checks the top value in the current column.

Because the column is sorted in increasing order, every value
below the top value is at least as large as the top value.

So if the top value is already greater than target, every
value in the column is also greater than target.

The target cannot be in this column, so the code moves left:

 c--;

If the top value is not greater than target, the target might
appear farther down the column. The code then searches that
column using Binary Search.

7. UNDERSTAND BINARY SEARCH
----------------------------------------------------------------

 while (low <= high)

This loop continues while there are still rows to search.

If low becomes greater than high, the search range is empty
and the target is not in this column.

CASE 1: TARGET FOUND

 if (matrix[midrow][c] == target)

The value at the middle row and current column equals target.

The code returns true immediately because the target has
been found.

CASE 2: MIDDLE VALUE IS TOO LARGE

 if (matrix[midrow][c] > target)

The middle value is greater than target.

Since the column is sorted downward, the rows at midrow
and below contain values at least as large as this middle
value. They cannot contain target.

The code searches the rows above midrow:

 high = midrow - 1;

CASE 3: MIDDLE VALUE IS TOO SMALL

 else

The middle value is less than target.

Since the column is sorted, the rows at midrow and above
contain values no larger than this middle value. They cannot
contain target.

The code searches the rows below midrow:

 low = midrow + 1;

8. WHY THE CODE MOVES TO THE NEXT COLUMN
----------------------------------------------------------------

If the inner Binary Search finishes without returning true,
the target was not found in the current column.

The code then moves one column left:

 c--;

The outer loop continues with the next column.

9. COMPLETE DRY RUN
----------------------------------------------------------------

INPUT:

 matrix = [
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
 ]

 target = 6

INITIAL VALUES:

 rows = 3 - 1 = 2
 cols = 3 - 1 = 2

 r = 0
 c = 2

COLUMN 2:

 Column 2 contains 7, 8, 9.

 matrix[0][2] = 7

 7 > 6

Since the column is sorted downward, every value in this
column is at least 7. The target cannot be here.

Move left:

 c = 1

COLUMN 1:

 Column 1 contains 4, 5, 6.

 matrix[0][1] = 4

 4 is not greater than 6.

The target might be in this column, so Binary Search begins:

 low = 0
 high = 2

BINARY SEARCH ITERATION 1:

 midrow = (0 + 2) / 2
 = 1

 matrix[1][1] = 5

 5 < 6

The target must be below row 1:

 low = midrow + 1
 = 2

BINARY SEARCH ITERATION 2:

 low = 2
 high = 2

 midrow = (2 + 2) / 2
 = 2

 matrix[2][1] = 6

 6 == 6

The target is found.

Return:

 true

10. DRY RUN SUMMARY TABLE
----------------------------------------------------------------

 Column | low | high | midrow | Value | Decision
 ------------------------------------------------
 2 | - | - | - | 7 | Skip column
 1 | 0 | 2 | 1 | 5 | Search lower rows
 1 | 2 | 2 | 2 | 6 | Target found

 Target = 6

 Found at matrix[2][1].

 Return true.

11. WHAT IF THE TARGET DOES NOT EXIST?
----------------------------------------------------------------

If Binary Search finishes without finding target in a column,
the code moves to the next column.

If every column is checked and no match is found, c becomes -1.
The outer loop ends, and the method returns false.

12. COMPLEXITY ANALYSIS
----------------------------------------------------------------

Let:

 R = Number of rows
 C = Number of columns

TIME COMPLEXITY:

 O(C * log R)

Each of the C columns may be searched with Binary Search,
which takes O(log R) time.

SPACE COMPLEXITY:

 O(1)

The code uses only a fixed number of extra variables and
does not create another array or copy the matrix.

13. IMPORTANT INTERVIEW OBSERVATIONS
----------------------------------------------------------------

1. Every column must be sorted from top to bottom.
2. The matrix must be non-empty for this code as written.
3. Binary Search is performed separately on each possible column.
4. The code does not use row sorting to eliminate columns.
5. The outer loop moves from right to left.
6. The inner loop narrows the row range using low and high.
7. rows is declared but not used.
8. If the matrix has no rows or no columns, this code would
 need an empty-matrix check before accessing matrix[0].

FINAL MEMORY TRICK:

 For each column:

 Check its top value.
 If it is too large, skip the column.
 Otherwise, Binary Search down the column.

================================================================
JAVA IMPLEMENTATION
================================================================

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // as the matrix is sorted in column wise

        int rows = matrix.length-1;
        int cols = matrix[0].length-1;

        int r =0;
        int c = cols;

        while( c >= 0){
            if(matrix[0][c]>target)
                c--;

            else{
                int low = r;
                int high = matrix.length-1;

                while(low<=high){
                    int midrow = (low+high)/2;

                    if(matrix[midrow][c] == target){
                        return true;
                    }

                    if(matrix[midrow][c] > target){
                        high = midrow-1;
                    }
                    else{
                        low = midrow+1;
                    }

                }
                c--;

            }
        }

        return false;

    }
}
I’ll keep your implementation and variable names unchanged, and turn the comments into self-contained problem-solving notes. One important assumption is that each column is sorted in ascending order from top to bottom.**File:** `SearchA2DMatrix.java`

        **Commit Message:** `Day 41 - Search a 2D Matrix`

        ```java
/*
================================================================
PROBLEM: SEARCH A 2D MATRIX
PATTERN: Binary Search in Each Column
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
We are given a grid of numbers and a target number. We need to
return true if the target appears anywhere in the grid; otherwise,
we return false.

This solution assumes:
- The matrix is non-empty.
- Every column is sorted in ascending order from top to bottom.

Example:

 matrix = [
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
 ]

The columns are sorted:
 Column 0: 1, 2, 3
 Column 1: 4, 5, 6
 Column 2: 7, 8, 9

For target = 6, the answer is true.


REAL-LIFE ANALOGY
----------------------------------------------------------------
Imagine several vertical shelves, each holding books labeled with
increasing numbers from top to bottom. You need to find a book
with a particular number.

Because the numbers on each shelf are sorted, you do not need to
inspect every book on a shelf one by one. You can use Binary Search
to repeatedly cut the possible part of that shelf in half.

This solution checks the shelves (columns) from right to left and
uses Binary Search within each column that might contain the target.


WHY BINARY SEARCH INSTEAD OF A NORMAL LOOP?
----------------------------------------------------------------
A normal approach could check every cell. With R rows and C columns,
that could take O(R * C) time.

Here, each column is sorted. Binary Search uses that order: after
checking the middle value, it can discard half the remaining rows.
Searching one column takes O(log R), and searching up to C columns
takes O(C * log R).

The column order lets us skip a whole column when its top value is
already greater than the target: every value below it is at least
as large, so none can match.


WHY THE SORTING CONDITION MATTERS
----------------------------------------------------------------
Binary Search only works when the values in the searched range are
sorted. This code searches vertically, so every column must increase
from top to bottom.

If even one column is not sorted, the code may discard the half that
contains the target and give an incorrect result.

This approach does not rely on rows being sorted.


VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
rows
 Stores the index of the last row. It is declared in the original
 code but is not used by the search.

cols
 Stores the index of the last column. It gives c its starting
 position at the rightmost column.

r
 Marks the first row to search. It stays at 0, so each Binary
 Search starts at the top of the column.

c
 Identifies the current column. It starts at the rightmost column
 and decreases to move left after a column is skipped or searched.

low
 Marks the first row still possible in the current Binary Search.
 It moves downward when the middle value is too small.

high
 Marks the last row still possible in the current Binary Search.
 It moves upward when the middle value is too large.

midrow
 Marks the middle row of the current search range. Its value is
 checked in the current column.


CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
while (c >= 0)
 Keep checking columns while c is a valid column index. Once c is
 -1, every column has been considered.

if (matrix[0][c] > target)
 The top value is the smallest value in this sorted column. If
 even this value is greater than target, every value below it is
 also too large. Skip the column by moving c left.

else
 The top value is not greater than target. The target might be
 somewhere in this column, so search its rows with Binary Search.

while (low <= high)
 Continue while at least one row remains in the current search
 range. If low becomes greater than high, the range is empty and
 the target is not in this column.

if (matrix[midrow][c] == target)
 The middle value matches the target, so return true immediately.

if (matrix[midrow][c] > target)
 The middle value is too large. Since the column increases
 downward, this value and the rows below it are too large as well.
 Discard them by setting high to midrow - 1.

else
 Equality and the too-large case have already been handled, so the
 middle value is smaller than target. The rows above it are no
 larger, so discard them by setting low to midrow + 1.

After Binary Search ends without a match
 This column did not contain the target. Decrease c and search the
 next column to the left.

After all columns have been checked
 The loop ends with c < 0. Since no match was found, return false.


COMPLETE DRY RUN
----------------------------------------------------------------
Input:

 matrix = [
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
 ]
 target = 6

There are 3 rows and 3 columns:
 cols = matrix[0].length - 1 = 2
 r = 0
 c = 2

FIRST COLUMN CHECK: c = 2
 Column 2 is [7, 8, 9].
 matrix[0][2] is 7. Since 7 > 6, every value in this column is
 too large. Skip it:
 c becomes 1

SECOND COLUMN CHECK: c = 1
 Column 1 is [4, 5, 6].
 matrix[0][1] is 4. Since 4 is not greater than 6, this column
 could contain the target.

 Start Binary Search:
 low = r = 0
 high = matrix.length - 1 = 2

 Binary Search iteration 1:
 midrow = (0 + 2) / 2 = 1
 matrix[1][1] is 5

 5 is not 6, and 5 is smaller than 6.
 The rows above row 1 cannot contain 6, so move down:
 low = midrow + 1 = 2

 Binary Search iteration 2:
 low = 2, high = 2
 midrow = (2 + 2) / 2 = 2
 matrix[2][1] is 6

 The value equals the target, so return true.

The target is at matrix[2][1].


IF THE TARGET IS ABSENT
----------------------------------------------------------------
If Binary Search does not find the target in a column, its search
range eventually becomes empty: low > high. The code then moves to
the next column.

If every column is searched without a match, c eventually becomes
-1, the outer loop ends, and the method returns false.


COMPLEXITY
----------------------------------------------------------------
Let R be the number of rows and C the number of columns.

Time: O(C * log R)
Each column may be searched with Binary Search.

Extra space: O(1)
The algorithm uses a fixed number of variables and creates no
additional data structure.


EDGE CASES AND ASSUMPTIONS
----------------------------------------------------------------
- A one-row or one-column non-empty matrix can be searched.
- An empty matrix is not handled by this code: it accesses
 matrix[0].length, which would cause an error.
- rows is retained to preserve the original code, although it is
 unused.
- If the matrix has columns but zero rows, it is also not supported .


================================================================
SOLUTION
================================================================
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        // as the matrix is sorted in column wise

        int rows = matrix.length-1;
        int cols = matrix[0].length-1;

        int r =0;
        int c = cols;

        while( c >= 0){
            if(matrix[0][c]>target)
                c--;

            else{
                int low = r;
                int high = matrix.length-1;

                while(low<=high){
                    int midrow = (low+high)/2;

                    if(matrix[midrow][c] == target){
                        return true;
                    }

                    if(matrix[midrow][c] > target){
                        high = midrow-1;
                    }
                    else{
                        low = midrow+1;
                    }

                }
                c--;

            }
        }

        return false;

    }
}
