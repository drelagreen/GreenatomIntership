import java.util.Collection;
import java.util.List;

public class CollectionTimer {

    static private final CollectionTimer instance = new CollectionTimer();

    public static CollectionTimer getInstance() {
        return instance;
    }

    public long add(Collection<Integer> c, int index, int element) {
        long before = System.nanoTime();
        if (c instanceof List) {
            ((List<Integer>) c).add(index, element);
        } else {
            c.add(element);
        }
        return System.nanoTime() - before;
    }

    public long search(Collection<Integer> c, int element) {
        long before = System.nanoTime();
        c.contains(element);
        return System.nanoTime() - before;
    }

    public long remove(Collection<Integer> c, int element) {
        long before = System.nanoTime();
        c.remove(element);
        return System.nanoTime() - before;
    }
}
