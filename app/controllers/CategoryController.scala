package controllers

import java.text.{ParseException, SimpleDateFormat}

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

  def getAll = authAction {
    try{
      Ok(model.getAll)
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
                (json \ "createdDate").asOpt[String].map { dateString =>
                  try {
                    val obj = new Category(id, userId, name, categoryType, icon, new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))
                    Ok(model.update(obj))
                  }
                  catch {
                    case e: ParseException =>
                      BadRequest(message.error(e.getLocalizedMessage +
                        " Date format: dd/mm/yyyy hh:mm"))
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
              BadRequest(message.error("Expecting imgLink"))
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
