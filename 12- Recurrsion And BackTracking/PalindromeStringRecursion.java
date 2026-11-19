/*
Problem Name: Palindrome String
Platform: GeeksforGeeks
Difficulty: Easy

Pattern:
1. Recursion
2. Two Pointers

=========================================================
PROBLEM EXPLANATION
=========================================================

A palindrome is a string that reads the same
from left to right and right to left.

Examples:

"madam"  -> true
"racecar" -> true
"abba"   -> true
"hello"  -> false

Our task is to check whether the given string
is a palindrome using recursion.

=========================================================
APPROACH: RECURSION + TWO POINTERS
=========================================================

IDEA:

We use two pointers:

start = First character index.
end   = Last character index.

Instead of using a while loop, we use recursion
to move both pointers toward the center.

At every recursive call:

1. Check whether start >= end.
2. If yes, all character pairs have been checked.
   Return true.
3. Otherwise, compare the characters at start and end.
4. If they are different, return false.
5. If they match, recursively call the function
   with start + 1 and end - 1.

=========================================================
WHY start >= end?
=========================================================

This is our BASE CASE.

There are two possibilities:

CASE 1: ODD LENGTH STRING

Example: "MADAM"

Pointers eventually meet at the middle:

start = 2
end = 2

All character pairs have matched.

Therefore, return true.

CASE 2: EVEN LENGTH STRING

Example: "ABBA"

Pointers eventually cross:

start = 2
end = 1

All character pairs have matched.

Therefore, return true.

So the correct condition is:

if(start >= end)
    return true;

=========================================================
RECURSIVE FUNCTION
=========================================================

findPalindrome(start, end, s)

Parameters:

start:
Index of the character from the left side.

end:
Index of the character from the right side.

s:
Original input string.

=========================================================
STEP-BY-STEP LOGIC
=========================================================

STEP 1: BASE CASE

if(start >= end)
    return true;

WHY?

When the pointers meet or cross, all required
character comparisons are complete.

There is nothing more to check.

STEP 2: CHARACTER COMPARISON

if(s.charAt(start) != s.charAt(end))
    return false;

WHY?

A palindrome must have matching characters
at corresponding positions.

One mismatch is enough to prove that the
string is not a palindrome.

STEP 3: RECURSIVE CALL

return findPalindrome(start + 1, end - 1, s);

WHY?

The current pair of characters has matched.

Now move:

start one position forward.
end one position backward.

Then check the next inner pair.

Notice that we return the recursive call directly.

This means the answer from the smaller problem
becomes the answer to the current problem.

=========================================================
DRY RUN 1: s = "MADAM"
=========================================================

String:

 M   A   D   A   M
 0   1   2   3   4

Initial function call:

findPalindrome(0, 4, "MADAM")

---------------------------------
CALL 1
---------------------------------

start = 0
end = 4

Check base condition:

0 >= 4 -> false

Compare:

s.charAt(0) = 'M'
s.charAt(4) = 'M'

Characters match.

Recursive call:

findPalindrome(1, 3, "MADAM")

---------------------------------
CALL 2
---------------------------------

start = 1
end = 3

Check base condition:

1 >= 3 -> false

Compare:

s.charAt(1) = 'A'
s.charAt(3) = 'A'

Characters match.

Recursive call:

findPalindrome(2, 2, "MADAM")

---------------------------------
CALL 3
---------------------------------

start = 2
end = 2

Check base condition:

2 >= 2 -> true

Return true.

The recursive calls now return true one by one.

Final Answer:

true

=========================================================
DRY RUN 2: s = "HELLO"
=========================================================

String:

 H   E   L   L   O
 0   1   2   3   4

Initial function call:

findPalindrome(0, 4, "HELLO")

---------------------------------
CALL 1
---------------------------------

start = 0
end = 4

Check base condition:

0 >= 4 -> false

Compare:

'H' != 'O'

Mismatch found.

Return false immediately.

No further recursive calls are made.

Final Answer:

false

=========================================================
DRY RUN SUMMARY TABLE
=========================================================

Example 1: "MADAM"

start    end    Characters    Decision
-----------------------------------------
0        4      M and M       Match
1        3      A and A       Match
2        2      D and D       Base case

Answer: true

Example 2: "HELLO"

start    end    Characters    Decision
-----------------------------------------
0        4      H and O       Mismatch

Answer: false

=========================================================
KEY OBSERVATIONS
=========================================================

1. Recursion replaces the while loop.

2. Both pointers move toward the center.

3. Every recursive call checks one character pair.

4. If any pair does not match, return false.

5. If start >= end, all required comparisons
   are complete, so return true.

6. For an odd-length string, pointers meet.

7. For an even-length string, pointers cross.

8. No additional string or array is required.

=========================================================
EDGE CASES
=========================================================

1. Empty string:

   start = 0
   end = -1

   0 >= -1 -> true

2. Single character: "a"

   start = 0
   end = 0

   0 >= 0 -> true

3. Two matching characters: "aa"

   First comparison matches.
   Next call reaches the base case.

4. Two different characters: "ab"

   First comparison fails.
   Return false.

=========================================================
COMPLEXITY ANALYSIS
=========================================================

TIME COMPLEXITY: O(n)

Each recursive call compares one pair of characters.

At most approximately n/2 pairs are compared.

Therefore:

O(n/2) = O(n)

SPACE COMPLEXITY: O(n)

Unlike the iterative two-pointer approach,
recursion uses the Java call stack.

In the worst case, the recursion depth is
approximately n/2, which is O(n).

=========================================================
ITERATIVE VS RECURSIVE APPROACH
=========================================================

Approach                  Time          Space
------------------------------------------------
Two Pointers + Loop       O(n)          O(1)
Recursion + Two Pointers  O(n)          O(n)

Both approaches have the same time complexity.

However, the iterative approach uses constant
auxiliary space, while recursion requires
additional call-stack memory.

=========================================================
FINAL JAVA IMPLEMENTATION
=========================================================

*/
 */
class Solution {

    boolean findPalindrome(int start, int end, String s) {

        // Base case:
        // If pointers meet or cross, all pairs
        // have been checked successfully.
        if(start >= end)
            return true;

        // If characters do not match,
        // the string is not a palindrome.
        if(s.charAt(start) != s.charAt(end))
            return false;

        // Move both pointers toward the center
        // and check the next pair recursively.
        return findPalindrome(start + 1, end - 1, s);
    }

    boolean isPalindrome(String s) {

        // Start from the first and last characters.
        return findPalindrome(0, s.length() - 1, s);
    }
}

