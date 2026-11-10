/*
================================================================
PROBLEM: KTH SMALLEST NUMBER IN A MULTIPLICATION TABLE
PATTERN: Binary Search on the Answer
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Imagine a table with m rows and n columns. The number in row i
and column j is i * j, using one-based row and column numbers.

For example, when m = 3 and n = 3, the table is:

 1 2 3
 2 4 6
 3 6 9

Return the k-th smallest number among all entries, counting
repeated values separately.

For this table, the sorted entries are:
 1, 2, 2, 3, 3, 4, 6, 6, 9

If k = 5, the answer is 3.

REAL-LIFE ANALOGY
----------------------------------------------------------------
Picture a classroom multiplication chart. Instead of writing
every number down and sorting the list, guess a number val and
ask:

“How many cells in the chart contain a number <= val?”

If at least k cells qualify, the k-th smallest number is no
larger than val. If fewer than k qualify, the answer must be
larger. Repeating this check lets binary search narrow the
answer efficiently.

WHY BINARY SEARCH THE ANSWER?
----------------------------------------------------------------
The table is not one globally sorted list: values increase
across each row, but the same value can appear in multiple
rows and columns.

The smallest possible entry is 1, and the largest is m * n.
We binary-search that value range. For each candidate mid,
isPossible counts entries <= mid.

This avoids building and sorting all m * n entries.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
m and n
 The number of rows and columns in the multiplication table.
 Rows and columns are numbered from 1.

k
 The one-based position requested in the sorted list of all
 table entries. Duplicate values count as separate entries.

low
 The smallest answer still possible. It begins at 1.

high
 The largest answer still possible. It begins at m * n.

mid
 The current guessed answer: the midpoint of low and high.

result
 Stores the smallest candidate found so far that has at least
 k table entries <= it.

row
 The current one-based row being counted in isPossible.

div
 For a fixed row, val / row is the largest column number whose
 product with row could be <= val. Integer division rounds
 down, which gives the number of qualifying columns before
 limiting it to the table's width.

count
 The total number of table entries <= val, including duplicates.

HOW isPossible COUNTS THE ENTRIES
----------------------------------------------------------------
In row row, the entries are:

 row * 1, row * 2,..., row * n

An entry in column j is <= val when:
 row * j <= val

Dividing by row gives:
 j <= val / row

So val / row tells how many columns could qualify in that row.
There cannot be more than n columns, so the row contributes
min(n, val / row) entries.

The method adds that amount for every row. If the final count
is at least k, then val is large enough to include the k-th
entry.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
while (low <= high)
 Continue while the candidate value range is not empty.

if (isPossible(m, n, k, mid))
 At least k entries are <= mid. Therefore, the k-th smallest
 value is <= mid. Save mid and search for a smaller valid
 value by moving high to mid - 1.

else
 Fewer than k entries are <= mid. The k-th smallest value must
 be greater than mid, so move low to mid + 1.

while (row <= m)
 Count qualifying entries in each valid row, from row 1 through
 row m.

Math.min(n, div)
 A row has only n columns. If div is larger than n, every cell
 in that row qualifies; otherwise, exactly div cells qualify.

return count >= k
 True means the candidate is at least as large as the
 k-th-smallest value. Binary search uses this answer to decide
 which half of the value range to keep.

COMPLETE DRY RUN
----------------------------------------------------------------
Use:

 m = 3
 n = 3
 k = 5

The table is:

 1 2 3
 2 4 6
 3 6 9

Its entries in sorted order are:
 1, 2, 2, 3, 3, 4, 6, 6, 9

The expected answer is 3.

Initial bounds:
 low = 1
 high = 9
 result = 0

ITERATION 1
 mid = (1 + 9) / 2 = 5

Count entries <= 5:
 Row 1: min(3, 5 / 1) = 3
 Row 2: min(3, 5 / 2) = 2
 Row 3: min(3, 5 / 3) = 1
 Total count = 6

Since 6 >= k (5), 5 is a possible upper bound:
 result = 5
 high = 4

ITERATION 2
 mid = (1 + 4) / 2 = 2

Count entries <= 2:
 Row 1: min(3, 2 / 1) = 2
 Row 2: min(3, 2 / 2) = 1
 Row 3: min(3, 2 / 3) = 0
 Total count = 3

Since 3 < k (5), 2 is too small:
 low = 3

ITERATION 3
 mid = (3 + 4) / 2 = 3

Count entries <= 3:
 Row 1: min(3, 3 / 1) = 3
 Row 2: min(3, 3 / 2) = 1
 Row 3: min(3, 3 / 3) = 1
 Total count = 5

Since 5 >= k, 3 is a possible answer:
 result = 3
 high = 2

Now low = 3 and high = 2, so the loop ends.
Return result = 3.

WHY DUPLICATES ARE HANDLED CORRECTLY
----------------------------------------------------------------
The method counts cells, not distinct values. In the dry run,
the value 2 appears in two cells, so both entries are included.
That is correct because the problem asks for the k-th entry
when the table's values are sorted, with duplicates included.

COMPLEXITY
----------------------------------------------------------------
Each isPossible call visits all m rows, so it takes O(m) time.

The binary search performs O(log(m * n)) checks over the answer
range.

Time: O(m * log(m * n))
Extra space: O(1)

The loop could instead iterate over the smaller dimension to
reduce counting work, but the supplied implementation iterates
over m rows.

ASSUMPTIONS AND LIMITATIONS
----------------------------------------------------------------
- m and n must be positive.
- k should be between 1 and m * n, inclusive. The supplied
 code does not validate this.
- long is used for the bounds, midpoint, and count so that
 m * n and accumulated counts are less likely to overflow int.
- If constraints allow m * n to exceed the long range, even
 long arithmetic would not be sufficient.
- This method relies on the multiplication-table structure:
 row r contains r, 2r, 3r, and so on.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public int findKthNumber(int m, int n, int k) {

        // The answer must be between the smallest table entry,
        // 1, and the largest entry, m * n.
        long low = 1;
        long high = m * n;

        // Keep the smallest candidate found so far that has at
        // least k table entries less than or equal to it.
        long result = 0;

        // Binary-search the answer value, not a table position.
        while (low <= high) {
            long mid = (low + high) / 2;

            // If at least k entries are <= mid, mid is large
            // enough; keep it and check whether a smaller value
            // also works.
            if (isPossible(m, n, k, mid)) {
                result = mid;
                high = mid - 1;
            }
            // If fewer than k entries are <= mid, the answer
            // must be larger.
            else {
                low = mid + 1;
            }
        }

        return (int) result;
    }

    /*
    Count how many cells in the m-by-n multiplication table
    contain a value <= val.

    In row row, the entries are row * 1 through row * n.
    The number of columns satisfying row * column <= val is
    val / row, but the row contains at most n columns.
    Add min(n, val / row) for each row.

    Return true when at least k cells qualify, meaning val is
    at least as large as the k-th-smallest table entry.
    */
    public boolean isPossible(int m, int n, int k, long val) {

        // Rows are numbered from 1 through m.
        long row = 1;

        // Count qualifying cells across all rows. Repeated
        // values in different cells are counted separately.
        long count = 0;

        while (row <= m) {
            // In this row, columns up to val / row produce
            // products no greater than val. The row has only n
            // columns, so cap the contribution at n.
            long div = val / row;
            count += (long) Math.min(n, div);

            row++;
        }

        // At least k qualifying cells means val could contain
        // the k-th smallest entry.
        return count >= k;
    }
}
