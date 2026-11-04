
/*
Problem Name: Find K Rotation
Problem Type: Find the number of rotations in a sorted array
Pattern: Binary Search
Difficulty: Medium

-------------------------------------------------------
1. PROBLEM UNDERSTANDING
-------------------------------------------------------

We are given a sorted array that has been rotated.

We need to find how many times the array has been
rotated towards the right.

IMPORTANT OBSERVATION:

The number of right rotations is equal to the index
of the smallest element.

Example:

Original Array:
[0, 1, 2, 4, 5, 6, 7]

After 4 right rotations:
[4, 5, 6, 7, 0, 1, 2]

Smallest element = 0
Index of smallest element = 4

Answer = 4


-------------------------------------------------------
2. REAL-LIFE ANALOGY
-------------------------------------------------------

Imagine books arranged alphabetically.

Initially:
A B C D E

After rotating twice:
D E A B C

If somebody asks how many times we rotated the books,
we simply find where A is located.

A is at index 2.

Therefore, rotations = 2.


-------------------------------------------------------
3. APPROACH: BINARY SEARCH
-------------------------------------------------------

A rotated sorted array has two sorted portions.

Example:

[4, 5, 6, 7, 0, 1, 2]

First portion  = [4, 5, 6, 7]
Second portion = [0, 1, 2]

The smallest element is where the second portion starts.

Instead of checking every element, we use Binary Search
to eliminate half of the search space in each iteration.

We compare:

nums[mid] with nums[high]


-------------------------------------------------------
4. STEP-BY-STEP LOGIC
-------------------------------------------------------

STEP 1:

Initialize two pointers.

low = 0
high = nums.length - 1

They represent our current search range.


STEP 2:

Continue while low < high.

Why?

When low and high become equal, we have found
the index of the smallest element.

We don't need another iteration.


STEP 3:

Calculate the middle index.

mid = (low + high) / 2

This divides our current search space into two parts.


STEP 4:

Check whether:

nums[mid] > nums[high]

If TRUE:

The middle element belongs to the larger sorted portion.

The minimum must be somewhere to the right.

Therefore:

low = mid + 1

We use mid + 1 because mid cannot be the minimum.


STEP 5:

Otherwise:

nums[mid] <= nums[high]

The minimum might be at mid or somewhere to its left.

Therefore:

high = mid

We do NOT use mid - 1 because mid itself might
be the smallest element.


STEP 6:

When the loop terminates:

low == high

Both pointers point to the smallest element.

Return high.


-------------------------------------------------------
5. KEY OBSERVATIONS
-------------------------------------------------------

1. We are searching for the minimum element's index.

2. The minimum element's index represents
   the number of right rotations.

3. nums[mid] > nums[high]:
   Minimum is on the right.

4. Otherwise:
   Minimum is at mid or on the left.

5. high = mid keeps the possibility that mid
   itself is the answer.

6. low = mid + 1 eliminates mid.


-------------------------------------------------------
6. DRY RUN
-------------------------------------------------------

Input:

nums = [4, 5, 6, 7, 0, 1, 2]

ITERATION 1:

low = 0
high = 6
mid = 3

nums[mid] = 7
nums[high] = 2

7 > 2

TRUE

low = mid + 1 = 4


ITERATION 2:

low = 4
high = 6
mid = 5

nums[mid] = 1
nums[high] = 2

1 > 2

FALSE

high = mid = 5


ITERATION 3:

low = 4
high = 5
mid = 4

nums[mid] = 0
nums[high] = 1

0 > 1

FALSE

high = mid = 4


ITERATION 4:

low = 4
high = 4

low < high is FALSE.

Loop terminates.

Answer = 4


-------------------------------------------------------
7. IMPORTANT CONDITIONS
-------------------------------------------------------

while(low < high)

Continue until both pointers meet.

low = mid + 1

Exclude mid because it cannot be the minimum.

high = mid

Keep mid because it might be the minimum.

return high

Return the index of the smallest element.


-------------------------------------------------------
8. COMPLEXITY ANALYSIS
-------------------------------------------------------

TIME COMPLEXITY: O(log N)

In every iteration, we eliminate approximately
half of the search space.

For 8 elements:
8 -> 4 -> 2 -> 1

Therefore, Binary Search takes logarithmic time.

SPACE COMPLEXITY: O(1)

We only use three variables:

low, high, mid

No additional array or data structure is created.


-------------------------------------------------------
9. JAVA IMPLEMENTATION
-------------------------------------------------------

*/

class Solution {
    public int findKRotation(int nums[]) {
        // Code here

        int high = nums.length-1;
        int low = 0;

        while(low<high){
            int mid = (low+high)/2;

            if(nums[mid]> nums[high]){
                low = mid+1;
            }
            else{
                high = mid;
            }
        }

        return high;
    }
}
