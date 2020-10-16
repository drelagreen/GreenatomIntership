public class Ex1 {
    public static void main(String[] args) {
        int n = 5;
        print(5);
    }

    public static void print(int n){
        for(int i = 0; i<n; i++){
            for (int j = 0; j < n; j++) {
                System.out.print(". ");
            }
            System.out.println();
        }
    }
}
