
/*
 * Problem Name: Course Schedule III
 * LeetCode Number: 630
 * Difficulty: Hard
 * Pattern: Greedy + Sorting + Max Heap
 *
 * ------------------------------------------------------------
 * EXPLAIN LIKE I'M 10 YEARS OLD
 * ------------------------------------------------------------
 *
 * Imagine you want to complete as many courses as possible.
 *
 * Every course has:
 *
 * course[0] = duration
 * course[1] = deadline
 *
 * You can study only one course at a time.
 *
 * You must finish each selected course on or before
 * its deadline.
 *
 * Your goal is to select the maximum number of courses.
 *
 * Example:
 *
 * Course A: duration = 5, deadline = 5
 * Course B: duration = 4, deadline = 6
 * Course C: duration = 2, deadline = 6
 *
 * If we select A first:
 *
 * elapsedtime = 5
 *
 * B does not fit:
 *
 * 5 + 4 = 9 > 6
 *
 * But instead of rejecting B immediately,
 * we can remove A (5 days) and select B (4 days).
 *
 * Now:
 *
 * elapsedtime = 4
 *
 * Course C can also fit:
 *
 * 4 + 2 = 6
 *
 * We finish 2 courses instead of 1.
 *
 * ------------------------------------------------------------
 * STEP 1: SORT BY DEADLINE
 * ------------------------------------------------------------
 *
 * Sort courses in increasing order of deadline.
 *
 * Why?
 *
 * Courses with earlier deadlines are more urgent.
 *
 * We should consider them before courses with later
 * deadlines.
 *
 * ------------------------------------------------------------
 * STEP 2: CREATE A MAX HEAP
 * ------------------------------------------------------------
 *
 * Store the durations of selected courses.
 *
 * Why Max Heap?
 *
 * When a new course does not fit, we want to remove
 * the longest selected course.
 *
 * The Max Heap gives us that duration immediately.
 *
 * ------------------------------------------------------------
 * STEP 3: TRACK ELAPSED TIME
 * ------------------------------------------------------------
 *
 * elapsedtime represents the total duration of all
 * currently selected courses.
 *
 * ------------------------------------------------------------
 * STEP 4: CHECK WHETHER THE COURSE FITS
 * ------------------------------------------------------------
 *
 * If:
 *
 * elapsedtime + course duration <= deadline
 *
 * We can accept the course.
 *
 * Add its duration to elapsedtime and the Max Heap.
 *
 * ------------------------------------------------------------
 * STEP 5: IF IT DOES NOT FIT, CONSIDER REPLACEMENT
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 * elapsedtime = 15
 * Current course duration = 3
 * Current deadline = 16
 *
 * 15 + 3 = 18 > 16
 *
 * The current course does not fit.
 *
 * Suppose the longest selected course takes 8 days.
 *
 * Compare:
 *
 * 8 > 3
 *
 * True.
 *
 * Remove the 8-day course and add the 3-day course.
 *
 * New elapsedtime:
 *
 * 15 - 8 + 3 = 10
 *
 * The number of selected courses stays the same,
 * but the total time becomes smaller.
 *
 * This creates more room for future courses.
 *
 * If the longest selected course is not longer than
 * the current course, do not replace anything.
 *
 * ------------------------------------------------------------
 * DETAILED DRY RUN
 * ------------------------------------------------------------
 *
 * Input:
 *
 * courses = {
 *     {5,5},
 *     {4,6},
 *     {2,6}
 * };
 *
 * They are already sorted by deadline.
 *
 * COURSE 1: {5,5}
 *
 * elapsedtime + duration:
 *
 * 0 + 5 <= 5
 *
 * True.
 *
 * Accept:
 *
 * elapsedtime = 5
 * heap = [5]
 *
 * COURSE 2: {4,6}
 *
 * 5 + 4 <= 6
 *
 * False.
 *
 * Check longest selected course:
 *
 * maxheap.peek() = 5
 * current duration = 4
 *
 * 5 > 4
 *
 * True.
 *
 * Remove 5 and add 4.
 *
 * elapsedtime = 5 - 5 + 4 = 4
 * heap = [4]
 *
 * COURSE 3: {2,6}
 *
 * 4 + 2 <= 6
 *
 * True.
 *
 * Accept:
 *
 * elapsedtime = 6
 * heap = [4,2]
 *
 * Final answer = 2
 *
 * ------------------------------------------------------------
 * IMPORTANT OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Sort by increasing deadline.
 *
 * 2. Max Heap stores selected course durations.
 *
 * 3. If a course fits, accept it.
 *
 * 4. If it does not fit, compare its duration with
 *    the longest selected course.
 *
 * 5. Replace the longest course only when it is longer
 *    than the current course.
 *
 * 6. Replacing a longer course with a shorter one
 *    reduces elapsed time without reducing course count.
 *
 * 7. Heap size is the number of selected courses.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * N = number of courses.
 *
 * Sorting:
 * O(N log N)
 *
 * Processing courses:
 * O(N) iterations.
 *
 * Heap insertion/removal:
 * O(log N) per operation.
 *
 * Overall Time Complexity:
 * O(N log N)
 *
 * Space Complexity:
 * O(N) for the Max Heap.
 */

class Solution {
    public int scheduleCourse(int[][] courses) {

        // STEP 1: Sort courses by increasing deadline.
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // STEP 2: Max Heap stores selected course durations.
        // The longest duration is available at the top.
        PriorityQueue<Integer> maxheap =
                new PriorityQueue<>(Collections.reverseOrder());

        // Total duration of currently selected courses.
        int elapsedtime = 0;

        // This variable is unused in the original implementation.
        int coursecount = 0;

        // STEP 3: Process courses in deadline order.
        for (int[] course : courses) {

            // Ignore a course that cannot be completed
            // even if we start it on Day 1.
            if (course[0] <= course[1]) {

                // STEP 4: Check whether the current course fits.
                if (elapsedtime + course[0] <= course[1]) {

                    // Accept the current course.
                    elapsedtime += course[0];

                    // Store its duration in the Max Heap.
                    maxheap.offer(course[0]);
                }

                else {

                    // STEP 5: Current course does not fit.
                    // Check whether the longest selected course
                    // is longer than the current course.
                    if (maxheap.peek() > course[0]) {

                        // Remove the longest course and add
                        // the current shorter course.
                        elapsedtime =
                                elapsedtime - maxheap.poll() + course[0];

                        // Store the current course duration.
                        maxheap.offer(course[0]);
                    }
                }
            }
        }

        // Heap size represents the maximum selected courses.
        return maxheap.size();
    }
}
