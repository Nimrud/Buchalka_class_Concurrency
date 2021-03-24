package m05_Java_Concurrent;

import m01_basics.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();   // ArrayList nie jest synchronizowana!!!
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_CYAN, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_RED, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

        // Java.Concurrent nie używamy w aplikacjach UI
        // zamiast nich korzystamy z pakietu JavaFX.Concurrent
        // w tem sposób nie "zawieszamy" interfejsu użytkownika (działanie w tle)
    }
}

