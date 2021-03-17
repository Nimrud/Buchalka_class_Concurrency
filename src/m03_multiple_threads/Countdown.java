package m03_multiple_threads;

import m01_basics.ThreadColor;

public class Countdown {

    private int i;   // ta zmienna jest współdzielona pomiędzy wszystkimi wątkami! (heap)
    // jeśli chcemy, aby każdy wątek miał swoją zmienną, to możemy np. stworzyć osobne obiekty typu Countdown
    // najlepiej jednak korzystać z opcji SYNCHRONIZED (w metodzie):
    // synchronized - mówimy, że chcemy, aby cała metoda została wykonana przez wątek, zanim inny wątek będzie miał do niej dostęp

    public synchronized void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        for (i = 10; i > 0; i--){
            System.out.println(color + Thread.currentThread().getName() + ": i=" + i);
        }

        // Inny sposób synchronizacji (bez użycia "synchronized" w deklaracji metody):
        // (synchronizacja obiektu typu Countdown - za pomocą słowa kluczowego "this")
//        synchronized (this){
//            for (i = 10; i > 0; i--){
//                System.out.println(color + Thread.currentThread().getName() + ": i=" + i);
//            }
//        }
    }
}
