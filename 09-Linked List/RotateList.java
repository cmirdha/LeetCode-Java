
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



/*
 * Problem Name: Rotate List
 * LeetCode Number: 61
 * Difficulty: Medium
 * Pattern: Linked List - Circular Linked List
 *
 * Approach:
 * Convert the linked list into a circular linked list.
 * Calculate the effective rotation using modulo.
 * Find the new tail and break the circular connection.
 *
 * Step-by-step Logic:
 * 1. If the list is empty, contains only one node, or k == 0,
 *    return head.
 * 2. Initialize temp = head and size = 1.
 * 3. Traverse the list to find the last node and calculate size.
 * 4. Connect the last node to head to form a circular list.
 * 5. Calculate the effective rotation:
 *    rotation = k % size.
 * 6. Calculate the number of steps to reach the new tail:
 *    count = size - rotation.
 * 7. Start traversenode from head.
 * 8. Move traversenode count - 1 times.
 * 9. Save the node after traversenode as nextnode.
 * 10. Break the circular connection:
 *     traversenode.next = null.
 * 11. Update head = nextnode.
 * 12. Return head.
 *
 * Key Observations:
 * - The last node is connected to head to form a circle.
 * - Modulo avoids unnecessary rotations.
 * - The new tail is at position size - rotation.
 * - The node after the new tail becomes the new head.
 * - Breaking the circular connection completes the rotation.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), because only pointers are used.
 */

class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode temp = head;
        int size = 1;

        // Find the last node and calculate the list size.
        while (temp != null && temp.next != null) {
            temp = temp.next;
            size++;
        }

        // Convert the linked list into a circular linked list.
        temp.next = head;

        // Calculate the effective rotation.
        int rotation = k % size;

        // Find the number of steps required to reach new tail.
        int count = size - rotation;

        ListNode traversenode = head;

        // Reach the new tail.
        while (count > 1) {
            traversenode = traversenode.next;
            count--;
        }

        // Save the new head.
        ListNode nextnode = traversenode.next;

        // Break the circular connection.
        traversenode.next = null;

        head = nextnode;

        return head;
    }
}
