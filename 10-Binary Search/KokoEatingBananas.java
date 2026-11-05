
/*
Problem Name: Koko Eating Bananas
LeetCode Number: 875
Difficulty: Medium
Pattern: Binary Search on Answer

-------------------------------------------------------
1. PROBLEM UNDERSTANDING
-------------------------------------------------------

We are given an array of banana piles.

We are also given h, which represents the maximum
number of hours available.

Koko can choose an eating speed k.

During each hour:
- She eats at most k bananas from one pile.
- If the pile has fewer than k bananas, she finishes it.
- She cannot move to another pile in that same hour.

Our task is to find the MINIMUM eating speed that
allows her to finish all bananas within h hours.


-------------------------------------------------------
2. REAL-LIFE ANALOGY
-------------------------------------------------------

Imagine four boxes containing chocolates.

[3, 6, 7, 11]

You have only 8 hours to finish everything.

You can choose how many chocolates to eat per hour.

If you eat too slowly, you won't finish on time.

If you eat too quickly, you finish earlier than necessary.

We want to find the smallest speed that allows
us to finish within the available time.


-------------------------------------------------------
3. APPROACH: BINARY SEARCH ON ANSWER
-------------------------------------------------------

We are not searching for an element in the array.

We are searching for a possible eating speed.

The minimum possible speed is 1.

The maximum possible speed is the largest pile.

Therefore:

low = 1
high = maximum pile size

We use Binary Search to find the minimum valid speed.

IMPORTANT OBSERVATION:

If a speed works, every speed greater than it
will also work.

If a speed does not work, every speed smaller
than it will also fail.

This creates a monotonic search space.


-------------------------------------------------------
4. STEP-BY-STEP LOGIC
-------------------------------------------------------

STEP 1: FIND THE SEARCH SPACE

Initialize:

low = 1
high = 0

Traverse the piles and find the maximum pile.

high = Math.max(high, piles[i]);

Why?

Because eating at the maximum pile size per hour
is sufficient to finish every pile in one hour each.


STEP 2: START BINARY SEARCH

while(low <= high)

Continue until all possible speeds have been checked
or eliminated.

We use <= because the final remaining speed
must also be checked.


STEP 3: FIND THE MIDDLE SPEED

mid = (low + high) / 2

This is one possible eating speed.

For example:

low = 1
high = 11

mid = 6

Now we check whether speed 6 is sufficient.


STEP 4: CALCULATE REQUIRED TIME

Call:

timeTaken(piles, mid)

This helper method calculates how many hours
Koko needs to finish all piles.

For every pile:

hours = ceil(pile / speed)

Add all hours to get the total time.


STEP 5: CHECK WHETHER THE SPEED IS VALID

Condition:

if(time <= h)

If TRUE:

Koko can finish within the available hours.

But we want the minimum speed.

Therefore, search for a smaller speed:

high = mid - 1


STEP 6: IF THE SPEED IS TOO SLOW

If:

time > h

Koko cannot finish within the given hours.

We need to eat faster.

Therefore:

low = mid + 1


STEP 7: RETURN THE ANSWER

When the loop finishes:

low = first valid speed
high = last invalid speed

Therefore:

return (int) low;


-------------------------------------------------------
5. KEY OBSERVATIONS
-------------------------------------------------------

1. Minimum speed is 1.

2. Maximum useful speed is the largest pile.

3. The middle value represents a possible speed,
   not an array index.

4. If a speed works, smaller speeds might also work.

5. If a speed fails, larger speeds must be tried.

6. We use ceiling division because partial hours
   still count as a complete hour.

7. We return low because we need the first valid speed.


-------------------------------------------------------
6. DRY RUN
-------------------------------------------------------

Input:

piles = [3, 6, 7, 11]
h = 8


ITERATION 1:

low = 1
high = 11
mid = 6

Required time:

ceil(3/6)  = 1
ceil(6/6)  = 1
ceil(7/6)  = 2
ceil(11/6) = 2

Total = 6 hours

6 <= 8

TRUE

Speed is valid.

Search for smaller speed.

high = mid - 1 = 5


ITERATION 2:

low = 1
high = 5
mid = 3

Required time:

ceil(3/3)  = 1
ceil(6/3)  = 2
ceil(7/3)  = 3
ceil(11/3) = 4

Total = 10 hours

10 <= 8

FALSE

Speed is too slow.

Search for faster speed.

low = mid + 1 = 4


ITERATION 3:

low = 4
high = 5
mid = 4

Required time:

ceil(3/4)  = 1
ceil(6/4)  = 2
ceil(7/4)  = 2
ceil(11/4) = 3

Total = 8 hours

8 <= 8

TRUE

Speed is valid.

Search for a smaller speed.

high = mid - 1 = 3


ITERATION 4:

low = 4
high = 3

low <= high is FALSE.

Loop terminates.

Answer = 4


-------------------------------------------------------
7. IMPORTANT CONDITIONS
-------------------------------------------------------

low = 1

Speed cannot be zero.

high = maximum pile

No speed greater than this is necessary.

while(low <= high)

We must check the final remaining candidate.

time <= h

Current speed is sufficient.

high = mid - 1

Try to find a smaller valid speed.

time > h

Current speed is insufficient.

low = mid + 1

Try a faster speed.

return (int) low

Return the minimum valid speed.


-------------------------------------------------------
8. COMPLEXITY ANALYSIS
-------------------------------------------------------

Let:

N = Number of piles
M = Maximum pile size

TIME COMPLEXITY:

O(N * log M)

Finding the maximum pile takes O(N).

Binary Search takes O(log M) iterations.

Each iteration calls timeTaken(), which traverses
all N piles and takes O(N).

Total = O(N + N * log M)
      = O(N * log M)


SPACE COMPLEXITY:

O(1)

We use only a fixed number of variables.

No additional array or data structure is created.


-------------------------------------------------------
9. JAVA IMPLEMENTATION
-------------------------------------------------------

*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        long low = 1;
        long high = 0;

        for (int i = 0; i < piles.length; i++) {
            high = Math.max(high, piles[i]);
        }

        while (low <= high) {
            long mid = (low + high) / 2;

            long time = timeTaken(piles, mid);

            if (time <= h) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return (int) low;
    }

    public long timeTaken(int[] piles, long hr) {

        long sum = 0;

        for (int i = 0; i < piles.length; i++) {

            // Ceiling division:
            // Calculates how many hours are needed
            // to finish the current pile.

            sum += (piles[i] + (hr - 1)) / hr;
        }

        return sum;
    }
}
