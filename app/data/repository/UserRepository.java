package data.repository;

import data.entity.User;
import data.models.FKSignUpModel;

public class UserRepository {

    public User signUp(User user){
        return new FKSignUpModel().createUser(user);
    }
}
