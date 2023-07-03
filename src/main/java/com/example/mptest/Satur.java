package com.example.mptest;

import java.security.Key;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Satur {
    public static void main(String[] args) {
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(trap(height));

//        String num = "112";
//        int k = 1;
//        System.out.println(removeKdigits(num, k));

//        String s = "cbacdcbc";
//        System.out.println(removeDuplicateLetters(s));

//        int[] nums = {1, -4, 2, -1, 3, -3, -4, 0, -3, -1};
//        System.out.println(find132pattern(nums));
//        int[] arr = {5, 4, 2, 3, 1};
//
//        quicksort(arr, 0, 4);
//        for (int i : arr) {
//            System.out.print(i);
//        }
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums, 2));
    }

    public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        int left = 0;
        for (int right = 0; right < len; right++) {
            if (nums[right] != val) {
               nums[left]=nums[right];
               left++;
            }
        }
        return left;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode result = new ListNode(0, head);
        ListNode current = head;

        for (int i = 0; i < n; i++) {
            current = current.next;
        }

        while (current.next != null) {
            head = head.next;
            current = current.next;
        }
        head.next = head.next.next;
        return result;
    }

    public static int maxArea(int[] height) {
        int len = height.length;
        int area = 0;
        if (len == 2) {
            return Math.min(height[1], height[0]);
        }
        for (int i = 0; i < len - 1; i++) {

            area = Math.max(area, (len - 1 - i) * Math.min(height[len - 1], height[i]));
            for (int j = len - 2; j > i; j--) {
                if (height[j + 1] < height[j]) {
                    area = Math.max(area, (j - i) * Math.min(height[j], height[i]));
                }
            }

        }
        return area;
    }

    public static int minNumberOfFrogs(String croakOfFrogs) {
        char[] chars = croakOfFrogs.toCharArray();
        int len = chars.length;
        if (len % 5 != 0) {
            return -1;
        }
        //croak
        int n = len / 5;
        Map<Character, Integer> map = new HashMap<>();
        map.put('c', n);
        map.put('r', n);
        map.put('o', n);
        map.put('a', n);
        map.put('k', n);

        for (int i = 0; i < len; i++) {

        }
        return 1;
    }

    public static void quicksort(int[] arr, int start, int end) {
        if (start < end) {
            int stard = arr[end];
            int low = start;
            int heigh = end;

            while (low < heigh) {
                while (low < heigh && arr[low] < stard) {
                    low++;
                }
                arr[heigh] = arr[low];
                while (low < heigh && arr[heigh] >= stard) {
                    heigh--;
                }
                arr[low] = arr[heigh];
            }
            arr[heigh] = stard;
            quicksort(arr, start, heigh - 1);
            quicksort(arr, heigh + 1, end);
        }
    }

    public static void bubbleSortOpt(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i : arr) {
            System.out.print(i);
        }

        System.out.println();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i);
        }
    }

    public static boolean find132pattern3(int[] nums) {
        int n = nums.length;
        Deque<Integer> d = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) return true;
            while (!d.isEmpty() && d.peekLast() < nums[i]) {
                // 事实上，k 的变化也具有单调性，直接使用 k = pollLast() 也是可以的
                k = Math.max(k, d.pollLast());
            }
            d.addLast(nums[i]);
        }
        return false;
    }

    //接雨水
    public static int trap(int[] height) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int len = height.length;
        int area = 0;
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && height[i] > height[deque.peek()]) {
                Integer pop = deque.pop();
                if (deque.isEmpty()) {
                    break;
                }
                int width = i - deque.peek() - 1;
                int heigh = Math.min(height[i], height[deque.peek()]) - height[pop];
                area += width * heigh;
            }
            deque.push(i);
        }
        return area;
    }

    public static String removeKdigits(String num, int k) {
        int len = num.length();
        if (k >= len) {
            return "0";
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && k > 0 && num.charAt(i) < num.charAt(deque.peek())) {
                deque.pop();
                k--;
            }
            deque.push(i);
        }
        for (int i = 0; i < k; i++) {
            deque.poll();
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            Integer last = deque.pollLast();
            while (!deque.isEmpty() && builder.length() == 0 && num.charAt(last) == '0') {
                last = deque.pollLast();
            }
            builder.append(num.charAt(last));
        }
        return builder.toString();
    }

    public static String removeDuplicateLetters(String s) {
        int len = s.length();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] visit = new boolean[26];
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < len; i++) {
            if (visit[s.charAt(i) - 'a']) {
                continue;
            }
            while (!deque.isEmpty()
                    && s.charAt(i) < s.charAt(deque.peek())
                    && lastIndex[s.charAt(deque.peek()) - 'a'] > i) {
                visit[s.charAt(deque.peek()) - 'a'] = false;
                deque.poll();
            }
            deque.push(i);
            visit[s.charAt(i) - 'a'] = true;
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(s.charAt(deque.pollLast()));
        }
        return builder.toString();
    }

    public static boolean find132pattern(int[] nums) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int len = nums.length;
        int k = Integer.MIN_VALUE;
        //维护一个递减的栈 时刻保留最大的2值
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!deque.isEmpty() && nums[deque.peek()] < nums[i]) {
                k = Math.max(k, nums[deque.pop()]);
            }
            deque.push(i);
        }
        return false;
    }

}
