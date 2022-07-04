package com.gc.zgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @description ZFC
 * @author 张子宽
 * @date 2022/06/19
 * @copyright 广州瑞云网络科技有限公司
 */
public class ZgcObject {
    /**
     * 是否为remapped视图
     */
    private AddressView addressView;

    /**
     * 对象真实的值
     */
    private Object val;

    /**
     * @description 获取引用的对象
     * @return java.util.List<com.gc.zgc.ZgcObject>
     * @author 张子宽
     * @date 2022/06/19
     */
    public List<ZgcObject> listReferenceObject(){
        return new ArrayList<>();
    }

    public AddressView getAddressView() {
        return addressView;
    }

    public ZgcObject setAddressView(AddressView addressView) {
        this.addressView = addressView;
        return this;
    }

    public boolean isRemapped() {
        return addressView==AddressView.REMAPPED;
    }

    public boolean isM0() {
        return addressView==AddressView.M0;
    }

    public boolean isM1() {
        return addressView==AddressView.M1;
    }

    public Object getVal() {
        return val;
    }

    public ZgcObject setVal(Object val) {
        this.val = val;
        return this;
    }
}
