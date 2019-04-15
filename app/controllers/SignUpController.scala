package controllers

import data.repositories.UserRepository
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class SignUpController @Inject()(cc: ControllerComponents, repo: UserRepository)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    Ok(repo.signUp("em", "pa").toString())
  }
}
