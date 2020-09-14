import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CollectionTimer {
    void fill(){

    }
    static private final CollectionTimer instance = new CollectionTimer();

    public static CollectionTimer getInstance() {
        return instance;
    }

    public long add(Collection<Integer> c, int index, int element) {
        long before = System.currentTimeMillis();
        if (c instanceof List) {
            ((List<Integer>) c).add(0, element);
        } else {
            c.add(element);
        }
        return System.currentTimeMillis() - before;
    }

    public long search(Collection<Integer> c, int element) {
        long before = System.currentTimeMillis();
        c.contains(element);
        return System.currentTimeMillis() - before;
    }

    public long remove(Collection<Integer> c, int element) {
        long before = System.currentTimeMillis();
        c.remove(element);
        return System.currentTimeMillis() - before;
    }
}
