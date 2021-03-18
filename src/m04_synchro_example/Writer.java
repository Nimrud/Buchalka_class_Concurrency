package m04_synchro_example;

import java.util.Random;

// 'Producer' class
public class Writer implements Runnable{
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String messages[] = {
                "Prowadź swój pług przez kości umarłych",
                "Księgi jakubowe",
                "Bieguni",
                "Dom dzienny, dom nocny"
        };

        Random random = new Random();

        for (int i = 0; i < messages.length; i++){
            message.write(messages[i]);
            try{
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        message.write("Finished");
    }
}
