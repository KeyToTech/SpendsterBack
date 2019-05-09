package domain.entity;

import  java.util.Date;

import static java.util.UUID.randomUUID;

public class Category {

    private String id;
    private String name;
    private String type;
    private String imgLink;
    private Date createdDate;

    public Category(String name, String type, String imgLink){
        this(randomUUID().toString(), name, type, imgLink, new Date());
    }

    public Category(String id, String name, String type, Date createdDate){
        this(id, name, type, "", createdDate);
    }

    public Category(String id, String name, String type, String imgLink, Date createdDate){
        this.id = id;
        this.name = name;
        this.type = type;
        this.imgLink = imgLink;
        this.createdDate = createdDate;
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

    public String getImgLink(){
        return this.imgLink;
    }

    public Date getCreatedDate(){
        return this.createdDate;
    }


}
