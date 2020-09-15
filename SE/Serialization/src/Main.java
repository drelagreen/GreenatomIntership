import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static boolean open = true;
    HashSet<Person> personHashSet = new HashSet<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().menu();
    }

    void menu() {
        while (open) {
            System.out.println("1 - Список пользователей\n" +
                    "2 - Добавить пользователя\n" +
                    "3 - Сохранить пользователей\n" +
                    "4 - Загрузить пользователей из файла\n" +
                    "5 - Выход");
            switch (scanner.nextInt()) {
                case 1:
                    printPersons();
                    break;
                case 2:
                    newPerson();
                    break;
                case 3:
                    download();
                    break;
                case 4:
                    upload();
                    break;
                case 5:
                    open = false;
                default:
            }
        }
    }

    void printPersons() {
        personHashSet.forEach(System.out::println);
    }

    void newPerson() {
        Scanner s = new Scanner(System.in);
        try {
            System.out.println("ИМЯ - ");
            String name = s.nextLine();
            System.out.println("\nВОЗРАСТ - ");
            int age = Integer.parseInt(s.nextLine());
            Person p = new Person(name, age);
            personHashSet.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void upload() {
        try (FileInputStream fileInputStream = new FileInputStream("db.db");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Person p;
            try {
                while ((p = (Person) objectInputStream.readObject()) != null) {
                    p.initOccupation();
                    personHashSet.add(p);
                }
            } catch (EOFException ignored){}
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void download() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("db.db");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (Person person : personHashSet) {
                objectOutputStream.writeObject(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
