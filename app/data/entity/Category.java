package data.entity;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("category")
public class Category {

    @Id
    @SerializedName("id")
    private ObjectId id;
    private String userId;
    private String name;
    private String type;
    private String icon;
    private Date createdDate;

    public Category(){

    }

    public Category(String userId, String name, String type, String icon){
        this(userId, name, type, new Date(), icon);
    }

    public Category(String id, String userId, String name, String type, Date createdDate){
        this(id, userId, name, type, "", createdDate);
    }

    public Category(String userId, String name, String type, Date createdDate, String icon){
        this.id = new ObjectId();
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.createdDate = createdDate;
    }

    public Category(String id, String userId, String name, String type, String icon, Date createdDate){
        this.id = new ObjectId(id);
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.createdDate = createdDate;
    }

    public String getId(){
        return this.id.toHexString();
    }

    public String getUserId() {
        return this.userId;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public String getIcon(){
        return this.icon;
    }

    public Date getCreatedDate(){
        return this.createdDate;
    }


}
