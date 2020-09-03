import java.util.*;

public class Field {
    Field(){
        init();
    }
    AreaType[][] arr = new AreaType[10][10];

    private void init(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 10; j++) {
                arr[i][j] = AreaType.NOTHING;
            }
        }
    }
    String getElementImage(int y, int x){
        return arr[y][x].getImg();
    }
}
