package com.ltlon.restapi.redis;

import com.google.common.hash.Funnel;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: restapi
 * @description: 漏斗限流
 * @author: LTL
 * @create: 2019-12-24 14:36
 **/
public class FunnalRateLimiter {

    static class Funnal {
        /**
         * 漏斗容量
         **/
        int capacity;
        /**
         * 露水速率
         **/
        float leakingRate;
        /**
         * 漏斗余量
         **/
        int leftQuota;
        /**
         * 上一次漏水时间
         **/
        long lastLeakingTs;

        public Funnal(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.lastLeakingTs = System.currentTimeMillis();
        }


        /**
         * @description: 腾出空间
         * @params: []
         * @return: void
         * @author: LTL
         * @date: 2019/12/24 14:53
         */
        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            //间隔时间
            long deltaTs = nowTs - lastLeakingTs;
            //间隔量
            int deltaQuota = (int) (deltaTs * leakingRate);
            //间隔量太大，超出整数范围溢出
            if (deltaQuota < 0) {
                this.leftQuota = capacity;
                this.lastLeakingTs = nowTs;
                return;
            }
            //间隔量太小
            if (deltaQuota < 1) {
                return;
            }

            this.leftQuota += deltaQuota;
            this.lastLeakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnal> funnels = new HashMap<>();

    /**
     * @description: 接口限流
     * @params: [userId, actionKey, capacity, leakingRate]
     * @return: boolean
     * @author: LTL
     * @date: 2019/12/24 15:37
     */
    public boolean isActionAllowd(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("hit:%s:%s", userId, actionKey);
        Funnal funnal = funnels.get(key);
        if (funnal == null) {
            funnal = new Funnal(capacity, leakingRate);
            funnels.put(key, funnal);
        }
        //需要1个quota
        return funnal.watering(1);
    }
}
