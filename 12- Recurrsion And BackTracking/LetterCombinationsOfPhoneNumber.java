/*
Problem: Letter Combinations of a Phone Number
LeetCode: 17
Difficulty: Medium
Pattern: Backtracking + Recursion + HashMap

APPROACH:
1. Map every digit to its corresponding letters.
2. Start with an empty StringBuilder.
3. Find the letters available for the current digit.
4. Try every available letter.
5. Move to the next digit recursively.
6. When every digit has a corresponding letter,
   store the completed combination.
7. Backtrack by removing the last selected letter.

KEY CONDITIONS:

Base case:
if(s.length() == digits.length())

We have selected one letter for every digit.

Choice:
for(char ch : choices.toCharArray())

Explore every possible letter for the current digit.

Recursive call:
backTrack(s,digits,index+1,list,map)

Move to the next digit.

Backtracking:
s.deleteCharAt(s.length()-1)

Undo the current selection before trying another letter.

DRY RUN:
Input: "23"

Digit 2 -> abc
Digit 3 -> def

Choose a:
  Choose d -> ad
  Choose e -> ae
  Choose f -> af

Choose b:
  Choose d -> bd
  Choose e -> be
  Choose f -> bf

Choose c:
  Choose d -> cd
  Choose e -> ce
  Choose f -> cf

Output:
["ad","ae","af","bd","be","bf","cd","ce","cf"]

COMPLEXITY:

Let m = digits.length().

Maximum number of combinations = 4^m.

Time Complexity: O(m * 4^m)
Auxiliary Space: O(m)
Output Space: O(m * 4^m)

ORIGINAL JAVA IMPLEMENTATION:
*/

class Solution {

    public void backTrack(StringBuilder s , String digits, int index , List<String>list , Map<Character,String> map ){
        //base case
        if(s.length() == digits.length()){
            list.add(s.toString());
            return;
        }

        char c = digits.charAt(index);
        String choices = map.get(c);

        //try all choice then backtrack
        for(char ch : choices.toCharArray()){
            s.append(ch);
            backTrack(s,digits,index+1,list, map);
            s.deleteCharAt(s.length()-1);
        }

    }
    public List<String> letterCombinations(String digits){

        int n = digits.length();
        List<String> list = new ArrayList<>();

        if(digits.length()==0)
            return list;

        Map<Character,String> map = new HashMap<>();

        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        StringBuilder s = new StringBuilder();

        backTrack(s, digits, 0, list, map);

        return list;

    }
}