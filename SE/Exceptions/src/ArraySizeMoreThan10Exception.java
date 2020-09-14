import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArraySizeMoreThan10Exception extends Exception{
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
    public void printStackTrace(String path){
        printStackTrace(new File(path));
    }
    @Override
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
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

    @Override
    public void printStackTrace(PrintWriter err) {
        super.printStackTrace(err);
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
}
