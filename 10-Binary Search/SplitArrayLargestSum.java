
/*
================================================================
PROBLEM NAME: Split Array Largest Sum
LEETCODE NUMBER: 410
DIFFICULTY: Medium
PATTERN: Binary Search on Answer + Greedy
================================================================


1. PROBLEM UNDERSTANDING (EXPLAIN LIKE I'M 10 YEARS OLD)
----------------------------------------------------------------

Imagine you have 5 boxes.

Each box contains some candies:

    nums = [7, 2, 5, 10, 8]

You have 2 children.

You must distribute these boxes between the children.

RULES:

1. Every child must get at least one box.
2. You cannot divide a box.
3. You must distribute boxes in their original order.
4. Every child gets a continuous group of boxes.
5. You want to minimize the maximum candies given to any child.

Example:

    Child 1 gets: [7, 2, 5]
    Child 2 gets: [10, 8]

    Child 1 total = 7 + 2 + 5 = 14
    Child 2 total = 10 + 8 = 18

    Maximum group sum = 18

Our goal is to find the SMALLEST possible maximum group sum.

In simple words:

    Divide the array into K continuous groups so that
    the largest group sum is as small as possible.


2. WHY BINARY SEARCH ON ANSWER?
----------------------------------------------------------------

We don't know the answer directly.

Instead, imagine guessing a maximum sum.

For example:

    What if every group is allowed a maximum sum of 15?

    [7, 2, 5] = 14
    [10]      = 10
    [8]       = 8

    We need 3 groups.

    But K = 2.

    Therefore, 15 is NOT possible.


Now imagine allowing a maximum sum of 18:

    [7, 2, 5] = 14
    [10, 8]   = 18

    We need only 2 groups.

    Therefore, 18 IS possible.


Notice the pattern:

    Maximum sum = 10  -> Not possible
    Maximum sum = 15  -> Not possible
    Maximum sum = 17  -> Not possible
    Maximum sum = 18  -> Possible
    Maximum sum = 20  -> Possible
    Maximum sum = 25  -> Possible
    Maximum sum = 32  -> Possible


Once a maximum sum becomes possible, every larger
maximum sum will also be possible.

This is called a MONOTONIC PROPERTY.

Since we want the SMALLEST possible maximum sum,
we use Binary Search on Answer.


3. FIND THE BINARY SEARCH RANGE
----------------------------------------------------------------

We need two boundaries:

    LOW  = Maximum individual element.
    HIGH = Sum of all elements.

WHY LOW?

Imagine:

    nums = [7, 2, 5, 10, 8]

The largest individual number is 10.

Can we set the maximum allowed sum to 5?

No!

We cannot divide the number 10 into smaller pieces.

Therefore, the answer cannot be smaller than 10.

    low = 10


WHY HIGH?

Imagine giving the entire array to one group.

    7 + 2 + 5 + 10 + 8 = 32

This is the maximum possible group sum.

Therefore:

    high = 32


Our answer must be between 10 and 32.


4. UNDERSTAND ALL VARIABLES
----------------------------------------------------------------

long low = 0;

    The smallest possible maximum subarray sum.

    It becomes the largest individual number.


long high = 0;

    The largest possible maximum subarray sum.

    It becomes the sum of all array elements.


long result = 0;

    Stores the best valid answer found so far.

    Whenever a maximum sum is possible, save it.


long mid;

    The maximum sum we are currently testing.

    We ask:

        Can I split the array into at most K groups
        if every group's sum must be <= mid?


long sum = 0;

    Stores the sum of the CURRENT subarray.

    We keep adding elements until adding another element
    would exceed mid.


int sarr = k;

    Stores the remaining subarray allowance.

    Initially, we have K subarrays available.

    Whenever we need a new subarray, decrease sarr.


5. UNDERSTAND THE GREEDY APPROACH
----------------------------------------------------------------

We need to check whether a particular mid is possible.

The easiest strategy:

    Keep adding elements to the current subarray.

    If adding the next element exceeds mid,
    start a new subarray.

This is called GREEDY because we fill the current
subarray as much as possible before starting another.


EXAMPLE:

    nums = [7, 2, 5, 10, 8]
    k = 2
    mid = 18


Initially:

    sum = 0
    sarr = 2


NUMBER 1: 7

    sum + nums[i] = 0 + 7 = 7

    7 <= 18

    Add 7 to the current subarray.

    sum = 7


NUMBER 2: 2

    sum + nums[i] = 7 + 2 = 9

    9 <= 18

    Add 2 to the current subarray.

    sum = 9


NUMBER 3: 5

    sum + nums[i] = 9 + 5 = 14

    14 <= 18

    Add 5 to the current subarray.

    sum = 14


NUMBER 4: 10

    sum + nums[i] = 14 + 10 = 24

    24 > 18

    We cannot add 10 to the current subarray.

    So start a NEW subarray.

    Decrease the available subarray count:

        sarr--;

    Start the new subarray with 10 itself:

        sum = 10


IMPORTANT:

    Why sum = nums[i] instead of sum = 0?

    Because 10 is not discarded.

    It becomes the FIRST element of the new subarray.


NUMBER 5: 8

    sum + nums[i] = 10 + 8 = 18

    18 <= 18

    Add 8 to the current subarray.

    sum = 18


FINAL DISTRIBUTION:

    Subarray 1 = [7, 2, 5] = 14
    Subarray 2 = [10, 8]   = 18

    We used 2 subarrays.

    sarr = 1

    Since sarr >= 1, this maximum sum is possible.


6. UNDERSTAND THE IF CONDITION
----------------------------------------------------------------

    if(sum + nums[i] > mid)

This condition asks:

    If I add the next element to the current subarray,
    will its total become greater than the allowed limit?

If YES:

    The current subarray cannot take this element.

    Start a new subarray.

    sarr--;

    sum = nums[i];


If NO:

    The current subarray can accept this element.

    Add it:

    sum += nums[i];


7. WHY DO WE CHECK sarr >= 1?
----------------------------------------------------------------

Initially:

    sarr = k

Every time we need another subarray:

    sarr--;

For example:

    k = 2

    Initially sarr = 2.

    If we create a second subarray:

        sarr = 1

    If we need a third subarray:

        sarr = 0

Now we have exceeded our available allowance.

Therefore:

    sarr >= 1 means allocation is possible.

    sarr < 1 means we needed more than K subarrays.


IMPORTANT INTERVIEW CONCEPT:

The problem asks for EXACTLY K subarrays.

Our greedy check verifies whether AT MOST K subarrays
are sufficient.

Why is this enough?

Because all numbers in this problem are positive,
and K <= nums.length.

If we can split the array into fewer than K groups,
we can split some existing groups further.

For example:

    [7, 2, 5, 10, 8]

    Suppose we have:

        [7, 2, 5] and [10, 8]

    If we need 3 groups, we can split:

        [7, 2] [5] [10, 8]

    The maximum group sum cannot increase.

Therefore, checking AT MOST K groups is sufficient
to determine whether EXACTLY K groups are possible.


8. UNDERSTAND THE BINARY SEARCH MOVEMENT
----------------------------------------------------------------

We are finding the MINIMUM valid maximum sum.

There are two cases.


CASE 1: sarr >= 1

    Allocation is possible.

    Example:

        mid = 18

        [7, 2, 5] = 14
        [10, 8]   = 18

    We have a valid answer.

    Save it:

        result = mid;

    But perhaps 17 or 16 can also work.

    We need to find a smaller valid answer.

    Therefore, move LEFT:

        high = mid - 1;


CASE 2: sarr < 1

    Allocation is NOT possible.

    We need more than K subarrays.

    Our maximum allowed sum is too small.

    We must increase it.

    Therefore, move RIGHT:

        low = mid + 1;


REMEMBER:

    MINIMUM VALID ANSWER:

        Valid   -> Save answer -> Move LEFT
        Invalid ->              -> Move RIGHT


    MAXIMUM VALID ANSWER:

        Valid   -> Save answer -> Move RIGHT
        Invalid ->              -> Move LEFT


9. COMPLETE BINARY SEARCH DRY RUN
----------------------------------------------------------------

INPUT:

    nums = [7, 2, 5, 10, 8]
    k = 2

INITIAL:

    low = 10
    high = 32
    result = 0


ITERATION 1:

    mid = (10 + 32) / 2
        = 21

    Allocation:

        [7, 2, 5] = 14
        [10, 8]   = 18

    Groups needed = 2

    Possible!

    result = 21
    high = 20

    WHY LEFT?

        21 works. Try a smaller maximum sum.


ITERATION 2:

    low = 10
    high = 20

    mid = (10 + 20) / 2
        = 15

    Allocation:

        [7, 2, 5] = 14
        [10]      = 10
        [8]       = 8

    Groups needed = 3

    Not possible!

    low = 16

    WHY RIGHT?

        15 is too small. Increase the allowed sum.


ITERATION 3:

    low = 16
    high = 20

    mid = (16 + 20) / 2
        = 18

    Allocation:

        [7, 2, 5] = 14
        [10, 8]   = 18

    Groups needed = 2

    Possible!

    result = 18
    high = 17

    WHY LEFT?

        18 works. Try a smaller maximum sum.


ITERATION 4:

    low = 16
    high = 17

    mid = (16 + 17) / 2
        = 16

    Allocation:

        [7, 2, 5] = 14
        [10]      = 10
        [8]       = 8

    Groups needed = 3

    Not possible!

    low = 17


ITERATION 5:

    low = 17
    high = 17

    mid = (17 + 17) / 2
        = 17

    Allocation:

        [7, 2, 5] = 14
        [10]      = 10
        [8]       = 8

    Groups needed = 3

    Not possible!

    low = 18


BINARY SEARCH ENDS:

    low = 18
    high = 17

    low > high, so the loop stops.

    result = 18

FINAL ANSWER = 18


10. DRY RUN SUMMARY TABLE
----------------------------------------------------------------

    Iteration | low | high | mid | Groups | Possible?
    -------------------------------------------------
       1      | 10  | 32   | 21  |   2    | Yes
       2      | 10  | 20   | 15  |   3    | No
       3      | 16  | 20   | 18  |   2    | Yes
       4      | 16  | 17   | 16  |   3    | No
       5      | 17  | 17   | 17  |   3    | No

    FINAL ANSWER = 18


11. EDGE CASES
----------------------------------------------------------------

CASE 1: k = 1

    nums = [7, 2, 5, 10, 8]

    Only one subarray is allowed.

    Answer = 32 (sum of all elements).


CASE 2: k = nums.length

    nums = [7, 2, 5, 10, 8]

    Every element must be its own subarray.

    Answer = 10 (largest element).


CASE 3: All elements are equal.

    nums = [5, 5, 5, 5]
    k = 2

    Split:

        [5, 5] = 10
        [5, 5] = 10

    Answer = 10.


12. COMPLEXITY ANALYSIS
----------------------------------------------------------------

Let:

    N = number of elements
    S = sum of all elements
    M = maximum individual element

Finding LOW and HIGH:

    O(N)

One greedy allocation check:

    O(N)

Binary Search range:

    M to S

Number of iterations:

    O(log(S - M + 1))

TOTAL TIME COMPLEXITY:

    O(N * log(S))

SPACE COMPLEXITY:

    O(1)

Why O(1)?

    We only use a few variables:

        low, high, result, mid, sum, sarr

    We do not create another array, list, or map.


13. FINAL INTERVIEW TAKEAWAY
----------------------------------------------------------------

Whenever a question asks:

    "Split an array into K continuous parts and
     minimize the largest part sum."

Think:

    1. Binary Search on Answer.
    2. LOW = maximum individual element.
    3. HIGH = sum of all elements.
    4. Greedily build continuous subarrays.
    5. If more than K groups are needed, increase mid.
    6. If K or fewer groups are enough, decrease mid.

MOST IMPORTANT QUESTION:

    Can I guess an answer and efficiently check
    whether that answer is possible?

If the answer is YES and the feasibility is monotonic,
Binary Search on Answer is a strong candidate.


================================================================
JAVA IMPLEMENTATION
================================================================

*/

class Solution {
    public int splitArray(int[] nums, int k) {

        long low = 0;
        long high =0;
        long result =0;

        // Find the smallest and largest possible answers.
        for(int num : nums){
            high+=num;                 // Sum of all elements
            low = Math.max(num,low);   // Largest element
        }

        // Binary Search on the maximum allowed subarray sum.
        while(low<=high){

            // The maximum sum we are currently testing.
            long mid = (low+high)/2;

            // Sum of the current subarray.
            long sum =0;

            // Remaining subarray allowance.
            int sarr = k;

            // Greedily allocate elements in original order.
            for(int i =0;i<nums.length;i++){

                // If adding this element exceeds mid,
                // we must start a new subarray.
                if(sum+ nums[i] >mid){

                    // Use one more subarray.
                    sarr--;

                    // The current element becomes the first
                    // element of the new subarray.
                    sum = (long)nums[i];

                }
                else{

                    // The current element fits.
                    // Add it to the current subarray.
                    sum+=(long)nums[i];
                }
            }

            // If at least one subarray allowance remains,
            // we did not need more than K subarrays.
            if(sarr>=1){

                // This is a valid maximum sum.
                result = mid;

                // Search LEFT for a smaller valid answer.
                high = mid-1;
            }
            else{

                // Too many subarrays were required.
                // Increase the maximum allowed sum.
                low =mid+1;
            }

            // This reset is unnecessary because sarr is
            // initialized to k at the start of every iteration.
            sarr =k;

        }

        return (int)result;
    }
}

