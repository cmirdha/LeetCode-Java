
/*
================================================================
PROBLEM: TASK SCHEDULER
LEETCODE: 621
DIFFICULTY: MEDIUM
PATTERNS: GREEDY, HASHMAP, FREQUENCY COUNTING
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an array of tasks represented by characters and a cooldown
period n, execute all tasks in the minimum number of intervals.

Rules:
1. Each task takes exactly one interval.
2. The same task must have at least n intervals between
   two consecutive executions.
3. Different tasks can be executed in any order.
4. If no task is available, the CPU remains idle.

Return the minimum number of intervals required.

Example:

Input:
tasks = ['A','A','A','B','B','B']
n = 2

Output:
8

Possible schedule:

A B idle A B idle A B

================================================================
APPROACH: GREEDY + FREQUENCY COUNTING
================================================================

CORE IDEA
----------------------------------------------------------------
The task occurring most frequently creates the biggest
scheduling restriction.

We need to identify:

1. The maximum frequency of any task.
2. How many tasks have that maximum frequency.

Using these two values, we can calculate the minimum
number of intervals required.

The idea is to arrange the most frequent tasks first
and account for the cooldown gaps between them.

================================================================
STEP 1: COUNT TASK FREQUENCIES
================================================================

Map<Character,Integer> map = new HashMap<>();

for(char ch : tasks){
    map.put(ch,map.getOrDefault(ch,0)+1);
}

WHY?

We need to know how many times each task occurs.

Example:

tasks = ['A','A','A','B','B','B','C']

Frequency Map:

A -> 3
B -> 3
C -> 1

The HashMap stores the frequency of every task.

================================================================
STEP 2: FIND THE MAXIMUM FREQUENCY
================================================================

int maxfreq = 0;

for(char c : map.keySet()){
    int val = map.get(c);
    maxfreq = Math.max(maxfreq,val);
}

WHY?

We need to identify the task that occurs the most.

Example:

A -> 3
B -> 3
C -> 1

maxfreq = 3

This means the most frequent task needs to execute
3 times.

================================================================
STEP 3: COUNT TASKS WITH MAXIMUM FREQUENCY
================================================================

int maxcount = 0;

for(char c : map.keySet()){
    int val = map.get(c);

    if(val == maxfreq)
        maxcount++;
}

WHY?

There might be multiple tasks having the same
maximum frequency.

Example:

A -> 3
B -> 3
C -> 1

Both A and B have maximum frequency 3.

Therefore:

maxcount = 2

This is important because all maximum-frequency tasks
can occupy the final group of the schedule.

================================================================
STEP 4: CALCULATE THE MINIMUM INTERVALS
================================================================

return Math.max(
    tasks.length,
    ((maxfreq-1)*(n+1))+maxcount
);

UNDERSTANDING THE EXPRESSION
----------------------------------------------------------------

(maxfreq - 1)

Represents the number of gaps between repetitions
of the most frequent task.

(n + 1)

Represents one task position plus the cooldown spacing.

maxcount

Represents the number of tasks sharing maximum frequency.
They occupy the final group.

tasks.length

Represents the total number of actual tasks.

WHY Math.max()?

The calculated scheduling arrangement might require idle
intervals.

But if there are enough other tasks to fill those gaps,
we don't need idle intervals.

Therefore, the minimum intervals cannot be smaller than
the total number of tasks.

================================================================
DRY RUN
================================================================

Input:

tasks = ['A','A','A','B','B','B']
n = 2

FREQUENCY MAP:

A -> 3
B -> 3

STEP 1:

maxfreq = 3

STEP 2:

maxcount = 2

STEP 3:

Calculate:

(maxfreq - 1) * (n + 1) + maxcount

= (3 - 1) * (2 + 1) + 2

= 2 * 3 + 2

= 8

STEP 4:

tasks.length = 6

Math.max(6,8) = 8

OUTPUT:

8

POSSIBLE SCHEDULE:

A B idle A B idle A B

Total intervals = 8

================================================================
IMPORTANT INTERVIEW OBSERVATIONS
================================================================

1. Frequency counting helps identify the most frequent task.

2. The most frequent task creates the biggest cooldown
   scheduling restriction.

3. Multiple tasks can share the maximum frequency.

4. maxcount is necessary to account for those tasks
   in the final group.

5. Other tasks can fill the idle intervals.

6. Math.max(tasks.length, formula) ensures that the result
   never becomes smaller than the number of actual tasks.

================================================================
COMPLEXITY ANALYSIS
================================================================

Let n = tasks.length.

TIME COMPLEXITY:

O(N)

1. Building the frequency map: O(N)
2. Finding maximum frequency: O(26)
3. Counting maximum-frequency tasks: O(26)

Since there are at most 26 uppercase English letters,
the frequency-map operations take constant space.

SPACE COMPLEXITY:

O(1)

At most 26 distinct task types can be stored.

================================================================
FULL JAVA IMPLEMENTATION
================================================================
*/

class Solution {
    public int leastInterval(char[] tasks, int n) {

        Map<Character,Integer> map = new HashMap<>();

        // Step 1: Count the frequency of every task
        for(char ch : tasks){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        int maxfreq = 0;
        int maxcount = 0;

        // Step 2: Find the maximum frequency
        for(char c : map.keySet()){
            int val = map.get(c);
            maxfreq = Math.max(maxfreq,val);
        }

        // Step 3: Count tasks having maximum frequency
        for(char c : map.keySet()){
            int val = map.get(c);

            if(val == maxfreq)
                maxcount++;
        }

        // Step 4: Calculate minimum intervals
        return Math.max(
                tasks.length,
                ((maxfreq-1)*(n+1))+maxcount
        );
    }
}
