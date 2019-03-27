package data.models;

import data.entity.User;

public class FKSignUpModel implements  SignUpModel {

    @Override
    public User getUser() {
        return new User();
    }


}
