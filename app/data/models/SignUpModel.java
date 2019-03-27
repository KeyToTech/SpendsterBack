package data.models;

import data.entity.User;

public interface SignUpModel {

    User createUser(String username,String email,String password);
}
