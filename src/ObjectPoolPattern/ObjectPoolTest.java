package ObjectPoolPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectPoolTest {
    //se inicializan los datos
    private ObjectPool<HeavyObject> pool;
    private AtomicLong processNo=new AtomicLong(0);

    public static void main(String args[])  {
        ObjectPoolTest op=new ObjectPoolTest();
        op.setUp();
        op.testObjectPool();
    }

    public void setUp() {
        // Crea una lista de objetos de ObjectPoolPattern.HeavyObject.
        pool = new ObjectPool<HeavyObject>(4){
            protected HeavyObject createObject() {
                // crea un objeto de testeo al cual le lleva tiempo
                return new HeavyObject( processNo.incrementAndGet() );
            }
        };
    }

    public void testObjectPool() {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        // ejecuta 8 tareas en diferentes hilos
        executor.execute(new Client(pool, 1));
        executor.execute(new Client(pool, 2));
        executor.execute(new Client(pool, 3));
        executor.execute(new Client(pool, 4));
        executor.execute(new Client(pool, 5));
        executor.execute(new Client(pool, 6));
        executor.execute(new Client(pool, 7));
        executor.execute(new Client(pool, 8));

        executor.shutdown();
        //Elimina el objeto despues de su tiempo de vida
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
