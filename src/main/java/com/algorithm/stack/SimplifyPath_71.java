package com.algorithm.stack;

import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * <p>
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * <p>
 * 示例 3：
 * <p>
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * <p>
 * 示例 4：
 * <p>
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * <p>
 * 示例 5：
 * <p>
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * <p>
 * 示例 6：
 * <p>
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:
 * @Author: zzk
 * @Date: 2019-12-14 10:26
 */
public class SimplifyPath_71 {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] ss = path.split("/");
        for (int i = 1; i < ss.length; i++) {
            if (ss[i].equals(".")) continue;
            else if (ss[i].equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (!ss[i].equals(""))
                stack.push(ss[i]);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0, len = stack.size(); i < len; i++) {
            result.append("/").append(stack.get(i));
        }
        return result.length()==0?"/":result.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));// /
        System.out.println(simplifyPath("/home//foo/"));// /home/foo
        System.out.println(simplifyPath("/a/./b/../../c/"));// /c
        System.out.println(simplifyPath("/a//b////c/d//././/.."));// /a/b/c
        System.out.println(simplifyPath("/a/../../b/../c//.//"));// /c
    }
}
