package com.example.mptest;

import io.swagger.models.Scheme;
import io.swagger.models.auth.In;

import java.util.*;

public class Tq {
    public static void main(String[] args) {
        int[] heights = {6,5,1};
        System.out.println(largestRectangleArea1(heights));

//        System.out.println(removeKdigits("112", 1));

//        int[] height = {73, 74, 75, 71, 69, 72, 76, 73};
//        System.out.println(trap(height));
//        System.out.println(Arrays.toString(dailyTemperatures(height)));

//        System.out.println(removeDuplicateLetters("bcabc"));

//        System.out.println(removeDuplicateLetters("cbacdcbc"));

//        System.out.println(mySqrt(8));

//        int[] nums = {1, 3, 5, 6};
//        int target = 7;
//        System.out.println(searchInsert(nums, target));
    }

    //柱状图中面积最大的矩形
    public static int largestRectangleArea1(int[] heights) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int len = heights.length;
        int area = 0;
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && heights[i] < heights[deque.peek()]) {
                int height = heights[deque.pop()];
                int width;
                //这个宽度的计算要根据栈顶上一个元素的位置来确定，有可能邻近的柱形已经退栈
                if (deque.isEmpty()) {
                    width = i;
                } else {
                    width = i - deque.peek() - 1;
                }
                area = Math.max(area, height * width);
            }
            deque.push(i);
        }
        while (!deque.isEmpty()) {
            Integer pop = deque.pop();
            while (!deque.isEmpty() && heights[pop] == heights[deque.peek()]) {
                deque.pop();
            }
            if (deque.isEmpty()) {
                area = Math.max(area, heights[pop] * len);
            } else {
                area = Math.max(area, heights[pop] * (len - deque.peek() - 1));
            }
        }
        return area;
    }


    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = nums.length;
        int mid;
        if (target > nums[len - 1]) {
            return len;
        }

        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int mid;
        while (left < right) {
            mid = (left + right + 1) / 2;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        Deque<Character> deque = new ArrayDeque<>();
        boolean[] visit = new boolean[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < len; i++) {
            if (visit[s.charAt(i) - 'a']) {
                continue;
            }
            while (!deque.isEmpty() && deque.peekLast() > s.charAt(i) && lastIndex[deque.peekLast() - 'a'] > i) {
                visit[deque.peekLast() - 'a'] = false;
                deque.pollLast();
            }
            deque.addLast(s.charAt(i));
            visit[s.charAt(i) - 'a'] = true;
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(deque.poll());
        }
        return builder.toString();
    }


    public void dfs(int num, int n, boolean[][] a, int row) {
        if (n == 0) {
            return;
        }
        for (int i = 0; i < num; i++) {
            if (!a[row][i]) {
                for (int j = 0; j < num; j++) {
                    a[j][i] = true;
                }
                n--;
                dfs(num, n, a, row + 1);
                for (int j = 0; j < 4; j++) {
                    a[j][i] = false;
                }
            }
        }
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int len = temperatures.length;
        int temp;
        int[] cc = new int[len];
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                temp = deque.pop();
                cc[temp] = i - temp;
            }
            deque.push(i);
        }
        return cc;
    }

    public static int trap2(int[] height) {
        Deque<Integer> deque = new ArrayDeque<>();
        int len = height.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && height[i] > height[deque.peek()]) {
                Integer bottom = deque.pop();
                if (deque.isEmpty()) {
                    break;
                }
                Integer left = deque.peek();
                ans += (Math.min(height[i], height[left]) - height[bottom]) * (i - left - 1);
            }
            deque.push(i);
        }
        return ans;
    }

    public static int trap(int[] height) {
        int len = height.length;
        int ans = 0;
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = height[0];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        right[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        for (int i = 0; i < len; i++) {
            ans += Math.min(right[i], left[i]) - height[i];
        }
        return ans;
    }

    public static int largestRectangleArea4(int[] heights) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = heights.length;
        int area = 0;

        for (int i = 0; i < heights.length; i++) {

            while (!deque.isEmpty() && heights[deque.peek()] > heights[i]) {
                int width = i - deque.peek();
                int height = heights[deque.pop()];
                area = Math.max(area, width * height);
            }
            deque.push(i);

        }

        while (!deque.isEmpty()) {
            Integer pop = deque.pop();

            if (deque.isEmpty()) {
                area = Math.max(area, heights[pop] * n);
            } else {
                area = Math.max(area, (n - deque.peek() - 1) * heights[pop]);
            }
        }
        return area;
    }

    public static String removeKdigits(String num, int k) {
        int len = num.length();
        if (k >= len) {
            return "0";
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (!deque.isEmpty() && deque.peek() > c && k > 0) {
                deque.pop();
                k--;
            }
            deque.push(c);
        }

        for (int i = 0; i < k; i++) {
            deque.pop();
        }

        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            Character pop = deque.pollLast();
            if (builder.length() == 0 && pop == '0') {
                while (!deque.isEmpty() && deque.getLast() == '0') {
                    deque.pollLast();
                }
            } else {
                builder.append(pop);
            }
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    public static int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int lIndex;
        int rIndex;
        int area = 0;

        for (int i = 0; i < heights.length; i++) {
            lIndex = i - 1;
            while (lIndex >= 0 && heights[i] <= heights[lIndex]) {
                lIndex--;
            }
            if (lIndex < 0) {
                left[i] = -1;
            } else {
                left[i] = lIndex;
            }
            rIndex = i + 1;
            while (rIndex < heights.length && heights[i] <= heights[rIndex]) {
                rIndex++;
            }
            if (rIndex == heights.length) {
                right[i] = len;
            } else {
                right[i] = rIndex;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            area = Math.max(area, (right[i] - left[i] - 1) * heights[i]);
        }
        return area;
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 1) {
            return heights[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!deque.isEmpty() && heights[i] < heights[deque.peek()]) {
                int width = i - deque.peek();
                int height = heights[deque.pop()];
                area = Math.max(area, width * height);
            }
            deque.push(i);
        }
        while (!deque.isEmpty()) {
            int height = heights[deque.pop()];
            while (!deque.isEmpty() && heights[deque.peek()] == height) {
                deque.pop();
            }
            int width;
            if (deque.isEmpty()) {
                width = n;
            } else {
                width = n - deque.peek() - 1;
            }
            area = Math.max(area, width * height);
        }
        return area;
    }

    public static int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}

class Solution {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            // 这个 while 很关键，因为有可能不止一个柱形的最大宽度可以被计算出来
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }

                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }

                // System.out.println("curIndex = " + curIndex + " " + curHeight * curWidth);
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            int curWidth;
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {6, 5, 1};
        Solution solution = new Solution();
        int res = solution.largestRectangleArea(heights);
        System.out.println(res);
    }
}



