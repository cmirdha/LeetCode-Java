/*
Problem Name: Sum of Digits
Platform: GeeksforGeeks
Difficulty: Basic

Pattern:
1. Recursion
2. Mathematical Digit Extraction

=========================================================
PROBLEM EXPLANATION
=========================================================

We are given a number n.

Our task is to calculate the sum of all its digits.

Examples:

n = 123

Sum = 1 + 2 + 3 = 6

n = 4567

Sum = 4 + 5 + 6 + 7 = 22

n = 908

Sum = 9 + 0 + 8 = 17

=========================================================
APPROACH: RECURSION
=========================================================

IDEA:

We repeatedly extract the last digit of the number
and add it to the sum of the remaining digits.

We use two mathematical operations:

1. MODULO (%) OPERATOR

n % 10

Returns the last digit of the number.

Example:

1234 % 10 = 4

2. DIVISION (/) OPERATOR

n / 10

Removes the last digit of the number.

Example:

1234 / 10 = 123

Since n is an integer, Java performs integer division.

Therefore, the decimal part is removed.

=========================================================
UNDERSTANDING THE RECURSIVE FORMULA
=========================================================

The sum of digits can be expressed as:

sumOfDigits(n) =
    (n % 10) + sumOfDigits(n / 10)

In simple words:

Sum of digits =
Last digit + Sum of remaining digits

Example:

sumOfDigits(1234)

= 4 + sumOfDigits(123)

= 4 + 3 + sumOfDigits(12)

= 4 + 3 + 2 + sumOfDigits(1)

= 4 + 3 + 2 + 1 + sumOfDigits(0)

When n becomes 0, recursion stops.

=========================================================
BASE CASE
=========================================================

if(n == 0)
    return 0;

WHY?

When the number becomes 0, there are no more
digits left to process.

Therefore, return 0.

This is the stopping condition for recursion.

Without this base case, recursion would not stop.

=========================================================
STEP-BY-STEP LOGIC
=========================================================

STEP 1:

Initialize:

int sum = 0;

This variable stores the last digit extracted
from the current number.

IMPORTANT:

In your implementation, sum is local to each
recursive function call.

It does not store the complete sum across calls.

STEP 2:

Check the base case:

if(n == 0)
    return 0;

If the number becomes 0, stop recursion.

STEP 3:

Extract the last digit:

sum += n % 10;

Example:

n = 1234

sum = 1234 % 10

sum = 4

STEP 4:

Remove the last digit and recursively calculate
the sum of the remaining digits:

return sum + sumOfDigits(n / 10);

For n = 1234:

return 4 + sumOfDigits(123)

The recursive call calculates the remaining digits.

STEP 5:

Each recursive call returns its result to the
previous call.

Finally, all extracted digits are added together.

=========================================================
DRY RUN: n = 1234
=========================================================

Initial function call:

sumOfDigits(1234)

---------------------------------
CALL 1
---------------------------------

n = 1234

Check base case:

n == 0 -> false

Extract last digit:

sum = 1234 % 10
sum = 4

Remove last digit:

1234 / 10 = 123

Recursive call:

return 4 + sumOfDigits(123)

---------------------------------
CALL 2
---------------------------------

n = 123

Check base case:

n == 0 -> false

Extract last digit:

sum = 123 % 10
sum = 3

Remove last digit:

123 / 10 = 12

Recursive call:

return 3 + sumOfDigits(12)

---------------------------------
CALL 3
---------------------------------

n = 12

Check base case:

n == 0 -> false

Extract last digit:

sum = 12 % 10
sum = 2

Remove last digit:

12 / 10 = 1

Recursive call:

return 2 + sumOfDigits(1)

---------------------------------
CALL 4
---------------------------------

n = 1

Check base case:

n == 0 -> false

Extract last digit:

sum = 1 % 10
sum = 1

Remove last digit:

1 / 10 = 0

Recursive call:

return 1 + sumOfDigits(0)

---------------------------------
CALL 5
---------------------------------

n = 0

Check base case:

n == 0 -> true

Return 0.

=========================================================
HOW RECURSION RETURNS THE ANSWER
=========================================================

Now the recursive calls return in reverse order.

sumOfDigits(0) = 0

sumOfDigits(1) = 1 + 0
                = 1

sumOfDigits(12) = 2 + 1
                 = 3

sumOfDigits(123) = 3 + 3
                  = 6

sumOfDigits(1234) = 4 + 6
                   = 10

FINAL ANSWER = 10

=========================================================
DRY RUN SUMMARY TABLE
=========================================================

n       n % 10      n / 10      Contribution
------------------------------------------------
1234       4          123             4
123        3           12             3
12         2            1             2
1          1            0             1
0          -            -             0

Total = 4 + 3 + 2 + 1 = 10

=========================================================
IMPORTANT OBSERVATION: WHY DOES sum WORK?
=========================================================

You declared:

int sum = 0;

Inside the recursive function.

Every recursive call gets its own local sum variable.

For example:

sumOfDigits(1234) -> sum = 4
sumOfDigits(123)  -> sum = 3
sumOfDigits(12)   -> sum = 2
sumOfDigits(1)    -> sum = 1

These are separate local variables.

The recursive return statements combine their
values while the calls return.

That is why this works:

return sum + sumOfDigits(n / 10);

=========================================================
KEY OBSERVATIONS
=========================================================

1. n % 10 extracts the last digit.

2. n / 10 removes the last digit.

3. Recursion processes one digit at a time.

4. n == 0 is the base case.

5. Each recursive call returns the sum of the
   remaining digits.

6. The return statements combine all digit values.

7. No additional array or collection is required.

=========================================================
EDGE CASES
=========================================================

1. n = 0

   Base case is immediately satisfied.

   Answer = 0

2. n = 5

   5 % 10 = 5

   Answer = 5

3. n = 123

   3 + 2 + 1 = 6

   Answer = 6

4. n = 100

   0 + 0 + 1 = 1

   Answer = 1

=========================================================
COMPLEXITY ANALYSIS
=========================================================

TIME COMPLEXITY: O(d)

Where d is the number of digits in n.

Each recursive call processes exactly one digit.

For example, 12345 contains 5 digits.

Therefore, 5 recursive calls process the digits,
followed by one base-case call.

SPACE COMPLEXITY: O(d)

Each recursive call occupies space in the
Java call stack.

The maximum recursion depth is proportional
to the number of digits.

=========================================================
INTERVIEW OBSERVATIONS
=========================================================

Q1. Why do we use n % 10?

Answer:

To extract the last digit of the number.

Q2. Why do we use n / 10?

Answer:

To remove the last digit so that the next
recursive call processes the remaining digits.

Q3. Why does recursion stop at n == 0?

Answer:

Because all digits have been processed.

Q4. Why is sum declared inside the function?

Answer:

Each function call only needs to store the
digit extracted during that particular call.

The recursive return statements combine the
individual digit sums.

Q5. What is the difference between this and
an iterative solution?

Answer:

In recursion, the function calls itself to
process the remaining digits.

In iteration, a loop repeatedly processes
the digits until the number becomes 0.

=========================================================
FINAL JAVA IMPLEMENTATION
=========================================================

*/
class Solution {
    static int sumOfDigits(int n) {
        // code here
        int sum =0;
        if(n==0)
            return 0;

        sum +=n%10;
        return sum+sumOfDigits(n/10);

    }
}
