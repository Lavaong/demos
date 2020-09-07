package com.ltlon.restapi.redis;

import redis.clients.jedis.Jedis;

/**
 * @program: restapi
 * @description: 查看Redis中的key
 * @author: LTL
 * @create: 2019-12-24 17:25
 **/
public class ScanRedisKeys {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("188.131.134.127");
        jedis.auth("ltlon");
        for (int i = 0; i < 10000; i++) {
            jedis.set("key"+i,i+"");
        }
    }
}
