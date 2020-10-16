package A.B;

public class Person {

    public static void main(String[] args) {
        Person person = new Person("Name","Lastname");
        person.l();
        Person.m();
    }

    public static String firstname;
    public String lastname;

//    double weight;
//    int size;
//    char sex;
//    int salary;
//    boolean flag;

    public Person(String firstname, String lastname){
        Person.firstname = firstname;
        this.lastname = lastname;
    }

    static void m(){
        System.out.println(firstname);
    }

    void l(){
        System.out.println(firstname);
        System.out.println(lastname);
    }
}
