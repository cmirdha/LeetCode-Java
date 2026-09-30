/*
 * Problem: Remove Duplicates from Sorted List
 * LeetCode: #83
 * Difficulty: Easy
 *
 * Pattern: Linked List
 *
 * Approach:
 * Since the linked list is sorted, duplicate values will be next
 * to each other.
 *
 * Compare the current node with the next node:
 * - If they are equal, skip the duplicate node.
 * - Otherwise, move to the next node.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;  // keep a pointer to traverse

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;  // skip duplicate
            } else {
                current = current.next;  // move forward
            }
        }

        return head;  // return the original head
    }
}
