package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

@Entity("user")
public class User {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final long TOKEN_EXPIRES_TIME = 7 * 86400000;

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String username;
    @Property(EMAIL)
    private String email;
    @Property(PASSWORD)
    private String password;
    private double balance;
    private Date createdDate;
    private String token;
    private Date tokenExpiresDate;

    public User() {
    }

    public User(String username, String email, String password, String token) {
        this(username, email, password, 0.0, new Date(), token);
    }

    public User(String username, String email, String password, double balance, Date createdDate, String token) {
        this.id = new ObjectId();
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.createdDate = createdDate;
        this.token = token;
        this.tokenExpiresDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRES_TIME);
    }

    public String getId() {
        return this.id.toHexString();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public String getToken() {
        return this.token;
    }

    public Date getTokenExpiresDate() {
        return this.tokenExpiresDate;
    }

    public String getEmail() {
        return this.email;
    }
}

