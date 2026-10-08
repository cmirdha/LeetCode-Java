/*
 * Problem: Linked List Cycle
 * LeetCode: #141
 * Difficulty: Easy
 *
 * Pattern: Fast & Slow Pointers
 *
 * Approach:
 * 1. Use two pointers, slowptr and fastptr, starting from the head.
 * 2. Move slowptr one node at a time.
 * 3. Move fastptr two nodes at a time.
 * 4. If the linked list contains a cycle, the two pointers will
 *    eventually meet.
 * 5. If fastptr reaches null or fastptr.next reaches null, the linked
 *    list does not contain a cycle.
 *
 * Explanation:
 * The Fast & Slow Pointer technique uses two pointers moving at
 * different speeds.
 *
 * If there is no cycle, the fast pointer will eventually reach the
 * end of the linked list.
 *
 * If a cycle exists, the fast pointer will keep moving around the
 * cycle and eventually catch up with the slow pointer. Therefore,
 * slowptr == fastptr confirms that a cycle exists.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class Solution {

    public boolean hasCycle(ListNode head) {

        ListNode fastptr = head;
        ListNode slowptr = head;
        boolean iscyclic = false;

        while (fastptr != null && fastptr.next != null) {

            slowptr = slowptr.next;
            fastptr = fastptr.next.next;

            if (slowptr == fastptr) {
                iscyclic = true;
                break;
            }
        }

        return iscyclic;
    }
}