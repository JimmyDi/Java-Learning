package java0.conc0303.Homework;

import java0.conc0302.atomic.Count;

import java.util.concurrent.*;

public class GetThreadValue {

    // Solution 1: use join... to wait thread running
//    public static void main(String[] args) throws InterruptedException{
//        CustomThread myThread = new CustomThread();
//        Thread curThread = new Thread(myThread);
//
//        curThread.start();
//        curThread.join();
//        System.out.println(myThread.getReturnValue());
//    }

    // Solution 2: while loop until return value is not null
//    public static void main(String[] args) throws InterruptedException{
//        CustomThread myThread = new CustomThread();
//        Thread curThread = new Thread(myThread);
//
//        curThread.start();
//        while(myThread.getReturnValue() == null) {
//            Thread.sleep(1000);
//        }
//        System.out.println(myThread.getReturnValue());
//    }

    // Solution 3: implement callable interface and use futuretask
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        FutureTask<String> task = new FutureTask<>(new CustomCallable());
//
//        Thread thread = new Thread(task);
//
//        thread.start();
//        String value = task.get();
//        System.out.println(value);
//    }

    // Solution 4: Thread poll
//    public static void main(String[] args) throws InterruptedException, ExecutionException{
//        ExecutorService pool = Executors.newCachedThreadPool();
//        Future<String> submit = pool.submit(new CustomCallable());
//        System.out.println(submit.get());
//    }

    // Solution 5: CountDownLatch
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        CountDownLatch latch = new CountDownLatch(1);

        LatchThread curThread = new LatchThread(latch);
        new Thread(curThread).start();

        latch.await();
        System.out.println();
        System.out.println(curThread.getReturnValue());
    }

    public static class LatchThread implements Runnable {
        private CountDownLatch latch;
        private String value = "initial value";

        public LatchThread(CountDownLatch latch) {
            this.latch = latch;
        }

        public String getReturnValue(){
            return value;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("===Start Thread===");
                System.out.println("===End Thread===");
                value = "return value";
                latch.countDown();
            }
        }

    }

    public static class CustomThread implements Runnable {

        private String returnValue;

        @Override
        public void run() {
            System.out.println("===Running on Thread===");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("===Work done===");

            returnValue = "Reach end of work";
        }

        public String getReturnValue() {
            return returnValue;
        }
    }

    public static class CustomCallable implements Callable<String>{
        @Override
        public String call() throws InterruptedException{
            System.out.println("Thread running...");
            Thread.sleep(1000);
            System.out.println("Thread running is done");

            return "Callable result!";
        }
    }
}


