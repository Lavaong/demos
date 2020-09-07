package com.ltlon.restapi.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ltlon.restapi.bean.User;
import redis.clients.jedis.Jedis;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2019-12-05 15:30
 **/
public class client {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(201342599);
        user.setAge(12);
        user.setUserName("小明");
        user.setPhone("13239998769");
        String strUser = JSONObject.toJSONString(user);
        Jedis jedis = new Jedis("188.131.134.127");
        jedis.auth("ltlon");
        System.out.println(jedis.ping());
        jedis.set("str-user-info",strUser);
        String userInfo = jedis.get("str-user-info");
        User user1 = JSON.parseObject(userInfo,User.class);
        System.out.println(user1.toString());
        //hash结构用户信息
        String hashKey = String.valueOf(user.getUserId());
        jedis.hset(hashKey,"userName",user.getUserName());
        jedis.hset(hashKey,"age",String.valueOf(user.getAge()));
        jedis.hset(hashKey,"phone",user.getPhone());
        System.out.println(jedis.hget(hashKey,"userName"));
        System.out.println(jedis.hget(hashKey,"age"));
        System.out.println(jedis.hget(hashKey,"phone"));
    }
}
