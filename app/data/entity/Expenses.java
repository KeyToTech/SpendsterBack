package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("expense")
public class Expenses {

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String userId;
    private double amount;
    private String note;
    private String categoryId;
    private Date createdDate;

    public Expenses(){

    }

    public Expenses(String userId, double amount, String note, String categoryId){
        this(userId, amount, note, categoryId, new Date());
    }

    public Expenses(String userId, double amount, String note, String categoryId, Date createdDate) {
        this.id = new ObjectId();
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
    }

    public Expenses(String id, String userId, double amount, String note, String categoryId, Date createdDate) {
        this.id = new ObjectId(id);
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
    }

    public String getId(){
        return this.id.toHexString();
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
