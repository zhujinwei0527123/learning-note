package com.learning.algorithm;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/8/2 15:25
 */
public class Demo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("1111");
        sb.replace(0,1,""+'2');
        System.out.println(sb);
    }


    public List<Integer> partitionLabels(String s) {
        if(s== null || s.length() == 0) {
            return new ArrayList<>();
        }
        Map<Character, int[]> map = new HashMap<>();
        int len = s.length();
        for(int i = 0;i< len; i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)) {
                int[] interval = map.get(ch);
                interval[1] = i;
            } else {
                map.put(ch, new int[]{i, i});
            }
        }
        List<int []> list = map.values().stream().sorted((o1,o2) ->o1[0]-o2[0]).collect(Collectors.toList());
        int i = 1;
        int[] prv = list.get(0);
        while(i< list.size ()) {
            int[] cur = list.get(i);
            int limit = prv[1];
            if(cur[0] < limit && cur[1]<=limit) {
                list.remove(i);
            } else if(cur[0] < limit && cur[1]>limit){
                prv[1] = cur[1];
                list.remove(i);
            } else {
                i++;
                prv = cur;
            }
        }
        return list.stream().map(tmp -> tmp.length).collect(Collectors.toList());
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort( intervals, (o1, o2) -> {
            if(o1[1] != o2[1]){
                return o1[1] - o2[1];
            } else {
                return  (o2[1]- o2[0]) -(o1[1] - o1[0]);
            }
        });
        int i = 0;
        int len = intervals.length;
        int result = 0;
        while(i< len) {
            int[] cur = intervals[i];
            i++;
            while(i < len &&cur[1] > intervals[i][0] && cur[1] < intervals[i][1]) {
                result++;
                i++;
            }
        }
        return result;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        List<Integer> list =new LinkedList<>();
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i< nums.length;i++) {
            if(nums[i]<0) {
                list.add(nums[i]);
                continue;
            }
            min = Math.min(nums[i], min);
            sum +=nums[i];
        }
        Collections.sort(list);
        while(!list.isEmpty() && k>0) {
            sum = sum - list.remove(0);
            k--;
        }
        int max = Integer.MIN_VALUE;
        while(!list.isEmpty()) {
            int value = list.remove(0);
            max = Math.max(max, value);
            sum += value;
        }
        if(k%2 == 1) {
            if(max != Integer.MIN_VALUE) {
                min = Math.min(max, min);
            }
            sum -= 2*min;
        }
        return sum;
    }

    public boolean canJump1(int[] nums) {
        if(nums == null || nums.length ==0) {
            return false;
        }
        int distance = nums[0];
        int index = 0;
        while(index + distance+1 < nums.length ){
            int curMaxInx = index;
            int curMaxDis = distance;
            for(int i = index+1;i<= index+distance && i<nums.length;i++) {
                if(nums[i] +i > index+distance){
                    curMaxInx = i;
                    curMaxDis = nums[i];
                }
            }
            if(curMaxInx == index){
                return false;
            }
            index = curMaxInx;
            distance = curMaxDis;
        }
        return true;
    }

    public String[] permutation(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        List<String> result = new ArrayList<>();
        boolean[] reach = new boolean[array.length];
        dfs(result,reach,new StringBuilder(), array );
        String[]res = new String[result.size()];
        result.toArray(res);
        return res;
    }

    public void dfs(List<String>result, boolean[] reach,StringBuilder sb, char[] array ) {
        if(sb.length() == array.length) {
            result.add(sb.toString());
            return;
        }
        for(int i = 0;i< array.length;i++) {
            if(reach[i] || i>0 && array[i] == array[i-1] && !reach[i-1]){
                continue;
            }
            sb.append(array[i]);
            reach[i] =true;
            dfs(result, reach, sb, array);
            sb.deleteCharAt(sb.length()-1);
            reach[i] =false;
        }
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Pair<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> {
            return  o2.getValue() - o1.getValue();
        });
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            queue.add(new Pair(entry.getKey(), entry.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            Pair<Character, Integer> entry = queue.poll();
            int time = entry.getValue();
            while(time>0) {
                sb.append(entry.getKey());
                time--;
            }
        }
        return sb.toString();
    }

    Map<String, Integer> note = new HashMap<>();

    public void insert(String key, int val) {
        note.put(key, val);
    }

    public int sum(String prefix) {

        return note.entrySet().stream().filter( key -> key.getKey().startsWith(prefix)).map(Map.Entry::getValue).mapToInt(Integer::intValue).sum();

    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i = 0;i<len;i++) {
            if( gas[i] - cost[i] < 0){
                continue;
            }
            if(helper(gas, cost, i)){
                return i;
            }
        }
        return -1;
    }

    public boolean helper(int[] gas, int[] cost, int index) {
        int len = gas.length;
        int tank = 0;
        for(int i =0 ;i< len;i++) {
            int next = (index+i) %len;
            if( tank +gas[next]< cost[next]) {
                return false;
            }
            tank += gas[next] -cost[next];
        }
        return true;
    }



    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrack(result, new StringBuilder(""),0,0,n);
        return result;
    }

    public void backTrack(List<String>result, StringBuilder sb, int open, int close,int max) {
        if(sb.length() == max*2) {
            result.add(sb.toString());
            return;
        }
        if(open<max) {
            sb.append("(");
            backTrack(result,sb,open+1,close,max);
            sb.deleteCharAt(sb.length()-1);
        }
        if (close<open) {
            sb.append(")");
            backTrack(result,sb,open,close+1,max);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i])-1;
            if(nums[index]>0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >0)
                res.add(i+1);
        }
        return res;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;
        for(int i =0;i< letters.length;i++) {
        if(letters[i]> target) {
            min = Math.min(letters[i]- target, min);
        }
    }
        return (char)(target+ min);
    }

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
        int left = 0, right = nums.length;
        while(left<right) {
            int mid = left + (right - left)/2;
            if(nums[mid] < mid+1 ) {
                right= mid;
            }else {
                left = mid+1;
            }
        }

        return nums[left];
    }

    public boolean canJump(int[] nums) {
        int len = nums.length;
        boolean [] dp = new boolean[len];
        dp[len-1] =true;
        for (int i = len-2; i >=0 ; i--) {
            int distance = nums[i];
            for (int j = i+1; j <= i+distance && j<len; j++) {
                dp[i] = dp[i] || dp[j];
                if(dp[i]) break;
            }
        }
        return dp[0];
    }

//    private void dfs(int[]nums, int index) {
//        if (result|| index>= nums.length) return;
//        if (note.containsKey(index)) {
//            return;
//        }
//        if(index == nums.length-1) {
//            result = true;
//            return;
//        }
//        int distance = nums[index];
//        for (int i = index+1; i <= index+distance && i< nums.length; i++) {
//            dfs(nums, i);
//            if (result) break;
//            note.put(i, false);
//        }
//    }

    public int singleNonDuplicate(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<right) {
            int mid = left + (right-left)/2;
            boolean flag = (right-mid)%2==0;
            if(nums[mid]==nums[mid+1]) {
                if(flag) {
                    left = mid+2;
                } else {
                    right = mid-1;
                }
            }
            else if (nums[mid-1]==nums[mid] ){
                if(flag) {
                    right = mid-2;
                } else {
                    left = mid +1;
                }
            }else {
                return nums[mid];
            }
        }
        return nums[left];
    }

    public int numIslands(char[][] grid) {
        int num = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j]=='1'){
                    num++;
                    dfs(grid,i,j);
                }
            }
            
        }
        return 0;
    }
    int[][]footprint = {{1,0},{-1,0},{0,1},{0,-1}};

    public void dfs(char[][]grid, int i, int j ) {
        int row = grid.length;
        int col = grid[0].length;
        if (i<0 || i>=row||j<0||j>col || grid[i][j] !='1') return;
        grid[i][j] = '0';
        for (int k = 0; k < footprint.length; k++) {
            dfs(grid,i+footprint[k][0],j+footprint[k][1]);
        }
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int tmp: arr) {
            map.put(tmp, map.getOrDefault(tmp,0)+1);
        }

        return map.values().size() != map.keySet().size();
    }

    int[][] footprints = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
    public int islandPerimeter(int[][] grid) {
        int len = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] ==1) {
                    len = getBorder(grid,i,j);
                    break;
                }
            }

        }
        return len;
    }

    public int getBorder(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if (x<0 || x >=m || y<0|| y>=n|| grid[x][y]==0) {
            return 1;
        }
        if(grid[x][y] ==2) {
            return 0;
        }
        grid[x][y] =2;
        int res = 0;
        for(int[] foot: footprints) {
            res+=getBorder(grid, x+foot[0], y+foot[1]);
        }
        return res;
    }

//    public List<Integer> partitionLabels(String S) {
//        List<Integer> res = new ArrayList<>();
//        int[][] arr = new int[26][2];
//        Arrays.fill(arr[0], -1);
//        Arrays.fill(arr[1], -1);
//        Set<Character> set = new LinkedHashSet<>();
//        for (int i = 0; i < S.length(); i++) {
//            char ch = S.charAt(i);
//            set.add(ch);
//            int index = ch -'a';
//            if (arr[index][0] ==-1) {
//                arr[index][0] = i;
//            } else {
//                arr[index][1] = i;
//            }
//        }
//        int p1 = 0;
//        int p2 = 0;
//        for (Character ch : set) {
//            int cur = ch-'a';
//            if (arr[cur][0] == -1) continue;
//            if(arr[cur][0] > p2) {
//                res.add(p2-p1+1);
//                p1 = arr[cur][0];
//                p2 = arr[cur][1] == -1? arr[cur][0]:arr[cur][1];
//            } else {
//                p2 = Math.max(arr[cur][1],p2);
//            }
//        }
//        return res;
//    }
}
