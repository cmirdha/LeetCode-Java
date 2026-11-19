/*
Problem Name: Fibonacci Number
LeetCode Number: 509
Difficulty: Easy

Pattern:
1. Recursion
2. Dynamic Programming
3. Space Optimized DP

=========================================================
PROBLEM EXPLANATION
=========================================================

The Fibonacci sequence follows this rule:

F(0) = 0
F(1) = 1

For every number after that:

F(n) = F(n-1) + F(n-2)

In simple words:

To calculate the current Fibonacci number,
add the previous two Fibonacci numbers.

Example:

F(0) = 0
F(1) = 1
F(2) = 1
F(3) = 2
F(4) = 3
F(5) = 5

=========================================================
APPROACH 1: RECURSION
=========================================================

IDEA:

Break the original problem into two smaller problems.

The recursive formula is:

fib(n) = fib(n-1) + fib(n-2)

For example:

fib(4)
    = fib(3) + fib(2)

    = [fib(2) + fib(1)] + [fib(1) + fib(0)]

    = 3

BASE CASES:

1. If n == 0, return 0.
2. If n == 1, return 1.

WHY DO WE NEED BASE CASES?

Every recursive function needs a stopping condition.

Without base cases, the function would keep calling
itself indefinitely.

These two conditions stop recursion when the answer
is already known.

=========================================================
RECURSION DRY RUN: n = 4
=========================================================

Function call:

fib(4)

Step 1:

fib(4) = fib(3) + fib(2)

Step 2:

fib(3) = fib(2) + fib(1)

fib(2) = fib(1) + fib(0)

Step 3: Resolve the base cases.

fib(1) = 1
fib(0) = 0

Step 4: Calculate from the bottom.

fib(2) = 1 + 0 = 1

fib(3) = 1 + 1 = 2

fib(4) = 2 + 1 = 3

Final Answer = 3

=========================================================
PROBLEM WITH RECURSION
=========================================================

Notice that fib(2) is calculated more than once.

For larger inputs, the same Fibonacci numbers are
calculated repeatedly.

This is called OVERLAPPING SUBPROBLEMS.

For example:

fib(5) calls fib(4) and fib(3).

But fib(4) also calls fib(3).

Therefore, fib(3) gets calculated multiple times.

This makes the recursive solution inefficient.

TIME COMPLEXITY: O(2^n)

Reason:
Each function call can generate two more recursive
calls, resulting in an exponential number of calls.

SPACE COMPLEXITY: O(n)

Reason:
The maximum depth of the recursive call stack
is proportional to n.

=========================================================
APPROACH 2: ITERATIVE SPACE OPTIMIZED DP
=========================================================

IDEA:

Instead of repeatedly calculating Fibonacci numbers,
we calculate each number only once.

We observe that:

F(n) = F(n-1) + F(n-2)

Therefore, we only need the previous two values.

We maintain three variables:

prev2 = Fibonacci number at i-2.
prev1 = Fibonacci number at i-1.
current = Fibonacci number at i.

Initially:

prev2 = 0
prev1 = 1

For every i from 2 to n:

current = prev1 + prev2

Then update:

prev2 = prev1
prev1 = current

Finally return prev1.

WHY START FROM i = 2?

Because F(0) and F(1) are already known.

We only need to calculate Fibonacci numbers
starting from index 2.

WHY UPDATE prev2 FIRST?

Initially:

prev2 = 0
prev1 = 1

Suppose:

current = prev1 + prev2 = 1

Now we need to move forward:

prev2 should receive the OLD prev1.
prev1 should receive current.

Therefore:

prev2 = prev1;
prev1 = current;

If we overwrite prev1 first, we lose its old value.

=========================================================
DRY RUN: n = 5
=========================================================

Initially:

prev2 = 0
prev1 = 1

---------------------------------
i = 2
---------------------------------

current = prev1 + prev2
        = 1 + 0
        = 1

Update:

prev2 = 1
prev1 = 1

---------------------------------
i = 3
---------------------------------

current = prev1 + prev2
        = 1 + 1
        = 2

Update:

prev2 = 1
prev1 = 2

---------------------------------
i = 4
---------------------------------

current = prev1 + prev2
        = 2 + 1
        = 3

Update:

prev2 = 2
prev1 = 3

---------------------------------
i = 5
---------------------------------

current = prev1 + prev2
        = 3 + 2
        = 5

Update:

prev2 = 3
prev1 = 5

Finally:

return prev1 = 5

Final Answer = 5

=========================================================
DRY RUN SUMMARY TABLE
=========================================================

i       prev2       prev1       current
-----------------------------------------
Initial   0           1           -
2         1           1           1
3         1           2           2
4         2           3           3
5         3           5           5

=========================================================
KEY OBSERVATIONS
=========================================================

1. Fibonacci has two base cases: 0 and 1.

2. Recursion directly represents the mathematical
   formula.

3. Recursive calls repeat the same calculations.

4. Dynamic Programming avoids repeated calculations.

5. We only need the previous two Fibonacci numbers
   to calculate the next number.

6. Therefore, we do not need an entire array to
   store the Fibonacci sequence.

7. We can optimize space from O(n) to O(1).

=========================================================
COMPLEXITY COMPARISON
=========================================================

Approach                  Time          Space
------------------------------------------------
Recursion                 O(2^n)        O(n)
Memoization               O(n)          O(n)
Space Optimized DP        O(n)          O(1)

=========================================================
RECOMMENDED SOLUTION:
ITERATIVE SPACE OPTIMIZED DP
=========================================================
*/
class Solution {
    public int fib(int n) {

        // Base cases:
        // F(0) = 0 and F(1) = 1.
        if(n <= 1) {
            return n;
        }

        // Stores the Fibonacci number at index i-2.
        int prev2 = 0;

        // Stores the Fibonacci number at index i-1.
        int prev1 = 1;

        // Start from index 2 because the first
        // two Fibonacci numbers are already known.
        for(int i = 2; i <= n; i++) {

            // Current number is the sum of the
            // previous two Fibonacci numbers.
            int current = prev1 + prev2;

            // Move the previous values forward.
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 contains the Fibonacci number at index n.
        return prev1;
    }
}

