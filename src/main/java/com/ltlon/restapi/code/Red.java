package com.ltlon.restapi.code;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: restapi
 * @description: 红色
 * @author: LTL
 * @create: 2020-02-17 16:30
 **/
public class Red {

    public static void main(String[] args) {
        int[] arrs = new int[]{1,2,3};
        //ArrayList  ar = new ArrayList(Arrays.asList(arrs));
        ArrayList ar = new ArrayList(arrs.length);
        Arrays.stream(arrs).forEach(ar::add);
        System.out.println(ar.size());
        System.out.println(ar.get(0));
        Integer integer = null;
        Optional<Integer> optional = Optional.ofNullable(integer);
        System.out.println(optional.orElse(0));

        HashMap hm = new HashMap();
        hm.put("null","123");
        hm.put(null,"123");
        System.out.println(hm.get(null));

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        //concurrentHashMap.put("null",null);
        //Executors.newCachedThreadPool().execute();
        //concurrentHashMap.put(null,"1234");
        //concurrentHashMap.get(null);

    }
}
