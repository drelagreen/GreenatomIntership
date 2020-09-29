import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostgresqlUserBDController implements UserBDController {
    private static final String LIMIT_SELECT_SQL_PATTERN = "select * from test order by ? limit ? offset ?;";
    private static final String UPDATE_SQL_PATTERN = "update test set name = ?, uuid = ?, email = ?, reg_date = ?, online = ?, last_online = ? where uuid = ?;";
    private static final String DELETE_BY_UUID_SQL_PATTERN = "delete from test where uuid = ?;";
    private static final String DELETE_BY_TABLE_ID_SQL_PATTERN = "delete from test where id = ?";
    private static final String CREATE_SQL_PATTERN = "insert into test (name, uuid, email, reg_date, online, last_online) values (?,?,?,?,?,?)";
    public static final String SELECT_BY_UUID_PATTERN ="select * from test where uuid=?;";
    public static final String SELECT_BY_ID_PATTERN ="select * from test where id=?;";

    private static final String DEFAULT_SELECT_ORDER_BY = "id";


    private Connection connection = null;

    public void connect(String url,String user,String pass){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(url,user,pass);
            prepareStatements();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        if  (connection==null){
            System.out.println("Connection error");
        } else {

            System.out.println("Connection ok");
        }
    }

    private PreparedStatement limitedStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteByUUIDStatement;
    private PreparedStatement deleteByTableIDStatement;
    private PreparedStatement createStatement;
    private PreparedStatement selectByIdStatement;
    private PreparedStatement selectByUUIDStatement;
    //todo ПЕРЕДЕЛАТЬ ПОД ОДИН СТЭЙТМЕНТ?
    private void prepareStatements() throws SQLException {
        limitedStatement = connection.prepareStatement(LIMIT_SELECT_SQL_PATTERN);
        updateStatement = connection.prepareStatement(UPDATE_SQL_PATTERN);
        deleteByUUIDStatement = connection.prepareStatement(DELETE_BY_UUID_SQL_PATTERN);
        deleteByTableIDStatement = connection.prepareStatement(DELETE_BY_TABLE_ID_SQL_PATTERN);
        createStatement = connection.prepareStatement(CREATE_SQL_PATTERN);
        selectByIdStatement = connection.prepareStatement(SELECT_BY_ID_PATTERN);
        selectByUUIDStatement = connection.prepareStatement(SELECT_BY_UUID_PATTERN);
    }


    public List<User> getList(int limit, int page, Column orderBy) throws SQLException {
        List<User> userList = new ArrayList<>(limit);
        String orderByString = orderBy == null ? DEFAULT_SELECT_ORDER_BY : orderBy.toString();

        limitedStatement.setString(1,orderByString);
        limitedStatement.setInt(2,limit*page);
        limitedStatement.setInt(3,limit);

        ResultSet resultSet = limitedStatement.executeQuery();
        for (int i = 0; i < limit; i++) {
            User user = extractData(resultSet);
            userList.add(user);
        }
        return userList;
    }

    private User extractData(ResultSet resultSet) throws SQLException {
        User user = new User();
        resultSet.next();
        user.setName(resultSet.getString(2));
        user.setUuid(resultSet.getString(3));
        user.setEMail(resultSet.getString(4));
        user.setRegisterDate(resultSet.getDate(5));
        user.setOnline(resultSet.getBoolean(6));
        user.setLastOnline(resultSet.getDate(7));
        user.setId(String.valueOf(resultSet.getInt(1)));
        return user;
    }
    public void update(User user) throws SQLException {
        createOrUpdate(updateStatement,user);
    }


    public void create(User user) throws SQLException {
        createOrUpdate(createStatement,user);
    }

    private void createOrUpdate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1,user.getName());
        statement.setString(2,user.getUuid().toString());
        statement.setString(3,user.getEMail());
        statement.setDate(4,user.getRegisterDate());
        statement.setBoolean(5,user.isOnline());
        statement.setDate(6,user.getLastOnline());
        statement.execute();
    }

    public void delete(User user) throws SQLException {
        delete(UUID.fromString(user.getUuid()));
    }
    public void delete(UUID uuid) throws SQLException {
        deleteByUUIDStatement.setString(1, String.valueOf(uuid));
    }
    public void delete(int tableID) throws SQLException {
        deleteByTableIDStatement.setInt(1,tableID);
    }
    public User get(UUID uuid) throws SQLException {
        selectByUUIDStatement.setString(1,uuid.toString());
        return extractData(selectByIdStatement.getResultSet());
    }
    public User get(int id) throws SQLException {
        selectByIdStatement.setInt(1,id);
        return extractData(selectByIdStatement.getResultSet());
    }
}
