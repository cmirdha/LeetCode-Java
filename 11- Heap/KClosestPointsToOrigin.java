/*
================================================================
PROBLEM: K CLOSEST POINTS TO ORIGIN
PATTERN: Priority Queue (Min-Heap)
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given points on a 2D plane, return the k points closest to the
origin (0, 0).

For a point (x, y), its squared distance from the origin is:
x * x + y * y

The square root is not needed to compare distances: a smaller
squared distance always means a smaller actual distance.

CORE IDEA
----------------------------------------------------------------
Create a CoOrdinate object for each point. The priority queue
orders these objects by increasing distance, so the closest
point is removed first.

Poll the queue k times, store the selected coordinates in a
Set, and then copy those coordinate arrays into the result.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
x, y
 Store a point's coordinates.

distance
 Stores the point's squared distance from the origin.

pq
 Holds CoOrdinate objects ordered by distance.

c1, c2
 The two CoOrdinate objects compared by the priority queue.

point
 The current input coordinate being added to the queue.

set
 Holds the coordinate arrays created for the k selected points.

i
 Counts how many points have been removed from the queue.

cd
 The current closest CoOrdinate object removed from the queue.

j
 Tracks the position for copying arrays into the result.

result
 The two-dimensional array returned by the method.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
(c1, c2) -> c1.distance - c2.distance
 Orders objects by increasing distance. The smallest distance
 is at the front of the priority queue.

for (int[] point : points)
 Visits each input point.

while (i < k)
 Repeats until k points have been removed from the queue.

pq.poll()
 Removes and returns the closest remaining point.

set.add(new int[] {cd.getx(), cd.gety()})
 Creates a coordinate array for the selected point and adds it
 to the set.

for (int[] s : set)
 Copies each selected coordinate array into the result.

DRY RUN
----------------------------------------------------------------
points = [[1, 3], [-2, 2]]
k = 1

Squared distances:
[1, 3] -> 1 * 1 + 3 * 3 = 10
[-2, 2] -> (-2) * (-2) + 2 * 2 = 8

The priority queue removes [-2, 2] first because its squared
distance is smaller. That point is stored in the set and copied
into the result.

IMPORTANT NOTES ABOUT THIS CODE
----------------------------------------------------------------
- The priority queue contains every point, so building it takes
 O(n log n) time, where n is the number of points.
- Polling k points takes O(k log n) time.
- The Set<int[]> does not compare arrays by their contents in
 Java; arrays use reference equality. It also does not guarantee
 iteration order. For this code, the arrays inserted into the
 set are newly created and distinct, and the problem typically
 allows the returned points in any order.
- This code assumes k is valid and that the queue contains at
 least k points.
- The distance uses int arithmetic, so very large coordinates
 could overflow; this note describes the code as written and
 does not alter it.

================================================================
SOLUTION
================================================================
*/

class CoOrdinate{
    int x;
    int y;
    int distance;

    CoOrdinate(int x, int y){
        this.x =x;
        this.y = y;
        this.distance= (x*x)+(y*y);
    }

    public int getx(){
        return this.x;

    }
    public int gety(){
        return this.y;
    }

}

class Solution {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<CoOrdinate> pq = new PriorityQueue<>((c1,c2)->c1.distance-c2.distance);

        for(int[] point : points){
            pq.add(new CoOrdinate (point[0],point[1]));
        }

        Set<int[]> set = new HashSet<>();

        int i =0;
        while(i<k){
            CoOrdinate cd = pq.poll();
            set.add(new int[] {cd.getx(),cd.gety()});
            i++;
        }

        int j =0 ;
        int [][] result = new int[k][2];

        for(int[] s : set){
            result[j++] = s;
        }

        return result;


    }
}
