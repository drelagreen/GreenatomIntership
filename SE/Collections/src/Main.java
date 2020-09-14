import com.google.common.collect.ArrayTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.*;

public class Main {
    ArrayList<Integer> arrayList;
    LinkedList<Integer> linkedList;
    TreeSet<Integer> treeSet;
    HashSet<Integer> hashSet;

    public static void main(String[] args) {
        Main m = new Main();
        Table<String,String,Long> t = m.getArrayTable();
            for (Table.Cell<String, String, Long> stringStringLongCell : t.cellSet()) {
                System.out.println(stringStringLongCell+" mills");
            }
    }

    ArrayTable<String,String,Long> getArrayTable() {
        arrayList = new ArrayList<>(1000);
        linkedList = new LinkedList<>();
        treeSet = new TreeSet<>();
        hashSet = new HashSet<>();

        long[] alCounts = getCounts(arrayList);
        long[] llCounts = getCounts(linkedList);
        long[] tsCounts = getCounts(treeSet);
        long[] hsCounts = getCounts(hashSet);

        return ArrayTable.create(new ImmutableTable.Builder<String, String, Long>()
                .put("ArrayList", "Searching", alCounts[0])
                .put("ArrayList", "Removing", alCounts[1])
                .put("ArrayList", "Adding", alCounts[2])

                .put("LinkedList", "Searching", llCounts[0])
                .put("LinkedList", "Removing", llCounts[1])
                .put("LinkedList", "Adding", llCounts[2])

                .put("TreeSet", "Searching", tsCounts[0])
                .put("TreeSet", "Removing", tsCounts[1])
                .put("TreeSet", "Adding", tsCounts[2])

                .put("HashSet", "Searching", hsCounts[0])
                .put("HashSet", "Removing", hsCounts[1])
                .put("HashSet", "Adding", hsCounts[2])
                .build());
    }

    long[] getCounts(Collection<Integer> c) {
        Random random = new Random();
        long count1 = 0L;
        long count2 = 0L;
        long count3 = 0L;
        for (int i = 0; i < 100000; i++) {
            c.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            count1 += CollectionTimer.getInstance().search(c, random.nextInt(100000));
            count2 += CollectionTimer.getInstance().remove(c, random.nextInt(100000));
            count3 += CollectionTimer.getInstance().add(c, random.nextInt(100000), random.nextInt(100000));
        }
        return new long[]{count1, count2, count3};
    }

}
