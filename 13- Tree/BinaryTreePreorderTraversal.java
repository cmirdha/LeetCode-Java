
/*
Problem Name: Binary Tree Preorder Traversal
LeetCode Number: 144
Difficulty: Easy
Pattern: Binary Tree + DFS + Recursion

==================================================
1. PROBLEM UNDERSTANDING
==================================================

We are given the root of a binary tree.

We need to return all node values using Preorder
Traversal.

Preorder follows:

ROOT -> LEFT -> RIGHT

Meaning:

1. Process the current node first.
2. Visit the left subtree.
3. Visit the right subtree.

Example:

            1
          /   \
         2     3
        / \   / \
       4   5 6   7

Preorder output:

[1, 2, 4, 5, 3, 6, 7]

==================================================
2. EXPLAIN LIKE I AM 10 YEARS OLD
==================================================

Imagine you are exploring a tree-shaped building.

At every room:

1. First write down the current room number.
2. Explore the left side completely.
3. Explore the right side completely.

Repeat the same rule for every room.

This is called Preorder Traversal.

ROOT -> LEFT -> RIGHT

==================================================
3. APPROACH
==================================================

We use recursion.

For every node:

1. Add the current node's value.
2. Recursively visit the left child.
3. Recursively visit the right child.

The same process repeats for every subtree.

==================================================
4. HELPER METHOD
==================================================

public void helper(TreeNode root, List<Integer> list)

Parameters:

root:
The current node we are visiting.

list:
Stores all values in Preorder order.

We pass the same list to all recursive calls
because we want one final traversal result.

==================================================
5. BASE CASE
==================================================

if (root == null)
    return;

If the current node does not exist, stop.

This can happen when:
- A node has no left child.
- A node has no right child.
- The tree itself is empty.

This prevents NullPointerException.

==================================================
6. PROCESS THE CURRENT NODE FIRST
==================================================

list.add(root.val);

We add the current node before visiting its children.

Example:

At root = 1

list = [1]

This is the ROOT processing step.

This is what makes the traversal Preorder.

==================================================
7. VISIT THE LEFT SUBTREE
==================================================

helper(root.left, list);

After processing the current node, explore its
left subtree completely.

Every left subtree follows the same rule:

ROOT -> LEFT -> RIGHT

==================================================
8. VISIT THE RIGHT SUBTREE
==================================================

helper(root.right, list);

After the left subtree is completely finished,
explore the right subtree.

The complete order is:

list.add(root.val);
helper(root.left, list);
helper(root.right, list);

This is:

ROOT -> LEFT -> RIGHT

==================================================
9. COMPLETE DRY RUN
==================================================

Tree:

            1
          /   \
         2     3
        / \   / \
       4   5 6   7

Initially:

root = 1
list = []

Step 1:

Add node 1.

list = [1]

Go left to node 2.

Step 2:

Add node 2.

list = [1, 2]

Go left to node 4.

Step 3:

Add node 4.

list = [1, 2, 4]

Node 4 has no left child.

Return.

Node 4 has no right child.

Return to node 2.

Step 4:

Visit node 2's right child, node 5.

Add node 5.

list = [1, 2, 4, 5]

Node 5 has no children.

Return to node 1.

Step 5:

Visit node 1's right child, node 3.

Add node 3.

list = [1, 2, 4, 5, 3]

Go left to node 6.

Step 6:

Add node 6.

list = [1, 2, 4, 5, 3, 6]

Node 6 has no children.

Return to node 3.

Step 7:

Visit node 3's right child, node 7.

Add node 7.

list = [1, 2, 4, 5, 3, 6, 7]

Node 7 has no children.

Traversal is complete.

Final result:

[1, 2, 4, 5, 3, 6, 7]

==================================================
10. RECURSION STACK
==================================================

When we call:

helper(root.left, list);

The current call pauses and waits for the left
subtree to finish.

After the child call returns:
- The parent continues execution.
- The parent then explores its right subtree.

Java manages this process through the call stack.

We do not manually return to parent nodes.

==================================================
11. COMPARISON WITH OTHER TRAVERSALS
==================================================

Preorder:

ROOT -> LEFT -> RIGHT

Inorder:

LEFT -> ROOT -> RIGHT

Postorder:

LEFT -> RIGHT -> ROOT

The difference is the position of:

list.add(root.val);

In Preorder, we add it before both recursive calls.

==================================================
12. COMPLEXITY ANALYSIS
==================================================

Let n = number of nodes.

Time Complexity:

O(n)

Every node is visited once.

Auxiliary Space:

O(h)

The recursive call stack depends on tree height.

Balanced tree:
O(log n)

Skewed tree:
O(n)

Output Space:

O(n)

We store every node's value in the result list.

==================================================
13. ORIGINAL JAVA IMPLEMENTATION
==================================================
*/

class Solution {
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        list.add(root.val);
        helper(root.left, list);
        helper(root.right, list);

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;

    }
}

/*
==================================================
END OF NOTES
==================================================
*/
