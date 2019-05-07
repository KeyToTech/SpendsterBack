package controllers

import domain.models.UserModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage

class AuthController @Inject()(cc: ControllerComponents,
                               model: UserModel,
                               message: ApiJsonMessage)
  extends AbstractController(cc){

  def signUp() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "username").asOpt[String].map{username =>
        (json \ "email").asOpt[String].map{email =>
          (json \ "password").asOpt[String].map{password =>
            try{
              Ok(model.signUp(username, email, password))
            }
            catch {
              case e: Exception =>
                InternalServerError(message.error(e.getLocalizedMessage))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting password"))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting email"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting username"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting user data"))
    }
  }

  def login() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "email").asOpt[String].map{email =>
        (json \ "password").asOpt[String].map{password =>
          try{
            Ok(model.login(email, password))
          }
          catch{
            case e: Exception =>
              InternalServerError(message.error(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting password"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting email"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting user data"))
    }
  }
}
