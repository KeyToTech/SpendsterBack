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

    public User(String username, String email, String password){
        this(randomUUID().toString(), username, email, password, 0.0, new Date());
    }

    public User(String username, String email, String password, double balance){
        this(randomUUID().toString(), username, email, password, balance, new Date());
    }

    public User(String id, String username, String email, String password, double balance, Date CreatedDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.CreatedDate = CreatedDate;
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
}

