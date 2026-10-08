/*
 * Problem: Linked List Cycle II
 * LeetCode: #142
 * Difficulty: Medium
 *
 * Pattern: Fast & Slow Pointers
 *
 * Approach:
 * 1. Use two pointers, slow and fast.
 * 2. Move slow by one step and fast by two steps.
 * 3. If there is no cycle, fast will eventually reach null.
 * 4. If slow and fast meet, a cycle exists.
 * 5. After the meeting point, move slow back to the head.
 * 6. Move both slow and fast one step at a time.
 * 7. The point where they meet again is the starting point of the cycle.
 *
 * Explanation:
 * Let the distance from the head to the beginning of the cycle be l1.
 * Let the distance from the beginning of the cycle to the meeting point
 * be l2, and let the cycle length be c.
 *
 * When slow and fast meet:
 *
 *      Slow distance = l1 + l2
 *
 *      Fast distance = l1 + l2 + n * c
 *
 * Since fast moves twice as fast as slow:
 *
 *      2(l1 + l2) = l1 + l2 + n * c
 *
 * Therefore:
 *
 *      l1 + l2 = n * c
 *
 * So:
 *
 *      l1 = n * c - l2
 *
 * This means that if one pointer starts from the head and the other
 * starts from the meeting point, and both move one step at a time,
 * they will meet at the beginning of the cycle.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class Solution {

    public ListNode detectCycle(ListNode head) {

        ListNode slowptr = head;
        ListNode fastptr = head;
        ListNode pos = null;

        while (fastptr != null && fastptr.next != null) {

            slowptr = slowptr.next;
            fastptr = fastptr.next.next;

            if (slowptr == fastptr) {

                slowptr = head;

                while (slowptr != fastptr) {
                    slowptr = slowptr.next;
                    fastptr = fastptr.next;
                }

                pos = slowptr;
                return pos;
            }
        }

        return pos;
    }
}