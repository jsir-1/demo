package com.jsrf.guava;


import com.google.common.base.MoreObjects;
import com.google.common.collect.HashBiMap;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author jsrf
 * @Date 2019-09-30 10:51
 * @Description
 */
public class GuavaTest {
    public static void main(String[] args) throws NoSuchMethodException {
//        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(newFixedThreadPool(10));
//        ListenableFuture<Integer> future1 = executorService.submit(() -> 1 + 2);
//        ListenableFuture<Integer> future2 = executorService.submit(() -> Integer.parseInt("3q"));
//        ListenableFuture<List<Object>> futures = Futures.allAsList(future1, future2);
//        futures = Futures.successfulAsList(future1, future2);
//
//        Futures.addCallback(futures, new FutureCallback<List<Object>>() {
//            @Override
//            public void onSuccess(@Nullable List<Object> result) {
//                System.out.println(result);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("fail");
//            }
//        }, executorService);


        Method getMethod = List.class.getMethod("get", int.class);
        Invokable<List<String>, Object> invokable = new TypeToken<List<String>>() {
        }.method(getMethod);

        System.out.println(invokable.isOverridable());
        System.out.println(invokable.isVarArgs());
        invokable.isPackagePrivate();
        boolean aPrivate = invokable.isPrivate();
    }

    public void bloomFilter(){
        BloomFilter.create((Funnel<Object>) (from, into) -> {
            into.putLong(from.hashCode());
        },2,0.3);
    }
}
