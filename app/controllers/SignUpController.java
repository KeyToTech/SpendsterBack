package controllers;

import com.google.gson.Gson;
import data.entity.User;
import data.repository.UserRepository;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

//import java.net.HttpURLConnection;

public class SignUpController extends Controller {

    public Result signUp() {
        //TODO: extract user from request
        User user = new User
                (1,"bla-bla-bla","bla-bla-bla@gmail.com","aabbbaabb",0,new Date());
        User createdUser = new UserRepository().signUp(user);//convert this to JSON than pass to ok
        //convert response to json
        String createdUserJSON = new Gson().toJson(createdUser);
        return ok(createdUserJSON);
    }

}