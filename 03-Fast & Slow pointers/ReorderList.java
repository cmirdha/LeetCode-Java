/*
 * Problem: Reorder List
 * LeetCode: #143
 * Difficulty: Medium
 *
 * Pattern: Fast & Slow Pointers + Linked List Reversal
 *
 * Approach:
 * 1. Return immediately if the list is empty or has only one node.
 * 2. Use slow and fast pointers to find the middle of the list.
 * 3. Reverse the second half of the linked list iteratively.
 * 4. Initialize firstptr at the head and secondptr at the
 *    head of the reversed second half.
 * 5. Alternately connect one node from the first half and
 *    one node from the reversed second half.
 * 6. Continue until the second half has been merged.
 *
 * Explanation:
 * The list must be reordered from:
 * L0 -> L1 -> L2 -> ... -> Ln
 *
 * Into:
 * L0 -> Ln -> L1 -> Ln-1 -> L2 -> ...
 *
 * First, slow and fast pointers locate the middle.
 * Then, the second half is reversed so its nodes can be
 * accessed from the last node toward the middle.
 *
 * Finally, the two halves are merged alternately.
 * The list is modified in place without creating new nodes.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public void reorderList(ListNode head) {

        ListNode fastptr = head;
        ListNode slowptr = head;

        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the linked list.
        while (fastptr != null && fastptr.next != null) {
            fastptr = fastptr.next.next;
            slowptr = slowptr.next;
        }

        // Step 2: Reverse the second half.
        ListNode prev = null;
        ListNode curr = slowptr;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: Merge the two halves alternately.
        ListNode firstptr = head;
        ListNode secondptr = prev;

        while (secondptr.next != null) {
            ListNode firstNext = firstptr.next;
            ListNode secondNext = secondptr.next;

            firstptr.next = secondptr;
            secondptr.next = firstNext;

            firstptr = firstNext;
            secondptr = secondNext;
        }
    }
}