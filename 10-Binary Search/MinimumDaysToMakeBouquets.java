
/*
Problem Name: Minimum Number of Days to Make m Bouquets
LeetCode Number: 1482
Difficulty: Medium
Pattern: Binary Search on Answer + Greedy

-------------------------------------------------------
1. PROBLEM UNDERSTANDING
-------------------------------------------------------

We are given an array bloomDay.

Each element tells us the day on which a particular
flower will bloom.

We are also given:

m = Number of bouquets required.
k = Number of adjacent flowers required per bouquet.

Our task is to find the minimum number of days needed
to make at least m bouquets.

IMPORTANT RULE:

Flowers must be adjacent.

We cannot pick flowers from different positions
and combine them into one bouquet.


-------------------------------------------------------
2. REAL-LIFE ANALOGY
-------------------------------------------------------

Imagine a garden containing flowers in a straight line.

You need to prepare bouquets for a wedding.

Each bouquet requires k adjacent flowers.

You cannot use:
- Flowers that have not bloomed.
- Flowers that have already been used.

You need to find the earliest day when you can
prepare all required bouquets.


-------------------------------------------------------
3. APPROACH
-------------------------------------------------------

We use Binary Search on Answer.

We are searching for the minimum number of days.

Minimum possible day = 1.

Maximum possible day = Maximum element in bloomDay.

We check whether a particular day is sufficient.

To count bouquets, we use a Greedy approach.

We traverse flowers from left to right and select
the earliest available consecutive groups of k flowers.


-------------------------------------------------------
4. STEP-BY-STEP LOGIC
-------------------------------------------------------

STEP 1: FIND SEARCH BOUNDARIES

low = 1

A flower cannot bloom before Day 1.

high = maximum blooming day.

After this day, every flower has bloomed.


STEP 2: CHECK WHETHER THE TASK IS POSSIBLE

If:

(long) m * k > bloomDay.length

We don't have enough flowers to make the required
number of bouquets.

Return -1 immediately.


STEP 3: INITIALIZE RESULT

result = 0

This stores the minimum valid day found so far.


STEP 4: START BINARY SEARCH

while(low <= high)

Calculate:

mid = (low + high) / 2

Mid represents a possible day.


STEP 5: COUNT POSSIBLE BOUQUETS

Call:

findNoOfBouque(bloomDay, k, mid)

The helper method counts how many bouquets can be
created by the selected day.


STEP 6: CHECK EACH FLOWER

If:

bloomDay[i] <= day

The flower has bloomed.

Increase:

bloomedFlower++


Otherwise:

bloomedFlower = 0

Why?

An unbloomed flower breaks the consecutive sequence.


STEP 7: MAKE A BOUQUET

If:

bloomedFlower == k

We have found k consecutive bloomed flowers.

Increase:

bouque++

Reset:

bloomedFlower = 0

Why?

Flowers already used cannot be reused.


STEP 8: CHECK THE NUMBER OF BOUQUETS

If:

noOfBouque >= m

We have enough bouquets.

Save the current day:

result = mid

Search for an earlier day:

high = mid - 1


Otherwise:

We don't have enough bouquets.

Wait for more flowers to bloom:

low = mid + 1


STEP 9: RETURN RESULT

After Binary Search finishes, return result.


-------------------------------------------------------
5. KEY OBSERVATIONS
-------------------------------------------------------

1. We need the minimum valid day.

2. The maximum blooming day is the upper boundary.

3. If a day is valid, all later days are also valid.

4. Adjacent flowers are mandatory.

5. An unbloomed flower breaks the consecutive sequence.

6. A flower cannot be reused.

7. We greedily form a bouquet as soon as k
   consecutive flowers are available.

8. We use Binary Search to avoid checking every day.


-------------------------------------------------------
6. DRY RUN
-------------------------------------------------------

Input:

bloomDay = [1, 10, 3, 10, 2]
m = 1
k = 2

We need one bouquet of two adjacent flowers.

Initial:

low = 1
high = 10
result = 0


ITERATION 1:

mid = 5

Available flowers:

[YES, NO, YES, NO, YES]

No two adjacent flowers have bloomed.

Bouquets = 0

0 >= 1 is FALSE.

Update:

low = mid + 1 = 6


ITERATION 2:

mid = 8

Available flowers:

[YES, NO, YES, NO, YES]

Bouquets = 0

0 >= 1 is FALSE.

Update:

low = mid + 1 = 9


ITERATION 3:

mid = 9

Available flowers:

[YES, NO, YES, NO, YES]

Bouquets = 0

0 >= 1 is FALSE.

Update:

low = mid + 1 = 10


ITERATION 4:

mid = 10

Available flowers:

[YES, YES, YES, YES, YES]

Consecutive pairs:

[1, 10] [3, 10]

Bouquets = 2

2 >= 1 is TRUE.

Save the answer:

result = 10

Search for an earlier day:

high = mid - 1 = 9


ITERATION 5:

low = 10
high = 9

Loop terminates.

Answer = 10


-------------------------------------------------------
7. IMPORTANT CONDITIONS
-------------------------------------------------------

(long)m * k > bloomDay.length

Checks whether enough flowers exist.

bloomDay[i] <= day

Checks whether the flower has bloomed.

bloomedFlower = 0

Resets consecutive count when an unbloomed
flower breaks the sequence.

bloomedFlower == k

Checks whether we have enough adjacent flowers
to form one bouquet.

bouque++

Creates one bouquet.

bloomedFlower = 0

Prevents flower reuse.

noOfBouque >= m

Checks whether the current day is valid.

high = mid - 1

Search for an earlier valid day.

low = mid + 1

Search for a later day.


-------------------------------------------------------
8. COMPLEXITY ANALYSIS
-------------------------------------------------------

Let:

N = Number of flowers.
D = Maximum blooming day.

TIME COMPLEXITY:

O(N * log D)

Finding the maximum day takes O(N).

Binary Search takes O(log D) iterations.

Each iteration traverses all N flowers
to calculate the number of bouquets.

Total = O(N * log D)


SPACE COMPLEXITY:

O(1)

Only a fixed number of variables are used.

No additional data structure is created.


-------------------------------------------------------
9. JAVA IMPLEMENTATION
-------------------------------------------------------

*/

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        int low = 1;
        int high = 0;

        for (int i = 0; i < bloomDay.length; i++) {
            high = Math.max(high, bloomDay[i]);
        }

        if ((long) m * k > bloomDay.length)
            return -1;

        int result = 0;

        while (low <= high) {

            int mid = (low + high) / 2;

            int noOfBouque = findNoOfBouque(
                    bloomDay, k, mid
            );

            if (noOfBouque >= m) {
                result = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return result;
    }

    public int findNoOfBouque(
            int[] bloomDay, int k, int day
    ) {

        int bouque = 0;
        int bloomedFlower = 0;

        for (int i = 0; i < bloomDay.length; i++) {

            if (bloomDay[i] <= day)
                bloomedFlower++;
            else
                bloomedFlower = 0;

            if (bloomedFlower == k) {
                bouque++;
                bloomedFlower = 0;
            }
        }

        return bouque;
    }
}
