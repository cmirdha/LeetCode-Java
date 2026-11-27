/*
Problem: Subtree of Another Tree
LeetCode: 572
Difficulty: Easy

Pattern:
Binary Tree + DFS + Recursion
Tree Comparison + Subtree Search

--------------------------------------------------
1. PROBLEM
--------------------------------------------------

Check whether subRoot exists inside root as an
exact subtree.

An exact subtree must have:
1. Same node values.
2. Same structure.
3. No extra or missing children.

--------------------------------------------------
2. INTUITION
--------------------------------------------------

Search every node of the main tree.

At every node:
1. Check if the tree starting here matches subRoot.
2. If yes, return true.
3. If not, search the left subtree.
4. If not found on left, search the right subtree.

--------------------------------------------------
3. TWO METHODS
--------------------------------------------------

sameTree(p, q):

Checks whether two trees are identical.

isSubtree(root, subRoot):

Searches the main tree for a node where the
subtree starting at that node matches subRoot.

--------------------------------------------------
4. SAME TREE BASE CASES
--------------------------------------------------

Both null:
Return true.

One null:
Return false.

Different values:
Return false.

Otherwise, recursively compare:
Left with Left.
Right with Right.

Both must match.

--------------------------------------------------
5. SUBTREE SEARCH
--------------------------------------------------

Base case:

if(root == null)
    return false;

No nodes left to search.

Check current node:

if(sameTree(root, subRoot))
    return true;

If the current tree matches, subtree found.

Otherwise search:

isSubtree(root.left, subRoot)
isSubtree(root.right, subRoot)

Use OR because finding the subtree on either
side is sufficient.

--------------------------------------------------
6. DRY RUN
--------------------------------------------------

Main tree:

             3
           /   \
          4     5
         / \
        1   2

Subtree:

       4
      / \
     1   2

At node 3:
sameTree(3,4) -> false

Search left.

At node 4:
sameTree(4,4) -> true

Compare children:
1 == 1 -> true
2 == 2 -> true

Found exact subtree.

Result: true

--------------------------------------------------
7. PATTERN
--------------------------------------------------

Outer recursion:
Preorder DFS to search candidate nodes.

Inner recursion:
Compare two trees simultaneously.

This is an example of reusing a previously
implemented helper function.

--------------------------------------------------
8. COMPLEXITY
--------------------------------------------------

Time Complexity: O(n * m)

Worst case, each of n nodes triggers a comparison
of up to m nodes.

Auxiliary Space: O(hn + hm)

Recursive stack of the outer search and inner
tree comparison.

Worst case: O(n + m)

--------------------------------------------------
9. ORIGINAL IMPLEMENTATION
--------------------------------------------------
*/

class Solution {

    public boolean sameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null)
            return true;

        if(p == null || q == null)
            return false;

        if(p.val != q.val)
            return false;

        return sameTree(p.left, q.left)
                && sameTree(p.right, q.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if(root == null)
            return false;

        if(sameTree(root, subRoot))
            return true;

        return isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot);
    }
}