/*
Problem: Symmetric Tree
LeetCode: 101
Difficulty: Easy

Pattern:
Binary Tree + DFS + Recursion + Two Nodes

--------------------------------------------------
1. PROBLEM
--------------------------------------------------

Check whether a binary tree is symmetric around its
center, meaning the left and right subtrees are mirror
images of each other.

Mirror symmetry requires:
1. Corresponding nodes must have equal values.
2. If one corresponding node is null, the other must
   also be null.
3. Left and right positions must be mirrored.

--------------------------------------------------
2. INTUITION
--------------------------------------------------

Imagine two people checking opposite sides of a mirror.

One person checks the left subtree.
The other person checks the right subtree.

At every step, they compare two nodes:

- If both are null -> matching.
- If only one is null -> not matching.
- If values differ -> not matching.
- If values match -> compare their children in
  mirror positions.

--------------------------------------------------
3. RECURSIVE STATE
--------------------------------------------------

helper(TreeNode leftnode, TreeNode rightnode)

The state consists of TWO nodes because we need to
compare the left and right sides simultaneously.

--------------------------------------------------
4. BASE CASES
--------------------------------------------------

Case 1:
if(leftnode == null && rightnode == null)

Both positions are empty.

Return true.

Case 2:
if(leftnode == null || rightnode == null)

Only one position is empty.

Return false.

Case 3:
if(leftnode.val != rightnode.val)

Both nodes exist, but their values differ.

Return false.

--------------------------------------------------
5. RECURSIVE LOGIC
--------------------------------------------------

Compare outer children:

helper(leftnode.right, rightnode.left)

Compare inner children:

helper(leftnode.left, rightnode.right)

Both comparisons must be true:

return res1 && res2;

We compare opposite sides because the tree must
be a mirror image, not an identical copy.

--------------------------------------------------
6. EXAMPLE
--------------------------------------------------

             1
           /   \
          2     2
         / \   / \
        3   4 4   3

Compare:

Left node 2 with right node 2.

Outer comparison:
leftnode.right (4)
rightnode.left (4)

Inner comparison:
leftnode.left (3)
rightnode.right (3)

Both pairs match, so return true.

--------------------------------------------------
7. COMPLEXITY
--------------------------------------------------

Time Complexity: O(n)

Every node is checked at most once.

Auxiliary Space: O(h)

Recursive call stack depends on tree height.

Balanced tree: O(log n)
Skewed tree: O(n)

--------------------------------------------------
8. ORIGINAL IMPLEMENTATION
--------------------------------------------------
*/

class Solution {
    // The state is a pair of nodes.
    // For every pair, check whether they are mirror images.
    public boolean helper(TreeNode leftnode, TreeNode rightnode){

        if(leftnode == null && rightnode == null)
            return true;

        if(leftnode ==null ||  rightnode == null)
            return false;

        if(leftnode.val != rightnode.val )
            return false;

        boolean res1 = helper(leftnode.right,rightnode.left);
        boolean res2 = helper(leftnode.left, rightnode.right);

        return res1&&res2 ;
    }

    public boolean isSymmetric(TreeNode root) {
        TreeNode node = root;

        if(root == null || root.left ==null && root.right ==null)
            return true;

        TreeNode left = root.left;
        TreeNode right = root.right;

        boolean result = helper(left , right);

        return result;
    }
}