/*
 * Problem: Palindrome Linked List
 * LeetCode: #234
 * Difficulty: Easy
 *
 * Pattern: Fast & Slow Pointers + In-place Linked List Reversal
 *
 * Approach:
 * 1. Initialize slowptr and fastptr at the head of the linked list.
 * 2. Move slowptr one step and fastptr two steps at a time to find the middle.
 * 3. Start from slowptr and reverse the second half of the linked list.
 * 4. Initialize first at the original head and second at the head of the reversed half.
 * 5. Compare the values of both halves node by node.
 * 6. If any pair of values differs, return false.
 * 7. If all values match, return true.
 *
 * Explanation:
 *
 * Step 1: Find the Middle
 * - slowptr moves one node at a time using slowptr = slowptr.next.
 * - fastptr moves two nodes at a time using fastptr = fastptr.next.next.
 * - When fastptr reaches the end, slowptr points to the middle.
 * - For an odd-length list, slowptr points to the actual middle node.
 * - For an even-length list, slowptr points to the first node of the second half.
 *
 * Step 2: Reverse the Second Half
 * - Set current = slowptr to start reversing from the middle.
 * - Set previous = null because the last node of the reversed list must point to null.
 * - Store current.next in next before changing the link, so the remaining list is not lost.
 * - Set current.next = previous to reverse the current node's link.
 * - Move previous = current and current = next to continue.
 * - When the loop ends, previous points to the head of the reversed second half.
 *
 * Step 3: Compare Both Halves
 * - Set first = head to traverse the original first half.
 * - Set second = previous to traverse the reversed second half.
 * - Compare first.val and second.val.
 * - If they differ, the list is not a palindrome, so return false.
 * - Otherwise, move both pointers one node forward and continue.
 * - The comparison stops when second becomes null. Every node in the
 *   second half has then been checked, so return true.
 *
 * Example:
 * Original list:  1 -> 2 -> 2 -> 1 -> null
 *
 * After finding the middle:
 * slowptr points to the second 2.
 *
 * After reversing the second half:
 * First half:   1 -> 2 -> ...
 * Reversed half: 1 -> 2 -> null
 *
 * Comparisons:
 * 1 == 1
 * 2 == 2
 *
 * All values match, so the result is true.
 *
 * Time Complexity: O(n)
 * - Finding the middle takes O(n).
 * - Reversing the second half takes O(n).
 * - Comparing the halves takes O(n).
 * - Overall: O(n).
 *
 * Space Complexity: O(1)
 * - Only a fixed number of pointers are used.
 * - The list is reversed in place without an extra array or collection.
 *
 * Note:
 * This implementation modifies the original linked list by reversing
 * its second half. It does not restore the original list afterward.
 */

class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode fastptr = head;
        ListNode slowptr = head;

        while (fastptr != null && fastptr.next != null) {
            slowptr = slowptr.next;
            fastptr = fastptr.next.next;
        }

        ListNode current = slowptr;
        ListNode previous = null;

        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        ListNode first = head;
        ListNode second = previous;

        while (second != null) {
            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }
}