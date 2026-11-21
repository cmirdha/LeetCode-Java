/*
Problem Name: Combination Sum
LeetCode Number: 39
Difficulty: Medium
Pattern: Backtracking + Recursion

--------------------------------------------------
1. PROBLEM UNDERSTANDING
--------------------------------------------------

We are given:
- An array of candidates.
- A target value.

We need to find all unique combinations whose sum
is equal to the target.

Important:
- We can use a candidate multiple times.
- We cannot generate duplicate combinations in
  different orders.

Example:

candidates = [2, 3, 6, 7]
target = 7

Answer:
[[2, 2, 3], [7]]

--------------------------------------------------
2. APPROACH
--------------------------------------------------

Use backtracking.

At every recursion level:
1. Try a candidate.
2. Add it to the current combination.
3. Reduce the remaining target.
4. Recursively explore further.
5. Restore the target.
6. Remove the selected candidate.
7. Try the next candidate.

This is:

CHOOSE -> EXPLORE -> UNDO

--------------------------------------------------
3. IMPORTANT VARIABLES
--------------------------------------------------

candidates:
Contains all available numbers.

target:
The remaining sum required to reach the answer.

index:
The starting position from which we can select
candidates.

sublist:
Stores the current combination being constructed.

list:
Stores all valid combinations.

i:
The candidate currently being selected.

--------------------------------------------------
4. WHY target == 0?
--------------------------------------------------

if(target == 0)

This means the current combination has reached
the exact required sum.

Example:
sublist = [2, 2, 3]
target = 0

We found a valid combination.

list.add(new ArrayList(sublist));

We create a copy because sublist will be modified
when recursion returns.

return;

We stop exploring this path because the target
has already been reached.

--------------------------------------------------
5. WHY target < 0?
--------------------------------------------------

if(target < 0)

Example:
target = 1
Selected candidate = 2

New target = 1 - 2 = -1

Since all candidates are positive, adding more
numbers cannot make the remaining target zero.

Therefore, stop this path.

This is called pruning.

--------------------------------------------------
6. WHY DOES THE LOOP START FROM index?
--------------------------------------------------

for(int i = index;i<candidates.length;i++)

We start from index instead of zero.

This prevents generating the same combination
in different orders.

Example:

[2, 2, 3] is generated.

[2, 3, 2] is not generated separately.

[3, 2, 2] is not generated separately.

--------------------------------------------------
7. WHY PASS i IN RECURSION?
--------------------------------------------------

helper(candidates, target,i, sublist, list);

We pass i, not i + 1.

This allows us to reuse the same candidate.

Example:

If we select 2 at index 0, the next recursive
call can select 2 again.

This is the key difference between:

Combination Sum:
Pass i because reuse is allowed.

Combination Sum II:
Pass i + 1 because reuse is not allowed.

--------------------------------------------------
8. BACKTRACKING EXPLANATION
--------------------------------------------------

sublist.add(candidates[i]);

Select the current candidate.

target-=candidates[i];

Reduce the remaining target.

helper(candidates, target,i, sublist, list);

Explore further using recursion.

target+= sublist.get(sublist.size()-1);

Restore the remaining target after recursion.

sublist.remove(sublist.size()-1);

Remove the last selected candidate.

Now the previous state is restored, and the loop
can try another candidate.

--------------------------------------------------
9. DRY RUN
--------------------------------------------------

Input:

candidates = [2, 3, 6, 7]
target = 7

First choose 2:

sublist = [2]
target = 5

Choose 2 again:

sublist = [2, 2]
target = 3

Choose 2 again:

sublist = [2, 2, 2]
target = 1

Choose 2 again:

sublist = [2, 2, 2, 2]
target = -1

Target is negative, so return.

Backtrack and remove the last 2.

Now choose 3:

sublist = [2, 2, 3]
target = 0

Valid combination found.

Save:
[2, 2, 3]

Continue exploring other possibilities.

Eventually choose 7 directly:

sublist = [7]
target = 0

Save:
[7]

Final result:

[[2, 2, 3], [7]]

--------------------------------------------------
10. IMPORTANT INTERVIEW OBSERVATIONS
--------------------------------------------------

1. Pass i to allow candidate reuse.
2. Start the loop from index to avoid duplicate
   orderings.
3. Copy sublist before storing it.
4. Stop when target becomes negative.
5. Restore the target after recursive exploration.
6. Remove the last selected candidate to backtrack.

--------------------------------------------------
11. COMPLEXITY ANALYSIS
--------------------------------------------------

Let:
N = number of candidates.
T = target.
M = smallest candidate.
D = T / M.

Time Complexity:
Exponential; O(N^D) is a loose upper bound
on the search tree.

Auxiliary Space:
O(D) for recursion stack and current combination.

Output Space:
Depends on the number of valid combinations.

--------------------------------------------------
12. JAVA IMPLEMENTATION
--------------------------------------------------
*/

class Solution {

    public void helper(int[] candidates, int target,int index,  List<Integer> sublist, List<List<Integer>> list){

        if(target == 0){
            list.add(new ArrayList(sublist)) ;
            return;
        }
        if(target < 0){
            return;
        }



        for(int i =index;i<candidates.length;i++){
            sublist.add(candidates[i]);
            target-=candidates[i];
            helper(candidates, target,i, sublist, list);
            target+= sublist.get(sublist.size()-1) ;
            sublist.remove(sublist.size()-1) ;

        }

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> sublist = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        helper(candidates, target,0, sublist, list);

        return list;

    }
}

/*
--------------------------------------------------
END OF NOTES
--------------------------------------------------
*/