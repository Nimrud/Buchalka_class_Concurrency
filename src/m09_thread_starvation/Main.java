package m09_thread_starvation;

// Starvation - sytuacja, w której wątki rzadko kiedy mają możliwość wystartować
// to częsty problem przy wielu wątkach
// często zdarza się przy ustawianiu priorytetów wątków
// rozwiązanie: Fair Lock (one działają na zasadzie: First Come - First Served)
// ale uwaga: Fair Lock gwarantuje tylko pierwszeństwo pozyskania locka, a nie kolejność wykonania wątków
// 2. uwaga: metoda .trylock() ignoruje zasadę First Come - First Served

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock(true);  // static, czyli jest tylko 1 instancja obiektu lock!
    // parametr 'true' oznacza, że jest to Fair Lock

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_YELLOW), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_BLACK), "Priority 2");

        t1.setPriority(10);   // priorytet to jedynie SUGESTIA dla systemu operacyjnego
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable{
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 40; i++){
                lock.lock();
                try{
                    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                    // execute critical section of code
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
