package domain.entity;

import java.util.Date;

import static java.util.UUID.randomUUID;

public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private double balance;
    private Date CreatedDate;
    private String token;
    private Date tokenExpiresDate;

    public User(String username, String email, String password, String token, Long tokenExpiresTime){
        this(randomUUID().toString(), username, email, password, 0.0, new Date(), token, tokenExpiresTime);
    }

    public User(String username, String email, String password, double balance, String token, Long tokenExpiresTime){
        this(randomUUID().toString(), username, email, password, balance, new Date(), token, tokenExpiresTime);
    }

    public User(String id, String username, String email, String password, double balance, Date CreatedDate, String token, Long tokenExpiresTime) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.CreatedDate = CreatedDate;
        this.token = token;
        this.tokenExpiresDate = new Date(System.currentTimeMillis() + tokenExpiresTime);
    }

    public  String getId(){
        return this.id;
    }

    public  String getUsername(){
        return  this.username;
    }

    public  String getPassword(){
        return  this.password;
    }

    public  double getBalance(){
        return  this.balance;
    }

    public  Date getCreatedDate(){
        return this.CreatedDate;
    }

    public  String getEmail(){
        return this.email;
    }

    public String getToken() {
        return this.token;
    }

    public Date getTokenExpiresDate() {
        return this.tokenExpiresDate;
    }
}

