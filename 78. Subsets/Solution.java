import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        getSubsets(stk, 0, nums, subsets);
        
        return subsets;
    }
    
    public void getSubsets(Stack<Integer> stk, int startIdx, int[] nums, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(stk));
        
        for (int i = startIdx; i < nums.length; i++) {
            stk.push(nums[i]);            
            getSubsets(stk, i + 1, nums, subsets);
            stk.pop();
        }
        
        return;
    }
}
