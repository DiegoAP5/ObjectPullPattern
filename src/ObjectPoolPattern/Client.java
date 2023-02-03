package ObjectPoolPattern;

public class Client implements Runnable{
    private ObjectPool<HeavyObject> pool;
    private int threadNo;

    //asigna objetos a hilos
    public Client(ObjectPool<HeavyObject> pool, int threadNo){
        this.pool = pool;
        this.threadNo = threadNo;
    }

    public void run() {
        // Obtiene un objeto de pool
        HeavyObject heavyObject = pool.borrowObject();
        System.out.println("Thread " + threadNo + ": Object with process no. " + heavyObject.getObjectNo() + " was borrowed");

        try {
            heavyObject.doStuff();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // retorna ExportingProces
        pool.returnObject(heavyObject);

        System.out.println("Thread " + threadNo +": Object with process no. " + heavyObject.getObjectNo() + " was returned");
    }
}
