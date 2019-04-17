package controllers

import com.google.gson.Gson
import domain.repositories.UserRepository
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class AuthController @Inject()(cc: ControllerComponents, repo: UserRepository, gson: Gson)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "username").asOpt[String].map{username =>
        (json \ "email").asOpt[String].map{email =>
          (json \ "password").asOpt[String].map{password =>
            Ok(repo.signUp(username, email, password).getUsername)
          }.getOrElse {
            BadRequest("Expecting password")
          }
        }.getOrElse{
          BadRequest("Expecting email")
        }
      }.getOrElse {
        BadRequest("Expecting username")
      }
    }.getOrElse{
      BadRequest("Expecting user data")
    }
  }

  def login() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "username").asOpt[String].map{username =>
        (json \ "password").asOpt[String].map{password =>
          Ok(repo.login(username, password).getUsername)
        }.getOrElse{
          BadRequest("Expecting password")
        }
      }.getOrElse{
        BadRequest("Expecting username")
      }
    }.getOrElse{
      BadRequest("Expecting user data")
    }
  }
}
