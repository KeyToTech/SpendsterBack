package data.models;

import data.entity.User;

import java.util.Date;

public class FKSignUpModel implements  SignUpModel {

    @Override
    public User createUser(String email,String password) {
        User user = new User(1,"",email,password,0,new Date());

        return user;
    }


}
