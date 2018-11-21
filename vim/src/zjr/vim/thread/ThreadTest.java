package zjr.vim.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTest implements Runnable{
    private int ticket = 20;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void run(){
        while (true){
            lock.writeLock().lock();
            try {
                if (ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + (20 - ticket + 1) + "张票!");
                    ticket--;
                }
                int num = new Random().nextInt(5);
                System.out.println(num);
                lock.writeLock().unlock();
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        ThreadTest test = new ThreadTest();
        Thread r1 = new Thread(test, "老张");
        Thread r2 = new Thread(test, "老王");
        Thread r3 = new Thread(test, "老李");

        r1.start();
        r2.start();
        r3.start();

        Date date = new Date();
        System.out.println(date);
    }
}
