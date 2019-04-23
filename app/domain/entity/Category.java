package domain.entity;

import  java.util.Date;

import static java.util.UUID.randomUUID;

public class Category {

    private String id;
    private String name;
    private String type;
    private Date CreatedDate;

    public Category(String name, String type){
        this(randomUUID().toString(), name, type, new Date());
    }

    public Category(String id, String name, String type, Date CreatedDate){
        this.id = id;
        this.name = name;
        this.type = type;
        this.CreatedDate = CreatedDate;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public Date getCreatedDate(){
        return this.CreatedDate;
    }


}
