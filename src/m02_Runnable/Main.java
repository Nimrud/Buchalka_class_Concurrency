package m02_Runnable;

import static m01_basics.ThreadColor.ANSI_RED;

public class Main {
    public static void main(String[] args) {
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        // inny sposób - anonymous class:
        Thread newRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                //super.run();
                System.out.println(ANSI_RED + "Anonymous class' implementation of run()");
            }
        });
        newRunnableThread.start();
        // WAŻNE! Używamy start(), a nie run() !
    }
}
