
/*
 * Problem Name: Reverse Linked List
 * LeetCode Number: 206
 * Difficulty: Easy
 * Pattern: Linked List - In-place Reversal
 *
 * Approach:
 * Reverse the linked list iteratively using three pointers:
 * prev, curr, and next.
 *
 * Logic:
 * 1. If the list is empty or has only one node, return head.
 * 2. Initialize prev = null and curr = head.
 * 3. Traverse the linked list while curr is not null.
 * 4. Store curr.next in next to avoid losing the remaining list.
 * 5. Reverse the current node's pointer: curr.next = prev.
 * 6. Move prev to curr and curr to next.
 * 7. When the loop ends, prev points to the new head.
 *
 * Key Observations:
 * - prev represents the already-reversed portion.
 * - curr represents the node currently being processed.
 * - next temporarily saves the remaining nodes.
 * - We reverse links instead of creating new nodes.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), because only a constant number
 * of pointers are used.
 */

class Solution {
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // Save the next node before reversing the link.
            ListNode next = curr.next;

            // Reverse the current node's pointer.
            curr.next = prev;

            // Move prev forward to the current node.
            prev = curr;

            // Move curr forward to the saved next node.
            curr = next;
        }

        // prev points to the new head of the reversed list.
        return prev;
    }
}
