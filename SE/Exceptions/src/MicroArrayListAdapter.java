import java.util.ArrayList;
import java.util.Collection;

public class MicroArrayListAdapter<T>{
    public static void main(String[] args) {
        MicroArrayListAdapter<Integer> mal= new MicroArrayListAdapter<>();
    }
    private final ArrayList<T> list;
    MicroArrayListAdapter(){
        list = new ArrayList<>();
    }

    public boolean add10(T t) throws ArraySizeMoreThan10Exception {
        checkSize();
        return list.add(t);
    }

    public void add10(int index, T element) throws ArraySizeMoreThan10Exception {
        checkSize();
        list.add(index, element);
    }

    public boolean addAll10(Collection<? extends T> c) throws ArraySizeMoreThan10Exception {
        checkSize(0,c);
        return list.addAll(c);
    }

    public boolean addAll10(int index, Collection<? extends T> c) throws ArraySizeMoreThan10Exception {
        checkSize(index,c);
        return list.addAll(index, c);
    }

    private void checkSize() throws ArraySizeMoreThan10Exception {
        if (list.size()>=10){
            throw new ArraySizeMoreThan10Exception();
        }
    }

    private void checkSize(int index, Collection<? extends T> c) throws ArraySizeMoreThan10Exception {
        int t = list.size()+c.size()-index;
        if (t>10){
            throw new ArraySizeMoreThan10Exception(t);
        }
    }

    public ArrayList<T> getList() {
        return list;
    }
}
