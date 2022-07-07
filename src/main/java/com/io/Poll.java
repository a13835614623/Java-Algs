package com.io;
/**
 * Poll
 * 与select对比
 * 1) fd数量无限制,底层是链表
 * 2) 不需要重置原来的数据
 * @author 张子宽
 * @date 2022/07/05
 */
public class Poll {

    public static int poll(){

        return 0;
    }

    public static class PollFd{
        /**
         * 文件描述符
         */
        int fd;
        /**
         * POLLIN 可读事件
         * POLLOUT 可写事件
         * 需要监听的文件描述符
         */
        short events;
        /**
         * 已经就绪的的文件描述符
         */
        short revents;
    }
}
