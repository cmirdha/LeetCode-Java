
/*
 * Problem Name: Rotate List
 * LeetCode Number: 61
 * Difficulty: Medium
 * Pattern: Linked List - Pointer Manipulation
 *
 * Approach:
 * First calculate the length of the linked list.
 * Use modulo to optimize the number of rotations.
 * Move the last node to the beginning repeatedly.
 *
 * Step-by-step Logic:
 * 1. If the list is empty, contains one node, or k == 0,
 *    return head.
 * 2. Traverse the linked list to calculate its size.
 * 3. Calculate the effective rotations:
 *    rotation = k % size.
 * 4. Repeat while rotation > 0:
 *    a. Store the current head as Firstnode.
 *    b. Find the second-last node using findPrevLastNode().
 *    c. Get the last node using prevLastNode.next.
 *    d. Disconnect the last node from the list.
 *    e. Connect the last node to the original head.
 *    f. Update head to the last node.
 * 5. Return the updated head.
 *
 * Key Observations:
 * - Rotating a list by its length produces the original list.
 * - Modulo avoids unnecessary rotations.
 * - The second-last node is required to detach the last node.
 * - The detached last node becomes the new head.
 *
 * Complexity Analysis:
 * Time Complexity: O(n * (k % n)), where n is list length.
 * Space Complexity: O(1), because only pointers are used.
 */

class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Calculate the linked list size.
        int size = 0;
        ListNode temp = head;

        while (temp != null) {
            temp = temp.next;
            size++;
        }

        // Calculate the effective number of rotations.
        int rotation = k % size;

        while (rotation > 0) {

            ListNode Firstnode = head;

            // Find the second-last node.
            ListNode prevLastNode = findPrevLastNode(Firstnode);

            // Get the last node.
            ListNode LastNode = prevLastNode.next;

            // Disconnect the last node.
            prevLastNode.next = null;

            // Connect the last node to the original head.
            LastNode.next = Firstnode;

            // Update the head.
            head = LastNode;

            rotation--;
        }

        return head;
    }

    // Finds the second-last node.
    public ListNode findPrevLastNode(ListNode Firstnode) {

        ListNode curr = Firstnode;

        while (curr.next.next != null) {
            curr = curr.next;
        }

        return curr;
    }
}
