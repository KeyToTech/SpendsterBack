package domain.entity;

import java.util.Date;

public class Expenses {

    private String id;
    private double amount;
    private String categoryId;
    private Date CreatedDate;

    public Expenses(){
        this.amount=0;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return this.id;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setCategoryId(String categoryId){
        this.categoryId=categoryId;
    }

    public String getCategoryId(){
        return this.categoryId;
    }

    public void setCreatedDate(Date CreatedDate){
        this.CreatedDate=CreatedDate;
    }

    public Date getCreatedDate(){
        return this.CreatedDate;
    }
}
