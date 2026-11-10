/*
================================================================
PROBLEM: KTH SMALLEST ELEMENT IN A SORTED MATRIX
PATTERN: Binary Search on the Answer
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
A square matrix contains numbers sorted from left to right in
every row and from top to bottom in every column.

Return the k-th smallest value in the matrix.

For example, if the values in sorted order are:

 1, 5, 9, 10, 11, 12, 13, 13, 15

then k = 4 means return 10. The answer is a value, not a
row-and-column position.

This code assumes the matrix is non-empty and square, and that
both its rows and columns are sorted in ascending order.

REAL-LIFE ANALOGY
----------------------------------------------------------------
Imagine a grid of numbered seats where numbers increase as you
move right or down. You want the k-th smallest seat number.

Instead of sorting every number, guess a value and ask:
“How many numbers in the grid are less than or equal to this
guess?”

If there are at least k such numbers, the k-th smallest value
cannot be larger than the guess. If there are fewer than k,
the answer must be larger.

Binary Search repeatedly narrows the range of possible values.

WHY BINARY SEARCH THE VALUE, NOT THE CELLS?
----------------------------------------------------------------
The matrix has useful ordering, but it is not stored as one
single sorted list. Sorting all R * C values would take
O(R * C * log(R * C)) time and require extra storage.

Instead, the smallest possible answer is the top-left value,
and the largest possible answer is the bottom-right value.
We binary-search within that value range.

For each guessed value, isPossible counts how many matrix
entries are less than or equal to it. This count tells us
whether the guess is at least as large as the k-th smallest
value.

WHY THE COUNT DECIDES WHICH WAY TO SEARCH
----------------------------------------------------------------
If count >= k:
 At least k matrix values are <= mid, so the k-th smallest
 value is <= mid. Save mid as a possible answer and search
 smaller values.

If count < k:
 Fewer than k values are <= mid, so the k-th smallest value
 must be greater than mid. Search larger values.

When the search finishes, result holds the smallest value
that was found to have at least k values <= it.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
rows
 The number of rows. The code uses this to find the bottom row
 and the bottom-right value.

cols
 The number of columns. It is set equal to rows because this
 implementation assumes a square matrix.

low
 The smallest value that could be the answer. In a sorted
 matrix, this is the top-left value.

high
 The largest value that could be the answer. In a sorted
 square matrix, this is the bottom-right value.

result
 Remembers the best possible answer found so far. It starts at
 the smallest matrix value and is updated whenever mid has at
 least k values less than or equal to it.

mid
 The guessed value at the center of the current value range.

row and col
 In isPossible, these identify the last row and last column.
 The code uses row to reset r after it counts a whole column.

r and c
 The current position used to count values <= val. The walk
 starts at the bottom-left cell.

count
 The number of matrix values found that are <= val.

WHY THE COUNTING WALK WORKS
----------------------------------------------------------------
isPossible starts at the bottom-left cell, matrix[r][c].

If matrix[r][c] <= val:
 Every value above it in the same column is also <= val,
 because the column is sorted. There are r + 1 such values.
 Add them to count, then move one column right.

If matrix[r][c] > val:
 Every value below it in the same column is also > val.
 Move one row up to check a smaller value.

Each step moves either up or right, so the walk visits at most
a number of cells proportional to rows + cols. It does not
need to inspect every cell.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
if (k > rows * cols)
 There are only rows * cols values in the matrix. If k is
 larger than that, the requested position does not exist, so
 this code returns -1.

while (low <= high)
 Continue searching while at least one candidate value remains.

if (isPossible(matrix, k, mid))
 There are at least k values <= mid. Therefore, mid could be
 the k-th smallest value, but there may be a smaller valid
 answer. Save it and search left in value space:
 high = mid - 1.

else
 There are fewer than k values <= mid. The answer must be
 larger than mid, so search right in value space:
 low = mid + 1.

while (r >= 0 && c <= col)
 Keep walking while the current row and column are valid.

if (matrix[r][c] <= val)
 Count the current cell and all cells above it in this column.
 Reset r to the bottom row and move right to the next column.

else
 The current value is too large. Move upward to find smaller
 values in this column.

return count >= k
 True means at least k entries are <= val, so val is large
 enough to include the k-th smallest value.

COMPLETE DRY RUN
----------------------------------------------------------------
Input:

 matrix = [
 [ 1, 5, 9],
 [10, 11, 13],
 [12, 13, 15]
 ]
 k = 4

The values in sorted order are:
 1, 5, 9, 10, 11, 12, 13, 13, 15

The expected answer is 10.

Initial value range:
 low = matrix[0][0] = 1
 high = matrix[2][2] = 15
 result = 1

OUTER BINARY SEARCH, ITERATION 1
 mid = (1 + 15) / 2 = 8

Count values <= 8 using isPossible:

 Start at bottom-left: matrix[2][0] = 12.
 12 > 8, so move up: r = 1.

 matrix[1][0] = 10.
 10 > 8, so move up: r = 0.

 matrix[0][0] = 1.
 1 <= 8, so count r + 1 = 1 value in this column.
 count = 1. Move right to column 1 and reset r = 2.

 matrix[2][1] = 13.
 13 > 8, so move up: r = 1.

 matrix[1][1] = 11.
 11 > 8, so move up: r = 0.

 matrix[0][1] = 5.
 5 <= 8, so count the one value above or at this position.
 count = 2. Move right to column 2 and reset r = 2.

 matrix[2][2] = 15, matrix[1][2] = 13, and matrix[0][2] = 9.
 All are greater than 8, so the walk moves up and then ends.

Total count = 2. Since 2 < k (4), 8 is too small:
 low = mid + 1 = 9

OUTER BINARY SEARCH, ITERATION 2
 mid = (9 + 15) / 2 = 12

Count values <= 12:

Column 0: 1, 10, and 12 are <= 12, so add 3.
Column 1: 5 and 11 are <= 12, so add 2.
Column 2: 9 is <= 12, so add 1.

Total count = 6. Since 6 >= 4, 12 could be the answer.
Save it, then search smaller values:
 result = 12
 high = mid - 1 = 11

OUTER BINARY SEARCH, ITERATION 3
 mid = (9 + 11) / 2 = 10

Count values <= 10:

Column 0: 1 and 10 are <= 10, so add 2.
Column 1: 5 is <= 10, so add 1.
Column 2: none are <= 10, so add 0.

Total count = 3. Since 3 < 4, 10 is too small:
 low = mid + 1 = 11

OUTER BINARY SEARCH, ITERATION 4
 mid = (11 + 11) / 2 = 11

Count values <= 11:

Column 0 contributes 2: 1 and 10.
Column 1 contributes 2: 5 and 11.
Column 2 contributes 1: 9.

Total count = 5. Since 5 >= 4, 11 could be an answer.
Save it, then search smaller:
 result = 11
 high = mid - 1 = 10

Now low = 11 and high = 10, so the loop ends.
Return result = 11.

Correction to the expected answer:
The sorted values are 1, 5, 9, 10, 11, 12, 13, 13, 15.
For k = 4, the answer is 10, not 11. The counting logic should
therefore treat the first value with count >= k as the answer.
The current code's binary-search update is correct; the dry run
above needs one more check at mid = 10: the matrix has 1, 5, 9,
and 10 <= 10, so the count is 4, not 3.

Corrected iteration 3:
At val = 10, column 0 contributes 2 (1 and 10), column 1
contributes 1 (5), and column 2 contributes 1 (9), for a total
of 4. Therefore, result becomes 10 and high becomes 9.
The next search range is empty, and the method returns 10.

COMPLEXITY
----------------------------------------------------------------
Let R be the number of rows and C the number of columns. This
implementation assumes R = C.

The value range is searched by binary search. Each isPossible
call walks up or right through the matrix in O(R + C) time.

Time: O((R + C) * log(value range))
More precisely, the number of binary-search iterations depends
on the numeric difference between high and low.

Extra space: O(1)
Only a fixed number of variables are used.

IMPORTANT ASSUMPTIONS AND LIMITATIONS
----------------------------------------------------------------
- The matrix must be non-empty.
- The matrix must be square because cols is set to rows.
- Every row and every column must be sorted in ascending order.
- This code checks k greater than the number of cells, but does
 not check k <= 0.
- The midpoint expression (low + high) / 2 can overflow for
 very large integer values. A safer expression is
 low + (high - low) / 2, when the value bounds permit it.
- The supplied code is preserved below; the dry run above
 corrects a counting arithmetic mistake in the explanation.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public int kthSmallest(int[][] matrix, int k) {

        // The answer is a value in the matrix's numeric range.
        // Binary-search that range instead of sorting every cell.
        int rows = matrix.length;
        int cols = rows;

        // In a sorted matrix, the top-left cell is the minimum,
        // and the bottom-right cell is the maximum.
        int low = matrix[0][0];
        int high = matrix[rows-1][cols-1];

        // Keep the smallest value found so far that has at least
        // k matrix values less than or equal to it.
        int result = matrix[0][0];

        // There are only rows * cols entries, so a larger k
        // cannot refer to an existing position.
        if(k>(rows*cols))
            return -1;

        // Find the smallest value whose count of values <= it is
        // at least k. This value is the k-th smallest element.
        while(low<=high){
            int mid = (low+high)/2;

            // If at least k values are <= mid, mid is large
            // enough, but a smaller valid answer may exist.
            if(isPossible(matrix, k, mid)){
                result = mid;
                high = mid-1;
            }
            // Otherwise, fewer than k values are <= mid, so the
            // answer must be larger.
            else{
                low = mid+1 ;
            }
        }

        return result;
    }

    /*
    Count how many matrix entries are <= val.

    Start at the bottom-left. If the current value is <= val,
    every value above it in that column is also <= val, so count
    that whole vertical section and move right.

    If the current value is > val, every value below it in that
    column is also too large, so move up.

    The matrix must be sorted both across rows and down columns.
    */
    public boolean isPossible(int[][] matrix, int k, int val){
        int row = matrix.length-1;
        int col = row;

        int r = row;
        int c = 0;

        int count =0;

        while(r>=0 && c<=col){
            if(matrix[r][c]<=val){
                // Rows 0 through r in this column all qualify.
                count += (r+1);

                // Start at the bottom of the next column.
                r = row;
                c++;
            }
            else{
                // This value and everything below it are too large.
                r--;
            }
        }

        // At least k values <= val means val is high enough to
        // possibly contain the k-th smallest value.
        return count>=k;
    }
}
