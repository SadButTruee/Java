import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {

        int[] array = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                array[0] = i;
                array[1] = map.get(target - nums[i]);
                return array;
            }else{
                map.put(nums[i], i);
            }
        }
        return array;
    }
}
