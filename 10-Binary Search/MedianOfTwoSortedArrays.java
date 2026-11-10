/*
================================================================
PROBLEM: MEDIAN OF TWO SORTED ARRAYS
PATTERN: Binary Search on a Partition
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given two arrays sorted in ascending order, find the median of
all their values combined.

The median is:
- The middle value when the total number of values is odd.
- The average of the two middle values when the total is even.

The goal is to find the median without merging the arrays.

CORE IDEA
----------------------------------------------------------------
Choose a partition in each array so the combined left side contains
half of all values, and every value on the left is less than or
equal to every value on the right.

For example, a partition might look like:

 nums1: [left values | right values]
 nums2: [left values | right values]

If the partition is correct:
- The largest value on the left is the median when the total
 length is odd.
- The average of the largest left value and smallest right value
 is the median when the total length is even.

The code binary-searches for the partition in the shorter array.
Once that partition is chosen, the other partition is determined
by how many values must be on the combined left side.

WHY SEARCH THE SHORTER ARRAY?
----------------------------------------------------------------
The binary search runs over possible partition positions in nums1.
Searching the shorter array keeps the search range small and helps
ensure the corresponding partition in nums2 is valid.

If nums1 is longer than nums2, the method swaps the arguments by
recursively calling itself with the arrays reversed.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
n1 and n2
 The lengths of the two arrays.

n
 The required number of values on the combined left side. The
 extra 1 makes the left side hold one more value when the total
 length is odd.

low and high
 The smallest and largest possible partition positions in nums1.

mid1
 The number of values placed on the left side from nums1.

mid2
 The number of values needed from nums2 so the combined left side
 contains n values.

l1 and l2
 The largest values on the left side of nums1 and nums2.

r1 and r2
 The smallest values on the right side of nums1 and nums2.

Integer.MIN_VALUE and Integer.MAX_VALUE act as boundary sentinels
when a partition lies at the beginning or end of an array. They
let the code compare partition values without separate boundary
cases.

PARTITION CONDITIONS
----------------------------------------------------------------
A partition is correct when:

 l1 <= r2
 l2 <= r1

These conditions mean that neither array has a left-side value
larger than a right-side value from the other array.

If l1 > r2, the partition in nums1 is too far right. Move it left.

Otherwise, the partition in nums1 is too far left, so move it right.

HOW THE MEDIAN IS FOUND
----------------------------------------------------------------
When the total length is odd, the combined left side has one extra
value. Its largest value is the median:

 max(l1, l2)

When the total length is even, the two middle values are:
- The largest value on the left: max(l1, l2).
- The smallest value on the right: min(r1, r2).

The median is their average.

DRY RUN
----------------------------------------------------------------
nums1 = [1, 3]
nums2 = [2]

The method swaps the arrays because nums1 is longer:

nums1 = [2]
nums2 = [1, 3]

n1 = 1, n2 = 2
n = (1 + 2 + 1) / 2 = 2

Try mid1 = 1:
 mid2 = n - mid1 = 1

 l1 = 2, r1 = Integer.MAX_VALUE
 l2 = 1, r2 = 3

The partition is valid:
 l1 <= r2, meaning 2 <= 3
 l2 <= r1, meaning 1 <= Integer.MAX_VALUE

The total length is odd, so return max(l1, l2) = 2.

COMPLEXITY
----------------------------------------------------------------
Time: O(log(min(n1, n2)))
The binary search runs on the shorter array.

Extra space: O(1)
The method uses a fixed number of variables. The recursive swap
adds only a constant amount of call-stack space.

ASSUMPTIONS AND NOTES
----------------------------------------------------------------
- Both input arrays must be sorted in ascending order.
- The standard problem permits one array to be empty, but not both.
- This implementation returns 0 if both arrays are empty, rather
 than reporting invalid input.
- The even-length average must avoid integer overflow. The code
 below casts before adding the middle values.
- The `n` calculation uses `int`; this is appropriate for typical
 problem constraints where the combined array lengths fit in int.

================================================================
SOLUTION
================================================================
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;

        // The left partition needs half the values, rounded up.
        // This gives it one extra value when the total is odd.
        int n = (n1 + n2 + 1) / 2;

        // Binary-search the shorter array so the search is faster
        // and the corresponding partition in the other array fits.
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        // mid1 is the number of nums1 values placed on the left.
        int low = 0;
        int high = n1;

        while (low <= high) {
            int mid1 = (low + high) / 2;

            // Choose mid2 so both left partitions contain n values
            // in total.
            int mid2 = n - mid1;

            // Boundary sentinels handle partitions at array ends.
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            // First values on the right sides of the partitions.
            if (mid1 < n1)
                r1 = nums1[mid1];
            if (mid2 < n2)
                r2 = nums2[mid2];

            // Last values on the left sides of the partitions.
            if (mid1 >= 1)
                l1 = nums1[mid1 - 1];
            if (mid2 >= 1)
                l2 = nums2[mid2 - 1];

            // The partition is valid when every left-side value
            // is <= every right-side value.
            if (l1 <= r2 && l2 <= r1) {

                // For an odd total, the left side has one extra
                // value, so its largest value is the median.
                if ((n1 + n2) % 2 != 0)
                    return (double) Math.max(l1, l2);

                    // For an even total, average the two middle values.
                    // Cast before addition to prevent int overflow.
                else
                    return ((double) Math.max(l1, l2)
                            + Math.min(r1, r2)) / 2.0;
            }

            // nums1's partition is too far right; move it left.
            if (l1 > r2)
                high = mid1 - 1;

                // Otherwise, nums1's partition is too far left.
            else
                low = mid1 + 1;
        }

        // Reached only if the inputs do not meet the expected
        // conditions, such as both arrays being empty.
        return 0;
    }
}
