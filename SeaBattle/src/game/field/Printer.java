package game.field;

import java.util.Arrays;
import java.util.List;

public class Printer {
    private static final List<String> yTips = Arrays.asList(" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 "," 10");
    private static final List<String> xTips = Arrays.asList(" А "," Б "," В "," Г "," Д "," Е "," Ж "," З "," И "," К ");
    private static final String SPACE = "      ";
    private final Field field1;
    private final Field field2;

    public Printer(Field field1, Field field2){
        this.field1 = field1;
        this.field2=field2;
    }

    private void printXTips(StringBuilder stringBuilder){
        stringBuilder.append("    ");
        for (int j = 0; j < 10; j++) {
            stringBuilder.append(xTips.get(j)).append(" ");
        }
    }

    private void printFieldRow(StringBuilder stringBuilder,Field field,int row, boolean hideShips){
        for (int j = 2; j < 12; j++) {
            if (hideShips&&field.getElement(row,j)==AreaType.SHIP){
                stringBuilder.append(AreaType.NOTHING.getImg());
            } else {
                stringBuilder.append(field.getElement(row,j).getImg());
            }
            stringBuilder.append(" ");
        }
    }

    public StringBuilder getFieldsStringBuilder(boolean hideOpponentsShips){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 2; i<12; i++){
            if (i == 2){
                printXTips(stringBuilder);
                stringBuilder.append(SPACE);
                printXTips(stringBuilder);
                stringBuilder.append("\n");
            }
            stringBuilder.append(yTips.get(i - 2)).append(" ");
            printFieldRow(stringBuilder,field1,i,false);
            stringBuilder.append(SPACE);
            stringBuilder.append(yTips.get(i - 2)).append(" ");
            printFieldRow(stringBuilder,field2,i, hideOpponentsShips);
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }
}
