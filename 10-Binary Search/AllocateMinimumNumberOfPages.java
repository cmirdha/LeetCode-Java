
/*
    ============================================================
    PROBLEM NAME: Allocate Minimum Number of Pages
    PROBLEM: Allocate Minimum Number of Pages (GFG)
    DIFFICULTY: Medium
    PATTERN: Binary Search on Answer + Greedy
    ============================================================


    1. UNDERSTAND THE PROBLEM (EXPLAIN LIKE I'M 10 YEARS OLD)
    ------------------------------------------------------------

    Imagine you are a teacher.

    You have some books, and you need to distribute them
    among K students.

    Example:

        arr = [12, 34, 67, 90]
        k = 2

    This means:

        Book 1 = 12 pages
        Book 2 = 34 pages
        Book 3 = 67 pages
        Book 4 = 90 pages

        Total students = 2

    RULES:

    1. Every book must be given to someone.
    2. A book cannot be divided between two students.
    3. Books must be allocated in the given order.
    4. Every student must get at least one book.
    5. We want to distribute books as fairly as possible.

    What does "fairly" mean?

    We want to minimize the maximum pages assigned to
    any one student.

    For example:

        Student 1: 12 + 34 + 67 = 113 pages
        Student 2: 90 pages

        Maximum workload = 113

    Our answer is 113.

    Why?

    Because the student who reads the most has to read
    113 pages.

    We want to make this maximum workload as small as
    possible.


    2. WHY DO WE NEED BINARY SEARCH?
    ------------------------------------------------------------

    We don't know the answer directly.

    So instead of finding the answer immediately,
    imagine that we GUESS a maximum page limit.

    For example, let's guess:

        Maximum pages allowed = 100

    Can we distribute all books among 2 students
    without any student receiving more than 100 pages?

        Student 1: 12 + 34 = 46
        Student 2: 67
        Student 3: 90

    We need 3 students.

    But we only have 2 students.

    Therefore, 100 is NOT possible.

    Now let's try 150:

        Student 1: 12 + 34 + 67 = 113
        Student 2: 90

    We need only 2 students.

    Therefore, 150 IS possible.

    Notice the important pattern:

        Capacity = 100  -> Not possible
        Capacity = 110  -> Not possible
        Capacity = 113  -> Possible
        Capacity = 120  -> Possible
        Capacity = 150  -> Possible
        Capacity = 200  -> Possible

    Once a capacity becomes possible, every larger capacity
    will also be possible.

    This is called a MONOTONIC PROPERTY.

    Whenever a problem has this kind of pattern, we can
    think about Binary Search on Answer.

    We want the SMALLEST capacity that is possible.


    3. UNDERSTAND LOW AND HIGH
    ------------------------------------------------------------

    Before Binary Search, we need to find our search range.

    LOW = Maximum pages in a single book.

    Why?

    Imagine the biggest book has 90 pages.

    Can we give that book to a student if the maximum
    allowed pages are only 50?

    No!

    We cannot divide the book.

    Therefore, the minimum possible answer must be
    at least 90.

    So:

        low = maximum book size


    HIGH = Sum of all book pages.

    Why?

    Imagine giving every book to one student.

    That student would receive all the pages.

    This is the largest possible workload.

    Therefore:

        high = sum of all book pages


    Example:

        arr = [12, 34, 67, 90]

        low  = 90
        high = 12 + 34 + 67 + 90
             = 203

    Our answer must be somewhere between 90 and 203.


    4. UNDERSTAND THE VARIABLES
    ------------------------------------------------------------

    long low = 0;

        Stores the minimum possible maximum pages.

        Initially 0, but later becomes the largest book size.

    long high = 0;

        Stores the maximum possible answer.

        It becomes the sum of all pages.

    long result = 0;

        Stores the best valid answer found so far.

        Whenever a capacity works, save it.

    long mid;

        The capacity we are currently testing.

        It represents the maximum pages one student
        is allowed to receive.

    long sum = 0;

        Stores the total pages currently assigned
        to the student being filled.

    int std = k;

        Stores the number of students remaining,
        including the student currently receiving books.

        Every time we need another student, we decrease std.


    5. UNDERSTAND THE GREEDY LOGIC
    ------------------------------------------------------------

    This is the MOST IMPORTANT PART of the problem.

    We need to check:

        Can we distribute all books using at most K students
        if every student can receive at most mid pages?

    We do this using GREEDY.

    What does greedy mean here?

    Give the current student as many consecutive books
    as possible without crossing the mid capacity.

    Example:

        arr = [12, 34, 67, 90]
        mid = 113

    Start:

        sum = 0
        std = 2

    Book 1 = 12

        sum + 12 = 12

        12 <= 113

        Give this book to Student 1.

        sum = 12


    Book 2 = 34

        sum + 34 = 46

        46 <= 113

        Give this book to Student 1.

        sum = 46


    Book 3 = 67

        sum + 67 = 113

        113 <= 113

        Give this book to Student 1.

        sum = 113


    Book 4 = 90

        sum + 90 = 203

        203 > 113

        Student 1 cannot take this book.

        Therefore:

            std--;

        We move to Student 2.

        Give Book 4 to Student 2.

        sum = 90


    Final:

        Student 1 = 12 + 34 + 67 = 113
        Student 2 = 90

        std = 1

    Since std >= 1, allocation is possible.

    IMPORTANT:

    Why do we write:

        if (sum + arr[i] > mid)

    Because we want to check whether adding the next book
    will cross the allowed page limit.

    If it crosses the limit, we must start a new student.

    Why do we write:

        sum = (long) arr[i];

    Because the current book is now assigned to the
    new student.

    We must start the new student's page count from
    the current book, not from zero.


    6. UNDERSTAND THE BINARY SEARCH CONDITIONS
    ------------------------------------------------------------

    We are searching for the MINIMUM valid capacity.

    There are only two possibilities.

    CASE 1: Allocation is possible.

        if (std >= 1)

    This means we could distribute all books using
    the available students.

    So mid is a valid answer.

    Save it:

        result = mid;

    But we want the MINIMUM possible answer.

    Maybe a smaller capacity can also work.

    Therefore, search LEFT:

        high = mid - 1;


    CASE 2: Allocation is NOT possible.

        else

    This means we needed more students than available.

    The capacity is too small.

    We must increase the capacity.

    Therefore, search RIGHT:

        low = mid + 1;


    REMEMBER THIS INTERVIEW RULE:

        MINIMUM VALID ANSWER:

            Valid     -> Move LEFT
            Invalid   -> Move RIGHT


        MAXIMUM VALID ANSWER:

            Valid     -> Move RIGHT
            Invalid   -> Move LEFT


    7. COMPLETE DRY RUN
    ------------------------------------------------------------

    Input:

        arr = [12, 34, 67, 90]
        k = 2

    Initial values:

        low = 90
        high = 203
        result = 0


    ITERATION 1:

        mid = (90 + 203) / 2
            = 146

        Check allocation:

            Student 1 = 12 + 34 + 67 = 113
            Student 2 = 90

        Students required = 2

        Allocation possible.

        result = 146
        high = 145

        Why move LEFT?

        Because 146 works, but maybe a smaller capacity
        can also work.


    ITERATION 2:

        low = 90
        high = 145

        mid = (90 + 145) / 2
            = 117

        Check allocation:

            Student 1 = 12 + 34 + 67 = 113
            Student 2 = 90

        Students required = 2

        Allocation possible.

        result = 117
        high = 116


    ITERATION 3:

        low = 90
        high = 116

        mid = (90 + 116) / 2
            = 103

        Check allocation:

            Student 1 = 12 + 34 = 46

            46 + 67 = 113

            113 > 103

            Start Student 2 with 67.

            67 + 90 = 157

            157 > 103

            Start Student 3 with 90.

        Students required = 3

        But we have only 2 students.

        Allocation NOT possible.

        low = 104

        Why move RIGHT?

        Because 103 is too small.
        We need to allow more pages per student.


    ITERATION 4:

        low = 104
        high = 116

        mid = 110

        Check allocation:

            Student 1 = 12 + 34 = 46
            Student 2 = 67
            Student 3 = 90

        Students required = 3

        Allocation NOT possible.

        low = 111


    ITERATION 5:

        low = 111
        high = 116

        mid = 113

        Check allocation:

            Student 1 = 12 + 34 + 67 = 113
            Student 2 = 90

        Students required = 2

        Allocation possible.

        result = 113
        high = 112


    ITERATION 6:

        low = 111
        high = 112

        mid = 111

        Check allocation:

            Student 1 = 12 + 34 = 46
            Student 2 = 67
            Student 3 = 90

        Students required = 3

        Allocation NOT possible.

        low = 112


    ITERATION 7:

        low = 112
        high = 112

        mid = 112

        Check allocation:

            Student 1 = 12 + 34 = 46
            Student 2 = 67
            Student 3 = 90

        Students required = 3

        Allocation NOT possible.

        low = 113


    BINARY SEARCH ENDS:

        low = 113
        high = 112

        Since low > high, the loop stops.

        result = 113

    FINAL ANSWER = 113


    8. COMPLETE BINARY SEARCH SUMMARY
    ------------------------------------------------------------

    Iteration | low | high | mid | Students | Possible?
    ------------------------------------------------------------
       1      | 90  | 203  | 146 |    2     | Yes
       2      | 90  | 145  | 117 |    2     | Yes
       3      | 90  | 116  | 103 |    3     | No
       4      | 104 | 116  | 110 |    3     | No
       5      | 111 | 116  | 113 |    2     | Yes
       6      | 111 | 112  | 111 |    3     | No
       7      | 112 | 112  | 112 |    3     | No

    Answer = 113


    9. IMPORTANT EDGE CASES
    ------------------------------------------------------------

    CASE 1:

        k > arr.length

        Example:

            arr = [10, 20]
            k = 3

        There are only 2 books but 3 students.

        Every student must get at least one book.

        Therefore, return -1.


    CASE 2:

        k = 1

        Only one student receives all books.

        Answer = sum of all pages.


    CASE 3:

        k = arr.length

        Every student receives exactly one book.

        Answer = maximum individual book size.


    CASE 4:

        All books have the same number of pages.

        Example:

            arr = [10, 10, 10, 10]
            k = 2

        Each student receives two books.

        Answer = 20.


    10. COMPLEXITY ANALYSIS
    ------------------------------------------------------------

    Let:

        N = number of books
        S = sum of all pages
        M = maximum individual book size

    Finding low and high:

        O(N)

    One allocation check:

        O(N)

    Binary Search range:

        From M to S

        Number of iterations = O(log(S - M + 1))

    Total Time Complexity:

        O(N * log(S))

    Space Complexity:

        O(1)

    Why O(1)?

        We only use variables like low, high, mid,
        sum, std and result.

        We do not create another array or data structure.


    11. FINAL INTERVIEW TAKEAWAY
    ------------------------------------------------------------

    Whenever you see a problem asking:

        "Minimize the maximum workload"

    Think about:

        1. Binary Search on Answer.
        2. Find the minimum and maximum possible answers.
        3. Write a greedy feasibility check.
        4. If valid, save answer and move LEFT.
        5. If invalid, move RIGHT.

    The most important question to ask yourself is:

        "If I guess an answer, can I efficiently check
         whether that answer is possible?"

    If YES, and the feasibility follows a monotonic pattern,
    Binary Search on Answer may be the right approach.


    ============================================================
    ORIGINAL JAVA IMPLEMENTATION
    ============================================================
*/

class Solution {
    public int findPages(int[] arr, int k) {

        long low =0;
        long high =0;
        long result =0;

        if(k > arr.length){
            return -1;
        }

        for(int val: arr){
            high+=val;
            low = Math.max(low,val);
        }

        while(low<=high){
            long mid = (low+high)/2;
            long sum =0;
            int std = k;

            for(int i =0;i<arr.length;i++){
                if(sum+arr[i] > mid){
                    std--;
                    sum =(long)arr[i];
                }
                else{
                    sum+=(long)arr[i];
                }
            }

            if(std>=1){
                result = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }

        return (int)result;
    }
}


