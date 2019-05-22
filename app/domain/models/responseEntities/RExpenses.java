package domain.models.responseEntities;

import data.entity.Expenses;

public class RExpenses {

    private String id;
    private String userId;
    private double amount;
    private String note;
    private String categoryId;
    private long createdDate;

    public RExpenses(Expenses expenses) {
        this.id = expenses.getId();
        this.userId = expenses.getUserId();
        this.amount = expenses.getAmount();
        this.note = expenses.getNote();
        this.categoryId = expenses.getCategoryId();
        this.createdDate = expenses.getCreatedDate().getTime();
    }
}
