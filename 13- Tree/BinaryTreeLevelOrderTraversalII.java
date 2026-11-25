/*
===============================================================================
PROBLEM: Binary Tree Level Order Traversal II
LEETCODE: 107
DIFFICULTY: Medium
PATTERN: Binary Tree + BFS + Queue + Level Order Traversal

===============================================================================
APPROACH:

This problem uses the same BFS logic as LC 102.

1. Create the final list.
2. Create a Deque to store TreeNode objects.
3. If root is null, return the empty list.
4. Insert root into the Deque.
5. Process the tree level by level.
6. Create a new sublist for every level.
7. Capture the number of nodes in the current level using dq.size().
8. Process exactly that many nodes.
9. Add each node's value to the sublist.
10. Add left and right children to the Deque.
11. Add the completed sublist to the outer list.
12. After BFS finishes, reverse the outer list.
13. Return the final list.

===============================================================================
IMPORTANT CONCEPT:

Collections.reverse(list);

This reverses the OUTER list, which changes the order of levels.

It does NOT reverse the values inside each individual level.

===============================================================================
DRY RUN:

Tree:

              1
           /     \
          2       3
         / \     / \
        4   5   6   7

Normal BFS:

[[1], [2, 3], [4, 5, 6, 7]]

After reversing the outer list:

[[4, 5, 6, 7], [2, 3], [1]]

===============================================================================
COMPLEXITY:

Time Complexity: O(n)
Every node is visited once. Reversing the outer list takes O(number of levels).

Space Complexity: O(n)
Queue + sublists + result list.

===============================================================================
ORIGINAL IMPLEMENTATION:
*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        Deque<TreeNode> dq = new ArrayDeque<>();

        if(root == null)
            return list;

        dq.offer(root);

        while(!dq.isEmpty()){

            List<Integer> sublist = new ArrayList<>();
            int size = dq.size();

            while(size>0){
                TreeNode node = dq.poll();
                sublist.add(node.val);

                if(node.left != null)
                    dq.offer(node.left);

                if(node.right != null)
                    dq.offer(node.right);

                size--;
            }

            list.add(sublist);

        }

        Collections.reverse(list);

        return list;

    }
}
