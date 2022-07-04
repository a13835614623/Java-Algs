package com.gc.zgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @description ZgcCollectorImpl
 * @author 张子宽
 * @date 2022/06/19
 */
public abstract class ZgcCollectorImpl implements ZgcCollector {

    /**
     * 地址视图
     */
    private AddressView addressView;
    /**
     * 活跃对象列表
     */
    private List<ZgcObject> liveObjects;
    /**
     * gcRoots
     * 主要有静态变量, 局部变量，常量，JNI（native方法的）指针
     */
    private List<ZgcObject> gcRoots;

    public AddressView getAddressView() {
        return addressView;
    }

    public ZgcCollectorImpl setAddressView(AddressView addressView) {
        this.addressView = addressView;
        return this;
    }

    @Override
    public void init() {
        addressView = AddressView.REMAPPED;
    }

    @Override
    public void mark() {
        addressView = AddressView.M0;
        // 初始标记,将所有GcRoots对象设置为M0视图
        List<ZgcObject> gcRoots = getGcRoots();
        this.gcRoots = gcRoots;
        // gcRoots直接引用的对象
        for (ZgcObject liveObject : gcRoots) {
            List<ZgcObject> listReferenceObject = listDirectReferenceObject(liveObject);
            for (ZgcObject zgcObject : listReferenceObject) {
                zgcObject.setAddressView(AddressView.M0);
            }
            liveObjects.addAll(listReferenceObject);
        }
    }

    @Override
    public void parallelMark() {
        List<ZgcObject> referenceObjects = new ArrayList<>();
        for (ZgcObject liveObject : liveObjects) {
            List<ZgcObject> listReferenceObject = listReferenceObjectRecursion(liveObject);
            for (ZgcObject zgcObject : listReferenceObject) {
                if (zgcObject.isRemapped()) {
                    zgcObject.setAddressView(AddressView.M0);
                }
            }
            referenceObjects.addAll(listReferenceObject);
        }
        liveObjects.addAll(referenceObjects);
        // 并发标记可能对导致对象被错误判断为垃圾或者漏标记，需要再标记阶段重新修正
    }

    @Override
    public void remark() {
        List<ZgcObject> referenceObjects = new ArrayList<>();
        for (ZgcObject liveObject : liveObjects) {
            List<ZgcObject> listReferenceObject = listDirectReferenceObject(liveObject);
            for (ZgcObject zgcObject : listReferenceObject) {
                if (zgcObject.isRemapped()) {
                    zgcObject.setAddressView(AddressView.M0);
                }
            }
            referenceObjects.addAll(listReferenceObject);
        }
        liveObjects.addAll(referenceObjects);
    }

    @Override
    public void parallelMoveReady() {

    }

    @Override
    public void initMove() {
        // 只转移GCRoots直接引用的对象
    }

    /**
     * @description 获取GcRoots对象, 主要有静态变量, 局部变量，常量，JNI（native方法的）指针
     * @return java.util.List<java.lang.Object>
     * @author 张子宽
     * @date 2022/06/19
     */
    public abstract List<ZgcObject> getGcRoots();

    /**
     * @description 获取直接引用的对象列表
     * @param zgcObject  对象
     * @return java.util.List<com.gc.zgc.ZgcObject>
     * @author 张子宽
     * @date 2022/06/19
     */
    public abstract List<ZgcObject> listDirectReferenceObject(ZgcObject zgcObject);
    /**
     * @description 获取直接/间接引用的对象列表(递归)
     * @param zgcObject  对象
     * @return java.util.List<com.gc.zgc.ZgcObject>
     * @author 张子宽
     * @date 2022/06/19
     */
    public abstract List<ZgcObject> listReferenceObjectRecursion(ZgcObject zgcObject);

    @Override
    public void parallelMove() {
        addressView = AddressView.REMAPPED;
        for (ZgcObject liveObject : liveObjects) {
            AddressView objectAddressView = liveObject.getAddressView();
            if (objectAddressView == AddressView.M0) {
                // 转移,并设置视图为Remapped
                move(liveObject);
            } else if (objectAddressView == AddressView.REMAPPED) {
                // 已转移,不做处理
            }
        }
    }

    /**
     * @description 转移对象
     * @param liveObject 活跃对象
     * @return void
     * @author 张子宽
     * @date 2022/06/19
     */
    protected abstract void move(ZgcObject liveObject);
}
