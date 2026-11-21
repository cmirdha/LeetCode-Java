/*
Problem Name: Permutations
LeetCode Number: 46
Difficulty: Medium
Pattern: Backtracking + Recursion + Used-Element Tracking

--------------------------------------------------
1. PROBLEM IN SIMPLE WORDS
--------------------------------------------------

Given an array of distinct integers, return all
possible arrangements of those integers.

Example:

Input:
nums = [1, 2, 3]

Output:
[
    [1, 2, 3],
    [1, 3, 2],
    [2, 1, 3],
    [2, 3, 1],
    [3, 1, 2],
    [3, 2, 1]
]

--------------------------------------------------
2. EXPLAIN LIKE I'M 10 YEARS OLD
--------------------------------------------------

Imagine you have three cards:

[1, 2, 3]

You need to arrange all three cards in a line.

Every card must be used exactly once.

You can choose:

First position:
1, 2, or 3.

Second position:
Any number that has not already been used.

Third position:
The only remaining number.

Every different arrangement is a permutation.

--------------------------------------------------
3. UNDERSTANDING THE VARIABLES
--------------------------------------------------

int[] nums:
Original input array.

List<Integer> sublist:
Current permutation being constructed.

List<List<Integer>> list:
Stores all completed permutations.

int i:
Current number being checked in the loop.

Unlike Subsets, there is no index variable.

Why?

Because we can select any unused number at
every recursive level.

--------------------------------------------------
4. BASE CASE
--------------------------------------------------

if(sublist.size() == nums.length){
    list.add(new ArrayList<>(sublist));
    return;
}

When the current permutation contains all
the elements, it is complete.

Example:

nums = [1, 2, 3]

sublist = [1, 2, 3]

Both sizes are 3.

Store an independent copy of the permutation.

Why new ArrayList<>(sublist)?

Because the same sublist is reused and modified
during backtracking.

We must preserve each completed permutation.

--------------------------------------------------
5. CHECK WHETHER THE ELEMENT IS ALREADY USED
--------------------------------------------------

for(int i : nums){

    if(sublist.contains(i))
        continue;

}

The loop checks every element in the original
array.

contains(i) checks whether that number is
already present in the current permutation.

If it is present:

continue;

Skip that number and try the next one.

Example:

nums = [1, 2, 3]

sublist = [1, 2]

Check:

1 -> Already present. Skip.
2 -> Already present. Skip.
3 -> Not present. Can choose it.

This prevents selecting the same number twice
in one permutation.

--------------------------------------------------
6. CHOOSE THE ELEMENT
--------------------------------------------------

sublist.add(i);

Add the available number to the current
permutation.

Example:

Before:
sublist = [1, 2]

After adding 3:
sublist = [1, 2, 3]

--------------------------------------------------
7. EXPLORE RECURSIVELY
--------------------------------------------------

helper(nums, sublist, list);

Now recursively build the remaining positions.

The recursive call checks every number again.

Already selected numbers are skipped using
sublist.contains().

--------------------------------------------------
8. BACKTRACK
--------------------------------------------------

sublist.remove(sublist.size()-1);

After exploring every possibility starting
with the selected number, remove it.

Example:

Before:
sublist = [1, 2, 3]

After removing the last element:
sublist = [1, 2]

Now another available number can be selected.

--------------------------------------------------
9. COMPLETE DRY RUN: [1, 2, 3]
--------------------------------------------------

Start:

sublist = []

Choose 1:

sublist = [1]

Choose 2:

sublist = [1, 2]

Choose 3:

sublist = [1, 2, 3]

Base case reached.
Store [1, 2, 3].

Backtrack:

sublist = [1, 2]

Return to the second position.

Choose 3:

sublist = [1, 3]

Choose 2:

sublist = [1, 3, 2]

Store [1, 3, 2].

Backtrack completely to [].

Choose 2:

sublist = [2]

Choose 1, then 3:

Store [2, 1, 3].

Backtrack and choose 3, then 1:

Store [2, 3, 1].

Backtrack completely to [].

Choose 3:

sublist = [3]

Choose 1, then 2:

Store [3, 1, 2].

Backtrack and choose 2, then 1:

Store [3, 2, 1].

FINAL OUTPUT:

[
    [1, 2, 3],
    [1, 3, 2],
    [2, 1, 3],
    [2, 3, 1],
    [3, 1, 2],
    [3, 2, 1]
]

--------------------------------------------------
10. WHY n! PERMUTATIONS?
--------------------------------------------------

For n = 3:

First position:
3 choices.

Second position:
2 choices.

Third position:
1 choice.

Total:

3 * 2 * 1 = 3! = 6.

General formula:

Number of permutations = n!

--------------------------------------------------
11. IMPORTANT OBSERVATIONS
--------------------------------------------------

1. Every element must appear exactly once.

2. Order matters in permutations.

3. for(int i : nums) checks every element
   at every recursive level.

4. sublist.contains(i) prevents reuse.

5. continue skips an already-used element.

6. add() chooses an element.

7. Recursive call explores the choice.

8. remove() undoes the choice.

9. new ArrayList<>(sublist) stores an
   independent copy.

10. This implementation assumes all input
    elements are distinct, as guaranteed by LC 46.

11. For duplicate input values, contains()
    cannot distinguish between equal elements.

--------------------------------------------------
12. COMPLEXITY ANALYSIS
--------------------------------------------------

Number of permutations:

n!

Time Complexity:

O(n^2 * n!)

Reason:
The loop checks n elements, and contains()
can take O(n) time.

Auxiliary Space:

O(n)

Recursion depth and current permutation
size are at most n.

Output Space:

O(n * n!)

--------------------------------------------------
13. ORIGINAL JAVA IMPLEMENTATION
--------------------------------------------------

--------------------------------------------------
14. FINAL TAKEAWAY
--------------------------------------------------

For every recursive level:

1. Try every number.
2. Skip numbers already selected.
3. Add an available number.
4. Explore recursively.
5. Remove the number.
6. Try another available number.

Remember:

SUBSETS:
Include or exclude.

PERMUTATIONS:
Choose any unused element.

This is the key difference between these
two backtracking patterns.
*/


class Solution {
    public void helper(int[] nums,  List<Integer> sublist, List<List<Integer>> list){
        if(sublist.size() == nums.length){
            list.add(new ArrayList<>(sublist));
            return;
        }

        for(int i : nums){
            if(sublist.contains(i))
                continue;
            sublist.add(i);
            helper(nums, sublist, list);
            sublist.remove(sublist.size()-1);
        }
    }
    public List<List<Integer>> permute(int[] nums) {

        List<Integer> sublist = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        helper(nums, sublist, list);

        return list;

    }
}
