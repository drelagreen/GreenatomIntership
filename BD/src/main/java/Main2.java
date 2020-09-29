import org.hibernate.mapping.Array;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Main2 {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    public static void main(String[] args) {
        UserBDController controller = new PostgresqlUserBDController();
        controller.connect(DB_URL,USER,PASS);

        User user1 = new User();
        user1.setName("Test");
        user1.setUuid(UUID.randomUUID().toString());
        user1.setOnline(true);
        user1.setRegisterDate(Date.valueOf("2018-12-21"));
        user1.setLastOnline(Date.valueOf("2020-09-29"));

        User user2 = new User();
        user2.setName("Dmitry");
        user2.setUuid(UUID.randomUUID().toString());
        user2.setRegisterDate(Date.valueOf("2016-07-07"));
        user2.setLastOnline(Date.valueOf("2018-09-29"));
        user2.setOnline(false);

        User user3 = new User();
        user3.setName("A");
        user3.setUuid(UUID.randomUUID().toString());
        user3.setOnline(true);
        user3.setRegisterDate(Date.valueOf("2011-05-24"));
        user3.setLastOnline(Date.valueOf("2020-09-29"));
        try {
            controller.create(user1);
            controller.create(user2);
            controller.create(user3);

            controller.getList(3, 1, null).forEach(System.out::println);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
