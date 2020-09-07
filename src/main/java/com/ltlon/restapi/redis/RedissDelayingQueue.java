package com.ltlon.restapi.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * @program: restapi
 * @description:  Redis实现的延时队列
 * @author: LTL
 * @create: 2019-12-13 16:59
 **/
public class RedissDelayingQueue<T> {


    static class  TaskItem<T> {
        public String id;
        public T msg;
    }

    //Fastjson在序列化存在泛型类型的对象时，需要使用TypeRefrence
    private Type taskType = new TypeReference<TaskItem<T>>(){}.getType();

    private Jedis jedis;
    private String queueKey;

    public RedissDelayingQueue(Jedis jedis,String queueKey){
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg){
        TaskItem<T> taskItem = new TaskItem<>();
        taskItem.id = UUID.randomUUID().toString();
        taskItem.msg = msg;
        String s = JSONObject.toJSONString(taskItem);
        jedis.zadd(queueKey,System.currentTimeMillis()+5000,s);
    }


    public void loop(){
        while(!Thread.interrupted()){
            //使用Redis中缓存的lua脚本执行原子操作
            String now = Long.toString(System.currentTimeMillis());
            Object object =  jedis.evalsha("7cbcdc548789d0abc895a0066f7444c3bcf5b6c1",1,queueKey,now,"msg");
/*            Set<String> values = jedis.zrangeByScore(queueKey,0,System.currentTimeMillis(),0,1);
            if(values.isEmpty()){
                try{
                 //阻塞5秒，重新获取一次。
                 Thread.sleep(500);
                }catch (InterruptedException e){
                    break;
                }
                continue;
            }i
            String s = values.iterator().next();*/
/*            if(result > 0){
                TaskItem<T> task = JSON.parseObject(s,taskType);
                this.handleMsg(task.msg);
            }*/
            if(object != null){
                this.handleMsg((T)object);
            }
        }
    }
    private void handleMsg(T msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("188.131.134.127");
        jedis.auth("ltlon");
        RedissDelayingQueue rdq = new RedissDelayingQueue(jedis,"delay-queue-demo");
        Thread producer = new Thread(){
            @Override
            public void run (){
                    for(int i = 0; i< 10;i++) {
                       rdq.delay("queue-demo"+i);
                    }
            }
        };
        Thread consumer = new Thread(){
            @Override
            public void run(){
                rdq.loop();
            }
        };
        //producer.start();
        consumer.start();
        try {
            //producer.join();
            //Thread.sleep(6000);
            //consumer.interrupt();
            //consumer.join();
        }catch (Exception e){

        }

    }

}
