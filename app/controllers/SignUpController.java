package controllers;

import com.google.gson.Gson;
import data.entity.User;
import data.repository.UserRepository;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.net.HttpURLConnection;

public class SignUpController extends Controller {

    private List<User> users = new Vector<>();

    public Result signUp(){
        RequestBody body = request().body();

        String  username = body.asJson().get("username").toString().replace("\"","");
        String email = body.asJson().get("email").toString().replace("\"","");
        String  password = body.asJson().get("password").toString().replace("\"","");
        User user = new User(users.size()+1, username,email,password,0,new Date());


        String EmailRestrictions =
                "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String UsernameRestrictions = "^[A-Za-z0-9._ -]{1,100}$";
        String PasswordRestrictions = "^[A-Za-z0-9.-]{1,100}$";



        Pattern EmailPattern = Pattern.compile(EmailRestrictions);
        Matcher EmailMatcher = EmailPattern.matcher(user.getEmail());

        Pattern UsernamePattern = Pattern.compile(UsernameRestrictions);
        Matcher UsernameMatcher = UsernamePattern.matcher(user.getUsername());

        Pattern PasswordPattern = Pattern.compile(PasswordRestrictions);
        Matcher PasswordMatcher = PasswordPattern.matcher(user.getPassword());

        if(EmailMatcher.matches() && PasswordMatcher.matches() && UsernameMatcher.matches()){
            users.add(user);
            User createdUser = new UserRepository().signUp(user);
            String createdUserJSON = new Gson().toJson(createdUser);
            return ok(createdUserJSON);
        }
        return ok("Invalid data");
    }

}