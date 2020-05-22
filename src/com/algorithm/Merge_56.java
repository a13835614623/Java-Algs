package com.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 
 * 示例 1:
 * 
 * 输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]] 解释: 区间 [1,3] 和
 * [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 示例 2:
 * 
 * 输入: [[1,4],[4,5]] 输出: [[1,5]] 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * 
 * @description: 合并区间
 * @author zzk
 * @className Merge_56.java
 * @date 2019年4月9日 下午9:21:30
 */
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}

}

public class Merge_56 {
	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals == null)
			return intervals;
		int len = intervals.size();
		if (len == 0 || len == 1) {
			return intervals;
		}
		for (int i = 0; i < intervals.size()-1;) {
			Interval intervalI = intervals.get(i);
			if (intervalI == null)
				continue;
			boolean isChange = false;
			for (int j = i + 1; j < intervals.size();) {
				Interval intervalJ = intervals.get(j);
				if (intervalJ == null)
					continue;
				Interval tmp = merge(intervalI, intervalJ);
				if (tmp != null) {
					intervals.set(i, tmp);
					intervalI=tmp;
					intervals.remove(j);
					isChange=true;
				}else {
					j++;
				}
			}
			if(!isChange)i++;
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
		List<Interval> intervals = new ArrayList<>();
		Interval interval1 = new Interval(1, 4);
		Interval interval2 = new Interval(0, 2);
		Interval interval3 = new Interval(3, 5);
		intervals.add(interval1);
		intervals.add(interval2);
		intervals.add(interval3);
		intervals = merge(intervals);
		for (Interval interval : intervals) {
			System.out.println(interval);
		}
	}
}
