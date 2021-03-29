package m10_live_locks;

public class Main {
    public static void main(String[] args) {
        final Worker worker1 = new Worker("Anna", true);
        final Worker worker2 = new Worker("Adam", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        }).start();
    }
}
