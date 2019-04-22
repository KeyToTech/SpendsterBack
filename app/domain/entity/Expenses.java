package domain.entity;

import java.util.Date;

public class Expenses {

    private String id;
    private double amount;
    private String categoryId;
    private Date CreatedDate;

    public Expenses(String id, double amount, String categoryId, Date CreatedDate) {
        this.id = id;
        this.amount = amount;
        this.categoryId = categoryId;
        this.CreatedDate = CreatedDate;
    }

    public String getId(){
        return this.id;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getCategoryId(){
        return this.categoryId;
    }

    public Date getCreatedDate(){
        return this.CreatedDate;
    }
}
