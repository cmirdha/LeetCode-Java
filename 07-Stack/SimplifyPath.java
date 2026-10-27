
/*
 * Problem Name: Simplify Path
 * LeetCode Number: 71
 * Difficulty: Medium
 * Pattern: Stack
 *
 * Approach:
 * Use a stack to process directory names and simplify
 * the given Unix-style absolute path.
 *
 * Ignore empty directory names and ".".
 * For "..", remove the last directory if one exists.
 * Otherwise, push the directory name onto the stack.
 *
 * Step-by-Step Logic:
 *
 * 1. Create an empty stack of strings.
 *
 * 2. Split the path using "/" as the delimiter.
 *
 * 3. Iterate through each directory name.
 *
 * 4. If the directory name is empty or ".":
 *       Skip it.
 *
 * 5. If the directory name is "..":
 *       If the stack is not empty, pop the top directory.
 *
 * 6. Otherwise:
 *       Push the directory name into the stack.
 *
 * 7. If the stack is empty:
 *       Return "/".
 *
 * 8. Create a StringBuilder to construct the simplified path.
 *
 * 9. Pop each directory from the stack and insert its name
 *    and "/" at the beginning of the StringBuilder.
 *
 * 10. Return the resulting simplified path.
 *
 * Key Observations:
 *
 * - Empty strings can occur because of repeated slashes.
 * - "." represents the current directory and can be ignored.
 * - ".." represents the parent directory.
 * - Directory names must be preserved in their original order.
 * - The path cannot move above the root directory.
 * - A valid simplified absolute path begins with "/".
 *
 * Walkthrough:
 *
 * Input:
 * path = "/home//foo/../bar/"
 *
 * Process:
 *
 * home -> Push
 * foo  -> Push
 * ..   -> Pop foo
 * bar  -> Push
 *
 * Output:
 * "/home/bar"
 *
 * Complexity Analysis:
 *
 * Time Complexity: O(n^2) in the worst case
 * Splitting and processing the path takes O(n).
 * Repeated insertions at the beginning of StringBuilder
 * can take O(n^2) overall.
 *
 * Space Complexity: O(n)
 * The split array and stack can store O(n) characters
 * and directory names.
 */

class Solution {
    public String simplifyPath(String path) {

        Stack<String> stack = new Stack<>();

        String[] arr = path.split("/");

        for (String dir : arr) {

            if (dir.equals("") || dir.equals(".")) {
                continue;
            }

            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }

        return sb.toString();
    }
}
