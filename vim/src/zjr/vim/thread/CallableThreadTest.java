package zjr.vim.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {
    public static void main(String args[]){
        CallableThreadTest ct = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ct);
        FutureTask<Integer> ft1 = new FutureTask<>(ct);
//        for (int i = 0; i < 100; i++){
//            System.out.println(Thread.currentThread().getName() + " abc " + i);
//            if (i == 20){
              Thread t = new Thread(ft);
              Thread t1 = new Thread(ft1);
              t.start();
              t1.start();
//            }
//        }
        try{
            System.out.println("子线程的返回值：" + ft.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() {
        int i = 0;
        for (; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
