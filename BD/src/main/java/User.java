import javax.persistence.*;
import javax.persistence.Column;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "test")
public class User {
    private UUID uuid;

    private String name;

    @Column(name = "reg_date")
    private Date registerDate;

    @Column(name = "email")
    private String eMail;

    private boolean online;

    @Column(name = "last_online")
    private Date lastOnline;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public String getUuid() {
        return uuid.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", registerDate=" + registerDate +
                ", eMail='" + eMail + '\'' +
                ", online=" + online +
                ", lastOnline=" + lastOnline +
                ", id='" + id + '\'' +
                '}';
    }
}
