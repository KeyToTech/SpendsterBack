package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class SignUpController extends Controller {


    public Result SignUpPage(){

        return ok(views.html.signUp.apply());
    }

}