
/*
Problem Name: Palindrome Partitioning
LeetCode Number: 131
Difficulty: Medium
Pattern: Backtracking + Recursion + Two Pointers

==================================================
1. PROBLEM UNDERSTANDING
==================================================

We are given a string.

We need to divide it into smaller substrings such that
EVERY substring is a palindrome.

We must return all possible valid partitions.

Example:

Input:
s = "aab"

Valid partitions:

["a", "a", "b"]
["aa", "b"]

Output:
[["a","a","b"],["aa","b"]]

A palindrome reads the same from left to right and
right to left.

Examples:
"a"   -> Palindrome
"aa"  -> Palindrome
"aba" -> Palindrome
"ab"  -> Not a palindrome

==================================================
2. EXPLAIN LIKE I AM 10 YEARS OLD
==================================================

Imagine you have the word:

aab

You want to break it into smaller pieces.

But every piece must be a palindrome.

You can try:

a | a | b
aa | b
aab

Now check each piece:

a | a | b
Every piece is a palindrome.

aa | b
Every piece is a palindrome.

aab
Not a palindrome.

Therefore, the valid answers are:

[["a","a","b"],["aa","b"]]

We use backtracking because we must try every possible
way to divide the string.

The process is:

CHOOSE
CHECK
EXPLORE
UNDO
TRY THE NEXT CHOICE

==================================================
3. WHY BACKTRACKING?
==================================================

At every position, we can try different ending
positions for the next substring.

For example:

s = "aab"

Starting from index 0, possible substrings are:

"a"
"aa"
"aab"

We check each substring.

If it is a palindrome:
    Select it.
    Explore the remaining string recursively.
    Remove it after recursion returns.

If it is not a palindrome:
    Ignore it.

This explores every valid partition.

==================================================
4. isPalindrome() METHOD
==================================================

public boolean isPalindrome(String s)

This method checks whether a string is a palindrome.

Variables:

left:
Starts from the first character.

right:
Starts from the last character.

Example:

s = "aba"

Initially:

left = 0
right = 2

Compare:

s.charAt(0) = 'a'
s.charAt(2) = 'a'

They match.

Move both pointers:

left++;
right--;

Now both pointers are at the middle character.

The comparison succeeds.

The loop finishes and returns true.

IMPORTANT CONDITION:

while (left <= right)

Why use <= ?

For odd-length strings, the pointers meet at the
middle character.

For even-length strings, the left pointer crosses
the right pointer.

Both cases mean all character pairs were checked.

If any pair does not match:

return false;

There is no need to continue checking.

Time Complexity: O(k), where k is the string length.
Auxiliary Space: O(1).

==================================================
5. HELPER METHOD
==================================================

public void helper(
    String s,
    int index,
    List<String> sublist,
    List<List<String>> list
)

Variables:

s:
Original input string.

index:
Starting position of the next substring.

sublist:
Current partition being constructed.

list:
Stores all valid partitions.

The most important variable is index.

It tells us where the next substring should start.

Example:

s = "aab"

If we select "aa":

Start = 0
End = 1

The next starting position is:

end + 1 = 2

Therefore, the next recursive call starts from index 2.

==================================================
6. BASE CONDITION
==================================================

if (index == s.length()) {
    list.add(new ArrayList(sublist));
    return;
}

When index reaches the string length, it means
we have successfully partitioned the complete string.

Example:

s = "aab"

sublist = ["aa", "b"]

index = 3
s.length() = 3

The entire string has been processed.

Therefore, save the current partition.

WHY new ArrayList(sublist)?

Because sublist is reused and modified during
backtracking.

We need an independent copy for every answer.

==================================================
7. FOR LOOP
==================================================

for (int end = index; end < s.length(); end++) {

    String substr = s.substring(index, end + 1);

}

The loop tries every possible ending position
for the next substring.

Example:

s = "aab"
index = 0

end = 0:

substring(0, 1) = "a"

end = 1:

substring(0, 2) = "aa"

end = 2:

substring(0, 3) = "aab"

WHY end + 1?

Java substring(start, end) includes start but
excludes end.

Therefore, we use end + 1 to include the character
at position end.

==================================================
8. PALINDROME CHECK
==================================================

if (isPalindrome(substr)) {

We only explore a substring when it is a palindrome.

Example:

"a"   -> true
"aa"  -> true
"aab" -> false

If the substring is not a palindrome, we skip it.

This avoids exploring invalid partitions.

==================================================
9. BACKTRACKING
==================================================

sublist.add(substr);

Select the current palindrome substring.

helper(s, end + 1, sublist, list);

Explore the remaining string.

WHY end + 1?

Because the next substring must start immediately
after the current substring.

sublist.remove(sublist.size() - 1);

Undo the previous choice after recursion returns.

This allows the loop to try another possible
substring.

Example:

First choose "a":

sublist = ["a"]

Explore the remaining string.

After recursion returns, remove "a".

Now the loop can try "aa".

This is backtracking.

==================================================
10. COMPLETE DRY RUN
==================================================

Input:

s = "aab"

Initial:

index = 0
sublist = []
list = []

FIRST PATH:

At index 0:

Try "a".

It is a palindrome.

sublist = ["a"]

Call helper with index = 1.

At index 1:

Try "a".

It is a palindrome.

sublist = ["a", "a"]

Call helper with index = 2.

At index 2:

Try "b".

It is a palindrome.

sublist = ["a", "a", "b"]

Call helper with index = 3.

Base condition reached.

Save:

["a", "a", "b"]

Backtrack:

Remove "b".
Remove the previous "a".

At index 1, try "ab".

"ab" is not a palindrome.

Ignore it.

Backtrack to index 0.

Remove the first "a".

SECOND PATH:

At index 0:

Try "aa".

It is a palindrome.

sublist = ["aa"]

Call helper with index = 2.

At index 2:

Try "b".

It is a palindrome.

sublist = ["aa", "b"]

Call helper with index = 3.

Base condition reached.

Save:

["aa", "b"]

THIRD POSSIBILITY:

At index 0:

Try "aab".

It is not a palindrome.

Ignore it.

Final result:

[
    ["a", "a", "b"],
    ["aa", "b"]
]

==================================================
11. IMPORTANT INTERVIEW OBSERVATIONS
==================================================

1. index represents where the next substring starts.

2. The loop tries every possible ending position.

3. end + 1 moves to the next unprocessed character.

4. isPalindrome() validates each selected substring.

5. sublist stores the current partition.

6. new ArrayList(sublist) saves an independent copy.

7. remove() restores the previous state.

8. We need to explore every valid path because
   the question asks for all possible partitions.

9. Empty string reaches the base case immediately
   and returns a list containing an empty partition.

==================================================
12. COMPLEXITY ANALYSIS
==================================================

Let n = length of the input string.

Time Complexity:

O(n^2 * 2^n) upper bound.

There are exponentially many possible partitions.

For each candidate substring, substring creation
and palindrome checking can take O(n).

Auxiliary Space:

O(n)

Recursion depth can reach n.

The current partition can also contain up to n pieces.

Output Space:

O(n * 2^n) upper bound.

We store all valid partitions.

==================================================
13. ORIGINAL JAVA IMPLEMENTATION
==================================================
*/

class Solution {

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public void helper(String s, int index, List<String> sublist, List<List<String>> list) {

        if (index == s.length()) {
            list.add(new ArrayList(sublist));
            return;
        }

        for (int end = index; end < s.length(); end++) {
            String substr = s.substring(index, end + 1);
            if (isPalindrome(substr)) {
                sublist.add(substr);
                helper(s, end + 1, sublist, list);
                sublist.remove(sublist.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<String> sublist = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        helper(s, 0, sublist, list);
        return list;
    }
}

/*
==================================================
END OF NOTES
==================================================
*/
