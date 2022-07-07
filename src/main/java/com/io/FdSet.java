package com.io;

/**
 * 文件描述符集合
 */
public class FdSet {
    /**
     * 位图表示,最多监听24*16=1024个文件描述符
     * 每个long 是 16位
     */
    long[] fds = new long[24];

    /**
     * 将所有文件描述符的状态设置为0
     */
    public static int FD_ZERO(FdSet fdSet) {
        long[] fds = fdSet.fds;
        int length = fds.length;
        for (int i = 0; i < length; i++) {
            fds[i] = 0;
        }
        return 0;
    }

    /**
     * 将指定位的fd的文件描述符设置为1,
     */
    public static int FD_SET(int fd, FdSet fdSet) {
        int index = fd / 24;
        int bit = fd % 16;
        // 假如是第三位,输入为 2 ,
        // 原先值为 0000 0000 0000 0000
        // 结果= 0000 0000 0000 0000 | 0000 0000 0000 0100 = 0000 0000 0000 0100
        fdSet.fds[index] = fdSet.fds[index] | (long) (Math.pow(2, bit));
        return 0;
    }

    /**
     * 指定位的fd的文件描述符是否为1
     */
    public static int FD_ISSET(int fd, FdSet fdSet) {
        int index = fd / 24;
        int bit = fd % 16;
        return (fdSet.fds[index] & (long) Math.pow(2, bit)) > 0 ? 1 : 0;
    }

    /**
     * 删除一个文件描述符,指定位 设置为 0
     */
    public static int FD_CLR(int fd, FdSet fdSet) {
        int index = fd / 24;
        int bit = fd % 16;
        fdSet.fds[index] = fdSet.fds[index] & (~(long) (Math.pow(2, bit)));
        return 0;
    }
}