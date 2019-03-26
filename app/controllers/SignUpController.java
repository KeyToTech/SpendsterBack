package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class SignUpController extends Controller {

    public SignUpController(){

    }


    public Result SignUpPage(){

        return ok(views.html.signUp.apply());
    }

}