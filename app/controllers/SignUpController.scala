package controllers

import com.google.gson.Gson
import domain.repositories.UserRepository
import domain.requestEntities.RUser
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class SignUpController @Inject()(cc: ControllerComponents, repo: UserRepository, gson: Gson)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    val json = request.body.asJson.get.toString()
    val rUser = gson.fromJson(json, classOf[RUser])
    Ok(repo.signUp(rUser))
  }
}
