import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArraySizeMoreThan10Exception extends Exception{

    public void printStackTrace(String path){
        printStackTrace(new File(path));
    }

    public void printStackTrace(File file){
            try {
                printStackTrace();
                PrintStream ps = new PrintStream(file);
                ps.println(new SimpleDateFormat().format(new Date()));
                printStackTrace(ps);
            } catch (FileNotFoundException ignored) {
            }
    }

    private int size = -1;
    ArraySizeMoreThan10Exception(int size){
            this.size = size;
    }
    ArraySizeMoreThan10Exception(){

    }
    @Override
    public String getMessage() {
        return "Array size "+ (size!=-1 ? "("+size+") " : "")+ "more than 10";
    }

    @Override
    public String toString() {
        return getClass().getName() + " -> " + getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage()+(System.getProperty("user.language")
                .equalsIgnoreCase("ru") ? "\nРазмер ArrayList больше чем 10" : "");
    }
}
