package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 
 * 示例 1:
 * 
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5] 输出: [[1,5],[6,9]]
 * 
 * 示例 2:
 * 
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] 输出:
 * [[1,2],[3,10],[12,16]] 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 
 * @description: 插入区间
 * @author zzk
 * @className Insert_57.java
 * @date 2019年4月13日 上午9:36:33
 */
public class Insert_57 {
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		int size = 0;
		if (intervals == null || (size = intervals.size()) == 0) {
			intervals = new ArrayList<Interval>();
			intervals.add(newInterval);
			return intervals;
		}
		if (intervals.get(0).start > newInterval.end) {
			intervals.add(0, newInterval);
			return intervals;
		}
		if (intervals.get(size - 1).end < newInterval.start) {
			intervals.add(newInterval);
			return intervals;
		}
		Interval intervalCur = intervals.get(0);
		Interval intervalNext = null;
		for (int i = 0; i < size; i++) {
			intervalNext = i + 1 < size ?intervals.get(i+1):null;
			if (intervalNext!=null&&newInterval.start > intervalCur.end && newInterval.end < intervalNext.start) {
				intervals.add(i + 1, newInterval);
				return intervals;
			}
			Interval tmpInterval = merge(newInterval, intervalCur);
			if (tmpInterval != null) {
				intervals.set(i, tmpInterval);
				intervalCur = tmpInterval;
				if (i + 1 == size)
					return intervals;
				while (i + 1 < size && intervalNext.start <= tmpInterval.end) {
					tmpInterval = merge(intervalCur, intervalNext = intervals.get(i + 1));
					if (tmpInterval != null) {
						intervals.set(i, tmpInterval);
						intervals.remove(i + 1);
						size--;
					} else {
						return intervals;
					}
				}
				break;
			}
			intervalCur=intervalNext;
		}
		return intervals;
	}
	
	public static Interval merge(Interval interval1, Interval interval2) {
		if (interval1 == null || interval2 == null)
			return null;
		Interval interval = null, left = null, right = null;
		if (interval1.start == interval2.start) {
			return interval1.end > interval2.end ? interval1 : interval2;
		} else {
			boolean flag = interval1.start > interval2.start;
			left = flag ? interval2 : interval1;
			right = flag ? interval1 : interval2;
		}
		if (left.end >= right.start) {
			if (left.end <= right.end) {
				interval = new Interval(left.start, right.end);
				return interval;
			} else {
				return left;
			}
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(0, 2));
		list.add(new Interval(3, 9));
		System.out.println(insert(list, new Interval(6, 10)));
	}

}
