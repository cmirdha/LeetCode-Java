/*
Problem Name: Remove Character
Platform: GeeksforGeeks
Difficulty: Easy

Pattern:
1. Recursion
2. StringBuilder
3. String Traversal

=========================================================
PROBLEM EXPLANATION
=========================================================

We are given:

1. A StringBuilder s.
2. A character c that needs to be removed.

Our task is to remove every occurrence of character c
from the given StringBuilder.

Examples:

s = "banana"
c = 'a'

After removing all occurrences of 'a':

"bnn"

Another example:

s = "hello"
c = 'l'

After removing all occurrences of 'l':

"heo"

=========================================================
APPROACH: RECURSION + STRINGBUILDER
=========================================================

IDEA:

We traverse the StringBuilder using an index.

At every recursive call:

1. Check whether idx has reached s.length().
2. If yes, stop recursion.
3. Check whether the character at the current index
   matches the character we want to remove.
4. If it matches, delete that character.
5. Continue recursively from the appropriate index.

We use StringBuilder because it allows us to delete
characters directly from the existing sequence.

=========================================================
UNDERSTANDING STRINGBUILDER
=========================================================

Consider:

StringBuilder s = new StringBuilder("banana");

Initially:

b  a  n  a  n  a
0  1  2  3  4  5

Suppose we execute:

s.deleteCharAt(1);

The character at index 1 is removed.

The StringBuilder becomes:

b  n  a  n  a
0  1  2  3  4

IMPORTANT:

When a character is deleted, all characters
after that index shift one position to the left.

Therefore, the next character takes the same index
as the character that was just deleted.

This is the important observation behind your code.

=========================================================
UNDERSTANDING YOUR RECURSIVE FUNCTION
=========================================================

indexremoval(idx, s, c)

Parameters:

idx:
Current index being checked.

s:
StringBuilder from which characters are removed.

c:
Character that needs to be removed.

=========================================================
STEP-BY-STEP LOGIC
=========================================================

STEP 1: BASE CASE

if(idx == s.length())
    return;

WHY?

If idx reaches the current StringBuilder length,
there are no more characters to process.

Therefore, stop recursion.

STEP 2: CHECK THE CURRENT CHARACTER

if(s.charAt(idx) == c)

Check whether the character at the current index
matches the character we want to remove.

STEP 3: DELETE THE CHARACTER

s.deleteCharAt(idx);

If the characters match, remove the character
at the current index.

Example:

Before deletion:

b  a  n  a  n  a
0  1  2  3  4  5

If idx = 1 and c = 'a':

After deletion:

b  n  a  n  a
0  1  2  3  4

Notice that the next character 'n' now occupies
index 1.

STEP 4: DECREMENT idx

idx--;

WHY?

After deleting a character, the next character
shifts into the same index.

Your code decreases idx by one so that when
the recursive call increments it again, the
same index gets checked.

STEP 5: RECURSIVE CALL

indexremoval(idx+1, s, c);

Continue checking the StringBuilder recursively.

=========================================================
IMPORTANT: WHY idx-- IS USED AFTER DELETION
=========================================================

This is the most important part of your solution.

Suppose:

s = "banana"
c = 'a'

Initially:

idx = 0

At index 0:

'b' != 'a'

No deletion happens.

Next recursive call:

indexremoval(1, s, c)

At index 1:

'a' == 'a'

Delete index 1.

StringBuilder becomes:

"bnana"

After deletion, the next character 'n' moves
into index 1.

Your code executes:

idx--;

So:

idx = 0

Then the recursive call executes:

indexremoval(idx+1, s, c)

Which becomes:

indexremoval(1, s, c)

Therefore, index 1 is checked again.

This is correct because the character at index 1
has changed after deletion.

If you moved directly to index 2, you would skip
the character that shifted into index 1.

=========================================================
DRY RUN: s = "banana", c = 'a'
=========================================================

Initially:

StringBuilder = "banana"
idx = 0
c = 'a'

---------------------------------
CALL 1
---------------------------------

idx = 0

Current character:

s.charAt(0) = 'b'

Compare:

'b' == 'a' -> false

No deletion.

Recursive call:

indexremoval(1, s, 'a')

StringBuilder = "banana"

---------------------------------
CALL 2
---------------------------------

idx = 1

Current character:

s.charAt(1) = 'a'

Compare:

'a' == 'a' -> true

Delete index 1.

StringBuilder:

"bnana"

Now:

idx--;

idx = 0

Recursive call:

indexremoval(1, s, 'a')

Notice that we check index 1 again.

---------------------------------
CALL 3
---------------------------------

idx = 1

Current character:

s.charAt(1) = 'n'

Compare:

'n' == 'a' -> false

No deletion.

Recursive call:

indexremoval(2, s, 'a')

StringBuilder = "bnana"

---------------------------------
CALL 4
---------------------------------

idx = 2

Current character:

s.charAt(2) = 'a'

Compare:

'a' == 'a' -> true

Delete index 2.

StringBuilder:

"bnna"

Now:

idx--;

idx = 1

Recursive call:

indexremoval(2, s, 'a')

---------------------------------
CALL 5
---------------------------------

idx = 2

Current character:

s.charAt(2) = 'n'

Compare:

'n' == 'a' -> false

No deletion.

Recursive call:

indexremoval(3, s, 'a')

StringBuilder = "bnna"

---------------------------------
CALL 6
---------------------------------

idx = 3

Current character:

s.charAt(3) = 'a'

Compare:

'a' == 'a' -> true

Delete index 3.

StringBuilder:

"bnn"

Now:

idx--;

idx = 2

Recursive call:

indexremoval(3, s, 'a')

---------------------------------
CALL 7
---------------------------------

idx = 3

Current StringBuilder:

"bnn"

Current length:

s.length() = 3

Check base condition:

idx == s.length()

3 == 3 -> true

Return.

Final StringBuilder:

"bnn"

=========================================================
DRY RUN SUMMARY TABLE
=========================================================

Original String: "banana"
Character to Remove: 'a'

Call    idx    Character    Action       StringBuilder
---------------------------------------------------------
1       0      b            Keep         banana
2       1      a            Delete       bnana
3       1      n            Keep         bnana
4       2      a            Delete       bnna
5       2      n            Keep         bnna
6       3      a            Delete       bnn
7       3      -            Stop         bnn

Final StringBuilder = "bnn"

=========================================================
KEY OBSERVATIONS
=========================================================

1. StringBuilder allows us to delete characters
   directly using deleteCharAt().

2. Every recursive call checks one index.

3. If the character matches c, it is deleted.

4. Deleting a character shifts the remaining
   characters to the left.

5. Your idx-- ensures that the next recursive
   call checks the shifted character again.

6. If no deletion happens, idx moves forward
   normally through idx + 1.

7. Recursion stops when idx reaches the
   current StringBuilder length.

8. The StringBuilder is modified directly.
   No new StringBuilder is created.

=========================================================
EDGE CASES
=========================================================

1. Empty StringBuilder:

   s = ""
   c = 'a'

   Initially:

   idx = 0
   s.length() = 0

   Base condition is true.

   Nothing is deleted.

2. Character does not exist:

   s = "hello"
   c = 'x'

   Every character is checked.
   No deletion occurs.

   Result = "hello"

3. Every character matches:

   s = "aaaa"
   c = 'a'

   Every character is deleted.

   Result = ""

4. Consecutive matching characters:

   s = "baaaac"
   c = 'a'

   After deleting an 'a', the next character
   shifts into the same index.

   Your code checks that index again.

   Result = "bc"

5. Single character matching:

   s = "a"
   c = 'a'

   Character is deleted.

   Result = ""

=========================================================
COMPLEXITY ANALYSIS
=========================================================

Let n be the original length of the StringBuilder.

TIME COMPLEXITY: O(n^2) worst case.

Reason:

There are at most O(n) recursive calls.

However, deleteCharAt(idx) may shift all characters
after the deleted position.

In the worst case, deleting characters near the
beginning repeatedly requires shifting many characters.

For example:

"aaaaa"

Each deletion shifts the remaining characters.

Total shifting work can be:

4 + 3 + 2 + 1 = O(n^2)

SPACE COMPLEXITY: O(n)

Reason:

Recursive calls use the Java call stack.

There can be O(n) recursive calls.

=========================================================
INTERVIEW OBSERVATIONS
=========================================================

Q1. Why use StringBuilder instead of String?

Answer:

String is immutable, meaning its contents cannot
be modified directly.

StringBuilder is mutable and provides deleteCharAt()
to remove a character.

Q2. Why do we decrement idx after deleting?

Answer:

Deleting a character shifts the next character
into the same index.

We need to check that shifted character instead
of skipping it.

Q3. What happens if the character does not match?

Answer:

No deletion happens.

The recursive call moves to idx + 1.

Q4. Why is the base condition based on
s.length() instead of the original length?

Answer:

Because the StringBuilder length changes whenever
a character is deleted.

We must compare idx against the current length.

Q5. What is the difference between this and
removing characters using a new StringBuilder?

Answer:

Your approach modifies the existing StringBuilder.

A separate StringBuilder approach would construct
a new result instead of deleting from the original.

=========================================================
FINAL JAVA IMPLEMENTATION
=========================================================

*/

class Solution {
    public void indexremoval(int idx,StringBuilder s, char c ){
        if(idx == s.length())
            return;
        if(s.charAt(idx) == c){
            s.deleteCharAt(idx);
            idx--;
        }
        indexremoval(idx+1,s,c);

    }
    public void removeCharacter(StringBuilder s, char c) {

        // code here
        indexremoval(0,s,c);

    }
}
