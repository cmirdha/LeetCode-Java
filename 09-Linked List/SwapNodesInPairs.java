
/*
 * Problem Name: Swap Nodes in Pairs
 * LeetCode Number: 24
 * Difficulty: Medium
 * Pattern: Linked List - Recursion
 *
 * Approach:
 * Use recursion to swap adjacent nodes in pairs.
 * A dummy node is used to simplify the first swap.
 *
 * Step-by-step Logic:
 * 1. Create a dummy node and connect it to head.
 * 2. Call swapnode(dummy).
 * 3. Inside swapnode(), node represents the node
 *    immediately before the pair to be swapped.
 * 4. first = node.next represents the first node.
 * 5. second = first.next represents the second node.
 * 6. If both nodes exist:
 *    a. Save second.next in secondnext.
 *    b. Make second.next point to first.
 *    c. Make node.next point to second.
 *    d. Connect first.next to secondnext.
 * 7. Recursively call swapnode(first).
 * 8. Return dummy.next.
 *
 * Key Observations:
 * - node is the previous node before the current pair.
 * - first and second are the nodes being swapped.
 * - secondnext saves the remaining linked list.
 * - After swapping, first becomes the previous node
 *   for the next recursive call.
 * - An incomplete pair is left unchanged.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(n), due to recursive call stack.
 */

class Solution {
    public ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        swapnode(dummy);

        return dummy.next;
    }

    public void swapnode(ListNode node) {

        if (node == null) {
            return;
        }

        ListNode first = node.next;
        ListNode second = null;

        if (first != null) {
            second = first.next;
        }

        if (second != null) {

            // Save the remaining linked list.
            ListNode secondnext = second.next;

            // Reverse the connection between the pair.
            second.next = first;

            // Connect the previous node to the second node.
            node.next = second;

            // Connect the first node to the remaining list.
            first.next = secondnext;

            // Process the next pair.
            swapnode(first);
        }
    }
}
