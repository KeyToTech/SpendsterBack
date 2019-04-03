package data.models;

import data.entity.User;

public class FKSignUpModel implements  SignUpModel {

    @Override
    public User createUser(User user) {
        return user;
    }


}
