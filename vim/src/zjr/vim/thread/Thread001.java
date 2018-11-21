package zjr.vim.thread;

public class Thread001 {
    private static void method01() {
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("t1 is finished");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 is finished");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 is finished");
            }
        });
        t2.start();

        t3.start();

        t1.start();
    }

    private static void method02(){
        Runnable runnable = new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        };
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        try {
            t2.start();
            t2.join();

            t1.start();
            t1.join();

            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        method01();
//        method02();
    }

}
