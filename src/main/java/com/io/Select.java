package com.io;


import com.algorithm.array.MaxArea_11;
import io.netty.util.Timeout;

/**
 * IO多路复用的背景
 * 原先的reve函数一次只能处理一个socket，而且是永久阻塞，直到有事件
 */
public class Select {

    /**
     * select 检查输入的文件描述符是否就绪
     * 1) 直到有文件描述符就绪之前都会阻塞
     * 2) 32位机 默认最大监听1024个, 64位默认2048
     * 缺点:
     * 1) fd_set最大数量有限制
     * 2) 采用轮询的方法,效率低
     * 3) 用户态和内核态频繁复制传递FD数据开销大
     * @param maxFd 监听的文件描述符的个数,用于判断最大检查到第几位
     * @param readSet 监听是否可读的文件描述符集合
     * @param writeSet 监听是否可写的文件描述符集合
     * @param exceptSet 检查是否有异常的文件描述符集合
     * @return int 就绪的文件描述符的个数 -1 代表异常  0 代表超时
     * @author 张子宽
     * @date 2022/07/05
     */
    public static int select(int maxFd, FdSet readSet, FdSet writeSet, FdSet exceptSet, long timeout) {
        long startTime = System.currentTimeMillis();
        // 就绪的文件描述符个数
        int ready = 0;
        boolean checkRead = readSet != null;
        boolean checkWrite = writeSet != null;
        boolean checkExcept = exceptSet != null;
        boolean[] read = new boolean[maxFd];
        boolean[] write = new boolean[maxFd];
        boolean[] except = new boolean[maxFd];
        while (ready == 0) {
            for (int fd = 0; fd < maxFd; fd++) {
                try {
                    // 判断是否可读
                    if (checkRead && FdSet.FD_ISSET(fd, readSet) == 1 && isReadable(fd, readSet)) {
                        ready++;
                        read[fd] = true;
                    }
                    // 判断是否可写
                    if (checkWrite && FdSet.FD_ISSET(fd, writeSet) == 1 && isWritable(fd, readSet)) {
                        ready++;
                        write[fd] = true;
                    }
                } catch (Exception e) {
                    // 异常则设置为-1
                    if (checkExcept) {
                        ready = -1;
                        except[fd] = true;
                    }
                }
                // 判断超时
                if (System.currentTimeMillis() - startTime > timeout) {
                    ready = 0;
                    break;
                }
            }
        }
        // 最后将没有就绪的 文件描述符设置为 0
        for (int fd = 0; fd < maxFd; fd++) {
            if (checkRead && !read[fd]) {
                FdSet.FD_CLR(fd, readSet);
            }
            if (checkWrite && !write[fd]) {
                FdSet.FD_CLR(fd, writeSet);
            }
            if (checkExcept && !except[fd]) {
                FdSet.FD_CLR(fd, exceptSet);
            }
        }
        return ready;
    }


    public static void main(String[] args) {
        FdSet readSet = new FdSet();
        FdSet.FD_ZERO(readSet);
        FdSet.FD_SET(1, readSet);
        int maxFd = 2;
        while (true) {
            // 每次都需要重置原来的数据,因此select函数会对传入的文件描述符集合进行修改,导致没有ready的文件描述符被清空
            FdSet.FD_ZERO(readSet);
            FdSet.FD_SET(1, readSet);
            int ready = select(maxFd, readSet, null, null, 10000);
            if (ready > 0) {
                for (int i = 0; i < maxFd; i++) {
                    if (FdSet.FD_ISSET(i, readSet) == 1) {
                        // TODO 进行读取数据的操作
                    }
                }
            }else if (ready==0){
                // 超时了
                System.out.println("超时了");
                break;
            }else if (ready==-1){
                System.out.println("发生异常");
                break;
            }
        }
    }

    /**
     * 是否可读
     */
    public static boolean isReadable(int fd, FdSet readSet) {

        return false;
    }

    /**
     * 是否可写
     */
    public static boolean isWritable(int fd, FdSet writeSet) {

        return false;
    }
}
