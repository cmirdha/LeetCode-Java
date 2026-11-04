
/*
Problem Name: Search in Rotated Sorted Array
LeetCode Number: 33
Difficulty: Medium
Pattern: Binary Search

-------------------------------------------------------
1. PROBLEM UNDERSTANDING
-------------------------------------------------------

We are given a sorted array that has been rotated.

We need to search for a target element and return
its index.

If the target does not exist, return -1.

Example:

nums = [4, 5, 6, 7, 0, 1, 2]
target = 0

Answer = 4


-------------------------------------------------------
2. REAL-LIFE ANALOGY
-------------------------------------------------------

Imagine people standing in a sorted line according
to their heights.

Someone rotates the line.

Now you need to find a particular person.

Instead of checking everybody:

1. Look at the middle.
2. Identify which half is sorted.
3. Check whether the person can be in that half.
4. Eliminate the other half.

Repeat until the person is found.


-------------------------------------------------------
3. APPROACH
-------------------------------------------------------

Use Binary Search.

Even though the entire array is not sorted,
at least one half is always sorted.

We identify the sorted half and check whether
the target belongs to that half.

If it does, search there.

Otherwise, search the opposite half.


-------------------------------------------------------
4. STEP-BY-STEP LOGIC
-------------------------------------------------------

STEP 1:

Initialize:

low = 0
high = nums.length - 1


STEP 2:

Run the loop while:

low <= high

We use <= because we must also check the
last remaining element.


STEP 3:

Calculate middle:

mid = (low + high) / 2


STEP 4:

Check whether the target is at mid.

If yes, return mid.


STEP 5:

Check whether the left half is sorted.

nums[low] <= nums[mid]

If TRUE, left half is sorted.

Now check whether target belongs to that range.

nums[low] <= target && target <= nums[mid]

If TRUE:

high = mid - 1

Otherwise:

low = mid + 1


STEP 6:

If left half is not sorted, right half is sorted.

Check whether target belongs to the right half.

nums[mid] <= target && target <= nums[high]

If TRUE:

low = mid + 1

Otherwise:

high = mid - 1


STEP 7:

If the loop finishes without finding the target,
return -1.


-------------------------------------------------------
5. KEY OBSERVATIONS
-------------------------------------------------------

1. At least one half is always sorted.

2. We identify the sorted half using the boundary
   values and the middle value.

3. We check whether the target belongs to the
   sorted half.

4. We eliminate half of the search space.

5. Binary Search reduces the time complexity
   to O(log N).


-------------------------------------------------------
6. DRY RUN
-------------------------------------------------------

Input:

nums = [4, 5, 6, 7, 0, 1, 2]
target = 0


ITERATION 1:

low = 0
high = 6
mid = 3

nums[mid] = 7

Target not found.

Check left sorted:

4 <= 7

TRUE.

Check target range:

4 <= 0 && 0 <= 7

FALSE.

Update:

low = mid + 1 = 4


ITERATION 2:

low = 4
high = 6
mid = 5

nums[mid] = 1

Target not found.

Check left sorted:

0 <= 1

TRUE.

Check target range:

0 <= 0 && 0 <= 1

TRUE.

Update:

high = mid - 1 = 4


ITERATION 3:

low = 4
high = 4
mid = 4

nums[mid] = 0

Target found!

Return 4.


-------------------------------------------------------
7. IMPORTANT CONDITIONS
-------------------------------------------------------

while(low <= high)

Continue while at least one element remains.

nums[low] <= nums[mid]

Identifies whether left half is sorted.

nums[low] <= target && target <= nums[mid]

Checks whether target belongs to sorted left half.

nums[mid] <= target && target <= nums[high]

Checks whether target belongs to sorted right half.

high = mid - 1

Eliminate mid and everything to its right.

low = mid + 1

Eliminate mid and everything to its left.


-------------------------------------------------------
8. COMPLEXITY ANALYSIS
-------------------------------------------------------

TIME COMPLEXITY: O(log N)

We eliminate approximately half the search space
in every iteration.

SPACE COMPLEXITY: O(1)

We only use low, high and mid.

No additional data structure is required.


-------------------------------------------------------
9. JAVA IMPLEMENTATION
-------------------------------------------------------

*/

class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
