
/*
 * Problem Name: Minimum Number of Refueling Stops
 * LeetCode Number: 871
 * Difficulty: Hard
 * Pattern: Greedy + Max Heap + Reachability
 *
 * ------------------------------------------------------------
 * EXPLAIN LIKE I'M 10 YEARS OLD
 * ------------------------------------------------------------
 *
 * Imagine you are driving a car towards a destination.
 *
 * Your car starts with some fuel.
 *
 * There are petrol stations along the road.
 * Each station provides a certain amount of fuel.
 *
 * You want to reach the destination using the minimum
 * number of refueling stops.
 *
 * Important:
 *
 * You don't need to refuel immediately when you reach
 * a petrol station.
 *
 * Instead, remember the fuel available at every station
 * you have already passed.
 *
 * Whenever your current fuel is insufficient to reach
 * the destination, choose the largest available fuel amount
 * from the stations you have passed.
 *
 * ------------------------------------------------------------
 * APPROACH: GREEDY + MAX HEAP
 * ------------------------------------------------------------
 *
 * STEP 1: CREATE A MAX HEAP
 *
 * Store fuel amounts from stations that are currently
 * reachable.
 *
 * Why Max Heap?
 *
 * When we need to refuel, we want the largest fuel amount.
 *
 * Every refueling stop costs exactly one stop.
 *
 * Therefore, choosing the largest available fuel gives
 * us the maximum increase in reach for that stop.
 *
 * STEP 2: TRACK CURRENT REACH
 *
 * reach = farthest distance we can currently travel.
 *
 * Initially:
 *
 * reach = startFuel
 *
 * Example:
 *
 * startFuel = 10
 *
 * We can initially reach distance 10.
 *
 * STEP 3: PROCESS REACHABLE STATIONS
 *
 * Stations are sorted by their positions.
 *
 * If:
 *
 * stations[idx][0] <= reach
 *
 * The station is reachable.
 *
 * Add its fuel amount to the Max Heap.
 *
 * Then increment idx because we have processed this station.
 *
 * STEP 4: DECIDE WHEN TO REFUEL
 *
 * Continue while:
 *
 * reach < target
 *
 * If the target is not reached, we need more fuel.
 *
 * If the heap is empty, no reachable station can extend
 * our reach. Therefore, return -1.
 *
 * Otherwise:
 *
 * reach += pq.poll()
 *
 * pq.poll() gives the largest available fuel amount.
 *
 * Increment stationcount because we have selected
 * one refueling stop.
 *
 * STEP 5: FINISH
 *
 * Once reach >= target, return stationcount.
 *
 * ------------------------------------------------------------
 * DETAILED DRY RUN
 * ------------------------------------------------------------
 *
 * target = 100
 * startFuel = 10
 *
 * stations = {
 *     {10, 60},
 *     {20, 30},
 *     {30, 30},
 *     {60, 40}
 * };
 *
 * INITIAL:
 *
 * reach = 10
 * idx = 0
 * stationcount = 0
 * pq = []
 *
 * ITERATION 1:
 *
 * Station at position 10 is reachable.
 *
 * Add its fuel:
 *
 * pq = [60]
 *
 * Select maximum fuel = 60.
 *
 * reach = 10 + 60 = 70
 * stationcount = 1
 *
 * ITERATION 2:
 *
 * Now stations at positions 20, 30 and 60 are reachable.
 *
 * Add their fuel:
 *
 * pq = [40, 30, 30]
 *
 * Select maximum fuel = 40.
 *
 * reach = 70 + 40 = 110
 * stationcount = 2
 *
 * ITERATION 3:
 *
 * reach = 110
 * target = 100
 *
 * reach < target is false.
 *
 * Return 2.
 *
 * FINAL ANSWER = 2
 *
 * ------------------------------------------------------------
 * IMPORTANT INTERVIEW OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Stations are processed from left to right.
 *
 * 2. Only stations whose positions are <= reach are added.
 *
 * 3. Max Heap stores fuel amounts, not station positions.
 *
 * 4. We don't refuel immediately upon reaching a station.
 *
 * 5. We choose the largest available fuel only when
 *    another refueling stop becomes necessary.
 *
 * 6. idx ensures every station is processed only once.
 *
 * 7. If the heap is empty while reach < target,
 *    reaching the target is impossible.
 *
 * 8. long is used for reach to avoid integer overflow.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * N = number of stations.
 *
 * Each station is inserted into the heap at most once.
 * Each station is removed from the heap at most once.
 *
 * Heap operations take O(log N).
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N)
 *
 */

class Solution {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        // Max Heap: largest fuel amount comes out first.
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        // Farthest distance we can currently travel.
        long reach = startFuel;

        // Index of the next station to process.
        int idx = 0;

        // Number of refueling stops selected.
        int stationcount = 0;

        // Total number of stations.
        int n = stations.length;

        // Continue until the destination is reachable.
        while (reach < target) {

            // Add every station that we can currently reach.
            while (idx < n && stations[idx][0] <= reach) {

                // Store the fuel available at this station.
                pq.offer(stations[idx][1]);

                // Move to the next station.
                idx++;
            }

            // No reachable station is available for refueling.
            if (pq.isEmpty()) {
                return -1;
            }

            // Select the station with the maximum fuel.
            // Extend our reach by that amount.
            reach += pq.poll();

            // One refueling stop has been selected.
            stationcount++;
        }

        // Destination reached.
        return stationcount;
    }
}
