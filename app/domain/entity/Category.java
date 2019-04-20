package domain.entity;

import  java.util.Date;

public class Category {

    private String id;
    private String name;
    private String type;
    private Date CreatedDate;

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return this.id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }

    public void setCreatedDate(Date CreatedDate){
        this.CreatedDate=CreatedDate;
    }

    public Date getCreatedDate(){
        return this.CreatedDate;
    }


}
