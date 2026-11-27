/*
Problem: Flip Equivalent Binary Trees
LeetCode: 951
Difficulty: Medium

Pattern:
Binary Tree + DFS + Recursion + Choices

--------------------------------------------------
1. PROBLEM
--------------------------------------------------

Two trees are flip equivalent if we can make them
identical by swapping left and right children at
any number of nodes.

At every node, we have two choices:

1. Don't flip.
2. Flip.

--------------------------------------------------
2. INTUITION
--------------------------------------------------

At every pair of corresponding nodes:

First check:
- Both null -> true.
- One null -> false.
- Different values -> false.

If values match, explore two possibilities:

Choice 1: Don't flip.
Compare left with left.
Compare right with right.

Choice 2: Flip.
Compare left with right.
Compare right with left.

If either choice works, the trees are equivalent.

--------------------------------------------------
3. RECURSIVE STATE
--------------------------------------------------

flipEquiv(root1, root2)

The state contains two nodes which we are checking
for flip equivalence.

--------------------------------------------------
4. BASE CASES
--------------------------------------------------

Both null:
Return true.

Only one null:
Return false.

Different values:
Return false.

--------------------------------------------------
5. CHOICE 1: WITHOUT FLIPPING
--------------------------------------------------

Compare:

root1.left  with root2.left
root1.right with root2.right

Both comparisons must be true.

res1 =
    flipEquiv(root1.left, root2.left)
    &&
    flipEquiv(root1.right, root2.right)

--------------------------------------------------
6. CHOICE 2: WITH FLIPPING
--------------------------------------------------

Compare:

root1.left  with root2.right
root1.right with root2.left

Both comparisons must be true.

res2 =
    flipEquiv(root1.left, root2.right)
    &&
    flipEquiv(root1.right, root2.left)

--------------------------------------------------
7. FINAL RESULT
--------------------------------------------------

return res1 || res2;

Either orientation must work.

--------------------------------------------------
8. DRY RUN
--------------------------------------------------

Tree 1:

        1
       / \
      2   3

Tree 2:

        1
       / \
      3   2

At root:
1 == 1

Without flip:
2 != 3
Result: false

With flip:
2 == 2
3 == 3
Result: true

Final:

false || true = true

--------------------------------------------------
9. COMPLEXITY
--------------------------------------------------

Time: Commonly stated O(n * m)

Auxiliary Space: O(h1 + h2)

Recursive stack depends on the heights of both
trees.

--------------------------------------------------
10. ORIGINAL IMPLEMENTATION
--------------------------------------------------
*/

class Solution {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 ==null)
            return true;

        if(root1 == null || root2 ==null)
            return false;

        if(root1.val!=root2.val)
            return false;

        // Without flipping
        boolean res1 =
                flipEquiv(root1.left,root2.left)
                        &&
                        flipEquiv(root1.right,root2.right);

        // With flipping
        boolean res2 =
                flipEquiv(root1.left,root2.right)
                        &&
                        flipEquiv(root1.right,root2.left);

        return res1 || res2;
    }
}