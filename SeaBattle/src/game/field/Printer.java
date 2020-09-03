import java.util.Arrays;
import java.util.List;

public class Printer {
    private static final List<String> xTips = Arrays.asList(" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 "," 10 ");
    private static final List<String> yTips = Arrays.asList(" А "," Б "," В "," Г "," Д "," Е "," Ж "," З "," И "," К ");

    private Field field;

    Printer(Field field){
        this.field = field;
    }

    void print(){
        for (int i = 0; i < 10; i++) {
            if (i==0){
                System.out.print("    ");
                for (int j = 0; j < 10; j++) {
                    System.out.print(xTips.get(j)+" ");
                }
                System.out.println();
            }
            System.out.print(yTips.get(i)+" ");
            for (int j = 0; j < 10; j++) {
                System.out.print(field.getElementImage(i,j)+" ");
            }
            System.out.println();
        }
    }
}
