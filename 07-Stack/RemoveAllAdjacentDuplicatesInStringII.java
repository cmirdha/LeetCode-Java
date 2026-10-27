
/*
 * Problem Name: Remove All Adjacent Duplicates in String II
 * LeetCode Number: 1209
 * Difficulty: Medium
 * Pattern: Stack
 *
 * Approach:
 * Use a stack to store each character along with its occurrence count.
 *
 * If the current character matches the character at the top
 * of the stack, increment its occurrence count.
 *
 * If the count reaches k, remove that entry from the stack.
 *
 * Step-by-Step Logic:
 *
 * 1. Create a stack of integer arrays.
 *    Each array stores:
 *       arr[0] = character
 *       arr[1] = occurrence count
 *
 * 2. Iterate through every character in the string.
 *
 * 3. If the stack is not empty and the top character
 *    matches the current character:
 *       Increment the occurrence count.
 *
 * 4. Otherwise:
 *       Push a new entry containing the current character
 *       and an initial count of 1.
 *
 * 5. If the top entry's count becomes equal to k:
 *       Pop the entry from the stack.
 *
 * 6. Create a StringBuilder to construct the result.
 *
 * 7. Pop each entry from the stack and append its character
 *    according to its stored occurrence count.
 *
 * 8. Reverse the StringBuilder and return the final string.
 *
 * Key Observations:
 *
 * - The stack stores both characters and their frequencies.
 * - Consecutive equal characters are grouped together.
 * - When a character's count reaches k, the entire group
 *   is removed.
 * - Removing a group can make previously separated groups
 *   adjacent, and the stack handles this automatically.
 * - Reversing the result restores the original character order.
 *
 * Walkthrough:
 *
 * Input:
 * s = "deeedbbcccbdaa", k = 3
 *
 * Groups of three consecutive equal characters are removed.
 *
 * Output:
 * "aa"
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n)
 * Each character is processed and appended at most once.
 *
 * Space Complexity: O(n)
 * The stack and StringBuilder may store up to n characters.
 */

class Solution {
    public String removeDuplicates(String s, int k) {

        // if we store the value of the occurance with the char in the stack and if the the stack top is the same element and the occurance is k-1 we will delete the value in the stack

        Stack<int []> stack = new Stack<>();

        for(char ch: s.toCharArray()){
            if(!stack.isEmpty() && stack.peek()[0]== ch){
                stack.peek()[1]++;
            }
            else{
                stack.push(new int[]{ch,1});
            }
            if(stack.peek()[1]==k){
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            int [] arr = stack.pop();
            while (arr[1]-- > 0) {
                sb.append((char)arr[0]);
            }

        }

        return sb.reverse().toString();


    }
}
