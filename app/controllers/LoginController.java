package controllers;

import com.google.gson.Gson;
import data.entity.User;
import data.repository.UserRepository;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginController extends Controller{

    public Result login(){
        RequestBody body = request().body();
        String email = body.asJson().get("email").toString().replace("\"","");
        String  password = body.asJson().get("password").toString().replace("\"","");
        User user = new User(1, "",email,password,0,new Date());


        String EmailRestrictions =
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String PasswordRestrictions = "^[A-Za-z0-9.-]{1,100}$";

        Pattern EmailPattern = Pattern.compile(EmailRestrictions);
        Matcher EmailMatcher = EmailPattern.matcher(user.getEmail());

        Pattern PasswordPattern = Pattern.compile(PasswordRestrictions);
        Matcher PasswordMatcher = PasswordPattern.matcher(user.getPassword());

        if(EmailMatcher.matches() && PasswordMatcher.matches()){
            User createdUser = new UserRepository().login(user);
            String createdUserJSON = new Gson().toJson(createdUser);
            return ok(createdUserJSON);
        }
        return ok("Invalid data");
    }

}
