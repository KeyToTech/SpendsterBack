package domain.models.responseEntities;

import data.entity.User;

public class RUser {

    private String id;
    private String token;
    private String username;
    private String email;
    private double balance;

    public RUser(User user) {
        this.id = user.getId();
        this.token = user.getToken();
        this.username = user.getUsername();
        this.email = user.getUsername();
        this.balance = user.getBalance();
    }
}
