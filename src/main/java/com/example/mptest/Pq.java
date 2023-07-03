package com.example.mptest;

import io.swagger.models.auth.In;

import java.util.*;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;

public class Pq {
    public static void main(String[] args) {
//        int[][] roads = {{0, 1}, {0, 3}, {1, 2}, {1, 3}};
//        System.out.println(maximalNetworkRank(4, roads));

//        System.out.println(generateParenthesis(1));

//        System.out.println(scoreOfParentheses("(())"));

//        System.out.println(checkValidString("()"));

//        System.out.println(strStr("mississippi", "issipi"));

//        int[][] a = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        System.out.println(minPathSum(a));

//        char[][] cl = {{'a', 'b', 'c'}};
//        System.out.println(exist(cl, "abc"));

//        char[][] board =
//                {{'a', 'b'}, {'c', 'd'}};
//        String word = "dbac";
//        System.out.println(exist(board, word));

//        System.out.println(letterCombinations(""));
//        System.out.println(generateParenthesis(1));

//        int[] candidates = {2, 5, 2, 1, 2};
//        int target = 5;
//        System.out.println(combinationSum(candidates, target));

//        int[] nums = {1, 2, 3};
//        System.out.println(permuteUnique(nums));

//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 6;
//        System.out.println(Arrays.toString(searchRange(nums, target)));

        int[][] matrix = {{1},{3}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (matrix[0][0] > target || matrix[n - 1][m - 1] < target) {
            return false;
        }
        int left = 1;
        while (left < n && target >= matrix[left][0]) {
            if(target==matrix[left][0]){
                return true;
            }
            left++;
        }
        left = left - 1;
        int i = 0;
        m=m-1;
        while (i < m) {
            int mid = (i + m) / 2;
            if (matrix[left][mid] < target) {
                i = mid + 1;
            } else {
                m = mid;
            }
        }
        if (matrix[left][i] == target) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    public static int[] binarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] ans = new int[2];
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            ans[0] = -1;
        } else {
            ans[0] = left;
        }

        left = 0;
        right = nums.length - 1;
        while (left < right) {
            mid = (left + right + 1) / 2;
            if (target >= nums[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] != target) {
            ans[1] = -1;
        } else {
            ans[1] = left;
        }
        return ans;
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return target == nums[0] ? 0 : -1;
        }
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        generatePermute(map, nums, deque, result);
        return result;
    }

    public static void generatePermute(Map<Integer, Integer> map, int[] nums, Deque<Integer> deque, List<List<Integer>> result) {
        if (deque.size() == nums.length) {
            result.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (map.get(nums[i]) > 0) {
                deque.push(nums[i]);
                map.put(nums[i], map.get(nums[i]) - 1);
                generatePermute(map, nums, deque, result);
                map.put(nums[i], map.get(nums[i]) + 1);
                deque.pop();
            }
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        generate(set, nums, deque, result);
        return result;
    }

    public static void generate(Set<Integer> set, int[] nums, Deque<Integer> deque, List<List<Integer>> result) {
        if (deque.size() == nums.length) {
            result.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                deque.push(nums[i]);
                set.add(nums[i]);
                generate(set, nums, deque, result);
                set.remove(nums[i]);
                deque.pop();
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(candidates);
        generate(0, 0, candidates, target, result, deque);
        return result;
    }

    public static void generate(int sum, int n, int[] candidates, int target, List<List<Integer>> result, Deque<Integer> deque) {
        if (sum > target) {
            return;
        } else if (sum == target) {
            result.add(new ArrayList<>(deque));
            return;
        }

        for (int i = n; i < candidates.length; i++) {
            if (i > n && candidates[i] == candidates[i - 1]) {
                continue;
            }
            deque.push(candidates[i]);
            int total = sum + candidates[i];
            generate(total, i + 1, candidates, target, result, deque);
            deque.pop();
        }
    }


    public static List<String> generateParenthesis(int n) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        generate(n, builder, list, 0, 0);
        return list;
    }

    public static void generate(int n, StringBuilder builder, List<String> list, int open, int close) {
        if (close == n) {
            list.add(builder.toString());
            return;
        }

        if (open < n) {
            builder.append("(");
            generate(n, builder, list, open + 1, close);
            builder.deleteCharAt(builder.length() - 1);
        }

        if (open > close) {
            builder.append(")");
            generate(n, builder, list, open, close + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        boolean[][] connect = new boolean[n][n];

        for (int[] road : roads) {

            connect[road[0]][road[1]] = true;
            connect[road[1]][road[0]] = true;
            degree[road[0]]++;
            degree[road[1]]++;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n && i != j; j++) {
                max = Math.max(max, degree[i] + degree[j] - (connect[i][j] ? 1 : 0));
            }
        }
        return max;
    }

    public static List<String> generateParenthesis3(int n) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        backCompute(list, builder, 0, 0, n);
        return list;
    }

    public static void backCompute(List<String> list, StringBuilder builder, int open, int close, int max) {
        if (close == max) {
            list.add(builder.toString());
        }

        if (open < max) {
            builder.append('(');
            backCompute(list, builder, open + 1, close, max);
            builder.deleteCharAt(builder.length() - 1);
        }

        if (close < open) {
            builder.append(')');
            backCompute(list, builder, open, close + 1, max);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static int scoreOfParentheses(String s) {
        if (s.length() == 2) {
            return 1;
        }

        int n = s.length();
        int len = 0;
        int bal = 0;
        for (len = 0; len < n; len++) {
            bal += s.charAt(len) == '(' ? 1 : -1;
            if (bal == 0) {
                len++;
                break;
            }
        }

        if (len == n) {
            return 2 * scoreOfParentheses(s.substring(1, n - 1));
        } else {
            return scoreOfParentheses(s.substring(0, len)) + scoreOfParentheses(s.substring(len, n));
        }
    }

    public static boolean checkValidString(String s) {
        Deque<Character> deque = new ArrayDeque<>();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == '(' && s.charAt(right) == ')') {
                left++;
                right--;
            } else if (s.charAt(left) == '*' || s.charAt(right) == '*') {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int left = 0;
        int right;
        int n;
        while (left < haystack.length()) {
            right = 0;
            n = left;
            while (n < haystack.length() && right < needle.length() && haystack.charAt(n) == needle.charAt(right)) {
                n++;
                right++;
            }
            if (right == needle.length()) {
                return left;
            } else {
                left++;
            }
        }
        return -1;
    }

    public static int minPathSum(int[][] grid) {


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    public static boolean exist(char[][] board, String word) {
        //回溯法 与暴力法的区别是 每走一步都会评估 不正确就会取消上一步的操作
        boolean[][] dp = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (check(dp, word, board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(boolean[][] dp, String word, char[][] board, int n, int m, int index) {
        if (board[n][m] != word.charAt(index)) {
            return false;
        }
        dp[n][m] = true;
        if (index == word.length() - 1) {
            return true;
        }
        boolean result = false;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int left = n + dir[0];
            int right = m + dir[1];
            if (left >= 0 && left < board.length && right >= 0 && right < board[0].length) {
                if (!dp[left][right]) {
                    result = check(dp, word, board, left, right, index + 1);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        dp[n][m] = false;
        return result;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));

        StringBuilder builder = new StringBuilder();
        generate(list, digits, map, builder, 0);
        return list;
    }

    public static void generate(List<String> result, String digits, Map<Integer, List<Character>> map, StringBuilder builder, int index) {
        if (index == digits.length()) {
            result.add(builder.toString());
            return;
        }

        for (Character character : map.get(Integer.parseInt(digits.substring(index, index + 1)))) {
            builder.append(character);
            generate(result, digits, map, builder, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
