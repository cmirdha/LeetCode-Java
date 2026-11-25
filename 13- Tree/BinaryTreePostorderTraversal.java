/*
===============================================================================
PROBLEM: Binary Tree Postorder Traversal
LEETCODE: 145
DIFFICULTY: Easy
PATTERN: Binary Tree + DFS + Recursion

POSTORDER:
Left -> Right -> Root

===============================================================================
APPROACH:

1. If the current node is null, return.
2. Recursively process the left subtree.
3. Recursively process the right subtree.
4. Add the current node's value to the result list.

The current root is added only after BOTH children are processed.

===============================================================================
WHY THIS WORKS:

The recursive call completely processes a subtree before returning.

Therefore:
- The left subtree is completed first.
- The right subtree is completed second.
- The current root is added last.

This naturally produces Postorder traversal.

===============================================================================
DRY RUN:

Tree:

              1
           /     \
          2       3
         / \     / \
        4   5   6   7

Processing order:
4 -> 5 -> 2 -> 6 -> 7 -> 3 -> 1

Result:
[4, 5, 2, 6, 7, 3, 1]

===============================================================================
COMPLEXITY:

Time Complexity: O(n)
Every node is visited exactly once.

Auxiliary Space: O(h)
Recursion stack depends on tree height.

Balanced Tree: O(log n)
Skewed Tree: O(n)

Output Space: O(n)

===============================================================================
ORIGINAL IMPLEMENTATION:
*/

class Solution {
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);

    }
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;

    }
}

/*
===============================================================================
FILENAME: BinaryTreePostorderTraversal.java

FOLDER: 07-Binary-Trees/

GITHUB COMMIT MESSAGE:
Day 58 - Binary Tree Postorder Traversal
===============================================================================
*/