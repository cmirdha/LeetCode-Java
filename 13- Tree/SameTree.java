/*
Problem: Same Tree
LeetCode: 100
Difficulty: Easy

Pattern:
Binary Tree + DFS + Recursion + Two Nodes

--------------------------------------------------
1. PROBLEM
--------------------------------------------------

Given two binary trees, check whether they are
identical.

Two trees are identical if:
1. They have the same structure.
2. Corresponding nodes have the same values.

--------------------------------------------------
2. INTUITION
--------------------------------------------------

Imagine comparing two trees side by side.

At every step, compare two corresponding nodes.

If:
- Both are null -> same.
- Only one is null -> different.
- Values differ -> different.
- Values match -> compare their children.

--------------------------------------------------
3. RECURSIVE STATE
--------------------------------------------------

helper(TreeNode firstnode, TreeNode secondnode)

We pass two nodes simultaneously because we need
to compare corresponding positions in two trees.

--------------------------------------------------
4. BASE CASES
--------------------------------------------------

Case 1:
Both nodes are null.

if(firstnode == null && secondnode == null)

Return true.

Both positions are empty.

Case 2:
Only one node is null.

if(firstnode == null || secondnode == null)

Return false.

The tree structures differ.

Case 3:
Values are different.

if(firstnode.val != secondnode.val)

Return false.

--------------------------------------------------
5. RECURSIVE CALLS
--------------------------------------------------

Compare left children:

helper(firstnode.left, secondnode.left)

Compare right children:

helper(firstnode.right, secondnode.right)

Both must match:

return result1 && result2;

--------------------------------------------------
6. DRY RUN
--------------------------------------------------

Tree P:

       1
      / \
     2   3

Tree Q:

       1
      / \
     2   3

Compare root: 1 == 1

Compare left: 2 == 2
Compare right: 3 == 3

All children match.

Result: true

--------------------------------------------------
7. IMPORTANT DIFFERENCE
--------------------------------------------------

Same Tree:
Compare children in the SAME direction.

Left  <-> Left
Right <-> Right

Symmetric Tree:
Compare children in MIRROR direction.

Left  <-> Right
Right <-> Left

--------------------------------------------------
8. COMPLEXITY
--------------------------------------------------

Time Complexity: O(min(n,m))

In the worst case, we compare every node in the
smaller tree.

Auxiliary Space: O(h)

Recursive stack depends on tree height.

Balanced: O(log n)
Skewed: O(n)

--------------------------------------------------
9. ORIGINAL IMPLEMENTATION
--------------------------------------------------
*/

class Solution {
    public boolean helper(TreeNode firstnode, TreeNode secondnode) {

        if(firstnode == null && secondnode == null)
            return true;

        if(firstnode == null || secondnode == null)
            return false;

        if(firstnode.val != secondnode.val)
            return false;

        boolean result1 = helper(firstnode.left, secondnode.left);
        boolean result2 = helper(firstnode.right, secondnode.right);

        return result1 && result2;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        TreeNode firstnode = p;
        TreeNode secondnode = q;

        boolean result = helper(firstnode, secondnode);

        return result;
    }
}