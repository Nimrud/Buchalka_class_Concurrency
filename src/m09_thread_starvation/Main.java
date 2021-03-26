package m09_thread_starvation;

public class Main {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 10");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_YELLOW), "Priority 10");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 10");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_BLACK), "Priority 10");
    }

    private static class Worker implements Runnable{
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++){
                System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                // execute critical section of code
            }
        }
    }
}
