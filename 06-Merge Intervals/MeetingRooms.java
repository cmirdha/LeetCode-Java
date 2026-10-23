```java
/*

 * Problem Name: Meeting Rooms
 * Problem Number: Depends on the platform
 * Difficulty: Medium
 * Pattern: Sorting + Two Pointers + Greedy
 *
 * Approach:
 * Sort the start times and end times independently.
 *
 * Use two pointers:
 * i -> next meeting to start
 * j -> earliest meeting to finish
 *
 * If start[i] >= end[j]:
 *     A meeting has finished before or exactly when
 *     the next meeting starts.
 *     Reuse the available room.
 *
 * Otherwise:
 *     The next meeting starts before the earliest
 *     meeting finishes.
 *     Allocate another room.
 *
 * Track the maximum number of rooms occupied
 * simultaneously.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) auxiliary space
 *                  excluding sorting implementation
 */

import java.util.Arrays;

class Solution {

    public int minMeetingRooms(int[] start, int[] end) {

        // Sort meeting start times independently.
        Arrays.sort(start);

        // Sort meeting end times independently.
        Arrays.sort(end);

        // i points to the next meeting to start.
        int i = 0;

        // j points to the earliest meeting to finish.
        int j = 0;

        // Currently occupied rooms.
        int rooms = 0;

        // Maximum rooms occupied simultaneously.
        int meetingrooms = 0;

        // Process all meeting start times.
        while (i < start.length && j < end.length) {

            /*
             * If a meeting has ended by the time
             * the next meeting starts, reuse its room.
             */
            if (start[i] >= end[j]) {

                rooms--;

                // Move to the next meeting ending.
                j++;

            } else {

                /*
                 * The next meeting starts before
                 * the earliest current meeting ends.
                 * Therefore, another room is needed.
                 */
                rooms++;

                // Update maximum rooms required.
                meetingrooms = Math.max(
                        meetingrooms,
                        rooms
                );

                // Move to the next meeting starting.
                i++;
            }
        }

        // Return the maximum simultaneous room usage.
        return meetingrooms;
    }
}
```