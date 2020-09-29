import java.sql.*;
import java.util.Random;

public class Main {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/my_database";
    static final String USER = "postgres";
    static final String PASS = "";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        if  (connection==null){
            System.out.println("Connection error");
            return;
        } else {
            System.out.println("Connection ok");
        }
        try {
            Random r = new Random();
            String sql = "insert into result values (?,?,?,null,null,?);";
            PreparedStatement s = connection.prepareStatement(sql);
            for (int i = 0; i < 30; i++) {
                s.setInt(1,r.nextInt(20));
                s.setInt(2,r.nextInt(20));
                s.setInt(3,r.nextInt(100));
                s.setInt(4,i);
                s.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    static String getName(int i){
        return "Name"+i+" Surname"+i;
    }

    static int getRank(){
        Random random = new Random();
        return random.nextInt(10);
    }

    static int getYearOfBirth(){
        Random random = new Random();
        return 1990+random.nextInt(20);
    }

    static int getPersonalRecord(){
        Random random = new Random();
        return random.nextInt(60);
    }

    static String getCountry(){
        int i = new Random().nextInt(8);
        switch (i){
            case 1:
                return "Австралия";

            case 2:
                return "Китай";

            case 3:
                return "Япония";

            case 4:
                return "Беларусь";

            case 5:
                return "Бразилия";

            case 6:
                return "США";

            case 7:
                return "Россия";

        }
        return "-";
    }
}
