package com.ltlon.restapi.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @program: restapi
 * @description: Redis实现限流
 * @author: LTL
 * @create: 2019-12-24 13:40
 **/
public class SimpleRateLimiter {

    private Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean actionIsAllowed(String userId,String actionKey,int priod,int maxCount){
        String key = String.format("hit:%s:%s",userId,actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        pipeline.zadd(key,nowTs,""+nowTs);
        pipeline.zremrangeByScore(key,0,nowTs-priod*1000);
        Response<Long> response = pipeline.zcard(key);
        pipeline.expire(key,priod+1);
        pipeline.exec();
        pipeline.close();
        return response.get() <= maxCount;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("188.131.134.127");
        jedis.auth("ltlon");
        SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(simpleRateLimiter.actionIsAllowed("ltlon","reply",60,5));
        }
    }
}
