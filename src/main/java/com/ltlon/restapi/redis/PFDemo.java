package com.ltlon.restapi.redis;

import redis.clients.jedis.Jedis;

/**
 * @program: restapi
 * @description: pf计数 稀疏矩阵 稠密矩阵
 * @author: LTL
 * @create: 2019-12-18 17:22
 **/
public class PFDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("188.131.134.127");
        jedis.auth("ltlon");
        jedis.del("app");
        for(int i = 0 ;i < 100000;i++){
            jedis.pfadd("app","user"+i);
        }
        System.out.printf("100,%s",jedis.pfcount("app"));
    }
}
