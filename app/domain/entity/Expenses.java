package domain.entity;

import java.util.Date;

import static java.util.UUID.randomUUID;

public class Expenses {

    private String id;
    private String userId;
    private double amount;
    private String note;
    private String categoryId;
    private Date createdDate;

    public Expenses(String userId, double amount, String note, String categoryId){
        this(randomUUID().toString(), userId, amount, note, categoryId, new Date());
    }

    public Expenses(String id, String userId, double amount, String note, String categoryId, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
    }

    public String getId(){
        return this.id;
    }

    public String getUserId(){
        return this.userId;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getNote() {
        return this.note;
    }

    public String getCategoryId(){
        return this.categoryId;
    }

    public Date getCreatedDate(){
        return this.createdDate;
    }
}
