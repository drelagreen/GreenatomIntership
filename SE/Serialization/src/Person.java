import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private UUID id;
    private transient String occupation;
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
        id = UUID.randomUUID();
        occupation =  mathOccupation(age);
    }

    public void initOccupation(){
        occupation = mathOccupation(age);
    }
    private String mathOccupation(int age){

        if (age<3&&age>=0) {
            return "Сидит дома";
        }
        if (age>=3&&age<7){
            return "Ходит в детский сад";
        }
        if (age>=7&&age<18){
            return "Учится в школе";
        }
        if (age>=18&&age<23){
            return "Учится в институте";
        }
        if (age>=23&&age<65){
            return "Работает";
        }
        if (age>=65){
            return "На пенсии";
        }
        return "Something wrong!";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
        occupation = mathOccupation(age);
    }

    public UUID getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
