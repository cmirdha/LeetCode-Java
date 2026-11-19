/*
Problem Name: Check if Array Is Sorted
Platform: GeeksforGeeks
Difficulty: Easy

Pattern:
1. Recursion
2. Array Traversal

=========================================================
PROBLEM EXPLANATION
=========================================================

We are given an array.

Our task is to check whether the array is sorted
in ascending (non-decreasing) order.

In simple words:

Every element must be smaller than or equal to
the element immediately after it.

Examples:

[1, 2, 3, 4, 5] -> true

[1, 2, 2, 3, 4] -> true

[1, 3, 2, 4, 5] -> false

[5, 4, 3, 2, 1] -> false

IMPORTANT:

Equal adjacent elements are allowed because
the array needs to be non-decreasing.

=========================================================
APPROACH: RECURSION
=========================================================

IDEA:

Instead of checking the entire array using a loop,
we check one pair of adjacent elements at a time.

We use a recursive function:

findSorted(idx, arr)

Here:

idx = Current element index.
arr = Original array.

At every recursive call:

1. Check whether idx has reached arr.length.
2. If yes, the entire array has been checked.
   Return true.
3. Compare the current element with the previous element.
4. If arr[idx] is smaller than arr[idx-1],
   the array is not sorted.
   Return false.
5. Otherwise, move to the next index using recursion.

=========================================================
WHY START FROM INDEX 1?
=========================================================

In your isSorted() method:

return findSorted(1, arr);

Why do we start from index 1 instead of index 0?

Because we compare:

arr[idx] with arr[idx-1]

If idx = 0:

arr[idx-1] would become arr[-1].

That is an invalid array index.

Therefore, we start from index 1.

At index 1, we compare the first pair:

arr[1] with arr[0].

=========================================================
STEP-BY-STEP LOGIC
=========================================================

STEP 1: BASE CASE

if(idx == arr.length)
    return true;

WHY?

If idx reaches the array length, it means
we have successfully checked every adjacent pair.

There are no more elements to check.

Therefore, return true.

STEP 2: CHECK WHETHER THE ARRAY IS UNSORTED

if(arr[idx] < arr[idx-1])
    return false;

WHY?

For an ascending sorted array, the current
element must not be smaller than the previous
element.

For example:

[1, 3, 2, 4]

At index 2:

arr[2] = 2
arr[1] = 3

Since:

2 < 3

The array is not sorted.

Therefore, return false immediately.

STEP 3: RECURSIVE CALL

return findSorted(idx+1, arr);

WHY?

If the current pair is sorted, we have no reason
to stop.

Move to the next index and check the next pair.

=========================================================
DRY RUN 1: arr = [1, 2, 3, 4, 5]
=========================================================

Initial call:

isSorted(arr)

findSorted(1, arr)

Array:

 1   2   3   4   5
 0   1   2   3   4

---------------------------------
CALL 1
---------------------------------

idx = 1

Check base condition:

1 == 5 -> false

Compare:

arr[1] < arr[0]

2 < 1 -> false

The pair is sorted.

Recursive call:

findSorted(2, arr)

---------------------------------
CALL 2
---------------------------------

idx = 2

Check base condition:

2 == 5 -> false

Compare:

arr[2] < arr[1]

3 < 2 -> false

The pair is sorted.

Recursive call:

findSorted(3, arr)

---------------------------------
CALL 3
---------------------------------

idx = 3

Check base condition:

3 == 5 -> false

Compare:

arr[3] < arr[2]

4 < 3 -> false

The pair is sorted.

Recursive call:

findSorted(4, arr)

---------------------------------
CALL 4
---------------------------------

idx = 4

Check base condition:

4 == 5 -> false

Compare:

arr[4] < arr[3]

5 < 4 -> false

The pair is sorted.

Recursive call:

findSorted(5, arr)

---------------------------------
CALL 5
---------------------------------

idx = 5

Check base condition:

5 == 5 -> true

Return true.

Final Answer: true

=========================================================
DRY RUN 2: arr = [1, 3, 2, 4, 5]
=========================================================

Array:

 1   3   2   4   5
 0   1   2   3   4

Initial call:

findSorted(1, arr)

---------------------------------
CALL 1
---------------------------------

idx = 1

Compare:

arr[1] < arr[0]

3 < 1 -> false

Move to next index.

findSorted(2, arr)

---------------------------------
CALL 2
---------------------------------

idx = 2

Compare:

arr[2] < arr[1]

2 < 3 -> true

The current element is smaller than
the previous element.

Therefore:

return false

No further recursive calls are made.

Final Answer: false

=========================================================
DRY RUN SUMMARY TABLE
=========================================================

Example 1: [1, 2, 3, 4, 5]

idx    Previous    Current    Decision
-----------------------------------------
1      1           2          Sorted
2      2           3          Sorted
3      3           4          Sorted
4      4           5          Sorted
5      -           -          Base case

Answer: true

Example 2: [1, 3, 2, 4, 5]

idx    Previous    Current    Decision
-----------------------------------------
1      1           3          Sorted
2      3           2          Unsorted

Answer: false

=========================================================
KEY OBSERVATIONS
=========================================================

1. We only need to compare adjacent elements.

2. We start from index 1 because every element
   is compared with its previous element.

3. If arr[idx] < arr[idx-1], the array is unsorted.

4. Equal elements are allowed.

5. If the current pair is sorted, recursively
   check the next pair.

6. If idx reaches arr.length, every pair has
   been checked successfully.

7. We stop immediately when an unsorted pair
   is found.

=========================================================
EDGE CASES
=========================================================

1. Already sorted array:

   [1, 2, 3, 4]

   Returns true.

2. Descending array:

   [5, 4, 3, 2, 1]

   Returns false.

3. Duplicate elements:

   [1, 2, 2, 3]

   Returns true.

4. Single element:

   [5]

   Initial idx = 1.
   arr.length = 1.

   Base condition is true.

   Returns true.

5. Empty array:

   []

   Initial idx = 1.
   arr.length = 0.

   Your base condition checks:

   idx == arr.length

   1 == 0 -> false

   Then the code tries to access arr[1] and arr[0].

   This causes ArrayIndexOutOfBoundsException.

=========================================================
BUG / OBSERVATION
=========================================================

IMPORTANT EDGE CASE IN YOUR ORIGINAL CODE:

Your implementation works for non-empty arrays,
but it does not handle an empty array.

Reason:

You start recursion at index 1.

For an empty array:

idx = 1
arr.length = 0

Your base condition uses equality:

idx == arr.length

Since 1 is not equal to 0, execution continues
and attempts to access invalid array indexes.

This is an edge-case limitation in your original code.

I have intentionally preserved your code without
changing its base condition or implementation.

=========================================================
COMPLEXITY ANALYSIS
=========================================================

TIME COMPLEXITY: O(n)

Each recursive call checks one adjacent pair.

In the worst case, every element is visited.

SPACE COMPLEXITY: O(n)

Every recursive call occupies space in the
Java call stack.

The maximum recursion depth is proportional
to the array length.

=========================================================
ITERATIVE VS RECURSIVE APPROACH
=========================================================

Approach                  Time          Space
------------------------------------------------
Iterative                 O(n)          O(1)
Recursion                 O(n)          O(n)

Both approaches have the same time complexity.

The iterative approach uses constant extra space,
while recursion requires additional call-stack memory.

=========================================================
FINAL JAVA IMPLEMENTATION
=========================================================


*/
 */
class Solution {

    public boolean findSorted(int idx , int[] arr){

        if(idx == arr.length)
            return true;

        if(arr[idx]<arr[idx-1])
            return false;

        else
           return findSorted(idx+1 , arr);


    }
    public boolean isSorted(int[] arr) {
        // code here
        return findSorted( 1, arr);

    }
}
