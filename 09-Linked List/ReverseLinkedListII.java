
/*
 * Problem Name: Reverse Linked List II
 * LeetCode Number: 92
 * Difficulty: Medium
 * Pattern: Linked List - In-place Reversal
 *
 * Approach:
 * Use a dummy node to simplify handling cases where left = 1.
 * Find the node immediately before the reversal section.
 * Reverse exactly (right - left + 1) nodes.
 * Reconnect the reversed section with the remaining list.
 *
 * Step-by-step Logic:
 * 1. If the list is empty or left == right, return head.
 * 2. Create a dummy node and connect it to head.
 * 3. Move br to the node immediately before position left.
 * 4. Calculate the number of nodes to reverse:
 *    loop = right - left + 1.
 * 5. Set curr to the first node of the section.
 * 6. Save this first node in ar because it becomes
 *    the tail after reversal.
 * 7. Reverse the selected nodes using prev, curr, and next.
 * 8. Connect ar.next to curr, the first node after the section.
 * 9. Connect br.next to prev, the new head of the section.
 * 10. Return dummy.next.
 *
 * Key Observations:
 * - dummy handles reversal starting from the head.
 * - br points to the node before the reversed section.
 * - ar becomes the tail of the reversed section.
 * - prev becomes the new head of the reversed section.
 * - curr points to the unreversed remainder of the list.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), because only pointers are used.
 */

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode br = dummy;
        int count = 1;

        // Find the node immediately before position left.
        while (count < left) {
            br = br.next;
            count++;
        }

        // Number of nodes that must be reversed.
        int loop = right - left + 1;

        ListNode curr = br.next;

        // This node becomes the tail after reversal.
        ListNode ar = curr;

        ListNode prev = null;

        // Reverse the selected section.
        while (loop > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            loop--;
        }

        // Connect the old first node to the remaining list.
        ar.next = curr;

        // Connect the node before the section to its new head.
        br.next = prev;

        return dummy.next;
    }
}
