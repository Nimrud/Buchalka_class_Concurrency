package m02_Runnable;

import m01_basics.AnotherThread;

import static m01_basics.ThreadColor.ANSI_RED;

public class Main {
    public static void main(String[] args) {

        Thread anotherThread = new AnotherThread();
        anotherThread.start();

//        Thread myRunnableThread = new Thread(new MyRunnable());
//        myRunnableThread.start();

        // inny sposób - anonymous class:
        Thread newRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                //super.run();
                System.out.println(ANSI_RED + "Anonymous class' implementation of run()");
                try {
                    anotherThread.join(2000);  // dołączamy bieżący wątek do anotherThread
                    // wartość w nawiasie - nasłuchuje drugi wątek i czeka na jego wykonanie,
                    // ALBO na upływ czasu podany jako parametr (w zależności, który nastąpi szybciej)
                    // stosuje się to jako zabezpieczenie, gdy drugi wątek potencjalnie może zająć dużo czasu
                    System.out.println("AnotherThread terminated or timed out, running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "Interrupted");
                }
            }
        });
        newRunnableThread.start();
        // WAŻNE! Używamy start(), a nie run() !
    }
}
