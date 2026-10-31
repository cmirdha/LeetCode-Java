
/*
 * Problem Name: Reverse Nodes in Even Length Groups
 * LeetCode Number: 2074
 * Difficulty: Medium
 * Pattern: Linked List - In-place Reversal
 *
 * Approach:
 * Traverse the linked list in groups of increasing sizes.
 * Reverse a group only when its actual number of nodes is even.
 *
 * Step-by-step Logic:
 * 1. Initialize prevGroupTail = head.
 * 2. Start curr from head.next because the first group
 *    contains only one node and does not need reversal.
 * 3. Initialize groupSize = 2.
 * 4. For each group:
 *    a. Traverse at most groupSize nodes.
 *    b. Count the actual number of nodes.
 *    c. Identify kthNode, the last node of the group.
 * 5. Save kthNode.next as nextGroupHead.
 * 6. Disconnect the current group using kthNode.next = null.
 * 7. If the actual group size is even:
 *    a. Reverse the current group.
 *    b. Connect the previous group to reversedHead.
 *    c. Connect the original first node to nextGroupHead.
 *    d. Update prevGroupTail to curr.
 * 8. Otherwise:
 *    a. Keep the current group unchanged.
 *    b. Connect prevGroupTail to curr.
 *    c. Update prevGroupTail to kthNode.
 * 9. Move curr to nextGroupHead.
 * 10. Increment groupSize.
 * 11. Return head.
 *
 * Key Observations:
 * - The first group always contains one node.
 * - Expected group sizes increase by one: 1, 2, 3, 4...
 * - The last group may contain fewer nodes than expected.
 * - Reversal depends on actual group size, not expected size.
 * - After reversal, curr becomes the tail of the group.
 * - For an odd-sized group, kthNode remains the tail.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), using iterative pointer manipulation.
 */

class Solution {

    public ListNode reverseEvenLengthGroups(ListNode head) {

        ListNode prevGroupTail = head;
        ListNode curr = head.next;
        int groupSize = 2;

        while (curr != null) {

            // Find the actual group size and its last node.
            int count = 0;
            ListNode kthNode = curr;
            ListNode temp = curr;

            while (temp != null && count < groupSize) {
                kthNode = temp;
                temp = temp.next;
                count++;
            }

            // Save the beginning of the next group.
            ListNode nextGroupHead = kthNode.next;

            // Disconnect the current group.
            kthNode.next = null;

            if (count % 2 == 0) {

                // Reverse the current group.
                ListNode reversedHead = reverse(curr);

                // Connect the reversed group.
                prevGroupTail.next = reversedHead;

                // Connect the reversed group to the remaining list.
                curr.next = nextGroupHead;

                // Original first node becomes the tail.
                prevGroupTail = curr;

            } else {

                // Keep the current group unchanged.
                prevGroupTail.next = curr;

                // Last node becomes the previous group tail.
                prevGroupTail = kthNode;
            }

            // Move to the next group.
            curr = nextGroupHead;

            // Increase the expected group size.
            groupSize++;
        }

        return head;
    }

    // Reverse the isolated linked list.
    private ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
