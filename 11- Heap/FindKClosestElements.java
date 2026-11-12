/*
================================================================
PROBLEM: FIND K CLOSEST ELEMENTS
PATTERNS: PRIORITY QUEUE AND BINARY SEARCH
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given a sorted array arr, an integer k, and a value x, return the
k elements closest to x.

If two elements are equally close to x, the smaller element is
considered closer. The returned elements should be sorted.

================================================================
APPROACH 1: PRIORITY QUEUE
================================================================

CORE IDEA
----------------------------------------------------------------
Add every array value to a priority queue ordered by distance
from x. When distances tie, order the smaller value first.

Remove the first k values, then sort them before returning them.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
arr
 The input array of values.

k
 The number of closest values to return.

x
 The target value used to measure closeness.

a, b
 Values compared by the priority queue.

pq
 Stores the values ordered by closeness to x.

i
 Counts how many values have been removed from the queue.

list
 Holds the selected values before sorting.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
Math.abs(a - x) == Math.abs(b - x)
 Checks whether the two values are equally distant from x.

return a - b
 If distances tie, puts the smaller value first.

return Math.abs(a - x) - Math.abs(b - x)
 Otherwise, puts the value closer to x first.

for (int i : arr)
 Adds each array value to the priority queue.

while (i < k)
 Removes exactly k values from the queue.

Collections.sort(list)
 Sorts the selected values into ascending order.

COMPLEXITY
----------------------------------------------------------------
Let n be arr.length.

Time: O(n log n + k log k), because all n values are added to
the heap and the selected k values are sorted.

Extra space: O(n), for the priority queue.

NOTE
----------------------------------------------------------------
This preserves your code as written. With large integer values,
subtraction in the comparator (such as a - b) can overflow.

================================================================
APPROACH 2: BINARY SEARCH FOR A CONTIGUOUS WINDOW
================================================================

CORE IDEA
----------------------------------------------------------------
Because arr is sorted, the k closest elements form a contiguous
window.

The window can start anywhere from index 0 through arr.length - k.
Binary search finds the best starting index by comparing the
element just outside the window on each side.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
left, right
 The inclusive range of possible starting indices for the
 k-element window.

start
 The midpoint starting index being tested.

leftCandidate
 The value that would be excluded if the window moved right.

rightCandidate
 The value that would enter the window if it moved right.

result
 Stores the k values in the chosen window.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
int right = arr.length - k
 The final valid starting index for a window of length k.

while (left < right)
 Continues narrowing the range of possible window starts.

start = left + (right - left) / 2
 Calculates the midpoint without adding left and right directly.

arr[start]
 The leftmost value in the current candidate window.

arr[start + k]
 The value immediately to the right of that window.

if (x - leftCandidate > rightCandidate - x)
 If the right candidate is closer to x than the left candidate,
 shift the window right.

else
 Keep searching at start or to its left.

for (int i = left; i < left + k; i++)
 Copies the k values from the selected window into the result.

DRY RUN
----------------------------------------------------------------
arr = [1, 2, 3, 4, 5]
k = 4
x = 3

There are two possible windows:
[1, 2, 3, 4] and [2, 3, 4, 5]

The elements being compared are 1 and 5. Both are equally far
from 3, so the search keeps the left window. The result is
[1, 2, 3, 4].

COMPLEXITY
----------------------------------------------------------------
Time: O(log(n - k) + k), for binary search and copying the
selected window.

Extra space: O(k), for the returned list.

================================================================
CODE — APPROACH 1
================================================================
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            if(Math.abs(a-x) == Math.abs(b-x))
                return a-b;
            else
                return Math.abs(a-x) - Math.abs(b-x) ;
        });

        for(int i : arr){
            pq.add(i);
        }

        List<Integer> list = new ArrayList<>();

        int i=0;
        while(i<k){
            list.add(pq.poll());
            i++;
        }

        Collections.sort(list);

        return list;


    }
}

/*
================================================================
CODE — APPROACH 2
================================================================
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0;
        int right = arr.length - k;

        // as the array is sorted the answer will be continuous array

        while (left < right) {

            int start = left + (right - left) / 2;

            // Element that will be removed if we move window right
            int leftCandidate = arr[start];

            // Element that will be included if we move window right
            int rightCandidate = arr[start + k];

            // Is the new right element closer to x
            // than the current left element?
            if (x - leftCandidate > rightCandidate - x) {
                left = start + 1; // move window right
            } else {
                right = start; // keep window on left side by breaking the loop
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
