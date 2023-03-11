package com.test;


import java.util.function.Function;

public interface Promise<A> {

    <B> Promise<B> flatMap(Function<A, Promise<B>> f);

    static <A, B, C> Function<A, Promise<C>> compose(Function<B, Promise<C>> f, Function<A, Promise<B>> g) {
        return a -> g.apply(a).flatMap(f);
    }

    // 接口根据数学关系提供一个缺省实现，实现类可以提供逻辑上等价的性能优化版本
    default <B> Promise<B> map(Function<A, B> f) {
        return flatMap(a -> unit(f.apply(a)));
    }

    static <T> Promise<T> unit(T a) {
        return new Promise<T>() {
            @Override
            public <B> Promise<B> flatMap(Function<T, Promise<B>> f) {
                return f.apply(a);
            }
        };
    }

    default Promise<A> join(Promise<Promise<A>> m) {
        return m.flatMap(x -> x);
    }

    public static void main(String[] args) {
    }
}
