package com.test;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends JFrame implements Runnable {

    /**
     * 日期
     */
    private JLabel date;
    /**
     * 时间
     */
    private JLabel time;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Time()).start();
    }


    public Time() {
        // 初始化图形界面
        // 设置可见
        this.setVisible(true);
        //设置窗口标题
        this.setTitle("数字时钟");
        //设置窗口大小
        this.setSize(280, 180);
        //设置窗口位置
        this.setLocation(200, 200);
        //设置窗口可移动
        this.setResizable(true);
        //创建面板
        JPanel panel = new JPanel();
        //将面板居中
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        // 时间
        time = new JLabel();
        //设置时间的大小
        time.setBounds(31, 54, 196, 59);
        //设置时间的字体
        time.setFont(new Font("Arial", Font.PLAIN, 50));
        //添加时间组件
        panel.add(time);
        // 日期
        date = new JLabel();
        //设置日期的字体
        date.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        //设置日期的大小
        date.setBounds(47, 10, 180, 22);
        //添加日期组件
        panel.add(date);
    }

    //用一个线程来更新时间
    @Override
    public void run() {
        while (true) {
            try {
                //更新日期和时间
                date.setText(new SimpleDateFormat("yyyy 年  MM 月 dd 日  EEEE").format(new Date()));
                time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}