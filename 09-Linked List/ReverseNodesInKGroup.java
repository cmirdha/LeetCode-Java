
/*
 * Problem Name: Reverse Nodes in k-Group
 * LeetCode Number: 25
 * Difficulty: Hard
 * Pattern: Linked List - In-place Reversal
 *
 * Approach:
 * Reverse the linked list in groups of k nodes.
 * Before reversing each group, check whether k nodes exist.
 * If fewer than k nodes remain, leave them unchanged.
 *
 * Step-by-step Logic:
 * 1. Initialize temp = head and Lprev = null.
 * 2. Use isPresent(temp, k) to find the kth node.
 * 3. If the kth node does not exist:
 *    a. Connect Lprev.next to temp if Lprev exists.
 *    b. Stop processing because fewer than k nodes remain.
 * 4. Save the node after the current group:
 *    nextNode = KthNode.next.
 * 5. Disconnect the current group:
 *    KthNode.next = null.
 * 6. Reverse the isolated group using reverseList(temp).
 * 7. If this is the first group, update head.
 *    Otherwise, connect Lprev.next to reversedHead.
 * 8. The original temp becomes the tail after reversal.
 *    Update Lprev = temp.
 * 9. Move temp to nextNode to process the next group.
 * 10. Return the updated head.
 *
 * Key Observations:
 * - isPresent() checks whether a complete group of k nodes exists.
 * - nextNode preserves the remaining linked list.
 * - KthNode.next = null isolates the current group.
 * - reverseList() reverses only the isolated group.
 * - The original first node becomes the tail after reversal.
 * - Lprev connects the previous reversed group to the current group.
 * - Incomplete groups at the end remain unchanged.
 *
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), iterative pointer manipulation.
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode temp = head;
        ListNode Lprev = null;

        while (temp != null) {

            // Find the kth node from the current node.
            ListNode KthNode = isPresent(temp, k);

            // Less than k nodes remain.
            if (KthNode == null) {
                if (Lprev != null) {
                    Lprev.next = temp;
                }
                break;
            }

            // Save the remaining linked list.
            ListNode nextNode = KthNode.next;

            // Isolate the current group.
            KthNode.next = null;

            // Reverse the current group.
            ListNode reversedHead = reverseList(temp);

            // Connect the reversed group.
            if (temp == head) {
                head = reversedHead;
            } else {
                Lprev.next = reversedHead;
            }

            // Original first node becomes the tail.
            Lprev = temp;

            // Move to the next group.
            temp = nextNode;
        }

        return head;
    }

    // Returns kth node from current node.
    public ListNode isPresent(ListNode node, int k) {

        while (node != null && k > 1) {
            node = node.next;
            k--;
        }

        return node;
    }

    // Reverse the isolated linked list.
    public ListNode reverseList(ListNode node) {

        ListNode prev = null;
        ListNode curr = node;

        while (curr != null) {

            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
