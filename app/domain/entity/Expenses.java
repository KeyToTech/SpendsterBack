package domain.entity;

import java.util.Date;

import static java.util.UUID.randomUUID;

public class Expenses {

    private String id;
    private double amount;
    private String note;
    private String categoryId;
    private Date CreatedDate;

    public Expenses(double amount, String note, String categoryId){
        this(randomUUID().toString(), amount, note, categoryId, new Date());
    }

    public Expenses(String id, double amount, String note, String categoryId, Date CreatedDate) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.CreatedDate = CreatedDate;
    }

    public String getId(){
        return this.id;
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
        return this.CreatedDate;
    }
}
