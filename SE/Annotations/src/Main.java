import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setName("NAME");
        Main main = new Main();
        main.printInfo(user, User.class);
        System.out.println(user.getName());
    }

    void printInfo(Object object, Class c) throws IllegalAccessException {
        for (Field declaredField : c.getDeclaredFields()) {
            Annotation[] annotations = declaredField.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(MyAnnotation.class)){
                    System.out.println("Information from reflection - "+declaredField.get(object));
                }
            }
        }
    }
}
