package controllers

import domain.models.UserModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class AuthController @Inject()(cc: ControllerComponents,
                               model: UserModel)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "username").asOpt[String].map{username =>
        (json \ "email").asOpt[String].map{email =>
          (json \ "password").asOpt[String].map{password =>
            Ok(model.signUp(username, email, password))
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
      (json \ "email").asOpt[String].map{email =>
        (json \ "password").asOpt[String].map{password =>
          Ok(model.login(email, password))
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
