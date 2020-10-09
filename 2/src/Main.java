public class Main {
    public static void main(String[] args) {
        T object = new T("Вася","Под троечку",'М');
        T object1 = new T("Елизавета","Под трочеку",'Ж');

//        object.name="Вася";
//        object1.hC="пусто";

        System.out.println(object.name);
        System.out.println(object.hC);
        System.out.println(object.s);

        System.out.println();

        System.out.println(object1.name);
        System.out.println(object1.hC);
        System.out.println(object1.s);
    }
}
