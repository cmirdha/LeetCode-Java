/*
 * Problem: Middle of the Linked List
 * LeetCode: #876
 * Difficulty: Easy
 *
 * Pattern: Fast & Slow Pointers or Floyd's Tortoise and Hare
 *
 * Approach:
 * 1. Initialize both slow and fast pointers at the head.
 * 2. Move slow one node at a time.
 * 3. Move fast two nodes at a time.
 * 4. Continue while fast and fast.next are not null.
 * 5. When fast reaches the end, slow points to the middle node.
 * 6. Return slow.
 *
 * Explanation:
 * The fast pointer moves twice as quickly as the slow pointer.
 * When fast reaches the end of the linked list, slow has traversed
 * approximately half the list and points to its middle node.
 *
 * If the list contains an even number of nodes, this approach returns
 * the second middle node, as required by the problem.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        head = slow;

        return head;
    }
}
