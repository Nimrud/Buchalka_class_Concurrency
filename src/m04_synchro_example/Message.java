package m04_synchro_example;

public class Message {
    private String message;
    private boolean empty = true;

    // w jednej chwili może działać tylko jedna synchronizowana metoda!
    // jeżeli jedna metoda wstrzymuje drugą, a druga czeka na pierwszą, to mamy DEADLOCK (zawieszenie)

    public synchronized String read(){
        while (empty){
            try {
                wait();  // kiedy wątek uruchamia metodę wait(), to zawiesza wykonanie
                // oraz zwalnia blokadę (lock) na obiekcie Message do momentu,
                // aż inny wątek poinformuje, że coś ważnego się stało
                // poprzez notify() lub notifyAll()
            } catch (InterruptedException e) {

            }
        }
        empty = true;
        notifyAll();   // kiedy wywołujemy notifyAll(), to wątek, który był wstrzymany, może z powrotem działać
        return message;
    }

    public synchronized void write (String message) {
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}
