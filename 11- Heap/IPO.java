
/*
 * Problem Name: IPO
 * LeetCode Number: 502
 * Difficulty: Hard
 * Pattern: Greedy + Two Priority Queues
 *           (Min Heap + Max Heap)
 *
 * ------------------------------------------------------------
 * EXPLAIN LIKE I'M 10 YEARS OLD
 * ------------------------------------------------------------
 *
 * Imagine you have some money and want to invest in projects.
 *
 * Every project has two things:
 *
 * 1. Capital: Minimum money required to start the project.
 * 2. Profit: Money earned after completing the project.
 *
 * You can complete at most k projects.
 *
 * Whenever you complete a project, its profit is added
 * to your current money.
 *
 * Your goal is to maximize your final money.
 *
 * Example:
 *
 * k = 2
 * w = 0
 *
 * profits = [1, 2, 3]
 * capital = [0, 1, 1]
 *
 * Initially, only the project requiring capital 0
 * is affordable.
 *
 * Complete it and earn profit 1.
 *
 * Now capital becomes 1.
 *
 * Two more projects are affordable:
 *
 * Project 1: Profit 2
 * Project 2: Profit 3
 *
 * Choose profit 3 because it is higher.
 *
 * Final capital = 0 + 1 + 3 = 4.
 *
 * ------------------------------------------------------------
 * WHY DO WE NEED TWO PRIORITY QUEUES?
 * ------------------------------------------------------------
 *
 * We have two different decisions.
 *
 * DECISION 1:
 * Which projects can I currently afford?
 *
 * Use a MIN HEAP sorted by capital.
 *
 * The project requiring the smallest capital comes first.
 *
 * DECISION 2:
 * Among affordable projects, which gives the highest profit?
 *
 * Use a MAX HEAP sorted by profit.
 *
 * The project with the highest profit comes first.
 *
 * Therefore:
 *
 * projects = Min Heap based on capital.
 * pq       = Max Heap based on profit.
 *
 * ------------------------------------------------------------
 * STEP-BY-STEP APPROACH
 * ------------------------------------------------------------
 *
 * STEP 1: CREATE PROJECT OBJECTS
 *
 * Every project has:
 *
 * capital = minimum required capital
 * profit  = profit earned after completion
 *
 * The ipo class stores both values together.
 *
 * STEP 2: INSERT ALL PROJECTS INTO MIN HEAP
 *
 * Sort projects by capital in ascending order.
 *
 * Example:
 *
 * (capital=0, profit=1)
 * (capital=1, profit=2)
 * (capital=1, profit=3)
 *
 * STEP 3: TRACK CURRENT CAPITAL
 *
 * maxprofit = w
 *
 * This variable represents the money currently available.
 *
 * STEP 4: PROCESS AT MOST k PROJECTS
 *
 * While k > 0:
 *
 * Move all affordable projects from the Min Heap
 * into the Max Heap.
 *
 * A project is affordable when:
 *
 * projects.peek().capital <= maxprofit
 *
 * STEP 5: SELECT THE HIGHEST PROFIT
 *
 * If the Max Heap is empty, no project is affordable.
 * Stop because we cannot increase our capital anymore.
 *
 * Otherwise:
 *
 * maxprofit += pq.poll()
 *
 * This selects the highest available profit.
 *
 * Decrease k because one project has been completed.
 *
 * STEP 6: REPEAT
 *
 * Increased capital may unlock additional projects.
 *
 * Move those newly affordable projects into the Max Heap.
 *
 * Continue until k projects are selected or no project
 * can be selected.
 *
 * ------------------------------------------------------------
 * DETAILED DRY RUN
 * ------------------------------------------------------------
 *
 * k = 2
 * w = 0
 *
 * profits = [1, 2, 3]
 * capital = [0, 1, 1]
 *
 * INITIAL:
 *
 * projects = [(0,1), (1,2), (1,3)]
 * pq = []
 *
 * maxprofit = 0
 * k = 2
 *
 * ITERATION 1:
 *
 * Current capital = 0.
 *
 * Project (0,1) is affordable.
 *
 * Move profit 1 into Max Heap.
 *
 * pq = [1]
 *
 * Projects requiring capital 1 are not affordable yet.
 *
 * Select maximum profit = 1.
 *
 * maxprofit = 0 + 1 = 1
 * k = 1
 *
 * ITERATION 2:
 *
 * Current capital = 1.
 *
 * Projects (1,2) and (1,3) are now affordable.
 *
 * Move both profits into Max Heap.
 *
 * pq = [3, 2]
 *
 * Select maximum profit = 3.
 *
 * maxprofit = 1 + 3 = 4
 * k = 0
 *
 * Outer loop ends.
 *
 * FINAL ANSWER = 4
 *
 * ------------------------------------------------------------
 * IMPORTANT INTERVIEW OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Min Heap identifies affordable projects.
 *
 * 2. Max Heap selects the highest-profit affordable project.
 *
 * 3. We move a project to the profit heap only once.
 *
 * 4. We move ALL affordable projects before selecting
 *    the highest profit.
 *
 * 5. We do not deduct capital when starting a project.
 *    Capital is only a minimum eligibility requirement.
 *
 * 6. If the Max Heap is empty, no currently affordable
 *    project exists, so we stop.
 *
 * 7. The greedy choice is optimal because selecting the
 *    highest profit gives the maximum possible increase
 *    in capital for one project selection.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * N = number of projects
 * K = maximum number of projects to select
 *
 * Building Min Heap:
 * O(N log N)
 *
 * Moving projects between heaps:
 * O(N log N) total
 *
 * Selecting up to K projects:
 * O(K log N)
 *
 * Overall Time Complexity:
 * O(N log N)
 *
 * Space Complexity:
 * O(N)
 *
 */

class ipo {

    // Minimum capital required to start this project.
    long capital;

    // Profit earned after completing this project.
    long profit;

    ipo(long capital, long profit) {
        this.capital = capital;
        this.profit = profit;
    }
}

class Solution {

    public int findMaximizedCapital(
            int k, int w, int[] profits, int[] capital) {

        // MIN HEAP:
        // Projects with the lowest capital requirement
        // are available at the top.
        PriorityQueue<ipo> projects = new PriorityQueue<>(
                (i1, i2) -> Long.compare(i1.capital, i2.capital)
        );

        // MAX HEAP:
        // Highest profit comes out first.
        PriorityQueue<Long> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        // STEP 1: Create project objects and add them
        // to the Min Heap.
        for (int i = 0; i < profits.length; i++) {

            projects.offer(
                    new ipo((long) capital[i], (long) profits[i])
            );
        }

        // Current available capital.
        long maxprofit = w;

        // STEP 2: Select at most k projects.
        while (k > 0) {

            // STEP 3: Move ALL currently affordable projects
            // from the Min Heap into the Max Heap.
            while (!projects.isEmpty()
                    && projects.peek().capital <= maxprofit) {

                pq.offer(projects.poll().profit);
            }

            // No affordable project is available.
            if (pq.isEmpty())
                break;

            // STEP 4: Select the project with the highest profit.
            // Add its profit to current capital.
            k--;
            maxprofit += pq.poll();
        }

        // Return the maximum capital after project selection.
        return (int) maxprofit;
    }
}
