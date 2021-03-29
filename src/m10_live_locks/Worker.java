package m10_live_locks;

public class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(SharedResource sharedResource, Worker otherWorker){
        while(active){
            // 1) wątek sprawdzi, czy posiada wspólny zasób:
            if (sharedResource.getOwner() != this){
                try{
                    wait(10);  // jeśli nie posiada, to zaczeka i po chwili sprawdzi ponownie
                } catch (InterruptedException e){

                }
                continue;
            }

            // 2) jeśli posiada, to sprawdzi, czy drugi wątek jest aktywny
            if (otherWorker.isActive()){
                System.out.println(getName() + ": give the resource to " + otherWorker.getName());
                sharedResource.setOwner(otherWorker);  // jeśli jest, to da zasoby temu drugiemu wątkowi
                continue;
            }

            // 3) jeśli drugi wątek nie jest aktywny, to "zużyje" zasób i wróci do początku pętli:
            System.out.println(getName() + ": working on the common resource");
            active = false;
            sharedResource.setOwner(otherWorker);
            // 4) jedyny moment, gdy pętla zakończy działanie jest moment, gdy posiada wspólny zasób
            // a drugi wątek nie jest aktywny
        }
    }
}
