package m07_Array_Blocking_Queue;

import java.util.concurrent.ArrayBlockingQueue;

import static m05_Java_Concurrent.Main.EOF;

public class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.peek().equals(EOF)) {    // <- peek()
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.take());  // <- take()
                }
            } catch (InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
    }
}
