
import  java.util.Date;

public class Incomes {

    private int id;
    private double amount;
    private Category category;
    private Date CreatedDate;

    public Incomes(){

        this.amount=0;
    }

    public void setId(int id){

        this.id=id;
    }

    public int getId(){

        return this.id;
    }

    public void setAmount(double amount){

        this.amount=amount;
    }

    public double getAmount(){

        return this.amount;
    }

    public void setCategory(Category category){

        this.category=category;
    }

    public Category getCategory(){

        return this.category;
    }

    public void setCreatedDate(Date CreatedDate){

        this.CreatedDate=CreatedDate;
    }

    public Date getCreatedDate(){

        return this.CreatedDate;
    }
}
