package m01_basics;

import static m01_basics.ThreadColor.ANSI_GREEN;
import static m01_basics.ThreadColor.ANSI_PURPLE;

public class Main {

    public static void main(String[] args) {
        // kolejność wątków zależy tu od konkretnej maszyny, na której uruchamiamy kod!
        // na pewno pierwszy wyświetli się napis z głównego wątku, a później - loteria
        System.out.println(ANSI_PURPLE + "Main thread");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("= Another Thread =");
        anotherThread.start();
        // WAŻNE! Używamy start(), a nie run() !
        // jeśli użyjemy run(), to otrzymamy inny wynik niż po użyciu start()

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN + "Anonymous class thread");
            }
        }.start();

        System.out.println(ANSI_PURPLE + "Main thread 2");
    }
}
