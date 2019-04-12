package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class SignUpController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    Ok("Success")
  }
}
