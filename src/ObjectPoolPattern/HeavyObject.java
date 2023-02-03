package ObjectPoolPattern;

import java.util.concurrent.TimeUnit;
public class HeavyObject {
    private long objectNo;
    //crea objetos
    public HeavyObject(long objectNo)  {
        this.objectNo = objectNo;
        System.out.println("Object with no. " + objectNo + " was created");
    }
    //retorna el numero del objeto
    public long getObjectNo() {
        return objectNo;
    }
    //retorna que un objeto esta haciendo algo
    public void doStuff() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("Object with no. " + objectNo + " is doing something");
    }
}
