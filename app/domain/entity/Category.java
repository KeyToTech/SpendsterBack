package domain.entity;

import  java.util.Date;

import static java.util.UUID.randomUUID;

public class Category {

    private String id;
    private String userId;
    private String name;
    private String type;
    private String icon;
    private Date createdDate;

    public Category(String userId, String name, String type, String icon){
        this(randomUUID().toString(), userId, name, type, icon, new Date());
    }

    public Category(String id, String userId, String name, String type, Date createdDate){
        this(id, userId, name, type, "", createdDate);
    }

    public Category(String id, String userId, String name, String type, String icon, Date createdDate){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.createdDate = createdDate;
    }

    public String getId(){
        return this.id;
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
