package com.ltlon.restapi.redis;

import io.rebloom.client.Client;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: restapi
 * @description: 布隆过滤器测试
 * @author: LTL
 * @create: 2019-12-19 11:03
 **/
public class BloomTest {

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",6379);
        for (int i = 0; i < 100000; i++) {
            client.add("app","user"+i);
            boolean ret = client.exists("app","user"+(i+1));
            if(ret){
                System.out.println(i);
                break;
            }
        }
        client.close();
    }
}
