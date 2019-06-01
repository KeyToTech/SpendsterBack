package domain.models.responseEntities;

import data.entity.Expenses;

public class RExpenses {

    private String id;
    private String userId;
    private double amount;
    private String note;
    private RCategory category;
    private long createdDate;

    public RExpenses(Expenses expenses, RCategory category) {
        this.id = expenses.getId();
        this.userId = expenses.getUserId();
        this.amount = expenses.getAmount();
        this.note = expenses.getNote();
        this.category = category;
        this.createdDate = expenses.getCreatedDate().getTime();
    }
}
