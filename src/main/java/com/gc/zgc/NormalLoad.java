package com.gc.zgc;

import java.util.HashMap;
import java.util.Map;

public class NormalLoad implements ILoad {
    Map<String,Object> heap = new HashMap<>();


    @Override
    public Object getFromHeap(String address) {
        return heap.get(address);
    }
}
