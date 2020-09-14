import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            int v = scanner.nextInt();
            if (!map.containsKey(k)){
                map.put(k, new LinkedList<>());
            }
            map.get(k).add(v);
        }

        HashMap<Integer, LinkedList<Integer>> map2 = new HashMap<>();
        for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
            for (Integer integer : entry.getValue()) {
                if (!map2.containsKey(integer)){
                    map2.put(integer, new LinkedList<>());
                }
                map2.get(integer).add(entry.getKey());
            }
        }

        System.out.println("Old MAP");
        for (Integer integer : map.keySet()) {
            System.out.println(integer + " - "+ map.get(integer).toString());
        }

        System.out.println("\nNew MAP");
        for (Integer integer : map2.keySet()) {
            System.out.println(integer + " - "+ map2.get(integer).toString());
        }
    }
}
