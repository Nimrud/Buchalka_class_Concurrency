package m04_synchro_example;

import java.util.Random;

// 'Consumer' class
public class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (String latestMessage = message.read(); !latestMessage.equals("Finished");
            latestMessage = message.read()){
            System.out.println(latestMessage);
            try{
                Thread.sleep(random.nextInt(2000));  // losowa liczba od 0 do 2000
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
