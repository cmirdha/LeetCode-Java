
/*
 * Problem Name: Reorganize String
 * LeetCode Number: 767
 * Difficulty: Medium
 * Pattern: Greedy + Max Heap + Frequency Counting
 *
 * ------------------------------------------------------------
 * EXPLAIN LIKE I'M 10 YEARS OLD
 * ------------------------------------------------------------
 *
 * We are given a string and need to rearrange its characters
 * so that no two adjacent characters are the same.
 *
 * Example:
 *
 * Input: "aaabbc"
 * Output: "ababac"
 *
 * Why is "aaabbc" invalid?
 *
 * Because two 'a' characters are next to each other.
 *
 * We need to distribute frequently occurring characters
 * across the string.
 *
 * ------------------------------------------------------------
 * STEP 1: COUNT FREQUENCIES
 * ------------------------------------------------------------
 *
 * Use HashMap to store:
 *
 * Character -> Frequency
 *
 * Example:
 *
 * "aaabbc"
 *
 * a = 3
 * b = 2
 * c = 1
 *
 * ------------------------------------------------------------
 * STEP 2: CHECK IF REORGANIZATION IS POSSIBLE
 * ------------------------------------------------------------
 *
 * Find the maximum frequency.
 *
 * A character appearing too many times cannot be separated
 * from its identical occurrences.
 *
 * For example:
 *
 * "aaaab"
 *
 * Four 'a' characters need three gaps between them:
 *
 * a _ a _ a _ a
 *
 * But only one 'b' is available.
 *
 * So it is impossible.
 *
 * General condition:
 *
 * maxfreq <= (N + 1) / 2
 *
 * If this condition fails, return an empty string.
 *
 * ------------------------------------------------------------
 * STEP 3: CREATE A MAX HEAP
 * ------------------------------------------------------------
 *
 * Store both character and frequency using the pair class.
 *
 * Example:
 *
 * (a,3)
 * (b,2)
 * (c,1)
 *
 * Max Heap ensures the character with the highest
 * remaining frequency is selected first.
 *
 * If frequencies are equal, the comparator uses
 * alphabetical order as a tie-breaker.
 *
 * ------------------------------------------------------------
 * STEP 4: SELECT TWO DIFFERENT CHARACTERS PER ROUND
 * ------------------------------------------------------------
 *
 * Each outer while-loop iteration is one round.
 *
 * We select at most two different characters.
 *
 * Why two?
 *
 * Because adjacent characters must be different.
 *
 * Selecting two different characters guarantees that
 * the two characters selected in the current round
 * cannot be equal.
 *
 * Example:
 *
 * Select a -> then b
 *
 * Result so far: "ab"
 *
 * ------------------------------------------------------------
 * STEP 5: USE A TEMPORARY LIST
 * ------------------------------------------------------------
 *
 * After selecting a character:
 *
 * 1. Append it to StringBuilder.
 * 2. Decrease its frequency.
 * 3. If its frequency is still positive, store it in list.
 *
 * Why not immediately put it back into the heap?
 *
 * Because it might be selected again in the same round.
 *
 * That could create adjacent duplicate characters.
 *
 * Therefore, we reinsert reduced frequencies only after
 * selecting up to two different characters.
 *
 * ------------------------------------------------------------
 * DETAILED DRY RUN
 * ------------------------------------------------------------
 *
 * Input:
 *
 * s = "aaabbc"
 *
 * Frequencies:
 *
 * a = 3
 * b = 2
 * c = 1
 *
 * Feasibility:
 *
 * maxfreq = 3
 * (s.length() + 1) / 2 = 3
 *
 * 3 > 3 is false.
 *
 * So reorganization is possible.
 *
 * ROUND 1:
 *
 * Select (a,3):
 * Append a.
 * Remaining frequency = 2.
 * Store (a,2) in list.
 *
 * Select (b,2):
 * Append b.
 * Remaining frequency = 1.
 * Store (b,1) in list.
 *
 * Result = "ab"
 *
 * Reinsert:
 *
 * (a,2), (b,1), (c,1)
 *
 * ROUND 2:
 *
 * Select (a,2):
 * Append a.
 * Remaining frequency = 1.
 * Store (a,1) in list.
 *
 * Select (b,1):
 * Append b.
 * Remaining frequency = 0.
 * Do not store it.
 *
 * Result = "abab"
 *
 * Reinsert:
 *
 * (a,1), (c,1)
 *
 * ROUND 3:
 *
 * Select (a,1):
 * Append a.
 * Remaining frequency = 0.
 *
 * Select (c,1):
 * Append c.
 * Remaining frequency = 0.
 *
 * Result = "ababac"
 *
 * Heap is empty.
 *
 * Final answer = "ababac"
 *
 * ------------------------------------------------------------
 * IMPORTANT OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Max Heap prioritizes the most frequent character.
 *
 * 2. We select two different characters in each round.
 *
 * 3. Temporary list prevents immediate reuse of a character.
 *
 * 4. We only reinsert characters whose frequencies are positive.
 *
 * 5. The feasibility condition avoids impossible arrangements.
 *
 * 6. Alphabetical tie-breaking is optional; it is not required
 *    for the correctness of the greedy algorithm.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * N = length of input string
 * K = number of distinct characters
 *
 * Frequency map: O(N)
 * Heap operations: O(N log K)
 * StringBuilder: O(N)
 *
 * Time Complexity: O(N log K)
 *
 * Space Complexity: O(N + K), including output.
 */

class pair {
    char ch;
    int freq;

    pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

class Solution {
    public String reorganizeString(String s) {

        // STEP 1: Store the frequency of each character.
        Map<Character, Integer> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        char[] chararr = s.toCharArray();

        for (char ch : chararr) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // STEP 2: Create a Max Heap.
        // Higher frequency comes first.
        // If frequencies are equal, use alphabetical order.
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq) {
                return a.ch - b.ch;
            }
            return b.freq - a.freq;
        });

        // Add every distinct character and its frequency.
        for (char c : map.keySet()) {
            pq.offer(new pair(c, map.get(c)));
        }

        // STEP 3: Check whether reorganization is possible.
        // The heap's top element has the maximum frequency.
        int maxfreq = pq.peek().freq;

        if (maxfreq > (s.length() + 1) / 2)
            return "";

        // STEP 4: Select at most two different characters
        // in every round.
        while (!pq.isEmpty()) {

            // Temporarily store decreased frequencies.
            List<pair> list = new ArrayList<>();

            // We want to select two different characters.
            int count = 2;

            while (count > 0 && !pq.isEmpty()) {

                // Select the character with the highest frequency.
                pair p = pq.poll();

                // Append the selected character to the result.
                sb.append(p.ch);

                // One occurrence has been used.
                p.freq--;

                // Store it only if more occurrences remain.
                // Do not immediately reinsert into the heap.
                if (p.freq > 0) {
                    list.add(p);
                }

                // If no other character is available,
                // this round is complete.
                if (pq.isEmpty())
                    break;

                // One character has been selected.
                count--;
            }

            // Reinsert the remaining frequencies
            // for the next round.
            for (pair pr : list) {
                pq.offer(pr);
            }
        }

        return sb.toString();
    }
}
