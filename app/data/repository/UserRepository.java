package data.repository;

import data.entity.User;
import data.models.FKLoginModel;
import data.models.FKSignUpModel;

public class UserRepository {

    public User signUp(User user){
        return new FKSignUpModel().createUser(user);
    }

    public User login(User user){
        return new FKLoginModel().createUser(user);
    }
}