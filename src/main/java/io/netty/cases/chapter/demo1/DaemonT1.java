package io.netty.cases.chapter.demo1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ????? on 2018/8/3.
 */
public class DaemonT1 {
    public static void main(String[] args)
            throws IllegalArgumentException, InterruptedException {
        long startTime = System.nanoTime();
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.DAYS.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();}}
        }, "Daemon-T");
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(15);
        System.out.println("??????????????" +(System.nanoTime() - startTime)/1000/1000/1000 + " s");}
}
