package data.models;

import data.entity.User;

public interface SignUpModel {

    User createUser(String email,String password);
}
