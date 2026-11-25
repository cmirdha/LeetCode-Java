/*
===============================================================================
PROBLEM: Binary Tree Zigzag Level Order Traversal
LEETCODE: 103
DIFFICULTY: Medium
PATTERN: Binary Tree + BFS + Queue + Level Order

===============================================================================
APPROACH:

This problem is similar to Binary Tree Level Order Traversal (LC 102).

We use BFS to visit nodes level by level.

The difference is that alternate levels are reversed.

1. Create the final list.
2. Create a Deque to store TreeNode objects.
3. If root is null, return the empty list.
4. Insert root into the Deque.
5. Initialize lorder = true.
6. Process each level using the outer while loop.
7. Capture the current level size.
8. Process exactly that many nodes using the inner while loop.
9. Add node values to sublist in normal left-to-right order.
10. Insert left and right children into the Deque.
11. When the current level is completed:
      - If lorder is false, reverse sublist.
      - Add sublist to the final result.
      - Toggle lorder.
12. Return the final result.

===============================================================================
IMPORTANT VARIABLE:

boolean lorder = true;

true  -> Left to Right
false -> Right to Left

After each level:

lorder = !lorder;

This toggles the direction for the next level.

===============================================================================
IMPORTANT LOGIC:

if(!lorder)
    Collections.reverse(sublist);

The BFS naturally collects each level from left to right.

When lorder is false, we reverse that level to make it right to left.

===============================================================================
DRY RUN:

Tree:

                1
             /     \
            2       3
           / \     / \
          4   5   6   7

Level 1:
[1]

Level 2:
Normal: [2, 3]
Reverse: [3, 2]

Level 3:
[4, 5, 6, 7]

Output:

[[1], [3, 2], [4, 5, 6, 7]]

===============================================================================
COMPLEXITY:

Time Complexity: O(n)

Every node is processed once.
Reversing all levels combined takes O(n) time.

Space Complexity: O(n)

Queue + output list + current level list.

===============================================================================
ORIGINAL IMPLEMENTATION:
*/

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();

        if (root == null)
            return list;

        queue.offer(root);

        boolean lorder=true;

        while(!queue.isEmpty()){
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();
            while(size>0){
                TreeNode node = queue.poll();
                sublist.add(node.val);
                if(node.left!= null){
                    TreeNode leftnode = node.left;
                    queue.offer(leftnode);
                }

                if(node.right!= null){
                    TreeNode rightnode = node.right;
                    queue.offer(rightnode);
                }
                size--;
            }
            if(!lorder)
                Collections.reverse(sublist);

            list.add(sublist);
            lorder = !lorder;

        }

        return list;

    }
}

