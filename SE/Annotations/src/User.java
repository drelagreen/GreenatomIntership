
public class User {
    @MyAnnotation()
    String name;
    @MyAnnotation
    int test = 777;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
