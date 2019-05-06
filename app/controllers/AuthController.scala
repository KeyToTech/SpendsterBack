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
              Ok(message.token(model.signUp(username, email, password)))
            }
            catch {
              case e: Exception =>
                InternalServerError(message.create(e.getLocalizedMessage))
            }
          }.getOrElse {
            BadRequest(message.create("Expecting password"))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting email"))
        }
      }.getOrElse {
        BadRequest(message.create("Expecting username"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting user data"))
    }
  }

  def login() = Action{ implicit request =>
    request.body.asJson.map {json =>
      (json \ "email").asOpt[String].map{email =>
        (json \ "password").asOpt[String].map{password =>
          try{
            Ok(message.token(model.login(email, password)))
          }
          catch{
            case e: Exception =>
              InternalServerError(message.create(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting password"))
        }
      }.getOrElse{
        BadRequest(message.create("Expecting email"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting user data"))
    }
  }
}
