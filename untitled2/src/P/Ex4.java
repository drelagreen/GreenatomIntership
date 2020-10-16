package A.B;

import P.Person;

import java.io.IOException;

public class Ex4 {

    public static void main(String[] args) throws IOException {
        Person person = new Person("fn","ln");
        person.l();

    }

    public static void  printField(Field field){
        System.out.println(field.l);
        System.out.println(field.w);
    }

    public static void printFirstname(String firstname){
        System.out.println(firstname);
    }

    public static void printPerson(Person person){
        System.out.println(person.firstname);
        System.out.println(person.lastname);
    }
}
