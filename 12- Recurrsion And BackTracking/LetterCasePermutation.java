/*
Problem Name: Letter Case Permutation
LeetCode Number: 784
Difficulty: Medium
Pattern: Backtracking + Recursion + StringBuilder

--------------------------------------------------
1. PROBLEM IN SIMPLE WORDS
--------------------------------------------------

Given a string containing letters and digits,
generate all possible strings by changing the
case of the alphabetic characters.

Digits must remain unchanged.

Example:

Input: "a1b"

Output:
["a1b", "a1B", "A1b", "A1B"]

--------------------------------------------------
2. EXPLAIN LIKE I'M 10 YEARS OLD
--------------------------------------------------

Imagine every letter has two switches:

1. Lowercase
2. Uppercase

For example:

a -> a or A
b -> b or B
c -> c or C

But numbers have no switch.

1 -> only 1
2 -> only 2

For every letter, we try both possibilities.

For every number, we keep it unchanged.

We explore every possible combination using
backtracking.

--------------------------------------------------
3. UNDERSTANDING THE VARIABLES
--------------------------------------------------

StringBuilder sb:
Stores the current permutation.

String s:
Original input string.

int index:
Current character position being processed.

List<String> list:
Stores all generated permutations.

char ch:
Current character being processed.

--------------------------------------------------
4. BASE CONDITION
--------------------------------------------------

if(index == s.length()){
    list.add(sb.toString());
    return;
}

When index reaches the input string length,
we have processed every character.

The current StringBuilder contains one complete
permutation.

We convert it into a String and store it.

--------------------------------------------------
5. CASE 1: NUMBER
--------------------------------------------------

if(ch>='0' && ch<='9'){

    sb.append(ch);

    backTracking(sb, s, index+1, list);

    sb.deleteCharAt(sb.length()-1);
}

A number has only one possible choice.

Example:

ch = '5'

Append '5', process the next character,
then remove '5' while backtracking.

--------------------------------------------------
6. CASE 2: LOWERCASE CHARACTER
--------------------------------------------------

if(ch>='a' && ch<='z'){

    sb.append(ch);

    backTracking(sb, s, index+1, list);

    sb.deleteCharAt(sb.length()-1);

    sb.append(Character.toUpperCase(ch));

    backTracking(sb, s, index+1, list);

    sb.deleteCharAt(sb.length()-1);
}

A lowercase character has two choices.

Example:

ch = 'a'

Choice 1:
Keep lowercase -> 'a'

Choice 2:
Convert to uppercase -> 'A'

Explore both possibilities recursively.

--------------------------------------------------
7. CASE 3: UPPERCASE CHARACTER
--------------------------------------------------

if(ch>='A' && ch<='Z'){

    sb.append(ch);

    backTracking(sb, s, index+1, list);

    sb.deleteCharAt(sb.length()-1);

    sb.append(Character.toLowerCase(ch));

    backTracking(sb, s, index+1, list);

    sb.deleteCharAt(sb.length()-1);
}

An uppercase character also has two choices.

Example:

ch = 'B'

Choice 1:
Keep uppercase -> 'B'

Choice 2:
Convert to lowercase -> 'b'

--------------------------------------------------
8. COMPLETE DRY RUN: "a1b"
--------------------------------------------------

Initial:

sb = ""
index = 0

First character = 'a'

Two choices:

Choice 1: 'a'
Choice 2: 'A'

Explore Choice 1:

sb = "a"

Next character = '1'

Only one choice:

sb = "a1"

Next character = 'b'

Two choices:

Choice 1: 'b'

sb = "a1b"

index = 3

Base condition reached.

Store "a1b".

Backtrack and remove 'b'.

Choice 2: 'B'

sb = "a1B"

Base condition reached.

Store "a1B".

Now backtrack to the first character.

Explore Choice 2:

sb = "A"

Process '1':

sb = "A1"

Process 'b':

Choice 1: 'b'

Store "A1b".

Choice 2: 'B'

Store "A1B".

FINAL OUTPUT:

["a1b", "a1B", "A1b", "A1B"]

--------------------------------------------------
9. IMPORTANT OBSERVATIONS
--------------------------------------------------

1. Every letter has exactly two choices.

2. Every digit has exactly one choice.

3. index+1 moves to the next original character.

4. append() makes a choice.

5. Recursive call explores that choice.

6. deleteCharAt() undoes the choice.

7. Original case is explored before opposite case.

8. The number of results depends on the number
   of alphabetic characters, not the total
   string length alone.

9. If there are L letters, total results = 2^L.

--------------------------------------------------
10. COMPLEXITY ANALYSIS
--------------------------------------------------

Let:
n = total input length
L = number of alphabetic characters

Number of combinations = 2^L.

Time Complexity:
O(n * 2^L)

Auxiliary Space:
O(n)

Output Space:
O(n * 2^L)

Worst-case time complexity:
O(n * 2^n)


--------------------------------------------------
12. FINAL TAKEAWAY
--------------------------------------------------

Remember:

NUMBER:
One choice.

LETTER:
Two choices.

BACKTRACKING:
Choose -> Explore -> Undo.

This is the key pattern behind Letter Case
Permutation.
*/


--------------------------------------------------
11. ORIGINAL JAVA IMPLEMENTATION
--------------------------------------------------

class Solution {
    public void backTracking(StringBuilder sb, String s, int index, List<String> list ){
        //Base condition
        // the length of sb is same of s

        if(index == s.length()){
            list.add(sb.toString());
            return;
        }
            char ch = s.charAt(index);

        // for numbers
        if(ch>='0' && ch<='9'){
            sb.append(ch);
            backTracking( sb, s, index+1, list);
            sb.deleteCharAt(sb.length()-1);
        }

        // for lowercase
        if(ch>='a' && ch<='z'){
            sb.append(ch);
            backTracking(sb, s, index+1, list );
            sb.deleteCharAt(sb.length()-1);

            sb.append(Character.toUpperCase(ch));
            backTracking(sb, s, index+1, list );
            sb.deleteCharAt(sb.length()-1);
        }
        // for uppercase
        if(ch>='A' && ch<='Z'){
            sb.append(ch);
            backTracking(sb, s, index+1, list );
            sb.deleteCharAt(sb.length()-1);

            sb.append(Character.toLowerCase(ch));
            backTracking(sb, s, index+1, list );
            sb.deleteCharAt(sb.length()-1);
        }

    }

    public List<String> letterCasePermutation(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        backTracking(sb, s, 0, list);

        return list;

    }
}
