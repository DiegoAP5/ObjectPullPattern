package ObjectPoolPattern;

import java.util.concurrent.ConcurrentLinkedQueue;
public abstract class ObjectPool<T> {
    private ConcurrentLinkedQueue<T> pool;

    //contador del tiempo de vida del objeto
    public ObjectPool(final int minObjects) {
        pool = new ConcurrentLinkedQueue<T>();
        for (int i = 0; i < minObjects; i++) {
            pool.add(createObject());
        }
    }

    protected abstract T createObject();

    //usa el objeto
    public T borrowObject() {
        T object;
        if ((object = pool.poll()) == null) {
            object = createObject();
        }
        return object;
    }

    //retorna el objeto
    public void returnObject(T object) {
        if (object == null) {
            return;
        }
        this.pool.offer(object);
    }
}