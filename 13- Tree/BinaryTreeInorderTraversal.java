
/*
Problem Name: Binary Tree Inorder Traversal
LeetCode Number: 94
Difficulty: Easy
Pattern: Binary Tree + DFS + Recursion

==================================================
1. PROBLEM UNDERSTANDING
==================================================

We are given the root of a binary tree.

We need to return the values of all nodes using
Inorder Traversal.

Inorder Traversal follows:

LEFT -> ROOT -> RIGHT

Meaning:

1. Visit the left subtree completely.
2. Process the current node.
3. Visit the right subtree completely.

Example:

            1
          /   \
         2     3
        / \   / \
       4   5 6   7

Inorder output:

[4, 2, 5, 1, 6, 3, 7]

==================================================
2. EXPLAIN LIKE I AM 10 YEARS OLD
==================================================

Imagine you are visiting houses in a tree-shaped
neighborhood.

Every house can have:
- A left-side neighbor.
- A right-side neighbor.

You must follow this visiting rule:

First visit the left neighborhood.
Then visit the current house.
Then visit the right neighborhood.

For every house, follow the same rule again.

This is called Inorder Traversal.

LEFT -> ROOT -> RIGHT

==================================================
3. RECURSION APPROACH
==================================================

We use recursion because every subtree is itself
a smaller binary tree.

For every node:

1. Call helper for the left child.
2. Add the current node's value to the list.
3. Call helper for the right child.

The same process repeats for every node.

==================================================
4. HELPER METHOD
==================================================

public void helper(TreeNode root, List<Integer> list)

Parameters:

root:
The current node we are visiting.

list:
Stores the values in Inorder order.

We do not need an index because the tree's
left and right pointers tell us where to go.

==================================================
5. BASE CASE
==================================================

if(root == null)
    return;

When root is null, there is no node to process.

This happens when:
- A node has no left child.
- A node has no right child.
- The entire tree is empty.

We stop that recursive call.

Without this condition, accessing root.left or
root.right when root is null would cause a
NullPointerException.

==================================================
6. LEFT SUBTREE
==================================================

helper(root.left, list);

We completely visit the left subtree before
processing the current node.

This means recursion continues going left until
it reaches a null node.

When null is reached:
- Return to the previous node.
- Continue executing the remaining statements.

==================================================
7. PROCESS THE CURRENT NODE
==================================================

list.add(root.val);

This line executes after the left subtree is
completely processed.

We add the current node's value to the list.

This is the ROOT / NODE processing step.

==================================================
8. RIGHT SUBTREE
==================================================

helper(root.right,list);

After processing the current node, we visit
the right subtree.

The complete order is:

helper(root.left, list);
list.add(root.val);
helper(root.right, list);

This is:

LEFT -> ROOT -> RIGHT

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
Node 1 calls its left child, Node 2.

Step 2:
Node 2 calls its left child, Node 4.

Step 3:
Node 4 calls its left child.

Left child is null.

Base condition returns.

Step 4:
Node 4 is added.

list = [4]

Step 5:
Node 4 calls its right child.

Right child is null.

Return to Node 2.

Step 6:
Node 2 is added.

list = [4, 2]

Step 7:
Node 2 calls its right child, Node 5.

Node 5 has no left child.

Add Node 5.

list = [4, 2, 5]

Node 5 has no right child.

Return to Node 1.

Step 8:
Node 1 is added.

list = [4, 2, 5, 1]

Step 9:
Node 1 calls its right child, Node 3.

Node 3 first visits Node 6.

Node 6 has no left child.

Add Node 6.

list = [4, 2, 5, 1, 6]

Node 6 has no right child.

Return to Node 3.

Step 10:
Add Node 3.

list = [4, 2, 5, 1, 6, 3]

Step 11:
Node 3 visits its right child, Node 7.

Node 7 has no left child.

Add Node 7.

list = [4, 2, 5, 1, 6, 3, 7]

Node 7 has no right child.

The traversal is complete.

Final output:

[4, 2, 5, 1, 6, 3, 7]

==================================================
10. RECURSION STACK
==================================================

When helper calls another helper:

helper(root.left, list);

The current call pauses and waits.

The child call executes.

After the child finishes:
- Execution returns to the parent.
- The parent continues from the next line.

Java manages this using the call stack.

We do not manually move back to parent nodes.

==================================================
11. OTHER TREE TRAVERSALS
==================================================

Inorder:

LEFT -> ROOT -> RIGHT

Preorder:

ROOT -> LEFT -> RIGHT

Postorder:

LEFT -> RIGHT -> ROOT

The difference is when we process root.val.

In this solution, root.val is processed between
the left and right recursive calls.

Therefore, this is Inorder Traversal.

==================================================
12. COMPLEXITY ANALYSIS
==================================================

Let n = number of nodes.

Time Complexity:

O(n)

Every node is visited once and added to the list
once.

Auxiliary Space:

O(h)

The recursion stack depends on tree height h.

Balanced tree:
O(log n)

Skewed tree:
O(n)

Output Space:

O(n)

The list stores the values of all nodes.

==================================================
13. ORIGINAL JAVA IMPLEMENTATION
==================================================
*/

class Solution {
    public void helper(TreeNode root, List<Integer> list ){
        if(root == null)
            return;

        helper(root.left, list);
        list.add(root.val);
        helper(root.right,list);

    }
    public List<Integer> inorderTraversal(TreeNode root) {
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
