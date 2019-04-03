package controllers;

import play.mvc.Controller;
import data.entity.*;
import play.mvc.Result;

import com.google.gson.*;


public class LoginController extends Controller{
    public Result login(){
        return ok(views.html.login.apply());
    }

}
