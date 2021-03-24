package m07_Array_Blocking_Queue;

import m01_basics.ThreadColor;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_BLUE + "Printed by Callable class");
                return "This is a callable result";
            }
        });

        try{
            System.out.println(future.get());
        } catch (ExecutionException e){
            System.out.println("Something went wrong");
        } catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }

        executorService.shutdown();
    }
}
