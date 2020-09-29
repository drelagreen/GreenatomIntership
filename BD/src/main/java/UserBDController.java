import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface UserBDController {
    public User get(UUID uuid) throws SQLException;
    public User get(int id)  throws SQLException;
    public void delete(User object)  throws SQLException;
    public void delete(int id)  throws SQLException;
    public void delete(UUID uuid)  throws SQLException;
    public void update(User user) throws SQLException;
    public void create(User user) throws SQLException;
    public List<User> getList(int limit, int page, Column orderBy) throws SQLException;
    public void connect(String url,String user,String pass);
}
