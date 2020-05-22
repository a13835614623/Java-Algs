package com.algorithm.array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * 示例 1:
 * 
 * 给定数组 nums = [1,1,2],
 * 
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 示例 2:
 * 
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 说明:
 * 
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 
 * 你可以想象内部操作如下:
 * 
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝 int len = removeDuplicates(nums);
 * 
 * // 在函数里修改输入数组对于调用者是可见的。 // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。 for (int i = 0;
 * i < len; i++) { print(nums[i]); }
 * 
 * 
 * @author zzk
 *
 */
public class RemoveDuplicates_26 {
	public static int removeDuplicates(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			int tmp = i;
			boolean isDup = false;
			while (tmp < len - 1 && nums[tmp] == nums[tmp + 1]) {
				tmp++;
				isDup = true;
			}
			// 此时tmp为不重复的最后一个元素的索引
			if (isDup && nums[i] < nums[len - 1]) {// 如果有重复，则移动数组
				int k = i + 1;
				for (int j = tmp + 1; j < len; j++) {
					nums[k++] = nums[j];
				}
			}
		}
		for (int i = 0; i < len - 1; i++) {
			if (nums[i] >= nums[i + 1]) {
				return i + 1;
			}
		}
		return len;
	}

	public static int removeDuplicates2(int[] nums) {
		int len = nums.length;
		int result = 0;
		for (int i = 0; i < len; i++) {
			int tmp = i;
			while (tmp < len - 1 && nums[tmp] == nums[tmp + 1]) {
				tmp++;
			}
			nums[result++] = nums[i];
			i = tmp;// 越过重复
		}
		for (int i = 0; i < len - 1; i++) {
			if (nums[i] >= nums[i + 1]) {
				return i + 1;
			}
		}
		return len;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int nums3[] = { 0, 1, 2, 3, 4 };
		System.out.println("个数为:" + removeDuplicates2(nums3));
		for (int i : nums3) {
			System.out.print(i + " ");
		}
		System.out.println();
		long end = System.currentTimeMillis();
		System.out.println("程序运行时间为:" + (end - start) + "ms");

	}
}
