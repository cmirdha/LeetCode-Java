/*
================================================================
PROBLEM: TOP K FREQUENT WORDS
PATTERN: Frequency Map + Priority Queue
================================================================

PROBLEM IN SIMPLE WORDS
----------------------------------------------------------------
Given an array of words, return the k words that appear most
often. If two words have the same frequency, the alphabetically
smaller word must come first.

For example:
words = ["i", "love", "leetcode", "i", "love", "coding"]
k = 2

The frequencies are:
"i" -> 2
"love" -> 2
"leetcode" -> 1
"coding" -> 1

The two most frequent words are ["i", "love"]. They have the
same frequency, so alphabetical order puts "i" first.

CORE IDEA
----------------------------------------------------------------
First count each word's frequency in a map.

Then add every distinct word to a priority queue. The queue is
configured to put the worst candidate at the top:
- Lower frequency comes first.
- For equal frequencies, the alphabetically later word comes first.

If the queue contains more than k words, remove the word at its
top. Once only k words remain, they are the top k candidates.

The queue removes candidates from worst to best, so the extracted
list is reversed to get the required output order.

WHY THIS IS NOT BINARY SEARCH
----------------------------------------------------------------
Binary search works when candidates have an ordered structure
and a condition lets us safely discard part of a search range.
Here, the task is to rank words by frequency and alphabetical
order, so a priority queue is the suitable tool.

VARIABLES: WHY THEY EXIST
----------------------------------------------------------------
words
 The input array containing words whose frequencies we count.

k
 The number of most frequent words to return.

map
 Maps each distinct word to the number of times it appears.

str
 The current word being counted in the input array.

pq
 A priority queue containing candidate words and their frequencies.
 Its comparator places the worst candidate at the top.

p1, p2
 The two pairs compared by the priority queue.

occ
 The frequency stored in each pair.

key
 A distinct word from the frequency map being added to the queue.

list
 Holds the retained words after they are removed from the queue.

CONDITION-BY-CONDITION LOGIC
----------------------------------------------------------------
map.getOrDefault(str, 0) + 1
 Get the word's current count, using 0 if it has not appeared yet,
 then add one for the current occurrence.

p1.occ == p2.occ
 Check whether the two words have the same frequency. If so,
 compare their spelling so the alphabetically later word is
 removed first.

Integer.compare(p1.occ, p2.occ)
 If frequencies differ, place the lower-frequency word at the
 top so it is the next candidate to remove.

for (String key : map.keySet())
 Visit every distinct word once and add it to the priority queue.

while (pq.size() > k)
 There are too many candidates, so remove the worst one. Stop
 when exactly k candidates remain.

while (!pq.isEmpty())
 Remove the retained words from the queue and add them to list.

Collections.reverse(list)
 Reverse the removal order to produce highest frequency first,
 with alphabetical order for ties.

DRY RUN
----------------------------------------------------------------
words = ["i", "love", "leetcode", "i", "love", "coding"]
k = 2

After counting:
"i" -> 2
"love" -> 2
"leetcode" -> 1
"coding" -> 1

The queue puts lower frequencies first. For words with equal
frequencies, it puts the alphabetically later word first.

There are four candidates, which is more than k:
- Remove "leetcode", the alphabetically later of the two words
 with frequency 1.
- Remove "coding", the other word with frequency 1.
- Two candidates remain: "i" and "love".

The queue removes "love" before "i" because both have frequency
2 and "love" is alphabetically later. The extracted list is
["love", "i"]. Reversing it gives ["i", "love"].

CORRECTNESS
----------------------------------------------------------------
The comparator places the worst remaining candidate at the top:
the least frequent word, or the alphabetically later word when
frequencies tie. Removing that candidate while the queue has
more than k entries leaves the k best candidates. Reversing the
extracted order gives the required result order.

COMPLEXITY
----------------------------------------------------------------
Let n be the number of words and d be the number of distinct words.

Counting takes O(n) expected time.
Adding and removing candidates takes O(d log d) time overall.
The map and priority queue use O(d) extra space.

NOTES
----------------------------------------------------------------
- This returns the words in the required order: highest frequency
 first, then alphabetically smaller first for ties.
- LinkedHashMap is not required for the result because the
 comparator defines the ordering.
- Integer.compare avoids possible overflow from subtracting
 frequencies.
- This assumes k is valid for the input, as specified by the
 problem constraints.

================================================================
SOLUTION
================================================================
*/

import java.util.*;

class pair {
    String str;
    int occ;

    pair(String str, int occ) {
        this.str = str;
        this.occ = occ;
    }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        PriorityQueue<pair> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.occ == p2.occ) {
                return p2.str.compareTo(p1.str);
            } else {
                return Integer.compare(p1.occ, p2.occ);
            }
        });

        for (String key : map.keySet()) {
            pq.add(new pair(key, map.get(key)));
        }

        while (pq.size() > k) {
            pq.poll();
        }

        List<String> list = new ArrayList<>();

        while (!pq.isEmpty()) {
            list.add(pq.poll().str);
        }

        Collections.reverse(list);
        return list;
    }
}
