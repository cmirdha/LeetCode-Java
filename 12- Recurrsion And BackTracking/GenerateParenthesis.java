/*
Problem Name: Generate Parentheses
LeetCode Number: 22
Difficulty: Medium
Pattern: Backtracking + Recursion + StringBuilder

--------------------------------------------------
1. PROBLEM IN SIMPLE WORDS
--------------------------------------------------

We are given n pairs of parentheses.

We need to generate every possible combination
that forms a valid parentheses sequence.

Example:

Input: n = 2

Output:
["(())", "()()"]

A valid combination must follow two rules:

1. We cannot use more than n opening brackets.
2. We cannot close a bracket unless an unmatched
   opening bracket is already available.

--------------------------------------------------
2. EXPLAIN LIKE I'M 10 YEARS OLD
--------------------------------------------------

Imagine opening and closing boxes.

You have n opening brackets '(' and n closing
brackets ')'.

Whenever you open a box, you can close it later.

But you cannot close a box before opening one.

For example:

"(("   -> Two boxes are open. Valid.
"(()"  -> One box is still open. Valid.
"(())" -> All boxes are closed. Valid.
"())"  -> Invalid. We closed too many boxes.

We build the answer one character at a time.

At every step, we have two possible choices:

CHOICE 1: Add '('

We can do this only when openB < n.

CHOICE 2: Add ')'

We can do this only when closeB < openB.

After exploring one choice, we undo it and try
the other choice.

This choosing, exploring and undoing process
is called BACKTRACKING.

--------------------------------------------------
3. WHY BACKTRACKING?
--------------------------------------------------

We need all valid combinations, not just one.

At every position, we may have more than one
valid choice.

We explore one choice completely, return to the
previous state, and explore another choice.

Three important steps:

1. Choose:
   s.append('(') or s.append(')')

2. Explore:
   Call backTracking() recursively.

3. Undo:
   s.deleteCharAt(s.length()-1)

Undoing is important because we reuse the same
StringBuilder for different combinations.

--------------------------------------------------
4. UNDERSTANDING THE VARIABLES
--------------------------------------------------

StringBuilder s:
Stores the combination currently being built.

int openB:
Counts how many '(' characters we have used.

int closeB:
Counts how many ')' characters we have used.

int n:
Total number of pairs required.

List<String> list:
Stores all completed valid combinations.

Initially:

s = ""
openB = 0
closeB = 0

--------------------------------------------------
5. UNDERSTANDING THE BASE CASE
--------------------------------------------------

if(s.length() == 2*n){
    list.add(s.toString());
}

Why 2*n?

Every valid answer needs:

n opening brackets
n closing brackets

Total characters = n + n = 2*n.

For n = 2, the required length is 4.

When the string becomes "(())" or "()()",
we have completed a valid combination.

Why toString()?

StringBuilder is mutable. Later recursive calls
will modify it.

toString() creates a String snapshot so the
completed answer remains unchanged in the list.

--------------------------------------------------
6. CONDITION 1: WHEN CAN WE ADD '('?
--------------------------------------------------

if(openB < n){

    s.append('(');

    backTracking(s, openB+1, closeB, n, list);

    s.deleteCharAt(s.length()-1);
}

We can add an opening bracket only while
openB is less than n.

For n = 3:

openB = 0 -> Can add '('
openB = 1 -> Can add '('
openB = 2 -> Can add '('
openB = 3 -> Cannot add '('

Why openB+1?

Because we have just added one opening bracket.

Why closeB remains unchanged?

Because we did not add a closing bracket.

Why deleteCharAt()?

After exploring all possibilities that begin
with this opening bracket, we remove it.

This restores the previous string.

--------------------------------------------------
7. CONDITION 2: WHEN CAN WE ADD ')'?
--------------------------------------------------

if(closeB < openB){

    s.append(')');

    backTracking(s, openB, closeB+1, n, list);

    s.deleteCharAt(s.length()-1);
}

We can add a closing bracket only when there
are more opening brackets than closing brackets.

Example:

Current string = "(("

openB = 2
closeB = 0

We can add ')' because 0 < 2.

Current string = "()"

openB = 1
closeB = 1

We cannot add another ')' because 1 < 1
is false.

Otherwise, we would create an invalid sequence.

Why closeB+1?

Because we have added one closing bracket.

Why openB remains unchanged?

Because we did not add an opening bracket.

--------------------------------------------------
8. COMPLETE DRY RUN: n = 2
--------------------------------------------------

Initially:

s = ""
openB = 0
closeB = 0

STEP 1:
Add '('.

s = "("
openB = 1
closeB = 0

STEP 2:
Add another '('.

s = "(("
openB = 2
closeB = 0

STEP 3:
Cannot add another '(' because openB == n.

But closeB < openB is true.

Add ')'.

s = "(()"
openB = 2
closeB = 1

STEP 4:
Add another ')'.

s = "(())"
openB = 2
closeB = 2

Length = 4 = 2*n.

Store "(())" in list.

STEP 5:
Backtrack.

Remove the last ')'.

s = "(()"

Continue returning from recursive calls until
the string becomes "((" and then "(".

STEP 6:
At the call where s = "(", explore the
closing-bracket condition.

Add ')'.

s = "()"
openB = 1
closeB = 1

STEP 7:
Add '('.

s = "()("
openB = 2
closeB = 1

STEP 8:
Add ')'.

s = "()()"
openB = 2
closeB = 2

Length = 4 = 2*n.

Store "()()" in list.

FINAL OUTPUT:

["(())", "()()"]

--------------------------------------------------
9. IMPORTANT OBSERVATIONS
--------------------------------------------------

1. openB < n prevents too many opening brackets.

2. closeB < openB prevents invalid closing brackets.

3. Every valid combination has exactly 2*n
   characters.

4. StringBuilder avoids creating a new string
   for every recursive step.

5. deleteCharAt(s.length()-1) restores the
   previous state.

6. Both conditions are independent if statements.
   This allows the algorithm to explore both
   possible choices when both are valid.

7. Your implementation explores '(' first,
   then ')' because of the order of the conditions.

8. No explicit return is needed after the base case
   here: when the length reaches 2*n, both counters
   must equal n, so neither recursive condition
   can be true.

--------------------------------------------------
10. COMPLEXITY ANALYSIS
--------------------------------------------------

Let C(n) be the number of valid combinations.

C(n) is the nth Catalan number:

C(n) = (1 / (n+1)) * (2n choose n)

TIME COMPLEXITY:

O(n * C(n))

There are C(n) valid combinations, and each
combination contains 2*n characters.

SPACE COMPLEXITY:

O(n) auxiliary space.

The maximum recursion depth is 2*n.
The StringBuilder also stores at most 2*n
characters.

OUTPUT SPACE:

O(n * C(n)) because all valid strings are stored.


--------------------------------------------------
12. FINAL TAKEAWAY
--------------------------------------------------

Remember these three things:

1. openB < n
   -> Can I still add an opening bracket?

2. closeB < openB
   -> Do I have an unmatched opening bracket
      that I can close?

3. append -> recursive call -> delete
   -> Choose -> Explore -> Undo

This is the central backtracking pattern.
--------------------------------------------------
11. ORIGINAL JAVA IMPLEMENTATION
--------------------------------------------------
*/

class Solution {
    public void backTracking(StringBuilder s, int openB, int closeB, int n, List<String> list){
        //basecase
        if(s.length()== 2*n){
            list.add(s.toString());
        }
        if(openB<n){
            s.append('(');
            backTracking(s, openB+1, closeB, n, list);
            s.deleteCharAt(s.length()-1);
        }
        if(closeB<openB){
            s.append(')');
            backTracking(s, openB, closeB+1, n, list);
            s.deleteCharAt(s.length()-1);
        }


    }

    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        backTracking(sb, 0, 0, n, list);

        return list;

    }
}


class Solution {

    public void addValidParenthesis(String s, List<String> list, int n, int openB, int closeB) {
        // State:
        // s       -> current parenthesis string
        // openB   -> number of '(' used
        // closeB  -> number of ')' used

        // Base Case:
        // If the string length becomes 2*n,
        // we have used all parentheses and formed a valid answer.
        if (s.length() == 2 * n) {
            list.add(s);
            return;
        }
        // Choice 1:
        // Add '(' only if we still have opens remaining.
        if (openB < n)
            addValidParenthesis(s + '(', list, n, openB + 1, closeB);

        // Choice 2:
        // Add ')' only if there is an unmatched '(' available.
        // This ensures closeB never becomes greater than openB.
        if (closeB < openB)
            addValidParenthesis(s + ')', list, n, openB, closeB + 1);

    }

    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<>();

        addValidParenthesis("", list, n, 0, 0);

        return list;

    }
}

// valid rule for parenthesis is openB should be more than closeB
// open brackets should be added when the openB<= n
// close brackets should be added when the closeB<openB && closeB<n
