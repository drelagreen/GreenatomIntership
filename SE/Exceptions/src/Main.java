import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ArraySizeMoreThan10Exception {
        MicroArrayListAdapter<Integer> microArrayListAdapter = new MicroArrayListAdapter<>();
        try {
            microArrayListAdapter.addAll10(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        }catch (ArraySizeMoreThan10Exception e){
            e.printStackTrace("log.txt");
        }
    }
}
