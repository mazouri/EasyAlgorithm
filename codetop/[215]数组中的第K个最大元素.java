//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 1217 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        //build heap,from last non-leaf node
        for (int i = nums.length / 2 - 1; i >= 0;i--) {
            adjustHeap(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }

        //adjust heap
        return nums[nums.length - k];
    }

    private void adjustHeap(int[] nums, int cur, int len) {
        int temp = nums[cur];
        for (int i = cur * 2 + 1; i < len; i = 2 * i + 1) {
            if (i + 1 < len && nums[i] < nums[i+1]) i++;
            if (nums[i] > temp) {
                nums[cur] = nums[i];
                cur = i;
            } else {
                break;
            }
        }
        nums[cur] = temp;
    }

    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];

        nums[p2] = temp;
    }

   public int findKthLargest2(int[] nums, int k) {
       if (nums.length == 0 || k<=0) return 0;
       int left = 0, right = nums.length - 1;
       quickSort(nums, left, right);
       return nums[nums.length -k];
   }
   private void quickSort(int[] nums, int left, int right) {
       if (left > right) return;
       int index = getIndex(nums, left, right);
       quickSort(nums, left, index - 1);
       quickSort(nums, index + 1, right);
   }

   private int getIndex(int[] nums, int left, int right) {
       int base = nums[left];
       while (left < right) {
           while (left <right && nums[right] >= base) right--;
           nums[left] = nums[right];
           while (left<right && nums[left] <= base) left++;
           nums[right] = nums[left];
       }
       nums[left] = base;
       return left;
   }
}
//leetcode submit region end(Prohibit modification and deletion)
