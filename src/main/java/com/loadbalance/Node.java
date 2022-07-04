package com.loadbalance;
/**
 * Node
 * @author 张子宽
 * @date 2022/06/28
 */
public class Node {

    /**
     * 权重
     */
    private int weight;
    /**
     * name
     */
    private String name;

    public Node(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return name;
    }
}
