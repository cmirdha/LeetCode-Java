/*
===============================================================================
PROBLEM: Binary Tree Level Order Traversal
LEETCODE: 102
DIFFICULTY: Medium
PATTERN: Binary Tree + BFS + Queue

===============================================================================
APPROACH:

Use a Queue to process nodes level by level.

1. Create the final list to store the result of each level.
2. Create a Queue to store tree nodes waiting to be processed.
3. If root is null, return the empty result.
4. Add root to the Queue.
5. While Queue is not empty:
       a. Create a new sublist for the current level.
       b. Store the current Queue size in size.
       c. Process exactly size nodes.
       d. Remove one node from Queue.
       e. Add its value to sublist.
       f. Add its left and right children to Queue if they exist.
       g. Decrease size.
       h. Add sublist to the final list.
6. Return the final list.

===============================================================================
IMPORTANT CONCEPT:

int size = queue.size();

This captures the number of nodes belonging to the current level.

Even if children are inserted into the Queue while processing the current
level, the inner loop processes only the original size of that level.

===============================================================================
DRY RUN:

Tree:

              1
           /     \
          2       3
         / \     / \
        4   5   6   7

Level 1: [1]
Level 2: [2, 3]
Level 3: [4, 5, 6, 7]

Output:

[[1], [2, 3], [4, 5, 6, 7]]

===============================================================================
COMPLEXITY:

Time Complexity: O(n)
Every node is inserted, removed, and processed once.

Space Complexity: O(n)
Queue and output list can contain O(n) elements.

===============================================================================
ORIGINAL IMPLEMENTATION:
*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();

        if(root == null)
            return list;

        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();

            while (size>0) {
                TreeNode node = queue.poll();
                sublist.add(node.val);
                if (node.left != null) {
                    TreeNode leftnode = node.left;
                    queue.offer(leftnode);
                }
                if (node.right != null) {
                    TreeNode rightnode = node.right;
                    queue.offer(rightnode);
                }
                size--;
            }
            list.add(sublist);

        }

        return list;

    }
}

