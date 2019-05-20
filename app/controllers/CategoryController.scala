package controllers

import java.util.Date

import data.entity.Category
import domain.models.CategoryModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage
import services.auth.AuthAction


class CategoryController @Inject()(cc: ControllerComponents,
                                   authAction: AuthAction,
                                   model: CategoryModel,
                                   message: ApiJsonMessage)
  extends AbstractController(cc){

  def getAll(userId: String) = authAction {
    try{
      Ok(model.getAll(userId))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def findBy(id: String) = authAction {
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def update = authAction {implicit request =>
    request.body.asJson.map {json =>
      (json \ "id").asOpt[String].map{id =>
        (json \ "userId").asOpt[String].map { userId =>
          (json \ "name").asOpt[String].map { name =>
            (json \ "type").asOpt[String].map { categoryType =>
              (json \ "icon").asOpt[String].map { icon =>
                (json \ "createdDate").asOpt[Long].map { dateTimestamp =>
                  try {
                    val obj = new Category(id, userId, name, categoryType, icon, new Date(dateTimestamp))
                    Ok(model.update(obj))
                  }
                  catch {
                    case e: Exception =>
                      InternalServerError(message.error(e.getLocalizedMessage))
                  }
                }.getOrElse {
                  BadRequest(message.error("Expecting createdDate"))
                }
              }.getOrElse {
                BadRequest(message.error("Expecting icon"))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting type"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting name"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting userId"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting id"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting category data"))
    }
  }

  def save = authAction{implicit request =>
    request.body.asJson.map {json =>
      (json \ "userId").asOpt[String].map { userId =>
        (json \ "name").asOpt[String].map { name =>
          (json \ "type").asOpt[String].map { categoryType =>
            (json \ "icon").asOpt[String].map { icon =>
              try {
                val obj = new Category(userId, name, categoryType, icon)
                Created(model.save(obj))
              }
              catch {
                case e: Exception =>
                  InternalServerError(message.error(e.getLocalizedMessage))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting icon"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting type"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting name"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting userId"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting category data"))
    }
  }

  def delete(id: String) = authAction{
    try{
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}
