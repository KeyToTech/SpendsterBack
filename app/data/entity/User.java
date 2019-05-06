package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

import static java.util.UUID.randomUUID;

@Entity("user")
public class User {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String username;
    @Property(EMAIL)
    private String email;
    @Property(PASSWORD)
    private String password;
    private double balance;
    private Date CreatedDate;

    public User() {
    }

    public User(String username, String email, String password) {
        this(username, email, password, 0.0, new Date());
    }

    public User(String username, String email, String password, double balance, Date CreatedDate) {
        this.id = new ObjectId();
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.CreatedDate = CreatedDate;
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
        return this.CreatedDate;
    }

    public String getEmail() {
        return this.email;
    }
}

