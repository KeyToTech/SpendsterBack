package domain.models.responseEntities;

import data.entity.Category;

public class RCategory {

    private String id;
    private String userId;
    private String name;
    private String categoryType;
    private String icon;
    private long createdDate;

    public RCategory() {
        this.id = "";
        this.userId = "";
        this.name = "";
        this.categoryType = "";
        this.icon = "";
        this.createdDate = 0;
    }

    public RCategory(Category category) {
        this.id = category.getId();
        this.userId = category.getUserId();
        this.name = category.getName();
        this.categoryType = category.getType();
        this.icon = category.getIcon();
        this.createdDate = category.getCreatedDate().getTime();
    }
}
