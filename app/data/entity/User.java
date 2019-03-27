package data.entity;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private double balance;
    private Date CreatedDate;


    public User(int id,String username,String email,String password,double balance,Date createdDate){
        setId(id);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBalance(balance);
        setCreatedDate(createdDate);
    }

    public void setId(int id){
        this.id=id;
    }

    public  int getId(){
        return this.id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public  String getUsername(){
        return  this.username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public  String getPassword(){
        return  this.password;
    }

    public  void setBalance(double balance){
        this.balance=balance;
    }

    public  double getBalance(){
        return  this.balance;
    }

    public void setCreatedDate(Date CreatedDate){
        this.CreatedDate=CreatedDate;
    }

    public  Date getCreatedDate(){
        return this.CreatedDate;
    }


    public void setEmail(String email){
        this.email=email;
    }

    public  String getEmail(){
        return this.email;
    }
}

