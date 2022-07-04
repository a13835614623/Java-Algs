package com.loadbalance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加权轮询
 * @author 张子宽
 * @date 2022/06/28
 */
public class WeightRoundRobin implements LoadBalance {
    @Override
    public Node select(List<Node> nodeList, long requestId) {
        int nodeCount = nodeList.size();
        int[] curWeight = new int[nodeCount];
        int sumWeight = 0;
        for (int i = 0; i < nodeCount; i++) {
            int weight = nodeList.get(i).getWeight();
            sumWeight += weight;
            curWeight[i] = weight;
        }
        int i = 0;
        int maxWeightIndex = 0;
        long seqIndex = requestId % sumWeight;
        while (i < sumWeight) {
            maxWeightIndex = getMaxWeightIndex(curWeight);
            curWeight[maxWeightIndex] -= sumWeight;
            for (int j = 0; j < curWeight.length; j++) {
                curWeight[j] += nodeList.get(j).getWeight();
            }
            if (i == seqIndex) {
                break;
            }
            i++;
        }
        // System.out.println(nodeList + " : " + nodeList.get(maxWeightIndex));
        return nodeList.get(maxWeightIndex);
    }

    private int getMaxWeightIndex(int[] curWeight) {
        int maxWeightIndex;
        maxWeightIndex = 0;
        for (int j = 0; j < curWeight.length; j++) {
            if (curWeight[j] > curWeight[maxWeightIndex]) {
                maxWeightIndex = j;
            }
        }
        return maxWeightIndex;
    }

    public static void main(String[] args) {
        WeightRoundRobin robin = new WeightRoundRobin();
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(5, "A"));
        nodes.add(new Node(1, "B"));
        nodes.add(new Node(1, "C"));
        for (int i = 0; i < 14; i++) {
            robin.select(nodes, i);
        }
    }
}
