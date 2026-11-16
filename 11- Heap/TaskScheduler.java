
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



/*
================================================================
PROBLEM: TASK SCHEDULER
LEETCODE: 621
DIFFICULTY: MEDIUM
PATTERNS: GREEDY, FREQUENCY ARRAY
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an array of tasks represented by uppercase characters
and a cooldown period n, execute all tasks in minimum intervals.

Rules:
1. Each task takes one interval.
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
APPROACH: GREEDY + FREQUENCY ARRAY
================================================================

CORE IDEA
----------------------------------------------------------------
Instead of using a HashMap, we use an integer array of size 26.

Since tasks contain only uppercase English letters (A-Z),
we can directly use their character positions as array indices.

We count the frequency of every task.

Then we find:

1. Maximum frequency of any task.
2. Number of tasks having that maximum frequency.

Finally, we use these values to calculate the minimum intervals.

================================================================
STEP 1: CREATE FREQUENCY ARRAY
================================================================

int[] freqarr = new int[26];

WHY?

There are 26 uppercase English letters.

Each index represents one character:

A -> 0
B -> 1
C -> 2
...
Z -> 25

Initially, every index contains 0.

================================================================
STEP 2: COUNT TASK FREQUENCIES
================================================================

for(char ch : tasks){
    freqarr[ch - 'A']++;
}

WHY ch - 'A'?

Java characters have numeric values.

For example:

'A' - 'A' = 0
'B' - 'A' = 1
'C' - 'A' = 2

Therefore, subtracting 'A' converts each character
into its corresponding array index.

DRY RUN:

tasks = ['A','A','A','B','B','B','C']

freqarr:

Index 0 (A) -> 3
Index 1 (B) -> 3
Index 2 (C) -> 1

All remaining indices -> 0

================================================================
STEP 3: FIND MAXIMUM FREQUENCY
================================================================

int maxfreq = 0;

for(int i : freqarr){
    maxfreq = Math.max(maxfreq,i);
}

WHY?

We need to identify the task that occurs most frequently.

From our example:

A -> 3
B -> 3
C -> 1

Therefore:

maxfreq = 3

================================================================
STEP 4: COUNT TASKS WITH MAXIMUM FREQUENCY
================================================================

int maxcount = 0;

for(int i : freqarr){
    if(i == maxfreq)
        maxcount++;
}

WHY?

There can be multiple tasks with the same maximum frequency.

Example:

A -> 3
B -> 3
C -> 1

Both A and B have the maximum frequency.

Therefore:

maxcount = 2

IMPORTANT:

The frequency array also contains unused characters with
frequency 0.

But maxfreq is at least 1 for a non-empty task array,
so those unused characters are not counted.

================================================================
STEP 5: CALCULATE MINIMUM INTERVALS
================================================================

return Math.max(
    tasks.length,
    ((maxfreq-1)*(n+1))+maxcount
);

UNDERSTANDING THE FORMULA
----------------------------------------------------------------

(maxfreq - 1)

Number of gaps between repetitions of the most frequent task.

(n + 1)

One task position plus the required cooldown spacing.

maxcount

Number of tasks sharing the maximum frequency.

tasks.length

Total number of actual tasks.

WHY Math.max()?

If there are enough different tasks to fill the cooldown
gaps, no idle time is required.

Therefore, the answer cannot be smaller than tasks.length.

================================================================
DRY RUN
================================================================

Input:

tasks = ['A','A','A','B','B','B']
n = 2

FREQUENCY ARRAY:

A -> 3
B -> 3

STEP 1:

maxfreq = 3

STEP 2:

maxcount = 2

STEP 3:

Formula:

(maxfreq - 1) * (n + 1) + maxcount

= (3 - 1) * (2 + 1) + 2

= 2 * 3 + 2

= 8

STEP 4:

Math.max(tasks.length, 8)

Math.max(6, 8) = 8

OUTPUT:

8

POSSIBLE SCHEDULE:

A B idle A B idle A B

Total intervals = 8

================================================================
IMPORTANT INTERVIEW OBSERVATIONS
================================================================

1. When the input contains a limited range of characters,
   a frequency array can replace a HashMap.

2. ch - 'A' converts uppercase characters into array indices.

3. maxfreq identifies the most frequent task.

4. maxcount handles multiple tasks having the same
   maximum frequency.

5. Greedy scheduling is based on the most frequent tasks.

6. Other tasks can occupy the cooldown gaps.

7. Math.max() ensures that we account for all actual tasks.

================================================================
COMPLEXITY ANALYSIS
================================================================

Let N = tasks.length.

TIME COMPLEXITY:

O(N + 26 + 26)

1. Building frequency array: O(N)
2. Finding maximum frequency: O(26)
3. Counting maximum frequency tasks: O(26)

Overall: O(N)

SPACE COMPLEXITY:

O(26) = O(1)

The frequency array always contains exactly 26 elements.

================================================================
FULL JAVA IMPLEMENTATION
================================================================
*/

class Solution {
    public int leastInterval(char[] tasks, int n) {

        // Step 1: Create frequency array
        int[] freqarr = new int[26];

        // Step 2: Count frequency of every task
        for(char ch : tasks){
            freqarr[ch - 'A']++;
        }

        int maxfreq = 0;
        int maxcount = 0;

        // Step 3: Find maximum frequency
        for(int i : freqarr){
            maxfreq = Math.max(maxfreq,i);
        }

        // Step 4: Count tasks having maximum frequency
        for(int i : freqarr){
            if(i == maxfreq)
                maxcount++;
        }

        // Step 5: Calculate minimum intervals
        return Math.max(
                tasks.length,
                ((maxfreq-1)*(n+1))+maxcount
        );
    }
}

/*
 * Problem Name: Task Scheduler
 * LeetCode Number: 621
 * Difficulty: Medium
 * Pattern: Greedy + Max Heap + Simulation
 *
 * ------------------------------------------------------------
 * EXPLAIN LIKE I'M 10 YEARS OLD
 * ------------------------------------------------------------
 *
 * Imagine a computer needs to complete several tasks.
 *
 * Some tasks appear multiple times.
 *
 * After executing a task, the computer must wait for n time
 * units before executing that SAME task again.
 *
 * During the waiting period, the computer can execute other
 * available tasks. If no task is available, it stays idle.
 *
 * Our goal is to complete all tasks in the minimum time.
 *
 * Example:
 *
 * tasks = ['A','A','A','B','B','B']
 * n = 2
 *
 * A valid schedule:
 *
 * A -> B -> Idle -> A -> B -> Idle -> A -> B
 *
 * Total time = 8
 *
 * ------------------------------------------------------------
 * APPROACH: GREEDY + MAX HEAP + CYCLE SIMULATION
 * ------------------------------------------------------------
 *
 * STEP 1: COUNT FREQUENCIES
 *
 * Use an array of size 26 because tasks are uppercase letters.
 *
 * freq[0] represents A
 * freq[1] represents B
 * freq[2] represents C
 * ...
 *
 * STEP 2: CREATE A MAX HEAP
 *
 * Store only the frequencies of tasks that actually exist.
 *
 * Why Max Heap?
 *
 * We want to execute the task with the highest remaining
 * frequency first.
 *
 * This helps schedule frequently repeated tasks early.
 *
 * STEP 3: CREATE A CYCLE OF n + 1
 *
 * Why n + 1?
 *
 * After executing a task, we need n time units before
 * executing that same task again.
 *
 * Therefore, one task can appear at most once in a cycle
 * containing n + 1 slots.
 *
 * Example:
 *
 * n = 2
 * cycle = 3
 *
 * A -> B -> C
 *
 * The same task cannot be selected twice in these 3 slots.
 *
 * STEP 4: USE A TEMPORARY LIST
 *
 * When we remove a frequency from the heap and execute
 * that task, decrease its frequency by 1.
 *
 * Store the decreased frequency in temp.
 *
 * Do NOT immediately insert it back into the heap.
 *
 * Why?
 *
 * Because that would allow the same task to be selected
 * again in the same cycle, violating the cooldown.
 *
 * After the cycle ends, put the remaining frequencies
 * back into the heap.
 *
 * STEP 5: COUNT TIME
 *
 * Every executed task consumes one time unit.
 *
 * Every unused slot in a cycle becomes idle time if
 * tasks are still remaining.
 *
 * If the heap becomes empty, all tasks are completed.
 * Do not count unnecessary idle slots after completion.
 *
 * ------------------------------------------------------------
 * DETAILED DRY RUN
 * ------------------------------------------------------------
 *
 * Input:
 *
 * tasks = ['A','A','A','B','B','B']
 * n = 2
 *
 * Frequency:
 *
 * A = 3
 * B = 3
 *
 * Initial heap:
 *
 * pq = [3, 3]
 *
 * time = 0
 *
 * CYCLE 1:
 *
 * cycle = n + 1 = 3
 *
 * 1. Remove 3 (A), remaining frequency = 2.
 *    Store 2 in temp.
 *    time = 1, cycle = 2.
 *
 * 2. Remove 3 (B), remaining frequency = 2.
 *    Store 2 in temp.
 *    time = 2, cycle = 1.
 *
 * Heap is now empty.
 *
 * Put temp values back:
 *
 * temp = [2, 2]
 * pq = [2, 2]
 *
 * One slot remains in the cycle.
 * Since tasks are still remaining, that slot is idle.
 *
 * time = 2 + 1 = 3.
 *
 * Schedule: A -> B -> Idle
 *
 * CYCLE 2:
 *
 * pq = [2, 2]
 * cycle = 3
 *
 * 1. Remove 2 (A), remaining = 1.
 *    Store 1 in temp.
 *    time = 4, cycle = 2.
 *
 * 2. Remove 2 (B), remaining = 1.
 *    Store 1 in temp.
 *    time = 5, cycle = 1.
 *
 * Put temp values back:
 *
 * temp = [1, 1]
 * pq = [1, 1]
 *
 * One slot remains, and tasks are still remaining.
 * Therefore, add one idle time unit.
 *
 * time = 5 + 1 = 6.
 *
 * Schedule: A -> B -> Idle
 *
 * CYCLE 3:
 *
 * pq = [1, 1]
 * cycle = 3
 *
 * 1. Remove 1 (A).
 *    This is its final execution.
 *    Do not add zero to temp.
 *    time = 7, cycle = 2.
 *
 * 2. Remove 1 (B).
 *    This is its final execution.
 *    Do not add zero to temp.
 *    time = 8, cycle = 1.
 *
 * Heap is empty.
 *
 * Break immediately.
 *
 * Do NOT add the remaining cycle slot because all tasks
 * are already completed.
 *
 * Final schedule:
 *
 * A -> B -> Idle -> A -> B -> Idle -> A -> B
 *
 * Answer = 8
 *
 * ------------------------------------------------------------
 * IMPORTANT OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Max Heap:
 *    Always gives us the task with the highest frequency.
 *
 * 2. cycle = n + 1:
 *    Creates a window in which a task can execute only once.
 *
 * 3. temp:
 *    Prevents a task from being selected again in the same cycle.
 *
 * 4. current > 1:
 *    Prevents zero frequencies from entering the heap.
 *
 * 5. pq.isEmpty():
 *    Prevents unnecessary idle time after all tasks finish.
 *
 * 6. time += cycle:
 *    Counts unused cycle slots as idle time when tasks remain.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Let N = total number of tasks.
 *
 * Frequency counting:
 * O(N)
 *
 * Heap size:
 * At most 26 because there are only 26 uppercase letters.
 *
 * Each task execution involves heap operations:
 * O(log 26)
 *
 * Total time:
 * O(N * log 26) = O(N), since 26 is constant.
 *
 * Space:
 * Frequency array = O(26)
 * Heap = O(26)
 * Temporary list = O(26)
 *
 * Overall auxiliary space = O(1), since the alphabet
 * size is fixed.
 */

class Solution {
    public int leastInterval(char[] tasks, int n) {

        // STEP 1: Count how many times each task appears.
        int[] freq = new int[26];

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // STEP 2: Create a Max Heap.
        // Highest frequency will be removed first.
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        // Add only frequencies greater than zero.
        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        // Counts task execution time + idle time.
        int time = 0;

        // Continue until every task is completed.
        while (!pq.isEmpty()) {

            // Stores decreased frequencies temporarily.
            // They cannot be reused in the same cycle.
            List<Integer> temp = new ArrayList<>();

            // One cycle contains n + 1 time slots.
            int cycle = n + 1;

            // Execute different available tasks in this cycle.
            while (cycle > 0 && !pq.isEmpty()) {

                // Get the task with the highest remaining frequency.
                int current = pq.poll();

                // One occurrence is now completed.
                // Store only if more occurrences are remaining.
                if (current > 1) {
                    temp.add(current - 1);
                }

                // One task execution consumes one time unit.
                time++;

                // One cycle slot has been used.
                cycle--;
            }

            // Make remaining tasks available for the next cycle.
            for (int num : temp) {
                pq.offer(num);
            }

            // All tasks are completed.
            // Do not count unnecessary idle time.
            if (pq.isEmpty()) {
                break;
            }

            // Any unused slots in this cycle are idle time.
            time += cycle;
        }

        return time;
    }
}
