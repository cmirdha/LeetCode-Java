/*
===============================================================================
PROBLEM: Invert Binary Tree
LEETCODE: 226
DIFFICULTY: Easy
PATTERN: Binary Tree + DFS + Recursion + Tree Manipulation

===============================================================================
APPROACH:

Use recursive Postorder Traversal.

For every node:

1. Invert its left subtree.
2. Invert its right subtree.
3. Swap its left and right children.

Traversal order:

Left -> Right -> Root

===============================================================================
WHY POSTORDER?

We first invert the children.

After both subtrees are processed, we swap the current node's children.

This guarantees that the complete tree is inverted.

===============================================================================
SWAP LOGIC:

Tree before:

        2
       / \
      1   3

Tree after:

        2
       / \
      3   1

Steps:

1. Save original left child in temp.
2. Move original right child to left.
3. Move temp to right.

===============================================================================
DRY RUN:

Original tree:

                4
             /     \
            2       7
           / \     / \
          1   3   6   9

Postorder processing:

1 -> 3 -> 2 -> 6 -> 9 -> 7 -> 4

Final inverted tree:

                4
             /     \
            7       2
           / \     / \
          9   6   3   1

===============================================================================
IMPORTANT OBSERVATION:

The tree is modified in place.

No new nodes are created.

The root reference remains the same.

===============================================================================
COMPLEXITY:

Time Complexity: O(n)
Every node is visited once.

Auxiliary Space: O(h)
Recursion stack depends on tree height.

Balanced Tree: O(log n)
Skewed Tree: O(n)

===============================================================================
ORIGINAL IMPLEMENTATION:
*/

class Solution {
    public void helper(TreeNode node){
        if(node == null)
            return;

        helper(node.left);
        helper(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;


    }
    public TreeNode invertTree(TreeNode root) {
        TreeNode node = root;

        if(node == null || node.right == null && node.left == null)
            return root;

        helper(node);

        return root;

    }
}
