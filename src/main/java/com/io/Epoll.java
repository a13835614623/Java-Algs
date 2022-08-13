package com.io;

import com.my.RedBlackTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Epoll
 * 优点
 * 1) 不需要轮询来判断是否需要就绪
 * 2) fd数量无限制
 * 3) 不需要重置原数据
 * 4) 支撑边缘触发(ET)和水平触发(LT),默认水平触发LT
 * 水平触发LT:不管用户是否处理，只要事件还没处理完,epoll_wait后都会返回此事件
 * 边缘触发ET:通过epoll_wait获取到事件后,不管是否处理完,后续都不会返回此事件了,效率比较高,但是容易出错
 * @author 张子宽
 * @date 2022/07/05
 */
public class Epoll {

    /**
     * 红黑树
     */
    public RedBlackTreeNode<EpollItem> rbr;

    /**
     * 就绪列表
     */
    public LinkedList<EpollEvent> readyList;

    private static final List<Epoll> EPOLLS = new ArrayList<>();


    public static final int EP_INSERT = 0;

    /**
     * 创建一个epoll实例,返回文件描述符
     * size 可忽略
     */
    public static int epoll_create(int size) {
        EPOLLS.add(new Epoll());
        return EPOLLS.size();
    }

    /**
     * epoll_ctl
     * @param epfd epoll 的描述符
     * @param op 操作类型
     * @param fd 监听的文件描述符
     * @param epollEvent 事件
     * @return int
     * @author 张子宽
     * @date 2022/07/05
     */
    public static int epoll_ctl(int epfd, int op, int fd, EpollEvent epollEvent) {
        Epoll epoll = EPOLLS.get(epfd);
        try {
            // 假设是插入操作,其他操作先忽略
            // 追加事件到红黑树上
            EpollItem epollItem = new EpollItem();
            epollItem.epfd = epfd;
            epollItem.fd = fd;
            epollItem.event = epollEvent;
            epoll.rbr.putVal(epollItem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 1.阻塞线程
     * 2.内核查询红黑树中ready的socket,放入到就绪列表
     * 3.就绪列表中内容复制到event
     * @param epfd
     * @param epollEvents
     * @param maxEvents
     * @param timeout
     * @return int
     * @author 张子宽
     * @date 2022/07/05
     */
    public static int epoll_wait(int epfd, EpollEvent[] epollEvents, int maxEvents, int timeout) {
        Epoll epoll = EPOLLS.get(epfd);
        LinkedList<EpollEvent> readyList = epoll.readyList;
        int i = 0;
        for (EpollEvent epollEvent : readyList) {
            epollEvents[i++] = epollEvent;
        }
        return 0;
    }

    public static void main(String[] args) {
        int maxEvents = 1024;
        int fds[] = new int[1024];
        // 内核态创建epoll实例,（红黑树rbr和就绪链表rdlist）
        int efd = epoll_create(1);
        for (int fd = 0; fd < fds.length; fd++) {
            // 对红黑树进行操作,添加所有socket节点
            epoll_ctl(efd, EP_INSERT, fd, new EpollEvent());
        }
        EpollEvent[] events = new EpollEvent[maxEvents];
        while (true) {
            // 1.阻塞线程
            // 2.内核查询红黑树中ready的socket,放入到就绪列表
            // 3.就绪列表中内容复制到events
            int n = epoll_wait(efd, events, maxEvents, 10000);
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    // 所有需要处理的socket
                    // events[i].data.fd;
                }
            }
        }
    }

    public static class EpollItem implements Comparable {
        int epfd;
        int fd;
        EpollEvent event;

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public static class EpollEvent implements Comparable<EpollEvent> {
        /**
         * 事件
         */
        int events;

        /**
         * 用户数据
         */
        EpollData data;

        @Override
        public int compareTo(EpollEvent o) {
            return 0;
        }
    }

    public static class EpollData {

        Object ptr;

        int fd;

        int u32;

        int u64;
    }
}
