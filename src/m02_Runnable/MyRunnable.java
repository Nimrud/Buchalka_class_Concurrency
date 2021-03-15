package m02_Runnable;

import m01_basics.ThreadColor;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Runnable's implementation of run()");
    }
}
