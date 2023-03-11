package com.test.function.order;

import com.google.common.collect.Lists;
import com.test.Promise;
import com.test.function.entity.Order;
import com.test.function.entity.Address;
import com.test.function.entity.OrderWithAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * OrderService
 *
 * @author 张子宽
 * @date 2022/09/23
 */
public class OrderService {

    public List<Order> listByOids(List<Long> oids) {

        return Lists.newArrayList();
    }

    public List<Address> listAddressByOids(List<Long> oids) {

        return Lists.newArrayList();
    }

    public List<Package> listPackageByOids(List<Long> oids) {
        return Lists.newArrayList();
    }


    public Promise<CustomList<Package>> packageByOids(List<Long> oids) {
        return Promise.unit(new CustomList<>());
    }

    public Promise<CustomList<Order>> orderByOids(List<Long> oids) {
        return Promise.unit(new CustomList<>());
    }

    public Promise<CustomList<Address>> addressByOids(List<Long> oids) {
        return Promise.unit(new CustomList<>());
    }
    public Promise<CustomList<OrderWithAddress>> orderAndAddressByOids(List<Long> oids) {
        return listOrderExtend(oids, orders -> addressByOids(oids).flatMap(addresses -> Promise.unit(convert(orders,addresses))));
    }

    public static CustomList<OrderWithAddress> convert(CustomList<Order> order,CustomList<Address> address){

        return new CustomList<>();
    }

    public <T> Promise<CustomList<T>> listOrderExtend(List<Long> oids, Function<CustomList<Order>, Promise<CustomList<T>>> function) {
        return orderByOids(oids)
                .flatMap(orders -> new Promise<CustomList<T>>() {
                    @Override
                    public <B> Promise<B> flatMap(Function<CustomList<T>, Promise<B>> f) {
                        return function.apply(orders).flatMap(f);
                    }
                });
    }

    public static class CustomList<T> extends ArrayList<T> {


        public <R> CustomList<R> map(Function<T, R> mapper) {
            CustomList<R> list = new CustomList<>();
            forEach(x -> {
                list.add(mapper.apply(x));
            });
            return list;
        }
    }
}
