package com.ltlon.restapi.utils;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    //private static ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();
    //private static ThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
    private static TransmittableThreadLocal<Integer> requestIdThreadLocal = new TransmittableThreadLocal<>();

    private static ExecutorService tomcatExecutors = Executors.newFixedThreadPool(10);
    private static ExecutorService bussinessExecutors = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));
    private String reqId;

    public static void main(String[] args) {
        for(int i = 0;i<10;i++){
            tomcatExecutors.submit(new ControlThread(i));
        }
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        bussinessExecutors.shutdown();
        tomcatExecutors.shutdown();
/*        Integer reqId = new Integer(5);
        Service  service = new Service();
        service.setReqId(reqId);*/
    }

    public void setReqId(Integer reqId){
        requestIdThreadLocal.set(reqId);
        doBussiness();
    }

    private void doBussiness(){
        System.out.println("requestId:"+requestIdThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程");
                System.out.println("子线程访问父线程的本地线程变量:"+requestIdThreadLocal.get());
            }
        }).start();
    }

    static class ControlThread implements Runnable{

        private int i;

        public ControlThread(int i){
            this.i = i;
        }
        @Override
        public void run() {
            requestIdThreadLocal.set(i);
            bussinessExecutors.submit(new BussinessTask(Thread.currentThread().getName()));
        }
    }

    static class BussinessTask implements Runnable{
        private String parentName;

        public BussinessTask(String parentName){
            this.parentName = parentName;
        }
        @Override
        public void run(){
            System.out.println("parentName:"+parentName+":"+requestIdThreadLocal.get());
        }
    }
}
