package com.gc.zgc;

import java.util.HashMap;
import java.util.Map;

public class ZLoad implements ILoad {
    /**
     *
     */
    private static final Map<String,String> forwardingTable = new HashMap<>();

    NormalLoad normalLoad;
    @Override
    public Object getFromHeap(String address) {
        // 在活跃表中,正常加载,无须加读屏障
        if (isActiveObject(address)){
            // 地址视图为Remapped,说明已被转移了,使用新的地址访问
            if (isRemapped(address)){
                return normalLoad.getFromHeap(forwardingTable.get(address));
            }else {
                // 说明对象还没转移,转移对象,视图更新为Remapped,获取到新的地址
                address = move(address);
            }
            return normalLoad.getFromHeap(address);
        }else {
            // 不在活跃表,还用旧的地址
            return normalLoad.getFromHeap(address);
        }
    }

    private String move(String address) {
        // 这里返回转移后的新的地址
        return address;
    }

    private boolean isActiveObject(String address) {
        return false;
    }

    private boolean isRemapped(String address) {
        return false;
    }

    private boolean isNeedMove(String address) {
        return false;
    }
}
