package m01_basics;

import static m01_basics.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread{

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + currentThread().getName());

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another thread woke me up");
        }

        System.out.println(ANSI_BLUE + "3 seconds have passed, I'm awake");
    }
}
