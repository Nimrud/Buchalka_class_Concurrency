package m06_Executor_Service;

import m01_basics.ThreadColor;
import m05_Java_Concurrent.MyConsumer;
import m05_Java_Concurrent.MyProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock reentrantLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED, reentrantLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, reentrantLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, reentrantLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        // można też zwracać wartości z wątku za pomocą .submit()
        // .submit() akceptuje jako parametr obiekt typu Callable - podobny do Runnable, ale zdolny do zwracania wartości
        // wartość zwracana jest jako obiekt typu Future

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_BLUE + "Printed by Callable class");
                return "This is a callable result";
            }
        });

        // wartość pobieramy za pomocą future.get()
        try{
            System.out.println(future.get());
        } catch (ExecutionException e){
            System.out.println("Something went wrong");
        } catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }

        // ExecutorService trzeba zamknąć!
        executorService.shutdown();
    }
}
