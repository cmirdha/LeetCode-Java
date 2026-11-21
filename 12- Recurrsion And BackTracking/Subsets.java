/*
Problem Name: Subsets
LeetCode Number: 78
Difficulty: Medium
Pattern: Backtracking + Recursion + Include / Exclude

--------------------------------------------------
1. PROBLEM IN SIMPLE WORDS
--------------------------------------------------

Given an integer array nums containing unique
elements, return all possible subsets.

Example:

Input:
nums = [1, 2, 3]

Output:
[
    [1, 2, 3],
    [1, 2],
    [1, 3],
    [1],
    [2, 3],
    [2],
    [3],
    []
]

--------------------------------------------------
2. EXPLAIN LIKE I'M 10 YEARS OLD
--------------------------------------------------

Imagine you have three toys:

[1, 2, 3]

For every toy, you have two choices:

1. Take the toy.
2. Don't take the toy.

For toy 1:
Take it or leave it.

For toy 2:
Take it or leave it.

For toy 3:
Take it or leave it.

Every different set of decisions creates
one unique subset.

For three elements:

2 * 2 * 2 = 8 subsets.

--------------------------------------------------
3. APPROACH: INCLUDE / EXCLUDE
--------------------------------------------------

We process the array one element at a time.

For every element, make two recursive calls:

FIRST CALL:
Include the current element.

SECOND CALL:
Exclude the current element.

After exploring the include choice, remove
the element before exploring the exclude choice.

This is called backtracking.

--------------------------------------------------
4. UNDERSTANDING THE VARIABLES
--------------------------------------------------

int[] nums:
Original input array.

int idx:
Index of the current element.

List<Integer> sublist:
Current subset being constructed.

List<List<Integer>> list:
Stores all completed subsets.

int x:
Current element, nums[idx].

Initially:

idx = 0
sublist = []
list = []

--------------------------------------------------
5. BASE CASE
--------------------------------------------------

if(idx == nums.length){
    list.add(new ArrayList<>(sublist));
    return;
}

When idx reaches nums.length, every element
has already been processed.

The current sublist is one complete subset.

Why new ArrayList<>(sublist)?

Because sublist is reused during backtracking.

We need an independent copy of every completed
subset.

Otherwise, later modifications to sublist
would affect previously stored references.

--------------------------------------------------
6. CHOICE 1: INCLUDE THE ELEMENT
--------------------------------------------------

sublist.add(x);

helper(nums, idx+1, sublist, list);

First, add the current element.

Then recursively process the next element.

Example:

nums = [1, 2, 3]
idx = 0
x = 1

After adding:

sublist = [1]

Now process index 1.

We are exploring all subsets that contain 1.

--------------------------------------------------
7. BACKTRACKING: UNDO THE CHOICE
--------------------------------------------------

sublist.remove(sublist.size()-1);

After exploring every possibility that includes
the current element, remove it.

Example:

Before removal:
sublist = [1]

After removal:
sublist = []

Why?

Because now we want to explore subsets that
do not contain the current element.

We must restore the previous state.

--------------------------------------------------
8. CHOICE 2: EXCLUDE THE ELEMENT
--------------------------------------------------

helper(nums, idx+1, sublist, list);

This time, we do not add the current element.

We simply move to the next index.

Example:

Current element = 1

We do not include 1.

Now process the remaining elements without 1.

--------------------------------------------------
9. COMPLETE DRY RUN: [1, 2, 3]
--------------------------------------------------

Initially:

sublist = []
idx = 0

NUMBER 1:

Choice 1: Include 1.
sublist = [1]

NUMBER 2:

Choice 1: Include 2.
sublist = [1, 2]

NUMBER 3:

Choice 1: Include 3.
sublist = [1, 2, 3]

Base case reached.
Store [1, 2, 3].

Backtrack: Remove 3.

sublist = [1, 2]

Choice 2: Exclude 3.

Base case reached.
Store [1, 2].

Backtrack: Remove 2.

sublist = [1]

Choice 2: Exclude 2.

NUMBER 3:

Include 3:
sublist = [1, 3]
Store [1, 3].

Exclude 3:
sublist = [1]
Store [1].

Backtrack: Remove 1.

sublist = []

Now explore all possibilities without 1.

NUMBER 2:

Include 2:
sublist = [2]

NUMBER 3:

Include 3:
Store [2, 3].

Exclude 3:
Store [2].

Backtrack: Remove 2.

sublist = []

NUMBER 3:

Include 3:
Store [3].

Exclude 3:
Store [].

FINAL OUTPUT:

[
    [1, 2, 3],
    [1, 2],
    [1, 3],
    [1],
    [2, 3],
    [2],
    [3],
    []
]

--------------------------------------------------
10. WHY 2^n SUBSETS?
--------------------------------------------------

Every element has two choices:

Include or Exclude.

For n = 3:

2 * 2 * 2 = 8.

For n = 4:

2 * 2 * 2 * 2 = 16.

General formula:

Number of subsets = 2^n.

--------------------------------------------------
11. IMPORTANT OBSERVATIONS
--------------------------------------------------

1. Every element has two choices.

2. First recursive call includes the element.

3. Second recursive call excludes the element.

4. idx+1 is used in both calls because
   the current element has been processed.

5. remove() restores the previous subset.

6. new ArrayList<>(sublist) creates an
   independent copy of the current subset.

7. Empty subset is also a valid subset.

8. Total subsets = 2^n.

--------------------------------------------------
12. COMPLEXITY ANALYSIS
--------------------------------------------------

Time Complexity:

O(n * 2^n)

There are 2^n subsets, and copying each
subset can take up to O(n).

Auxiliary Space:

O(n)

Recursion depth and current subset size
can both reach n.

Output Space:

O(n * 2^n)

All 2^n subsets are stored.

--------------------------------------------------
13. ORIGINAL JAVA IMPLEMENTATION
--------------------------------------------------


--------------------------------------------------
14. FINAL TAKEAWAY
--------------------------------------------------

For every element:

1. INCLUDE it.
2. EXPLORE all possibilities.
3. REMOVE it.
4. EXCLUDE it.
5. EXPLORE all possibilities.

Remember this template:

Choose -> Explore -> Undo -> Explore another choice.

This is the Include / Exclude pattern in
Backtracking.
*/

class Solution {


    public void helper(int[] nums, int idx,  List<Integer> sublist, List<List<Integer>> list){
        if(idx == nums.length){
            list.add(new ArrayList<>(sublist));
            return;
        }

        int x = nums[idx];


        sublist.add(x);
        helper(nums, idx+1, sublist, list);
        sublist.remove(sublist.size()-1);
        helper(nums, idx+1, sublist, list);

    }

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> sublist = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        helper(nums, 0, sublist, list);

        return list;

    }
}