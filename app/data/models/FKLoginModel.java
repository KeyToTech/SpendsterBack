package data.models;

import data.entity.User;

public class FKLoginModel  implements LoginModel{

    @Override
    public User createUser(User user){
        return user;
    }
}
