
 /*
  * Problem Name: Remove Nodes From Linked List
  * LeetCode Number: 2487
  * Difficulty: Medium
  * Pattern: Stack
  *
  * Approach:
  * Use a stack to store the values of the linked list.
  *
  * Traverse the stack from right to left and remove
  * nodes whose values are smaller than a value
  * already processed to their right.
  *
  * Step-by-Step Logic:
  *
  * 1. Create an empty stack.
  *
  * 2. Traverse the linked list and push every node's
  *    value into the stack.
  *
  * 3. Create a new linked list using the last value
  *    in the stack.
  *
  * 4. Pop the last value from the stack.
  *
  * 5. Continue while the stack is not empty.
  *
  * 6. If the top value is smaller than curr.val:
  *       Remove it from the stack.
  *
  * 7. Otherwise:
  *       Create a new node using the top value.
  *       Link it to curr.
  *       Update curr to the new node.
  *       Pop the stack.
  *
  * 8. Return curr as the head of the resulting list.
  *
  * Key Observations:
  *
  * - A node must be removed if a greater value exists
  *   somewhere to its right.
  * - Processing values from right to left makes it
  *   possible to identify such nodes.
  * - The stack stores node values in traversal order.
  * - The resulting linked list is constructed in reverse
  *   traversal order.
  * - Nodes with values equal to the current maximum
  *   are retained because the comparison uses <.
  *
  * Walkthrough:
  *
  * Input:
  * [5,2,13,3,8]
  *
  * Stack initially contains:
  * [5,2,13,3,8]
  *
  * Process from right to left:
  *
  * 8  -> Keep
  * 3  -> Remove because 3 < 8
  * 13 -> Keep; it becomes the new head
  * 2  -> Remove because 2 < 13
  * 5  -> Remove because 5 < 13
  *
  * Output:
  * [13,8]
  *
  * Complexity Analysis:
  *
  * Time Complexity: O(n)
  * The linked list is traversed once to fill the stack
  * and each stack element is processed once.
  *
  * Space Complexity: O(n)
  * The stack and newly created linked list require
  * additional space.
  */

 class Solution {
     public ListNode removeNodes(ListNode head) {

         Stack<Integer> stack = new Stack<>();

         ListNode ptr = head;
         while(ptr!=null){
             stack.push(ptr.val);
             ptr= ptr.next;
         }
         ListNode curr = new ListNode(stack.peek());
         stack.pop();


         while(!stack.isEmpty()){
             if(stack.peek()<curr.val){
                 stack.pop();
             }
             else{
                 ListNode prev = new ListNode(stack.peek());
                 prev.next = curr;
                 curr = prev;
                 stack.pop();
             }
         }

         return curr;


     }
 }
